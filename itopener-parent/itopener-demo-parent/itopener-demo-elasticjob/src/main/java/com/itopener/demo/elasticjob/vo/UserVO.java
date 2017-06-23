package com.itopener.demo.elasticjob.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserVO implements Serializable {

	/** */
	private static final long serialVersionUID = -6991880706066761450L;

	private long id;
	
	private String name;
	
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
