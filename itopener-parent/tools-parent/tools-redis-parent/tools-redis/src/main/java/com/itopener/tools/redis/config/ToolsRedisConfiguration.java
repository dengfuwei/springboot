package com.itopener.tools.redis.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Configuration
public class ToolsRedisConfiguration {

//	@Bean("redisTemplate")
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//		RedisTemplate<String, Object> redisTmplate = new RedisTemplate<String, Object>();
//		redisTmplate.setConnectionFactory(redisConnectionFactory);
//		redisTmplate.setKeySerializer(new StringRedisSerializer());
//		redisTmplate.setHashKeySerializer(new StringRedisSerializer());
//		return redisTmplate;
//	}
	
	@Bean
	public RedisTemplateMap redisTemplateMap(RedisTemplate<Object, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
		RedisTemplateMap redisTemplateMap = new RedisTemplateMap();
		redisTemplateMap.setRedisTemplateMap(new ConcurrentHashMap<>());
		redisTemplateMap.getRedisTemplateMap().put(RedisSerializerEnum.DEFAULT.getCode(), redisTemplate);
		redisTemplateMap.getRedisTemplateMap().put(RedisSerializerEnum.STRING.getCode(), stringRedisTemplate);
		return redisTemplateMap;
	}
}
