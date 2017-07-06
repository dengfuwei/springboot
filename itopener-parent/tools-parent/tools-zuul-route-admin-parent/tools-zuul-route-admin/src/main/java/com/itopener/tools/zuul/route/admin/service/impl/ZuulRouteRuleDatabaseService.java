package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteRuleEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.db.ZuulRouteDatabaseProperties;
import com.itopener.tools.zuul.route.admin.dao.ZuulRouteRuleEntityDao;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteRuleService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;

public class ZuulRouteRuleDatabaseService implements IZuulRouteRuleService {
	
	@Resource
	private ZuulRouteRuleEntityDao zuulRouteRuleEntityDao;
	
	@Autowired
	private ZuulRouteDatabaseProperties zuulRouteDatabaseProperties;
	
	@Resource
	private HttpServletRequest request;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_DB;
	}

	@Override
	public void save(ZuulRouteRuleEntity entity) {
		ZuulRouteRuleEntityCondition condition = handle(entity);
		condition.setTableName(getRuleTableName());
		int count = zuulRouteRuleEntityDao.selectCountById(condition);
		if(count > 0){
			zuulRouteRuleEntityDao.update(condition);
		} else{
			zuulRouteRuleEntityDao.insert(condition);
		}
	}
	
	private ZuulRouteRuleEntityCondition handle(ZuulRouteRuleEntity entity){
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		if(entity == null){
			return condition;
		}
		BeanUtils.copyProperties(entity, condition);
		return condition;
	}
	
	@Override
	public void delete(ZuulRouteRuleEntity entity) {
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		condition.setTableName(getRuleTableName());
		condition.setId(entity.getId());
		condition.setRouteId(entity.getRouteId());
		zuulRouteRuleEntityDao.delete(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ZuulRouteRuleEntity> list(ZuulRouteRuleEntityCondition condition) {
		condition.setTableName(getRuleTableName());
		return zuulRouteRuleEntityDao.selectList(condition);
	}

	@Override
	public void clear(ZuulRouteRuleEntity entity) {
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		condition.setTableName(getRuleTableName());
		condition.setRouteId(entity.getRouteId());
		zuulRouteRuleEntityDao.clear(condition);
	}

	@Override
	public void updateEnable(ZuulRouteRuleEntity entity) {
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		condition.setTableName(getRuleTableName());
		condition.setId(entity.getId());
		condition.setRouteId(entity.getRouteId());
		condition.setEnable(entity.isEnable());
		zuulRouteRuleEntityDao.updateEnable(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public int totalCount() {
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		condition.setTableName(getRuleTableName());
		return zuulRouteRuleEntityDao.count(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public int enableCount() {
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		condition.setTableName(getRuleTableName());
		condition.setEnable(true);
		return zuulRouteRuleEntityDao.selectCount(condition);
	}

	@Override
	public void change(String namespace) {
		request.getSession().setAttribute("ruleTableName", namespace);
//		zuulRouteDatabaseProperties.setRuleTableName(namespace);
	}

	@Override
	public String count() {
		ZuulRouteRuleEntityCondition condition = new ZuulRouteRuleEntityCondition();
		condition.setTableName(getRuleTableName());
		int totalCount = zuulRouteRuleEntityDao.count(condition);
		condition.setEnable(true);
		int enableCount = zuulRouteRuleEntityDao.selectCount(condition);
		return enableCount + "/" + totalCount;
	}

	private String getRuleTableName(){
		String ruleTableName = (String) request.getSession().getAttribute("ruleTableName");
		return StringUtils.isEmpty(ruleTableName) ? zuulRouteDatabaseProperties.getRuleTableName() : ruleTableName;
	}
}
