package com.julius.quartz.cloud.quartz.job;

import org.quartz.*;

/**
 * @Package: com.julius.quartz.cloud.quartz.job
 * @ClassName: HelloJob
 * @Author: Julius
 * @Description: hello job
 * @Date: 2019/12/12 18:55
 * @Version: 1.0
 */
@PersistJobDataAfterExecution //使得jobDataMap下次执行是更新后的数据
@DisallowConcurrentExecution  //使得同一个job只能有一个job实例（jobDetail）运行
public class HelloJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        String userName = jobDataMap.getString("userName");
        Integer age = jobDataMap.getIntValue("age");
        jobDataMap.put("userName","ceshi2");// update jobDataMap
        System.out.println("user name:"+userName+",age:"+age+",job key:"+jobKey);
    }


}
