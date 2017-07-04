package com.itopener.tools.zuul.route.admin.config;

import java.util.Map;

import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;

public class ZuulRouteHelper {

	private Map<String, IZuulRouteService> zuulRouteService;

	public Map<String, IZuulRouteService> getZuulRouteService() {
		return zuulRouteService;
	}

	public void setZuulRouteService(Map<String, IZuulRouteService> zuulRouteService) {
		this.zuulRouteService = zuulRouteService;
	}
	
	public IZuulRouteService get(String key){
		return zuulRouteService.get(key);
	}
}
