package com.itopener.tellist.conditions;

import com.itopener.framework.base.BaseCondition;
import com.itopener.tellist.model.TelList;

/**
 * @desc TelList查询条件辅助类
 * @author fuwei.deng
 * @date 2017-06-12 17:33:11
 */
public class TelListCondition extends TelList implements BaseCondition {
	
	/** */
	private static final long serialVersionUID = 943866958981162715L;

	/** 页数*/
	private int page = 1;
	
	/** 每页数量*/
	private int size = 20;
	
	/** 排序*/
	private String orderBy;

	/** 姓名--模糊匹配*/
	private String likeName;

	/** 电话--模糊匹配*/
	private String likeTelephone;

	/** 手机--模糊匹配*/
	private String likeMobile;
	
	public int getPage() {
		return page;
	}

	public TelListCondition setPage(int page) {
		this.page = page;
		return this;
	}

	public int getSize() {
		return size;
	}

	public TelListCondition setSize(int size) {
		this.size = size;
		return this;
	}

	public TelListCondition setLikeName(String likeName) {
		this.likeName = likeName;
		return this;
	}

	public String getLikeName() {
		return likeName;
	}

	public TelListCondition setLikeTelephone(String likeTelephone) {
		this.likeTelephone = likeTelephone;
		return this;
	}

	public String getLikeTelephone() {
		return likeTelephone;
	}

	public TelListCondition setLikeMobile(String likeMobile) {
		this.likeMobile = likeMobile;
		return this;
	}

	public String getLikeMobile() {
		return likeMobile;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public TelListCondition setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}
}