package com.itopener.tools.zuul.route.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.itopener.tools.zuul.route.admin.conditions.ZuulRouteEntityCondition;
import com.itopener.tools.zuul.route.admin.config.ZuulRouteConstant;
import com.itopener.tools.zuul.route.admin.config.db.ZuulRouteDatabaseProperties;
import com.itopener.tools.zuul.route.admin.dao.ZuulRouteEntityDao;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

public class ZuulRouteDatabaseService implements IZuulRouteService {
	
	@Resource
	private ZuulRouteEntityDao zuulRouteEntityDao;
	
	@Autowired
	private ZuulRouteDatabaseProperties zuulRouteDatabaseProperties;
	
	@Resource
	private HttpServletRequest request;

	@Override
	public String key() {
		return ZuulRouteConstant.KEY_DB;
	}

	@Override
	public void save(ZuulRouteEntity entity) {
		ZuulRouteEntityCondition condition = handle(entity);
		condition.setTableName(getTableName());
		int count = zuulRouteEntityDao.selectCountById(condition);
		if(count > 0){
			zuulRouteEntityDao.update(condition);
		} else{
			zuulRouteEntityDao.insert(condition);
		}
	}
	
	private ZuulRouteEntityCondition handle(ZuulRouteEntity entity){
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		if(entity == null){
			return condition;
		}
		BeanUtils.copyProperties(entity, condition);
		return condition;
	}
	
	@Override
	public void delete(String id) {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		condition.setId(id);
		zuulRouteEntityDao.delete(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ZuulRouteEntity> list(ZuulRouteEntityCondition condition) {
		condition.setTableName(getTableName());
		return zuulRouteEntityDao.selectList(condition);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ZuulRouteEntity> listAll() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		return zuulRouteEntityDao.listAll(condition);
	}

	@Override
	public void clear() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		zuulRouteEntityDao.clear(condition);
	}

	@Override
	public void updateEnable(ZuulRouteEntity entity) {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		condition.setId(entity.getId());
		condition.setEnable(entity.isEnable());
		zuulRouteEntityDao.updateEnable(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public int totalCount() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		return zuulRouteEntityDao.count(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public int enableCount() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		condition.setEnable(true);
		return zuulRouteEntityDao.selectCount(condition);
	}

	@Override
	public void change(String namespace) {
		request.getSession().setAttribute("tableName", namespace);
//		zuulRouteDatabaseProperties.setTableName(namespace);
	}

	@Override
	public String count() {
		ZuulRouteEntityCondition condition = new ZuulRouteEntityCondition();
		condition.setTableName(getTableName());
		int totalCount = zuulRouteEntityDao.count(condition);
		condition.setEnable(true);
		int enableCount = zuulRouteEntityDao.selectCount(condition);
		return enableCount + "/" + totalCount;
	}

	private String getTableName(){
		String tableName = (String) request.getSession().getAttribute("tableName");
		return StringUtils.isEmpty(tableName) ? zuulRouteDatabaseProperties.getTableName() : tableName;
	}
}
