package com.itopener.zuul.route.spring.boot.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class ZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(ZuulRouteLocator.class);
	
	private ZuulProperties properties;
	
	@Resource
	private HttpServletRequest request;
	
	public ZuulRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		this.properties = properties;
		logger.info("servletPath:{}", servletPath);
	}
	
	@Override
	public void refresh() {
		doRefresh();
	}
	
	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
		// 从application.properties中加载路由信息
		// routesMap.putAll(super.locateRoutes());
		// 从db中加载路由信息
		routesMap.putAll(loadLocateRoute());
		// 优化一下配置
		LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
		for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
			String path = entry.getKey();
			// Prepend with slash if not already present.
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (StringUtils.hasText(this.properties.getPrefix())) {
				path = this.properties.getPrefix() + path;
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		return values;
	}
	
	/**
	 * @description 自定义实现加载路由配置
	 * @author fuwei.deng
	 * @date 2017年7月3日 下午6:04:42
	 * @version 1.0.0
	 * @return
	 */
	public Map<String, ZuulRoute> loadLocateRoute(){
		return null;
	}
	
	@Override
	public Route getMatchingRoute(String path) {
		Route route = super.getMatchingRoute(path);
		// 增加自定义路由规则判断
		return matchingRule(route);
	}
	
	/**
	 * @description 自定义实现规则判断
	 * @author fuwei.deng
	 * @date 2017年7月3日 下午6:05:52
	 * @version 1.0.0
	 * @param route
	 * @return
	 */
	public Route matchingRule(Route route){
		logger.info(JSON.toJSONString(route));
		String a = request.getParameter("a");
		logger.info(a);
		if("abc".equals(a)){
			route.setLocation("demo-zuul-server2");
		}
		return route;
	}

	@Override
	public int getOrder() {
		return -1;
	}
	
	/**
	 * @description 复制查询出来的数据的属性
	 * @author fuwei.deng
	 * @date 2017年7月3日 下午6:19:40
	 * @version 1.0.0
	 * @param locateRouteList
	 * @return
	 */
	public Map<String, ZuulRoute> handle(List<ZuulRouteEntity> locateRouteList){
		Map<String, ZuulRoute> routes = new LinkedHashMap<>();
		for (ZuulRouteEntity locateRoute : locateRouteList) {
			if (StringUtils.isEmpty(locateRoute.getPath())
					|| !locateRoute.isEnable()
					|| (StringUtils.isEmpty(locateRoute.getUrl()) && StringUtils.isEmpty(locateRoute.getServiceId()))) {
				continue;
			}
			ZuulRoute zuulRoute = new ZuulRoute();
			try {
				zuulRoute.setCustomSensitiveHeaders(locateRoute.isCustomSensitiveHeaders());
				zuulRoute.setSensitiveHeaders(locateRoute.getSensitiveHeadersSet());
				zuulRoute.setId(locateRoute.getId());
//				zuulRoute.setLocation("");
				zuulRoute.setPath(locateRoute.getPath());
				zuulRoute.setRetryable(locateRoute.isRetryable());
				zuulRoute.setServiceId(locateRoute.getServiceId());
				zuulRoute.setStripPrefix(locateRoute.isStripPrefix());
				zuulRoute.setUrl(locateRoute.getUrl());
				logger.info("add zuul route: " + JSON.toJSONString(zuulRoute));
			} catch (Exception e) {
				logger.error("=============load zuul route info from db with error==============", e);
			}
			routes.put(zuulRoute.getPath(), zuulRoute);
		}
		return routes;
	}
}
