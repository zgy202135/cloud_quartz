package com.julius.quartz.cloud.quartz.listener;

import org.quartz.TriggerListener;
import org.quartz.listeners.TriggerListenerSupport;
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
public class OwnerTriggerListenerSupport extends TriggerListenerSupport implements TriggerListener{
    @Override
    public String getName() {
        return null;
    }

}
