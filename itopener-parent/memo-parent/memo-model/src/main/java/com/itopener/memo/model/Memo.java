package com.itopener.memo.model;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @desc 
 * @author fuwei.deng
 * @date 2017-09-09 00:21:51
 */
public class Memo implements Serializable {

	/** SerialVersionUID*/
	private static final long serialVersionUID = -6503593542246083937L;

	/** 主键*/
	private long id;

	/** 用户id*/
	private long userId;

	/** 备忘事项*/
	private String eventName;

	/** 提醒周期*/
	private int cycle;

	/** 农历/阳历*/
	private int calendarType;

	/** 下次提醒时间*/
	private Timestamp nextTime;

	/** 创建时间*/
	private Timestamp createTime;

	/** 最后修改时间*/
	private Timestamp updateTime;

	public long getId() {
		return id;
	}

	public Memo setId(long id) {
		this.id = id;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public Memo setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public String getEventName() {
		return eventName;
	}

	public Memo setEventName(String eventName) {
		this.eventName = eventName;
		return this;
	}

	public int getCycle() {
		return cycle;
	}

	public Memo setCycle(int cycle) {
		this.cycle = cycle;
		return this;
	}

	public int getCalendarType() {
		return calendarType;
	}

	public Memo setCalendarType(int calendarType) {
		this.calendarType = calendarType;
		return this;
	}

	public Timestamp getNextTime() {
		return nextTime;
	}

	public Memo setNextTime(Timestamp nextTime) {
		this.nextTime = nextTime;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Memo setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public Memo setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}
}