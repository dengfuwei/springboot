package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.zk.CuratorFrameworkClient;
import com.itopener.tools.zuul.route.admin.config.zk.ZuulRouteZookeeperProperties;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

public class ZuulRouteZookeeperService implements IZuulRouteService {
	
	@Autowired
	private CuratorFrameworkClient curatorFrameworkClient;
	
	@Autowired
	private ZuulRouteZookeeperProperties zuulRouteZookeeperProperties;
	
	@Resource
	private HttpServletRequest request;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_ZK;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
		namespace();
		
		curatorFrameworkClient.persist("/" + entity.getId(), JSON.toJSONString(entity));
	}

	@Override
	public void delete(String id) {
		namespace();
		
		curatorFrameworkClient.remove("/" + id);
	}

	@Override
	public List<ZuulRouteEntity> list(ZuulRouteEntityCondition condition) {
		namespace();
		
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		locateRouteList = new ArrayList<ZuulRouteEntity>();
		List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
		for(String item : keys){
			String value = curatorFrameworkClient.get("/" + item);
			if(!StringUtils.isEmpty(value)){
				ZuulRouteEntity one = JSON.parseObject(value, ZuulRouteEntity.class);
				if(condition.isEnable() != one.isEnable()){
					continue;
				}
				if(!StringUtils.isEmpty(condition.getLikeId()) && !one.getId().startsWith(condition.getLikeId())){
					continue;
				}
				if(!StringUtils.isEmpty(condition.getLikeRouterName()) && !one.getRouterName().startsWith(condition.getLikeRouterName())){
					continue;
				}
				
				locateRouteList.add(one);
			}
		}
		return locateRouteList;
	}

	@Override
	public List<ZuulRouteEntity> listAll() {
		namespace();
		
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		locateRouteList = new ArrayList<ZuulRouteEntity>();
		List<String> keys = curatorFrameworkClient.getChildrenKeys("/");
		for(String item : keys){
			String value = curatorFrameworkClient.get("/" + item);
			if(!StringUtils.isEmpty(value)){
				ZuulRouteEntity one = JSON.parseObject(value, ZuulRouteEntity.class);
				locateRouteList.add(one);
			}
		}
		return locateRouteList;
	}

	@Override
	public void clear() {
		namespace();
		
		curatorFrameworkClient.remove("/");
	}

	@Override
	public void updateEnable(ZuulRouteEntity entity) {
		namespace();
		
		String resultStr = curatorFrameworkClient.get("/" + entity.getId());
		if(!StringUtils.isEmpty(resultStr)){
			ZuulRouteEntity result = JSON.parseObject(resultStr, ZuulRouteEntity.class);
			result.setEnable(entity.isEnable());
			save(result);
		}
	}

	@Override
	public int totalCount() {
		namespace();
		
		return curatorFrameworkClient.getNumChildren("/");
	}

	@Override
	public int enableCount() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setEnable(true);
		List<ZuulRouteEntity> list = list(condition);
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

	@Override
	public void change(String namespace) {
		request.getSession().setAttribute("namespace", namespace);
//		curatorFrameworkClient.getCuratorFramework().usingNamespace(namespace);
	}

	@Override
	public String count() {
		namespace();
		
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
	
	private void namespace(){
		String namespace = (String) request.getSession().getAttribute("namespace");
		namespace = StringUtils.isEmpty(namespace) ? zuulRouteZookeeperProperties.getZk().getNamespace() : namespace;
		if(!curatorFrameworkClient.getCuratorFramework().getNamespace().equals(namespace)){
			curatorFrameworkClient.setCuratorFramework(curatorFrameworkClient.getCuratorFramework().usingNamespace(namespace));
		}
	}
}
