package com.julius.quartz.cloud.quartz.listener;

import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @Package: com.julius.quartz.cloud.quartz.listener
 * @ClassName: OwnerSchedulerListenerSupport
 * @Author: Julius
 * @Description: custom owner Scheduler Listener
 * @Date: 2019/12/18 9:27
 * @Version: 1.0
 */
@Component
public class OwnerSchedulerListenerSupport implements SchedulerListener {

    @Override
    public void jobScheduled(Trigger trigger) {

    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {

    }

    @Override
    public void triggerFinalized(Trigger trigger) {

    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {

    }

    @Override
    public void triggersPaused(String s) {

    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {

    }

    @Override
    public void triggersResumed(String s) {

    }

    @Override
    public void jobAdded(JobDetail jobDetail) {

    }

    @Override
    public void jobDeleted(JobKey jobKey) {

    }

    @Override
    public void jobPaused(JobKey jobKey) {

    }

    @Override
    public void jobsPaused(String s) {

    }

    @Override
    public void jobResumed(JobKey jobKey) {

    }

    @Override
    public void jobsResumed(String s) {

    }

    @Override
    public void schedulerError(String s, SchedulerException e) {

    }

    @Override
    public void schedulerInStandbyMode() {

    }

    @Override
    public void schedulerStarted() {

    }

    @Override
    public void schedulerStarting() {

    }

    @Override
    public void schedulerShutdown() {

    }

    @Override
    public void schedulerShuttingdown() {

    }

    @Override
    public void schedulingDataCleared() {

    }
}
