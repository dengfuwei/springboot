package com.itopener.tellist.enums;

public enum UserRoleEnum {
	
	NORMAL((byte)1, "普通"),
	MANAGER((byte)2, "管理员"),
	SUPER_MANAGER((byte)3, "超级管理员");

	private byte code;
	
	private String name;
	
	UserRoleEnum(byte code, String name){
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
