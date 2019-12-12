package com.julius.quartz.cloud.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

/**
 * @Package: com.julius.quartz.cloud.quartz.job
 * @ClassName: PrintWordsJob
 * @Author: Julius
 * @Description: 打印任意日志的任务 jobDetail
 * @Date: 2019/12/12 10:56
 * @Version: 1.0
 */
@Component
public class PrintWordsJob implements Job{
    private static final Logger logger = LoggerFactory.getLogger(PrintWordsJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("jobExecutionContext:{}",jobExecutionContext.getJobInstance().hashCode());
        LocalDateTime localDateTime = LocalDateTime.now();
        String printTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("PrintWordsJob start in :{},prints:hello job:{}",printTime,new Random().nextInt(100));

        //通过JobExecutionContext获取JobDataMap并解析
        JobDataMap detailMap = jobExecutionContext.getJobDetail().getJobDataMap();
        for(Map.Entry<String,Object> entry:detailMap.entrySet()){
            System.out.println("key:"+entry.getKey()+"---value:"+entry.getValue());
        }

        JobDataMap triggerMap = jobExecutionContext.getTrigger().getJobDataMap();
        for(Map.Entry<String,Object> entry:triggerMap.entrySet()){
            System.out.println("key:"+entry.getKey()+"---value:"+entry.getValue());
        }
    }


}
