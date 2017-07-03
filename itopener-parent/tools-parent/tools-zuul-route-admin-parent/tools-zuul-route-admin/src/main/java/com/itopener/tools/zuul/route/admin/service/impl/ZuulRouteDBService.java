package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteConfigCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulDBRouteProperties;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.dao.ZuulRouteConfigDao;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.zuul.route.spring.boot.common.ZuulRouteEntity;

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
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		condition.setTableName(zuulDBRouteProperties.getTableName());
		BeanUtils.copyProperties(entity, condition);
		int count = zuulRouteConfigDao.selectCount(condition);
		if(count > 0){
			zuulRouteConfigDao.update(condition);
		} else{
			zuulRouteConfigDao.insert(condition);
		}
	}

	@Override
	public void delete(String id) {
		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
		condition.setTableName(zuulDBRouteProperties.getTableName());
		condition.setId(id);
		zuulRouteConfigDao.delete(condition);
	}

	@Override
	public List<ZuulRouteEntity> list() {
//		ZuulRouteConfigCondition condition = new ZuulRouteConfigCondition();
//		return zuulRouteConfigDao.selectList(condition);
		return null;
	}

}
