package com.itopener.tools.zuul.route.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.tools.zuul.route.admin.config.CuratorFrameworkClient;
import com.itopener.tools.zuul.route.admin.config.ZuulDBRouteProperties;
import com.itopener.tools.zuul.route.admin.config.ZuulRedisRouteProperties;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteHelper;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

@RestController
@RequestMapping("route")
public class ZuulRouteController {

	@Resource
	private ZuulRouteHelper zuulRouteHelper;
	
	@Autowired
	private ZuulDBRouteProperties zuulDBRouteProperties;
	
	@Autowired
	private ZuulRedisRouteProperties zuulRedisRouteProperties;
	
	@Autowired
	private CuratorFrameworkClient curatorFrameworkClient;
	
	@Resource
	private HttpServletRequest request;
	
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public ResultMap count(){
		String dbCount = "";
		try {
			dbCount += zuulRouteHelper.get(ZuulRouteConstant.KEY_DB).enableCount();
		} catch (Exception e) {
			dbCount += "获取失败";
		}
		dbCount += "/";
		try {
			dbCount += zuulRouteHelper.get(ZuulRouteConstant.KEY_DB).totalCount();
		} catch (Exception e) {
			dbCount += "获取失败";
		}
		
		String redisCount = "";
		try {
			redisCount += zuulRouteHelper.get(ZuulRouteConstant.KEY_REDIS).enableCount();
		} catch (Exception e) {
			redisCount += "获取失败";
		}
		redisCount += "/";
		try {
			redisCount += zuulRouteHelper.get(ZuulRouteConstant.KEY_REDIS).totalCount();
		} catch (Exception e) {
			redisCount += "获取失败";
		}
		
		String zkCount = "";
		try {
			zkCount += zuulRouteHelper.get(ZuulRouteConstant.KEY_ZK).enableCount();
		} catch (Exception e) {
			zkCount += "获取失败";
		}
		zkCount += "/";
		try {
			zkCount += zuulRouteHelper.get(ZuulRouteConstant.KEY_ZK).totalCount();
		} catch (Exception e) {
			zkCount += "获取失败";
		}
		return ResultMap.buildSuccess().put("db", dbCount)
				.put("redis", redisCount)
				.put("zk", zkCount);
	}
	
	@RequestMapping(value = "{type}/save", method = RequestMethod.PUT)
	public ResultMap save(@PathVariable String type, ZuulRouteEntity entity){
		IZuulRouteService zuulRouteService = zuulRouteHelper.get(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.save(entity);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/{id}", method = RequestMethod.DELETE)
	public ResultMap delete(@PathVariable String type, @PathVariable String id){
		IZuulRouteService zuulRouteService = zuulRouteHelper.get(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.delete(id);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/list", method = RequestMethod.GET)
	public ResultMap list(@PathVariable String type, ZuulRouteEntity entity){
		IZuulRouteService zuulRouteService = zuulRouteHelper.get(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		List<ZuulRouteEntity> list = zuulRouteService.list(entity);
		return ResultMap.buildSuccess().put("list", list);
	}
	
	@RequestMapping(value = "{type}/change", method = RequestMethod.PUT)
	public ResultMap change(@PathVariable String type, String value){
		if(ZuulRouteConstant.KEY_DB.equals(type)){
			zuulDBRouteProperties.setTableName(value);
		} else if(ZuulRouteConstant.KEY_REDIS.equals(type)){
			zuulRedisRouteProperties.setNamespace(value);
		} else if(ZuulRouteConstant.KEY_ZK.equals(type)){
			curatorFrameworkClient.getCuratorFramework().usingNamespace(value);
		}
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/clear", method = RequestMethod.PUT)
	public ResultMap clear(@PathVariable String type){
		IZuulRouteService zuulRouteService = zuulRouteHelper.get(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.clear();
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping(value = "{type}/enable", method = RequestMethod.PUT)
	public ResultMap enable(@PathVariable String type, ZuulRouteEntity entity){
		IZuulRouteService zuulRouteService = zuulRouteHelper.get(type);
		if(zuulRouteService == null){
			return ResultMap.buildFailed("存储类型暂不支持");
		}
		zuulRouteService.updateEnable(entity);
		return ResultMap.buildSuccess();
	}
}
