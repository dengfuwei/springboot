package com.itopener.demo.jsonp.fastjson.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserVO implements Serializable {

	/** */
	private static final long serialVersionUID = -4412168370923196334L;

	private long id;
	
	private String name;
	
	private byte state;
	
	private Timestamp createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
