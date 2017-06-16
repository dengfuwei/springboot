package com.itopener.demo.mongodb.enums;

public enum RoleStateEnum implements StateEnum {

	NORMAL(CODE_NORMAL, "正常"),
	INVALID(CODE_INVALID, "禁用");
	
	private byte code;
	
	private String name;
	
	RoleStateEnum(byte code, String name) {
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
