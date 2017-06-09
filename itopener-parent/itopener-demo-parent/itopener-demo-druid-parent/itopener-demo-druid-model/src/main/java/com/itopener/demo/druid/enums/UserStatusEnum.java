package com.itopener.demo.druid.enums;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public enum UserStatusEnum {

	NORMAL((byte)1, "正常"),
	INVALID((byte)2, "禁用");
	
	UserStatusEnum(byte code, String name){
		this.code = code;
		this.name = name;
	}
	
	private byte code;
	
	private String name;
	
	public byte getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
