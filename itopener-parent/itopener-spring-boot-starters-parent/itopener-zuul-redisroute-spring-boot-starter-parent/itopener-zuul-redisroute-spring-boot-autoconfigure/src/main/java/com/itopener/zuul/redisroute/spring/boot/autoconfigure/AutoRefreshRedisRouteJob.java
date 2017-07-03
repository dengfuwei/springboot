package com.itopener.zuul.redisroute.spring.boot.autoconfigure;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class AutoRefreshRedisRouteJob {
	
	private final Logger logger = LoggerFactory.getLogger(AutoRefreshRedisRouteJob.class);
	
	@Resource
	private RefreshRouteService refreshRouteService;

	@Scheduled(cron = "${spring.zuul.route.redis.refreshCron:0/30 * * * * ?}")
	public void run() {
		logger.info("refresh zuul route config from redis");
		refreshRouteService.refreshRoute();
	}

}
