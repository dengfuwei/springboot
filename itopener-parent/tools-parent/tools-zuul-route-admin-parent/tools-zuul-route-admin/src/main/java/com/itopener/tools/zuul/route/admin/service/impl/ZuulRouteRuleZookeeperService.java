package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteRuleEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.zk.CuratorFrameworkClient;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteRuleService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;

public class ZuulRouteRuleZookeeperService implements IZuulRouteRuleService {
	
	@Autowired
	private CuratorFrameworkClient curatorFrameworkClient;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_ZK;
	}

	@Override
	public void save(ZuulRouteRuleEntity entity) {
		curatorFrameworkClient.persist("/" + entity.getRouteId() + "/" + entity.getId(), JSON.toJSONString(entity));
	}

	@Override
	public void delete(ZuulRouteRuleEntity entity) {
		curatorFrameworkClient.remove("/" + entity.getRouteId() + "/" + entity.getId());
	}

	@Override
	public List<ZuulRouteRuleEntity> list(ZuulRouteRuleEntityCondition condition) {
		List<ZuulRouteRuleEntity> locateRouteList = new ArrayList<ZuulRouteRuleEntity>();
		locateRouteList = new ArrayList<ZuulRouteRuleEntity>();
		List<String> keys = curatorFrameworkClient.getChildrenKeys("/" + condition.getRouteId());
		for(String item : keys){
			String value = curatorFrameworkClient.get("/" + condition.getRouteId() + "/" + item);
			if(!StringUtils.isEmpty(value)){
				ZuulRouteRuleEntity one = JSON.parseObject(value, ZuulRouteRuleEntity.class);
				if(condition.isEnable() != one.isEnable()){
					continue;
				}
				if(!StringUtils.isEmpty(condition.getLikeId()) && !one.getId().startsWith(condition.getLikeId())){
					continue;
				}
				
				locateRouteList.add(one);
			}
		}
		return locateRouteList;
	}

	@Override
	public void clear(ZuulRouteRuleEntity entity) {
		curatorFrameworkClient.remove("/" + entity.getRouteId());
	}

	@Override
	public void updateEnable(ZuulRouteRuleEntity entity) {
		String resultStr = curatorFrameworkClient.get("/" + entity.getRouteId() + "/" + entity.getId());
		if(!StringUtils.isEmpty(resultStr)){
			ZuulRouteRuleEntity result = JSON.parseObject(resultStr, ZuulRouteRuleEntity.class);
			result.setEnable(entity.isEnable());
			save(result);
		}
	}

	@Override
	public int totalCount() {
		List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
		int totalCount = 0;
		for(String item : keys){
			totalCount += curatorFrameworkClient.getNumChildren("/" + item);
		}
		return totalCount;
	}

	@Override
	public int enableCount() {
		List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
		int enableCount = 0;
		for(String item : keys){
			String value = curatorFrameworkClient.get("/" + item);
			if(!StringUtils.isEmpty(value)){
				ZuulRouteEntity one = JSON.parseObject(value, ZuulRouteEntity.class);
				List<String> ruleKeys = curatorFrameworkClient.getChildrenKeys("/" + one.getId());
				for(String ruleKey : ruleKeys){
					String ruleStr = curatorFrameworkClient.get("/" + one.getId() + "/" + ruleKey);
					if(!StringUtils.isEmpty(ruleStr)){
						ZuulRouteRuleEntity rule = JSON.parseObject(value, ZuulRouteRuleEntity.class);
						if(!rule.isEnable()){
							continue;
						}
						enableCount ++ ;
					}
				}
			}
		}
		return enableCount;
	}

	@Override
	public void change(String namespace) {
		curatorFrameworkClient.getCuratorFramework().usingNamespace(namespace);
	}

	@Override
	public String count() {
		return enableCount() + "/" + totalCount();
	}
	
}
