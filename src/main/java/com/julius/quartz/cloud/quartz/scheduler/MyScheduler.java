package com.julius.quartz.cloud.quartz.scheduler;

import com.julius.quartz.cloud.quartz.job.HelloJob;
import com.julius.quartz.cloud.quartz.job.PrintWordsJob;
import com.julius.quartz.cloud.quartz.listener.OwnerJobListenerSupport;
import com.julius.quartz.cloud.quartz.listener.OwnerSchedulerListenerSupport;
import com.julius.quartz.cloud.quartz.listener.OwnerTriggerListenerSupport;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Package: com.julius.quartz.cloud.quartz.scheduler
 * @ClassName: MyScheduler
 * @Author: Julius
 * @Description: 任务调度器
 * @Date: 2019/12/12 11:06
 * @Version: 1.0
 */
@Component
public class MyScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MyScheduler.class);

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private PrintWordsJob printWordsJob;
    @Autowired
    private OwnerTriggerListenerSupport ownerTriggerListenerSupport;
    @Autowired
    private OwnerJobListenerSupport ownerJobListenerSupport;
    @Autowired
    private OwnerSchedulerListenerSupport ownerSchedulerListenerSupport;

    /**
     * 1、create scheduler
     * 2、create jobDetail
     * 3、create trigger
     * 4、do it
     */

    //test use SimpleTrigger
    public void testQuartz() throws SchedulerException, InterruptedException {
        //create jobDetail
        JobDetail jobDetail = JobBuilder.newJob(printWordsJob.getClass())
                .usingJobData("jobDetail1","This is one JobDetail of Job")
                .withIdentity("jobOne","groupOne")
                .build();
        //create trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .usingJobData("trigger1","This is one Trigger!")
                .withIdentity("triggerOne","triggerGroupOne") //set name and group name for trigger
//                .startNow() //start trigger now
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever()) //use scheduler
//                .startAt(startDate) //start time
//                .endAt(endDate) //end don't execute job
                .build();

        // add custom job listener
        scheduler.getListenerManager().addJobListener(ownerJobListenerSupport);
        //do it
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        logger.info("-------------scheduler start ---------------- ");
        //stop it
//        TimeUnit.SECONDS.sleep(10);
//        scheduler.shutdown();
//        logger.info("-------------scheduler stop ------------------");

    }


    /**
     * @Description test use CronTrigger
     */
    public void testCronQuartz() throws SchedulerException {
        //create JobDetail
        JobDetail jobDetail = JobBuilder.newJob(printWordsJob.getClass())
                .usingJobData("jobDetail2","This is a cron job detail .")
                .withIdentity("jobTwo","groupOne1")
                .build();

        //create CronTrigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("20 26 17 * * ?");
        CronTrigger trigger =  (CronTrigger) TriggerBuilder.newTrigger()
                .usingJobData("cronTrigger","This is a cron trigger .")
                .startNow()
                .withIdentity("triggerTwo","triggerGroupOne1")
                .withSchedule(cronScheduleBuilder)
                .build();
        //start
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

    //test jobDataMap
    public void testJobDataMap()throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("jobDataMap","groupOne").build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("userName","ceshi");
        jobDataMap.put("age",22);

        JobDetail jobDetail2 = JobBuilder.newJob(PrintWordsJob.class).withIdentity("jobDataMap2","groupOne").build();
        JobDataMap jobDataMap2 = jobDetail.getJobDataMap();
        jobDataMap2.put("userName","ceshi");
        jobDataMap2.put("age",22);

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerOne","triggerGroupOne1")
                .withSchedule(cronScheduleBuilder)
                .build();

        CronTrigger cronTrigger2 = TriggerBuilder.newTrigger()
                .withIdentity("triggerOne1","triggerGroupOne1")
                .withSchedule(cronScheduleBuilder)
                .withPriority(6)
                .build();

        if(!scheduler.isShutdown()){
            scheduler.scheduleJob(jobDetail,cronTrigger);
            scheduler.scheduleJob(jobDetail2,cronTrigger2);
            scheduler.start();
        }else{
            logger.info(" scheduler is already shut down");
        }
    }

    /**
     * @Description test owner trigger listener and job listener
     */
    public void testOwnerListener() throws SchedulerException {
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever())
                .build();
        //add owner trigger listener
        scheduler.getListenerManager().addTriggerListener(ownerTriggerListenerSupport);

        //add owner job listener
        scheduler.getListenerManager().addJobListener(ownerJobListenerSupport);

        //add owner scheduler listener
        scheduler.getListenerManager().addSchedulerListener(ownerSchedulerListenerSupport);
    }
}
