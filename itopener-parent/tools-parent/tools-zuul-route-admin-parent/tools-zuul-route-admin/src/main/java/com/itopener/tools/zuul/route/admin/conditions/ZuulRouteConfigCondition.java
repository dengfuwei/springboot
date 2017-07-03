package com.itopener.tools.zuul.route.admin.conditions;

import com.itopener.tools.zuul.route.admin.model.ZuulRouteConfig;

/**
 * @desc ZuulRouteConfig查询条件辅助类
 * @author fuwei.deng
 * @date 2017-07-03 19:22:13
 */
public class ZuulRouteConfigCondition extends ZuulRouteConfig {
	
	/** */
	private static final long serialVersionUID = -3152738792893289690L;

	private int pageNum;
	
	private int pageSize;
	
	private String tableName;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}