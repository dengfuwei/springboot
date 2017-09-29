package com.itopener.memo.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.itopener.framework.interceptors.PerformanceInterceptor;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(new FastJsonHttpMessageConverter4());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("add PerformanceInterceptor");
		registry.addInterceptor(new PerformanceInterceptor());
	}

}
