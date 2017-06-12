package com.itopener.tellist.model;

import java.sql.Timestamp;

/**
 * @desc 分部
 * @author fuwei.deng
 * @date 2017-06-12 18:04:17
 */
public class Dept {

	/** ID*/
	private long id;

	/** 序号*/
	private long serialNumber;

	/** 名称*/
	private String name;

	/** 父级ID*/
	private long parentId;

	/** 创建时间*/
	private Timestamp createTime;

	/** 创建人ID*/
	private long createUserId;

	/** 最后更新时间*/
	private Timestamp updateTime;

	/** 最后更新人ID*/
	private long updateUserId;

	public long getId() {
		return id;
	}

	public Dept setId(long id) {
		this.id = id;
		return this;
	}

	public long getSerialNumber() {
		return serialNumber;
	}

	public Dept setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
		return this;
	}

	public String getName() {
		return name;
	}

	public Dept setName(String name) {
		this.name = name;
		return this;
	}

	public long getParentId() {
		return parentId;
	}

	public Dept setParentId(long parentId) {
		this.parentId = parentId;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Dept setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public Dept setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public Dept setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public long getUpdateUserId() {
		return updateUserId;
	}

	public Dept setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
		return this;
	}
}