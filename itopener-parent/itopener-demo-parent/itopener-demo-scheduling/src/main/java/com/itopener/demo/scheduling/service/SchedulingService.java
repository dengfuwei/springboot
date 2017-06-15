package com.itopener.demo.scheduling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {
	
	private final Logger logger = LoggerFactory.getLogger(SchedulingService.class);

	public void execute(long id){
		logger.info("scheduling service : " + id);
	}
}
