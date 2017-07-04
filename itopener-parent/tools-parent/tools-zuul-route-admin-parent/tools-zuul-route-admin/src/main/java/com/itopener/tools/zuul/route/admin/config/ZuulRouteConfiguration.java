package com.itopener.tools.zuul.route.admin.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.itopener.framework.base.BaseDao;
import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

@Configuration
@EnableConfigurationProperties({ZuulZookeeperRouteProperties.class, ZuulRedisRouteProperties.class, ZuulDBRouteProperties.class})
public class ZuulRouteConfiguration {

	@Bean
	public BaseDao baseDao(){
		return new BaseDao();
	}
	
	@Bean(initMethod = "init")
	public CuratorFrameworkClient curatorFrameworkClient(){
		return new CuratorFrameworkClient();
	}
	
	@Bean("redisTemplate")
	public RedisTemplate<String, ZuulRouteEntity> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, ZuulRouteEntity> redisTmplate = new RedisTemplate<String, ZuulRouteEntity>();
		redisTmplate.setConnectionFactory(redisConnectionFactory);
		redisTmplate.setKeySerializer(new StringRedisSerializer());
		redisTmplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTmplate;
	}
	
	@Bean
	public ZuulRouteHelper zuulRouteHelper(List<IZuulRouteService> zuulRouteServiceList){
		ZuulRouteHelper zuulRouteHelper = new ZuulRouteHelper();
		zuulRouteHelper.setZuulRouteService(new HashMap<String, IZuulRouteService>());
		for(IZuulRouteService item : zuulRouteServiceList){
			zuulRouteHelper.getZuulRouteService().put(item.key(), item);
		}
		return zuulRouteHelper;
	}
}
