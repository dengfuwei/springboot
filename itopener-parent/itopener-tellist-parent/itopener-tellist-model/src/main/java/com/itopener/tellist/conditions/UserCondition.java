package com.itopener.tellist.conditions;

import com.itopener.framework.base.BaseCondition;
import com.itopener.tellist.model.User;

/**
 * @desc User查询条件辅助类
 * @author fuwei.deng
 * @date 2017-06-12 17:33:11
 */
public class UserCondition extends User implements BaseCondition {

	/** */
	private static final long serialVersionUID = 221841485967414826L;
	
	/** 页数*/
	private int page;
	
	/** 每页数量*/
	private int size;
	
	/** 排序*/
	private String orderBy;

	public int getPage() {
		return page;
	}

	public UserCondition setPage(int page) {
		this.page = page;
		return this;
	}

	public int getSize() {
		return size;
	}

	public UserCondition setSize(int size) {
		this.size = size;
		return this;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public UserCondition setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}
}