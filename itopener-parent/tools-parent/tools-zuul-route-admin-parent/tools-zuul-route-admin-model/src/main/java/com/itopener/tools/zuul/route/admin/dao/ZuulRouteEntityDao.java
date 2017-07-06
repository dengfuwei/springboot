package com.itopener.tools.zuul.route.admin.dao;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.itopener.framework.base.BaseDao;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteEntityCondition;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

public class ZuulRouteEntityDao {

	private final String NAMESPACE = "com.itopener.tools.zuul.route.admin.mapper.ZuulRouteEntityMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(ZuulRouteEntityCondition condition) {
		return baseDao.insert(NAMESPACE + "insert", condition);
	}

	public List<ZuulRouteEntity> selectList(ZuulRouteEntityCondition condition) {
		PageHelper.startPage(1, 9999, false);
//		PageHelper.clearPage();
		return baseDao.selectList(NAMESPACE + "select", condition);
	}
	
	public List<ZuulRouteEntity> listAll(ZuulRouteEntityCondition condition) {
		PageHelper.startPage(1, 9999, false);
		return baseDao.selectList(NAMESPACE + "listAll", condition);
	}

	public List<ZuulRouteEntity> selectPage(ZuulRouteEntityCondition condition) {
		PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(ZuulRouteEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}
	
	public int count(ZuulRouteEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "count", condition);
	}
	
	public int selectCountById(ZuulRouteEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCountById", condition);
	}

	public ZuulRouteEntity selectOne(ZuulRouteEntityCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}
	
	public int update(ZuulRouteEntityCondition condition) {
		return baseDao.update(NAMESPACE + "update", condition);
	}
	
	public int updateEnable(ZuulRouteEntityCondition condition) {
		return baseDao.update(NAMESPACE + "updateEnable", condition);
	}
	
	public int delete(ZuulRouteEntityCondition condition) {
		return baseDao.update(NAMESPACE + "delete", condition);
	}
	
	public int clear(ZuulRouteEntityCondition condition) {
		return baseDao.update(NAMESPACE + "clear", condition);
	}

}