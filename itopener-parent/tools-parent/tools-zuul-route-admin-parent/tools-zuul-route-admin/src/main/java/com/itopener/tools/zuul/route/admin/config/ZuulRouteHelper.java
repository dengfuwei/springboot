package com.itopener.tools.zuul.route.admin.config;

import java.util.Map;

import com.itopener.tools.zuul.route.admin.service.IZuulRouteRuleService;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;

public class ZuulRouteHelper {

	private Map<String, IZuulRouteService> zuulRouteService;
	
	private Map<String, IZuulRouteRuleService> zuulRouteRuleService;

	public IZuulRouteService getRouteService(String key){
		return zuulRouteService.get(key);
	}
	
	public IZuulRouteRuleService getRouteRuleService(String key){
		return zuulRouteRuleService.get(key);
	}
	
	public Map<String, IZuulRouteService> getZuulRouteService() {
		return zuulRouteService;
	}

	public void setZuulRouteService(Map<String, IZuulRouteService> zuulRouteService) {
		this.zuulRouteService = zuulRouteService;
	}
	
	public Map<String, IZuulRouteRuleService> getZuulRouteRuleService() {
		return zuulRouteRuleService;
	}

	public void setZuulRouteRuleService(Map<String, IZuulRouteRuleService> zuulRouteRuleService) {
		this.zuulRouteRuleService = zuulRouteRuleService;
	}

}
