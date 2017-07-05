package com.itopener.tools.zuul.route.admin.config.db;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itopener.framework.base.BaseDao;
import com.itopener.tools.zuul.route.admin.dao.ZuulRouteEntityDao;
import com.itopener.tools.zuul.route.admin.service.impl.ZuulRouteDatabaseService;

@Configuration
@ConditionalOnProperty("spring.datasource.url")
@ImportAutoConfiguration(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(ZuulRouteDatabaseProperties.class)
public class ZuulRouteDatabaseConfiguration {

	@Bean
	public BaseDao baseDao(){
		return new BaseDao();
	}
	
	@Bean
	public ZuulRouteDatabaseService zuulRouteDatabaseService(){
		return new ZuulRouteDatabaseService();
	}
	
	@Bean
	public ZuulRouteEntityDao zuulRouteEntityDao(){
		return new ZuulRouteEntityDao();
	}
}
