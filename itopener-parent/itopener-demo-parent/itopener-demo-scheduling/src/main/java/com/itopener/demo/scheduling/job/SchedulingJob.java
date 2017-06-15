package com.itopener.demo.scheduling.job;

import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itopener.demo.scheduling.service.SchedulingService;

@Component
public class SchedulingJob {

	private final Logger logger = LoggerFactory.getLogger(SchedulingJob.class);
	
	@Resource
	private SchedulingService schedulingService;

	@Scheduled(cron = "0/5 * * * * ?")
	public void scheduling(){
		logger.info("scheduling...");
		schedulingService.execute(new Random().nextLong());
	}
}
