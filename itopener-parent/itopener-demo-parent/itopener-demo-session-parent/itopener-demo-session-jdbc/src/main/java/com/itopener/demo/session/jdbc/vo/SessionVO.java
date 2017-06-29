package com.itopener.demo.session.jdbc.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class SessionVO implements Serializable {

	/** */
	private static final long serialVersionUID = -2716970007801579130L;

	private long id;
	
	private String name;
	
	private Timestamp time;
	
	private byte state;

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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}
	
}
