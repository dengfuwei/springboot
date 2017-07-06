package com.itopener.tools.zuul.route.admin.dao;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.itopener.framework.base.BaseDao;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteRuleEntityCondition;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteRuleEntity;

public class ZuulRouteRuleEntityDao {

	private final String NAMESPACE = "com.itopener.tools.zuul.route.admin.mapper.ZuulRouteRuleEntityMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(ZuulRouteRuleEntityCondition condition) {
		return baseDao.insert(NAMESPACE + "insert", condition);
	}

	public List<ZuulRouteRuleEntity> selectList(ZuulRouteRuleEntityCondition condition) {
		PageHelper.startPage(1, 9999, false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<ZuulRouteRuleEntity> selectPage(ZuulRouteRuleEntityCondition condition) {
		PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(ZuulRouteRuleEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}
	
	public int count(ZuulRouteRuleEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "count", condition);
	}
	
	public int selectCountById(ZuulRouteRuleEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCountById", condition);
	}

	public ZuulRouteRuleEntity selectOne(ZuulRouteRuleEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}
	
	public int update(ZuulRouteRuleEntityCondition condition) {
		return baseDao.update(NAMESPACE + "update", condition);
	}
	
	public int updateEnable(ZuulRouteRuleEntityCondition condition) {
		return baseDao.update(NAMESPACE + "updateEnable", condition);
	}
	
	public int delete(ZuulRouteRuleEntityCondition condition) {
		return baseDao.update(NAMESPACE + "delete", condition);
	}
	
	public int clear(ZuulRouteRuleEntityCondition condition) {
		return baseDao.update(NAMESPACE + "clear", condition);
	}

}