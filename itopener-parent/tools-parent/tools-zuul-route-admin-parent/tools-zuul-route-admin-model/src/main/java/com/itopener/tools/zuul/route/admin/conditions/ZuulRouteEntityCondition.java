package com.itopener.tools.zuul.route.admin.conditions;

import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

/**
 * @desc ZuulRouteConfig查询条件辅助类
 * @author fuwei.deng
 * @date 2017-07-03 19:22:13
 */
public class ZuulRouteEntityCondition extends ZuulRouteEntity {
	
	/** */
	private static final long serialVersionUID = -3152738792893289690L;

	private int pageNum;
	
	private int pageSize;
	
	private String tableName;
	
	/** 模糊匹配ID*/
	private String likeId;
	
	/** 模糊匹配路由名称*/
	private String likeRouterName;

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

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public String getLikeRouterName() {
		return likeRouterName;
	}

	public void setLikeRouterName(String likeRouterName) {
		this.likeRouterName = likeRouterName;
	}
	
}