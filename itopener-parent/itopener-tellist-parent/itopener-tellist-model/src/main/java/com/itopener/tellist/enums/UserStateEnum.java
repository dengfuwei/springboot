package com.itopener.tellist.enums;

public enum UserStateEnum {
	
	NORMAL((byte)1, "正常"),
	INVALID((byte)2, "禁用");

	private byte code;
	
	private String name;
	
	UserStateEnum(byte code, String name){
		this.code = code;
		this.name = name;
	}

	public byte getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
}
