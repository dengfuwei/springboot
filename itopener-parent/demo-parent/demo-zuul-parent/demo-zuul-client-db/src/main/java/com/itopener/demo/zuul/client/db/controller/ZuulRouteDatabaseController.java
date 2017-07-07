package com.itopener.demo.zuul.client.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.zuul.route.spring.boot.common.refresh.RefreshRouteService;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 下午1:25:23
 * @version 1.0.0
 */
@RestController
@RequestMapping("db")
public class ZuulRouteDatabaseController {

	@Autowired
	RefreshRouteService refreshRouteService;

	@RequestMapping("/refreshRoute")
	public ResultMap refreshRoute() {
		refreshRouteService.refreshRoute();
		return ResultMap.buildSuccess();
	}

}