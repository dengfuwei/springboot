package com.itopener.zuul.route.db.spring.boot.autoconfigure;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class AutoRefreshDBRouteJob {
	
	private final Logger logger = LoggerFactory.getLogger(AutoRefreshDBRouteJob.class);
	
	@Resource
	private RefreshRouteService refreshRouteService;

	@Scheduled(cron = "${spring.zuul.route.db.refreshCron:0/30 * * * * ?}")
	public void run() {
		logger.info("refresh zuul route config from db");
		refreshRouteService.refreshRoute();
	}

}
