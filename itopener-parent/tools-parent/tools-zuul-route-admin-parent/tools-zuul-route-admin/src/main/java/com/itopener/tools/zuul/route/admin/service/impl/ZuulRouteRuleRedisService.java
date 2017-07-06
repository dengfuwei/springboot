package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteRuleEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.redis.ZuulRouteRedisProperties;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteRuleService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;

public class ZuulRouteRuleRedisService implements IZuulRouteRuleService {
	
	@Resource
	private RedisTemplate<String, ZuulRouteRuleEntity> redisTemplate;
	
	@Autowired
	private ZuulRouteRedisProperties zuulRedisRouteProperties;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_REDIS;
	}

	@Override
	public void save(ZuulRouteRuleEntity entity) {
		redisTemplate.opsForHash().put(zuulRedisRouteProperties.getNamespace() + "_" + entity.getRouteId(), entity.getId(), entity);
	}

	@Override
	public void delete(ZuulRouteRuleEntity entity) {
		redisTemplate.opsForHash().delete(zuulRedisRouteProperties.getNamespace() + "_" + entity.getRouteId(), entity.getId());
	}

	@Override
	public List<ZuulRouteRuleEntity> list(ZuulRouteRuleEntityCondition condition) {
		List<ZuulRouteRuleEntity> locateRouteList = new ArrayList<ZuulRouteRuleEntity>();
		List<Object> redisResult = redisTemplate.opsForHash().values(zuulRedisRouteProperties.getNamespace() + "_" + condition.getRouteId());
		if (!CollectionUtils.isEmpty(redisResult)) {
			for (Object item : redisResult) {
				ZuulRouteRuleEntity one = (ZuulRouteRuleEntity) item;
				if(condition.isEnable() != one.isEnable()){
					continue;
				}
				if(!StringUtils.isEmpty(condition.getLikeId()) && !one.getId().startsWith(condition.getLikeId())){
					continue;
				}
				locateRouteList.add((ZuulRouteRuleEntity) item);
			}
		}
		return locateRouteList;
	}

	@Override
	public void clear(ZuulRouteRuleEntity entity) {
		redisTemplate.delete(zuulRedisRouteProperties.getNamespace() + "_" + entity.getRouteId());
	}

	@Override
	public void updateEnable(ZuulRouteRuleEntity entity) {
		ZuulRouteRuleEntity result = (ZuulRouteRuleEntity) redisTemplate.opsForHash().get(zuulRedisRouteProperties.getNamespace() + "_" + entity.getRouteId(), entity.getId());
		if(result != null){
			result.setEnable(entity.isEnable());
			save(result);
		}
	}

	@Override
	public int totalCount() {
		Set<Object> set = redisTemplate.opsForHash().keys(zuulRedisRouteProperties.getNamespace() + "_*");
		return CollectionUtils.isEmpty(set) ? 0 : set.size();
	}

	@Override
	public int enableCount() {
		Set<String> set = redisTemplate.keys(zuulRedisRouteProperties.getNamespace() + "_*");
		if(CollectionUtils.isEmpty(set)){
			return 0;
		}
		int enableCount = 0;
		for (Object item : set) {
			List<Object> rules = redisTemplate.opsForHash().values(zuulRedisRouteProperties.getNamespace() + "_" + item);
			if(CollectionUtils.isEmpty(rules)){
				continue;
			}
			for(Object rule : rules){
				ZuulRouteRuleEntity one = (ZuulRouteRuleEntity) rule;
				if(one.isEnable()){
					enableCount ++;
				}
			}
		}
		return enableCount;
	}
	
	@Override
	public void change(String namespace) {
		zuulRedisRouteProperties.setNamespace(namespace);
	}

	@Override
	public String count() {
		Set<String> set = redisTemplate.keys(zuulRedisRouteProperties.getNamespace() + "_*");
		if(CollectionUtils.isEmpty(set)){
			return "0/0";
		}
		int totalCount = set.size();
		int enableCount = 0;
		for (Object item : set) {
			List<Object> rules = redisTemplate.opsForHash().values(zuulRedisRouteProperties.getNamespace() + "_" + item);
			if(CollectionUtils.isEmpty(rules)){
				continue;
			}
			for(Object rule : rules){
				ZuulRouteRuleEntity one = (ZuulRouteRuleEntity) rule;
				if(one.isEnable()){
					enableCount ++;
				}
			}
		}
		
		return enableCount + "/" + totalCount;
	}

}
