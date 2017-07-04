package com.itopener.zuul.route.redis.spring.boot.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteLocator;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 上午11:11:19
 * @version 1.0.0
 */
public class ZuulRedisRouteLocator extends ZuulRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(ZuulRedisRouteLocator.class);

	@Autowired
	private RedisTemplate<String, ZuulRouteEntity> redisTemplate;

	@Autowired
	private ZuulRedisRouteProperties zuulRedisRouteProperties;

	public ZuulRedisRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
	}

	@Override
	public Map<String, ZuulRoute> loadLocateRoute() {
		List<Object> redisResult = new ArrayList<Object>();
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		try {
			redisResult = redisTemplate.opsForHash().values(zuulRedisRouteProperties.getNamespace());
			if (!CollectionUtils.isEmpty(redisResult)) {
				for (Object item : redisResult) {
					ZuulRouteEntity one = (ZuulRouteEntity) item;
					if(!one.isEnable()){
						continue;
					}
					locateRouteList.add(one);
				}
			}
		} catch (Exception e) {
			logger.error("load zuul route from redis exception", e);
		}
		return handle(locateRouteList);
	}

}