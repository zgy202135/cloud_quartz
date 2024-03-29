package com.julius.quartz.cloud.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.julius.quartz.cloud.quartz.listener
 * @ClassName: OwnerTriggerListenerSupport
 * @Author: Julius
 * @Description: 自定义Trigger Listener
 * @Date: 2019/12/17 10:55
 * @Version: 1.0
 */
@Component
public class OwnerTriggerListenerSupport implements TriggerListener{

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

    }
}
