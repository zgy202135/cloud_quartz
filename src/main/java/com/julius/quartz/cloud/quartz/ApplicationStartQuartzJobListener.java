package com.julius.quartz.cloud.quartz;

/**
 * @Package: com.julius.quartz.cloud.quartz
 * @ClassName: ApplicationStartQuartzJobListener
 * @Author: Julius
 * @Description: 监听
 * @Date: 2019/12/12 16:44
 * @Version: 1.0
 */

import com.julius.quartz.cloud.quartz.scheduler.MyScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private MyScheduler myScheduler;

    /**
     * 初始启动quartz
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            myScheduler.testCronQuartz();
            System.out.println("任务已经启动...");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始注入scheduler
     * @return
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException{
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }

}
