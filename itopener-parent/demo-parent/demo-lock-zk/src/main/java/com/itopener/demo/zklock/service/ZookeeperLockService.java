package com.itopener.demo.zklock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ZookeeperLockService {

	private final Logger logger = LoggerFactory.getLogger(ZookeeperLockService.class);
	
	public void update(String key){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("exp", e);
		}
	}
	
}
