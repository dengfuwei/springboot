package com.itopener.tools.zuul.route.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteHelper;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteRuleService;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

@RestController
@RequestMapping("route")
public class ZuulRouteController {
	
	private final Logger logger = LoggerFactory.getLogger(ZuulRouteController.class);

	@Resource
	private ZuulRouteHelper zuulRouteHelper;
	
	@Resource
	private HttpServletRequest request;
	
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public ResultMap count(){
		ResultMap resultMap = ResultMap.buildSuccess();
		Map<String, IZuulRouteService> routeMap = zuulRouteHelper.getZuulRouteService();
		for(String key : routeMap.keySet()){
			try {
				resultMap.put(key, routeMap.get(key).count());
			} catch (Exception e) {
				logger.error("count from " + key + "exception", e);
				resultMap.put(key, "获取失败");
			}
		}
		Map<String, IZuulRouteRuleService> ruleMap = zuulRouteHelper.getZuulRouteRuleService();
		for(String key : ruleMap.keySet()){
			try {
				resultMap.put(key + "Rule", ruleMap.get(key).count());
			} catch (Exception e) {
				logger.error("count from " + key + "exception", e);
				resultMap.put(key, "获取失败");
			}
		}
		return resultMap;
	}
	
	@RequestMapping(value = "{type}/save", method = RequestMethod.PUT)
	public ResultMap save(@PathVariable String type, ZuulRouteEntity entity){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.save(entity);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/{id}", method = RequestMethod.DELETE)
	public ResultMap delete(@PathVariable String type, @PathVariable String id){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.delete(id);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/list", method = RequestMethod.GET)
	public ResultMap list(@PathVariable String type, ZuulRouteEntityCondition condition){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		List<ZuulRouteEntity> list = zuulRouteService.list(condition);
		return ResultMap.buildSuccess().put("list", list);
	}
	
	@RequestMapping(value = "{type}/listall", method = RequestMethod.GET)
	public ResultMap listAll(@PathVariable String type, ZuulRouteEntity entity){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		List<ZuulRouteEntity> list = zuulRouteService.listAll();
		return ResultMap.buildSuccess().put("list", list);
	}
	
	@RequestMapping(value = "{type}/change", method = RequestMethod.PUT)
	public ResultMap change(@PathVariable String type, String value){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.change(value);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/clear", method = RequestMethod.PUT)
	public ResultMap clear(@PathVariable String type){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.clear();
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/enable", method = RequestMethod.PUT)
	public ResultMap enable(@PathVariable String type, ZuulRouteEntity entity){
		IZuulRouteService zuulRouteService = zuulRouteHelper.getRouteService(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.updateEnable(entity);
		return ResultMap.buildSuccess();
	}
}
