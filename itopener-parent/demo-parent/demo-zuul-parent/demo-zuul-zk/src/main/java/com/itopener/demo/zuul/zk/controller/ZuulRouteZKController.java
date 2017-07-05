package com.itopener.demo.zuul.zk.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.zuul.route.spring.boot.common.refresh.RefreshRouteService;
import com.itopener.zuul.route.zk.spring.boot.autoconfigure.CuratorFrameworkClient;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 下午1:25:23
 * @version 1.0.0
 */
@RestController
@RequestMapping("zk")
public class ZuulRouteZKController {

	@Autowired
	RefreshRouteService refreshRouteService;

	@Autowired
	ZuulHandlerMapping zuulHandlerMapping;

	@Resource
	private CuratorFrameworkClient curatorFrameworkClient;

	@RequestMapping("/refreshRoute")
	public ResultMap refreshRoute() {
		refreshRouteService.refreshRoute();
		return ResultMap.buildSuccess();
	}

}