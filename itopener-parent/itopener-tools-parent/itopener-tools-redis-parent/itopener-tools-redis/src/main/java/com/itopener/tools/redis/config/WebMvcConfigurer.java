/**  
 * Project Name:msxf-activity-web 
 * File Name:WebMvcConfigurer.java  
 * Package Name:com.msxf.activity.config  
 * Date:2017年3月17日下午4:51:32 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.redis.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.itopener.framework.interceptors.PerformanceInterceptor;

/**  
 * @ClassName:WebMvcConfigurer <br/> 
 * @Description 增加 interceptors, formatters, view controllers 等配置<br/>
 * @date 2017年3月17日下午4:51:32 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FastJsonHttpMessageConverter4());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("添加拦截器");
		registry.addInterceptor(new PerformanceInterceptor());
	}

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/views/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 
}
