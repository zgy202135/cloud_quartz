package com.julius.quartz.cloud.quartz;

import com.julius.quartz.cloud.quartz.scheduler.MyScheduler;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudQuartzApplicationTests {
	@Autowired
	private MyScheduler myScheduler;

	@Test
	void contextLoads() {
	}

	@Test
	public void testScheduler() throws InterruptedException, SchedulerException {
		myScheduler.testQuartz();
	}

}
