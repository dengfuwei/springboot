package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.itopener.tools.zuul.route.admin.config.ZuulRedisRouteProperties;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

@Service
public class ZuulRouteRedisService implements IZuulRouteService {
	
	@Resource
	private RedisTemplate<String, ZuulRouteEntity> redisTemplate;
	
	@Autowired
	private ZuulRedisRouteProperties zuulRedisRouteProperties;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_REDIS;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
		redisTemplate.opsForHash().put(zuulRedisRouteProperties.getNamespace(), entity.getId(), entity);
	}

	@Override
	public void delete(String id) {
		redisTemplate.opsForHash().delete(zuulRedisRouteProperties.getNamespace(), id);
	}

	@Override
	public List<ZuulRouteEntity> list(ZuulRouteEntity entity) {
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		List<Object> redisResult = redisTemplate.opsForHash().values(zuulRedisRouteProperties.getNamespace());
		if (!CollectionUtils.isEmpty(redisResult)) {
			for (Object item : redisResult) {
				ZuulRouteEntity one = (ZuulRouteEntity) item;
				if(entity.isEnable() != one.isEnable()){
					continue;
				}
				if(!StringUtils.isEmpty(entity.getId()) && !one.getId().startsWith(entity.getId())){
					continue;
				}
				if(!StringUtils.isEmpty(entity.getRouterName()) && !one.getRouterName().startsWith(entity.getRouterName())){
					continue;
				}
				locateRouteList.add((ZuulRouteEntity) item);
			}
		}
		return locateRouteList;
	}

	@Override
	public void clear() {
		redisTemplate.delete(zuulRedisRouteProperties.getNamespace());
	}

	@Override
	public void updateEnable(ZuulRouteEntity entity) {
		ZuulRouteEntity result = (ZuulRouteEntity) redisTemplate.opsForHash().get(zuulRedisRouteProperties.getNamespace(), entity.getId());
		if(result != null){
			result.setEnable(entity.isEnable());
			save(result);
		}
	}

	@Override
	public int totalCount() {
		List<Object> list = redisTemplate.opsForHash().values(zuulRedisRouteProperties.getNamespace());
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

	@Override
	public int enableCount() {
		ZuulRouteEntity entity = new ZuulRouteEntity();
		entity.setEnable(true);
		List<ZuulRouteEntity> list = list(entity);
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

}
