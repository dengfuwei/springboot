//package com.itopener.demo.zuul.client.controller;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSON;
//import com.itopener.framework.ResultMap;
//import com.itopener.zuul.zkroute.spring.boot.autoconfigure.CuratorFrameworkClient;
//import com.itopener.zuul.zkroute.spring.boot.autoconfigure.RefreshRouteService;
//import com.itopener.zuul.zkroute.spring.boot.autoconfigure.ZuulZookeeperRouteLocator.ZuulRouteEntity;
//
///**
// * @author fuwei.deng
// * @date 2017年6月30日 下午1:25:23
// * @version 1.0.0
// */
//@RestController
//@RequestMapping("zk")
//public class ZuulRouteZKController {
//
//	@Autowired
//	RefreshRouteService refreshRouteService;
//
//	@Autowired
//	ZuulHandlerMapping zuulHandlerMapping;
//
//	@Resource
//	private CuratorFrameworkClient curatorFrameworkClient;
//
//	@RequestMapping("/refreshRoute")
//	public ResultMap refreshRoute() {
//		 refreshRouteService.refreshRoute();
//		return ResultMap.buildSuccess();
//	}
//
//	@RequestMapping("addroute")
//	public ResultMap addRoute() {
//		ZuulRouteEntity entity = new ZuulRouteEntity();
//		entity.setCustomSensitiveHeaders(false);
//		entity.setEnable(true);
//		entity.setId("itopener-demo-zuul-server1");
//		entity.setPath("/api-server1/**");
//		entity.setRetryable(false);
//		entity.setRouterName("server1路由");
//		entity.setSensitiveHeader("");
//		entity.setSensitiveHeaders(null);
//		entity.setServiceId("itopener-demo-zuul-server1");
//		entity.setStripPrefix(true);
//		entity.setUrl("");
//		curatorFrameworkClient.persist("/" + entity.getId(), JSON.toJSONString(entity));
//		return ResultMap.buildSuccess();
//	}
//
//	@RequestMapping("remove/{id}")
//	public ResultMap remove(@PathVariable String id) {
//		curatorFrameworkClient.remove("/" + id);
//		return ResultMap.buildSuccess();
//	}
//
//}