package com.julius.quartz.cloud.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Package: com.julius.quartz.cloud.quartz.listener
 * @ClassName: OwnerJobListenerSupport
 * @Author: Julius
 * @Description: custom owner job listener
 * @Date: 2019/12/18 9:25
 * @Version: 1.0
 */
@Component
public class OwnerJobListenerSupport implements JobListener {
    private static final Logger logger = LoggerFactory.getLogger(OwnerJobListenerSupport.class);

    @Override
    public String getName() {
        String name = getClass().getSimpleName();
        logger.info("The listener's name is {}",name);
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        logger.info("{} is going to be executed",jobName);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

    }
}
