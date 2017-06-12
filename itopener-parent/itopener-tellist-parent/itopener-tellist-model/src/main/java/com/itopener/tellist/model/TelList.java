package com.itopener.tellist.model;

import java.sql.Timestamp;

/**
 * @desc 通讯录
 * @author fuwei.deng
 * @date 2017-06-12 18:04:17
 */
public class TelList {

	/** ID*/
	private long id;

	/** 序号*/
	private long serialNumber;

	/** 用户ID*/
	private long userId;

	/** 姓名*/
	private String name;

	/** 分部ID*/
	private long deptId;

	/** 分部名称*/
	private String deptName;

	/** 手机*/
	private String mobile;

	/** V网短号*/
	private String vnetNumber;

	/** 电话*/
	private String telephone;

	/** 职位*/
	private String position;

	/** 邮箱*/
	private String email;

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

	public TelList setId(long id) {
		this.id = id;
		return this;
	}

	public long getSerialNumber() {
		return serialNumber;
	}

	public TelList setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public TelList setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public String getName() {
		return name;
	}

	public TelList setName(String name) {
		this.name = name;
		return this;
	}

	public long getDeptId() {
		return deptId;
	}

	public TelList setDeptId(long deptId) {
		this.deptId = deptId;
		return this;
	}

	public String getDeptName() {
		return deptName;
	}

	public TelList setDeptName(String deptName) {
		this.deptName = deptName;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public TelList setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getVnetNumber() {
		return vnetNumber;
	}

	public TelList setVnetNumber(String vnetNumber) {
		this.vnetNumber = vnetNumber;
		return this;
	}

	public String getTelephone() {
		return telephone;
	}

	public TelList setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public String getPosition() {
		return position;
	}

	public TelList setPosition(String position) {
		this.position = position;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public TelList setEmail(String email) {
		this.email = email;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public TelList setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public TelList setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public TelList setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public long getUpdateUserId() {
		return updateUserId;
	}

	public TelList setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
		return this;
	}
}