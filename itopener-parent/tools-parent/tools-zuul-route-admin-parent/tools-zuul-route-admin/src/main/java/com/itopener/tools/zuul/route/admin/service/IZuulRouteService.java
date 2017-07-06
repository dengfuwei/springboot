package com.itopener.tools.zuul.route.admin.service;

import java.util.List;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteEntityCondition;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

public interface IZuulRouteService {
	
	public String key();

	public void save(ZuulRouteEntity entity);
	
	public void delete(String id);
	
	public List<ZuulRouteEntity> list(ZuulRouteEntityCondition condition);
	
	public List<ZuulRouteEntity> listAll();
	
	public void clear();
	
	public void updateEnable(ZuulRouteEntity entity);
	
	public int totalCount();
	
	public int enableCount();
	
	public void change(String namespace);
	
	public String count();
}
