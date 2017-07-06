package com.itopener.tools.zuul.route.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteRuleEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteHelper;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteRuleService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;

@RestController
@RequestMapping("rule")
public class ZuulRouteRuleController {
	
	@Resource
	private ZuulRouteHelper zuulRouteHelper;
	
	@Resource
	private HttpServletRequest request;
	
	@RequestMapping(value = "{type}/save", method = RequestMethod.PUT)
	public ResultMap save(@PathVariable String type, ZuulRouteRuleEntity entity){
		IZuulRouteRuleService zuulRouteRuleService = zuulRouteHelper.getRouteRuleService(type);
		if(zuulRouteRuleService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteRuleService.save(entity);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/{routeId}/{id}", method = RequestMethod.DELETE)
	public ResultMap delete(@PathVariable String type, @PathVariable String routeId, @PathVariable String id){
		IZuulRouteRuleService zuulRouteRuleService = zuulRouteHelper.getRouteRuleService(type);
		if(zuulRouteRuleService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		ZuulRouteRuleEntity entity = new ZuulRouteRuleEntity();
		entity.setRouteId(routeId);
		entity.setId(id);
		zuulRouteRuleService.delete(entity);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/list", method = RequestMethod.GET)
	public ResultMap list(@PathVariable String type, ZuulRouteRuleEntityCondition condition){
		IZuulRouteRuleService zuulRouteRuleService = zuulRouteHelper.getRouteRuleService(type);
		if(zuulRouteRuleService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		List<ZuulRouteRuleEntity> list = zuulRouteRuleService.list(condition);
		return ResultMap.buildSuccess().put("list", list);
	}
	
	@RequestMapping(value = "{type}/change", method = RequestMethod.PUT)
	public ResultMap change(@PathVariable String type, String value){
		IZuulRouteRuleService zuulRouteRuleService = zuulRouteHelper.getRouteRuleService(type);
		if(zuulRouteRuleService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteRuleService.change(value);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/clear", method = RequestMethod.PUT)
	public ResultMap clear(@PathVariable String type, @PathVariable String routeId){
		IZuulRouteRuleService zuulRouteRuleService = zuulRouteHelper.getRouteRuleService(type);
		if(zuulRouteRuleService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		ZuulRouteRuleEntity entity = new ZuulRouteRuleEntity();
		entity.setRouteId(routeId);
		zuulRouteRuleService.clear(entity);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/enable", method = RequestMethod.PUT)
	public ResultMap enable(@PathVariable String type, ZuulRouteRuleEntity entity){
		IZuulRouteRuleService zuulRouteRuleService = zuulRouteHelper.getRouteRuleService(type);
		if(zuulRouteRuleService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteRuleService.updateEnable(entity);
		return ResultMap.buildSuccess();
	}
}
