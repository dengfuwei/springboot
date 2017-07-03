package com.itopener.tellist.conditions;

import com.itopener.framework.base.BaseCondition;
import com.itopener.tellist.model.Dept;

/**
 * @desc Dept查询条件辅助类
 * @author fuwei.deng
 * @date 2017-06-12 17:33:11
 */
public class DeptCondition extends Dept implements BaseCondition {

	/** */
	private static final long serialVersionUID = -5007308565297797079L;
	
	/** 页数*/
	private int page;
	
	/** 每页数量*/
	private int size;
	
	/** 排序*/
	private String orderBy;

	public int getPage() {
		return page;
	}

	public DeptCondition setPage(int page) {
		this.page = page;
		return this;
	}

	public int getSize() {
		return size;
	}

	public DeptCondition setSize(int size) {
		this.size = size;
		return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public DeptCondition setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
}