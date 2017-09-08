package com.itopener.memo.conditions;

import com.itopener.memo.model.Memo;

import com.itopener.framework.base.BaseCondition;

/**
 * @desc Memo查询条件辅助类
 * @author fuwei.deng
 * @date 2017-09-09 00:21:51
 */
public class MemoCondition extends Memo implements BaseCondition {

	/** SerialVersionUID*/
	private static final long serialVersionUID = 1511547777563529295L;

	/** 页数*/
	private int page;
	
	/** 每页数量*/
	private int size;
	
	/** 排序*/
	private String orderBy;

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public BaseCondition setPage(int page) {
		this.page = page;
		return this;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public BaseCondition setSize(int size) {
		this.size = size;
		return this;
	}

	@Override
	public String getOrderBy() {
		return orderBy;
	}

	@Override
	public BaseCondition setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}
}