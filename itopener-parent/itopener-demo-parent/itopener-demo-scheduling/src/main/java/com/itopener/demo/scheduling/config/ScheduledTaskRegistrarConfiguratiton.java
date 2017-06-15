package com.itopener.demo.scheduling.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskRegistrarConfiguratiton implements SchedulingConfigurer {
	
	private final Logger logger = LoggerFactory.getLogger(ScheduledTaskRegistrarConfiguratiton.class);
	
	private List<CronTask> cronTaskList;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		logger.info("task registrar");
		cronTaskList = taskRegistrar.getCronTaskList();
	}

	public List<CronTask> getCronTaskList() {
		return cronTaskList;
	}

}
