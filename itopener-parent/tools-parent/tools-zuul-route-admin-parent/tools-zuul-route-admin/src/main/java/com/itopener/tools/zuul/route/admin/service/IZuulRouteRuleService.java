package com.itopener.tools.zuul.route.admin.service;

import java.util.List;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteRuleEntityCondition;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;

public interface IZuulRouteRuleService {
	
	public String key();

	public void save(ZuulRouteRuleEntity entity);
	
	public void delete(ZuulRouteRuleEntity entity);
	
	public List<ZuulRouteRuleEntity> list(ZuulRouteRuleEntityCondition condition);
	
	public void clear(ZuulRouteRuleEntity entity);
	
	public void updateEnable(ZuulRouteRuleEntity entity);
	
	public int totalCount();
	
	public int enableCount();
	
	public void change(String namespace);
	
	public String count();
}
