package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteConfigCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulDBRouteProperties;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.dao.ZuulRouteConfigDao;
import com.itopener.tools.zuul.route.admin.model.ZuulRouteConfig;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

@Service
public class ZuulRouteDBService implements IZuulRouteService {
	
	@Resource
	private ZuulRouteConfigDao zuulRouteConfigDao;
	
	@Autowired
	private ZuulDBRouteProperties zuulDBRouteProperties;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_DB;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
		ZuulRouteConfigCondition condition = handle(entity);
		int count = zuulRouteConfigDao.selectCountById(condition);
		if(count > 0){
			zuulRouteConfigDao.update(condition);
		} else{
			zuulRouteConfigDao.insert(condition);
		}
	}
	
	private ZuulRouteConfigCondition handle(ZuulRouteEntity entity){
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		if(entity == null){
			return condition;
		}
		condition.setCustomSensitiveHeaders(entity.isCustomSensitiveHeaders());
		condition.setEnable(entity.isEnable());
		condition.setId(entity.getId());
		condition.setPath(entity.getPath());
		condition.setRetryable(entity.isRetryable());
		condition.setRouterName(entity.getRouterName());
		condition.setSensitiveHeaders(entity.getSensitiveHeader());
		condition.setServiceId(entity.getServiceId());
		condition.setStripPrefix(entity.isStripPrefix());
		condition.setTableName(zuulDBRouteProperties.getTableName());
		condition.setUrl(entity.getUrl());
		return condition;
	}
	
	private ZuulRouteEntity handle(ZuulRouteConfig zuulRouteConfig){
		ZuulRouteEntity entity = new ZuulRouteEntity();
		if(zuulRouteConfig == null){
			return entity;
		}
		entity.setCustomSensitiveHeaders(zuulRouteConfig.getCustomSensitiveHeaders());
		entity.setEnable(zuulRouteConfig.isEnable());
		entity.setId(zuulRouteConfig.getId());
		entity.setPath(zuulRouteConfig.getPath());
		entity.setRetryable(zuulRouteConfig.getRetryable());
		entity.setRouterName(zuulRouteConfig.getRouterName());
		entity.setSensitiveHeader(zuulRouteConfig.getSensitiveHeaders());
		entity.setServiceId(zuulRouteConfig.getServiceId());
		entity.setStripPrefix(zuulRouteConfig.getStripPrefix());
		entity.setUrl(zuulRouteConfig.getUrl());
		return entity;
	}

	@Override
	public void delete(String id) {
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		condition.setTableName(zuulDBRouteProperties.getTableName());
		condition.setId(id);
		zuulRouteConfigDao.delete(condition);
	}

	@Override
	public List<ZuulRouteEntity> list(ZuulRouteEntity entity) {
		ZuulRouteConfigCondition condition = handle(entity);
		condition.setTableName(zuulDBRouteProperties.getTableName());
		List<ZuulRouteConfig> list = zuulRouteConfigDao.selectList(condition);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		List<ZuulRouteEntity> resultList = new ArrayList<ZuulRouteEntity>();
		for(ZuulRouteConfig item : list){
			resultList.add(handle(item));
		}
		return resultList;
	}

	@Override
	public void clear() {
	}

	@Override
	public void updateEnable(ZuulRouteEntity entity) {
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		condition.setTableName(zuulDBRouteProperties.getTableName());
		condition.setId(entity.getId());
		condition.setEnable(entity.isEnable());
		zuulRouteConfigDao.updateState(condition);
	}

	@Override
	public int totalCount() {
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		condition.setTableName(zuulDBRouteProperties.getTableName());
		return zuulRouteConfigDao.count(condition);
	}

	@Override
	public int enableCount() {
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		condition.setTableName(zuulDBRouteProperties.getTableName());
		condition.setEnable(true);
		return zuulRouteConfigDao.selectCount(condition);
	}

}
