package com.itopener.demo.druid.conditions;

import com.itopener.demo.druid.model.User;
import com.itopener.framework.base.BaseCondition;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class UserCondition extends User implements BaseCondition {
	
	/** */
	private static final long serialVersionUID = 1292720196300671560L;

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