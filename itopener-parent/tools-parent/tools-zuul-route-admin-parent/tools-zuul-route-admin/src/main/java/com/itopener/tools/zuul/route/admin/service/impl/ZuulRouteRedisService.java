package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.redis.ZuulRouteRedisProperties;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

public class ZuulRouteRedisService implements IZuulRouteService {
	
	@Resource
	private RedisTemplate<String, ZuulRouteEntity> redisTemplate;
	
	@Autowired
	private ZuulRouteRedisProperties zuulRouteRedisProperties;
	
	@Resource
	private HttpServletRequest request;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_REDIS;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
		redisTemplate.opsForHash().put(getNamespace(), entity.getId(), entity);
	}

	@Override
	public void delete(String id) {
		redisTemplate.opsForHash().delete(getNamespace(), id);
	}

	@Override
	public List<ZuulRouteEntity> list(ZuulRouteEntityCondition condition) {
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		List<Object> redisResult = redisTemplate.opsForHash().values(getNamespace());
		if (!CollectionUtils.isEmpty(redisResult)) {
			for (Object item : redisResult) {
				ZuulRouteEntity one = (ZuulRouteEntity) item;
				if(condition.isEnable() != one.isEnable()){
					continue;
				}
				if(!StringUtils.isEmpty(condition.getLikeId()) && !one.getId().startsWith(condition.getLikeId())){
					continue;
				}
				if(!StringUtils.isEmpty(condition.getLikeRouterName()) && !one.getRouterName().startsWith(condition.getLikeRouterName())){
					continue;
				}
				locateRouteList.add(one);
			}
		}
		return locateRouteList;
	}

	@Override
	public List<ZuulRouteEntity> listAll() {
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		List<Object> redisResult = redisTemplate.opsForHash().values(getNamespace());
		if (!CollectionUtils.isEmpty(redisResult)) {
			for (Object item : redisResult) {
				locateRouteList.add((ZuulRouteEntity) item);
			}
		}
		return locateRouteList;
	}

	@Override
	public void clear() {
		redisTemplate.delete(getNamespace());
	}

	@Override
	public void updateEnable(ZuulRouteEntity entity) {
		ZuulRouteEntity result = (ZuulRouteEntity) redisTemplate.opsForHash().get(getNamespace(), entity.getId());
		if(result != null){
			result.setEnable(entity.isEnable());
			save(result);
		}
	}

	@Override
	public int totalCount() {
		List<Object> list = redisTemplate.opsForHash().values(getNamespace());
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

	@Override
	public int enableCount() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setEnable(true);
		List<ZuulRouteEntity> list = list(condition);
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}
	
	@Override
	public void change(String namespace) {
		request.getSession().setAttribute("namespace", namespace);
//		zuulRedisRouteProperties.setNamespace(namespace);
	}

	@Override
	public String count() {
		List<Object> list = redisTemplate.opsForHash().values(getNamespace());
		if(CollectionUtils.isEmpty(list)){
			return "0/0";
		}
		int totalCount = list.size();
		int enableCount = 0;
		for (Object item : list) {
			ZuulRouteEntity one = (ZuulRouteEntity) item;
			if(one.isEnable()){
				enableCount ++;
			}
		}
		
		return enableCount + "/" + totalCount;
	}

	private String getNamespace(){
		String namespace = (String) request.getSession().getAttribute("namespace");
		return StringUtils.isEmpty(namespace) ? zuulRouteRedisProperties.getNamespace() : namespace;
	}
}
