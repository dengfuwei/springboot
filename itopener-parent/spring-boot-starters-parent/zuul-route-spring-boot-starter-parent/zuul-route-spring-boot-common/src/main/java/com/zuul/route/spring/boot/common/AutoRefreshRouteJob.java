package com.zuul.route.spring.boot.common;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.zuul.route.spring.boot.common.RefreshRouteService;

public class AutoRefreshRouteJob {
	
	private final Logger logger = LoggerFactory.getLogger(AutoRefreshRouteJob.class);
	
	@Resource
	private RefreshRouteService refreshRouteService;

	@Scheduled(cron = "${spring.zuul.route.redis.refreshCron:0/30 * * * * ?}")
	public void run() {
		logger.info("refresh zuul route config from redis");
		refreshRouteService.refreshRoute();
	}

}
