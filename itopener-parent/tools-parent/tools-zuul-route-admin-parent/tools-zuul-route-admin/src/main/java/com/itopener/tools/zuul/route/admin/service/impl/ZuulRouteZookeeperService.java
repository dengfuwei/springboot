package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.zk.CuratorFrameworkClient;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

public class ZuulRouteZookeeperService implements IZuulRouteService {
	
	@Autowired
	private CuratorFrameworkClient curatorFrameworkClient;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_ZK;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
		curatorFrameworkClient.persist("/" + entity.getId(), JSON.toJSONString(entity));
	}

	@Override
	public void delete(String id) {
		curatorFrameworkClient.remove("/" + id);
	}

	@Override
	public List<ZuulRouteEntity> list(ZuulRouteEntity entity) {
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		locateRouteList = new ArrayList<ZuulRouteEntity>();
		List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
		for(String item : keys){
			String value = curatorFrameworkClient.get("/" + item);
			if(!StringUtils.isEmpty(value)){
				ZuulRouteEntity one = JSON.parseObject(value, ZuulRouteEntity.class);
				if(entity.isEnable() != one.isEnable()){
					continue;
				}
				if(!StringUtils.isEmpty(entity.getId()) && !one.getId().startsWith(entity.getId())){
					continue;
				}
				if(!StringUtils.isEmpty(entity.getRouterName()) && !one.getRouterName().startsWith(entity.getRouterName())){
					continue;
				}
				
				locateRouteList.add(one);
			}
		}
		return locateRouteList;
	}

	@Override
	public void clear() {
		curatorFrameworkClient.remove("/");
	}

	@Override
	public void updateEnable(ZuulRouteEntity entity) {
		String resultStr = curatorFrameworkClient.get("/" + entity.getId());
		if(!StringUtils.isEmpty(resultStr)){
			ZuulRouteEntity result = JSON.parseObject(resultStr, ZuulRouteEntity.class);
			result.setEnable(entity.isEnable());
			save(result);
		}
	}

	@Override
	public int totalCount() {
		return curatorFrameworkClient.getNumChildren("/");
	}

	@Override
	public int enableCount() {
		ZuulRouteEntity entity = new ZuulRouteEntity();
		entity.setEnable(true);
		List<ZuulRouteEntity> list = list(entity);
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

	@Override
	public void change(String namespace) {
		curatorFrameworkClient.getCuratorFramework().usingNamespace(namespace);
	}

	@Override
	public String count() {
		int totalCount = curatorFrameworkClient.getNumChildren("/");
		int enableCount = 0;
		if(totalCount > 0){
			List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
			for(String item : keys){
				String value = curatorFrameworkClient.get("/" + item);
				if(!StringUtils.isEmpty(value)){
					ZuulRouteEntity one = JSON.parseObject(value, ZuulRouteEntity.class);
					if(one.isEnable()){
						enableCount ++;
					}
				}
			}
		}
		return enableCount + "/" + totalCount;
	}
	
}
