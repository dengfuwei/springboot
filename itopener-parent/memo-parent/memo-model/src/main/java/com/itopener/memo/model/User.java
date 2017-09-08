package com.itopener.memo.model;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @desc 
 * @author fuwei.deng
 * @date 2017-09-09 00:21:51
 */
public class User implements Serializable {

	/** SerialVersionUID*/
	private static final long serialVersionUID = 5815985154093508519L;

	/** 主键*/
	private long id;

	/** 微信openId*/
	private String openId;

	/** 微信unionId*/
	private String unionId;

	/** 用户信息，json格式数据*/
	private String userInfo;

	/** 创建时间*/
	private Timestamp createTime;

	/** 最后修改时间*/
	private Timestamp updateTime;

	public long getId() {
		return id;
	}

	public User setId(long id) {
		this.id = id;
		return this;
	}

	public String getOpenId() {
		return openId;
	}

	public User setOpenId(String openId) {
		this.openId = openId;
		return this;
	}

	public String getUnionId() {
		return unionId;
	}

	public User setUnionId(String unionId) {
		this.unionId = unionId;
		return this;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public User setUserInfo(String userInfo) {
		this.userInfo = userInfo;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public User setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public User setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}
}