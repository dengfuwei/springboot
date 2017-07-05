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
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteLocator;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;
import com.itopener.zuul.route.spring.boot.common.rule.IZuulRouteRule;

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
			//获取所有路由配置的id
			List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
			//遍历获取所有路由配置
			for(String item : keys){
				String value = curatorFrameworkClient.get("/" + item);
				if(!StringUtils.isEmpty(value)){
					ZuulRouteEntity route = JSON.parseObject(value, ZuulRouteEntity.class);
					//只需要启用的路由配置
					if(!route.isEnable()){
						continue;
					}
					route.setRuleList(new ArrayList<IZuulRouteRule>());
					//获取路由配置对应的所有路由规则的ID
					List<String> ruleKeys = curatorFrameworkClient.getChildrenKeys("/" + item);
					//遍历获取所有的路由规则
					for(String ruleKey : ruleKeys){
						String ruleStr = curatorFrameworkClient.get("/" + item + "/" + ruleKey);
						if(!StringUtils.isEmpty(ruleStr)){
							ZuulRouteRuleEntity rule = JSON.parseObject(ruleStr, ZuulRouteRuleEntity.class);
							//只保留可用的路由规则
							if(!rule.isEnable()){
								continue;
							}
							route.getRuleList().add(rule);
						}
					}
					locateRouteList.add(route);
				}
			}
		} catch (Exception e) {
			logger.error("load zuul route from zk exception", e);
		}
		logger.info("load zuul route from zk : " + JSON.toJSONString(locateRouteList));
		return handle(locateRouteList);
	}

}