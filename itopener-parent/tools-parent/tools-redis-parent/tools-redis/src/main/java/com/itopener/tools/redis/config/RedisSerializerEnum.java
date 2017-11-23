package com.itopener.tools.redis.config;

public enum RedisSerializerEnum {

	DEFAULT(1, "JdkSerializationRedisSerializer"),
	STRING(2, "StringRedisSerializer");
	
	private int code;
	
	private String name;

	private RedisSerializerEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static boolean exists(int code) {
		for(RedisSerializerEnum item : RedisSerializerEnum.values()) {
			if(item.getCode() == code) {
				return true;
			}
		}
		return false;
	}
	
	public static String getName(int code) {
		for(RedisSerializerEnum item : RedisSerializerEnum.values()) {
			if(item.getCode() == code) {
				return item.getName();
			}
		}
		return null;
	}
}
