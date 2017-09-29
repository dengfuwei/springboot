package com.itopener.memo.vo;

public class MemoParams {
	
	/** 微信openId*/
	private String openId;

	/** 微信unionId*/
	private String unionId;
	
	/** 备忘事项*/
	private String eventName;

	/** 提醒周期*/
	private int cycle;

	/** 农历/阳历*/
	private int calendarType;
	
	/** 最近一次提醒日期*/
	private String latelyRemindDate;
	
	/** 最近一次提醒时间*/
	private String latelyRemindTime;
	
	/** 描述*/
	private String desc;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public int getCalendarType() {
		return calendarType;
	}

	public void setCalendarType(int calendarType) {
		this.calendarType = calendarType;
	}

	public String getLatelyRemindDate() {
		return latelyRemindDate;
	}

	public void setLatelyRemindDate(String latelyRemindDate) {
		this.latelyRemindDate = latelyRemindDate;
	}

	public String getLatelyRemindTime() {
		return latelyRemindTime;
	}

	public void setLatelyRemindTime(String latelyRemindTime) {
		this.latelyRemindTime = latelyRemindTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
