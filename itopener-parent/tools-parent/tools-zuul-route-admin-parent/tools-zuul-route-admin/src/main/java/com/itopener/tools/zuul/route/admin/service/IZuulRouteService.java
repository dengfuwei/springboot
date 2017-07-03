package com.itopener.tools.zuul.route.admin.service;

import java.util.List;

import com.zuul.route.spring.boot.common.ZuulRouteEntity;

public interface IZuulRouteService {
	
	public String key();

	public void save(ZuulRouteEntity entity);
	
	public void delete(String id);
	
	public List<ZuulRouteEntity> list();
}
