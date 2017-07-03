package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.List;

import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.zuul.route.spring.boot.common.ZuulRouteEntity;

public class ZuulRouteRedisService implements IZuulRouteService {

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_REDIS;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public List<ZuulRouteEntity> list() {
		return null;
	}

}
