package com.itopener.demo.scheduling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.CronTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.scheduling.config.ScheduledTaskRegistrarConfiguration;
import com.itopener.framework.ResultMap;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {
	
	private final Logger logger = LoggerFactory.getLogger(SchedulingController.class);
	
	@Autowired
	private ScheduledTaskRegistrarConfiguration scheduledTaskRegistrarConfiguration;

	@RequestMapping("list")
	public ResultMap list(){
		logger.info("get cron task list");
		List<CronTask> cronTaskList = scheduledTaskRegistrarConfiguration.getCronTaskList();
		return ResultMap.buildSuccess().put("cronTaskList", cronTaskList);
	}
}
