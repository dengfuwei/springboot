package com.itopener.zuul.route.zk.spring.boot.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.zuul.route.spring.boot.common.ZuulRouteEntity;
import com.zuul.route.spring.boot.common.ZuulRouteLocator;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 上午11:11:19
 * @version 1.0.0
 */
public class ZuulZookeeperRouteLocator extends ZuulRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(ZuulZookeeperRouteLocator.class);

	@Autowired
	private CuratorFrameworkClient curatorFrameworkClient;

	public ZuulZookeeperRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
	}

	@Override
	public Map<String, ZuulRoute> loadLocateRoute() {
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		try {
			locateRouteList = new ArrayList<ZuulRouteEntity>();
			List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
			for(String item : keys){
				String value = curatorFrameworkClient.get("/" + item);
				if(!StringUtils.isEmpty(value)){
					locateRouteList.add(JSON.parseObject(value, ZuulRouteEntity.class));
				}
			}
		} catch (Exception e) {
			logger.error("load zuul route from zk exception", e);
		}
		logger.info("load zuul route from zk : " + JSON.toJSONString(locateRouteList));
		return handle(locateRouteList);
	}

}