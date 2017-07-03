package com.itopener.demo.zuul.client.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.zuul.redisroute.spring.boot.autoconfigure.RefreshRouteService;
import com.itopener.zuul.redisroute.spring.boot.autoconfigure.ZuulRedisRouteLocator.ZuulRouteEntity;
import com.itopener.zuul.redisroute.spring.boot.autoconfigure.ZuulRedisRouteProperties;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 下午1:25:23
 * @version 1.0.0
 */
@RestController
@RequestMapping("redis")
public class ZuulRouteRedisController {

	@Autowired
	RefreshRouteService refreshRouteService;

	@Autowired
	ZuulHandlerMapping zuulHandlerMapping;

	@Resource
	private RedisTemplate<String, ZuulRouteEntity> redisTemplate;
	
	@Autowired
	private ZuulRedisRouteProperties zuulRedisRouteProperties;

	@RequestMapping("/refreshRoute")
	public ResultMap refreshRoute() {
		 refreshRouteService.refreshRoute();
		return ResultMap.buildSuccess();
	}

	@RequestMapping("addroute")
	public ResultMap addRoute() {
		ZuulRouteEntity entity = new ZuulRouteEntity();
		entity.setCustomSensitiveHeaders(false);
		entity.setEnable(true);
		entity.setId("itopener-demo-zuul-server1");
		entity.setPath("/api-server1/**");
		entity.setRetryable(false);
		entity.setRouterName("server1路由");
		entity.setSensitiveHeader("");
		entity.setSensitiveHeaders(null);
		entity.setServiceId("itopener-demo-zuul-server1");
		entity.setStripPrefix(true);
		entity.setUrl("");
		redisTemplate.opsForHash().put(zuulRedisRouteProperties.getNamespace(), entity.getId(), entity);
		return ResultMap.buildSuccess();
	}

	@RequestMapping("remove/{id}")
	public ResultMap remove(@PathVariable String id) {
		redisTemplate.opsForHash().delete(zuulRedisRouteProperties.getNamespace(), id);
		return ResultMap.buildSuccess();
	}

}