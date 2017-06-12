package com.itopener.tellist.model;

import java.sql.Timestamp;

/**
 * @desc 用户
 * @author fuwei.deng
 * @date 2017-06-12 18:04:17
 */
public class User {

	/** ID*/
	private long id;

	/** 登录名*/
	private String loginName;

	/** 登录密码*/
	private String loginPwd;

	/** 状态*/
	private byte state;

	/** 角色*/
	private byte role;

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

	public User setId(long id) {
		this.id = id;
		return this;
	}

	public String getLoginName() {
		return loginName;
	}

	public User setLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public User setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
		return this;
	}

	public byte getState() {
		return state;
	}

	public User setState(byte state) {
		this.state = state;
		return this;
	}

	public byte getRole() {
		return role;
	}

	public User setRole(byte role) {
		this.role = role;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public User setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public User setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public User setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public long getUpdateUserId() {
		return updateUserId;
	}

	public User setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
		return this;
	}
}