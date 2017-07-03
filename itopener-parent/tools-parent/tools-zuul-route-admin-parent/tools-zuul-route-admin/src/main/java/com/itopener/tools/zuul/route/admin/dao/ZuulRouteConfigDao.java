package com.itopener.tools.zuul.route.admin.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.itopener.framework.base.BaseDao;
import com.itopener.tools.zuul.route.admin.model.ZuulRouteConfig;
import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteConfigCondition;

@Repository
public class ZuulRouteConfigDao {

	private final String NAMESPACE = "com.itopener.tools.zuul.route.admin.mapper.ZuulRouteConfigMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(ZuulRouteConfigCondition condition) {
		return baseDao.insert(NAMESPACE + "insert", condition);
	}

	public List<ZuulRouteConfig> selectList(ZuulRouteConfigCondition condition) {
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<ZuulRouteConfig> selectPage(ZuulRouteConfigCondition condition) {
		PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(ZuulRouteConfigCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}

	public ZuulRouteConfig selectOne(ZuulRouteConfigCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}
	
	public int update(ZuulRouteConfigCondition condition) {
		return baseDao.update(NAMESPACE + "update", condition);
	}

	public int delete(ZuulRouteConfigCondition condition) {
		return baseDao.update(NAMESPACE + "delete", condition);
	}

}