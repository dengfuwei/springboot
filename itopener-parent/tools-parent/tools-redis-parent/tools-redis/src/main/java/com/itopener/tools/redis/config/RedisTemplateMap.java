package com.itopener.tools.redis.config;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateMap {

	private Map<Integer, RedisTemplate<?, ?>> redisTemplateMap;
	
	public RedisTemplate<?, ?> getRedisTemplate(Integer serializer) {
		if(redisTemplateMap == null) {
			return null;
		}
		return redisTemplateMap.get(serializer);
	}

	public Map<Integer, RedisTemplate<?, ?>> getRedisTemplateMap() {
		return redisTemplateMap;
	}

	public void setRedisTemplateMap(Map<Integer, RedisTemplate<?, ?>> redisTemplateMap) {
		this.redisTemplateMap = redisTemplateMap;
	}
	
}
