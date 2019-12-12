package com.julius.quartz.cloud.quartz.scheduler;

import com.julius.quartz.cloud.quartz.job.HelloJob;
import com.julius.quartz.cloud.quartz.job.PrintWordsJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
                .withIntervalInSeconds(1)) //use scheduler
//                .startAt(startDate) //start time
//                .endAt(endDate) //end don't execute job
                .build();

        //do it

        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        logger.info("-------------scheduler start ---------------- ");


        //stop it

        TimeUnit.SECONDS.sleep(10);
//        scheduler.shutdown();
        logger.info("-------------scheduler stop ------------------");
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

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerOne","triggerGroupOne1")
                .withSchedule(cronScheduleBuilder)
                .build();

        if(!scheduler.isShutdown()){
            scheduler.scheduleJob(jobDetail,cronTrigger);
            scheduler.start();
        }else{
            logger.info(" scheduler is already shut down");
        }

    }
}
