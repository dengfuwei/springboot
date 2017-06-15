package com.itopener.tellist.config;

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
import com.itopener.framework.interceptors.AuthorityInterceptor;
import com.itopener.framework.interceptors.LoginRequiredInterceptor;
import com.itopener.framework.interceptors.PerformanceInterceptor;

/**
 * @author fuwei.deng
 * @date 2017年6月11日 下午5:28:09
 * @version 1.0.0
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FastJsonHttpMessageConverter4());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("添加拦截器");
		registry.addInterceptor(new PerformanceInterceptor());
		registry.addInterceptor(new LoginRequiredInterceptor());
		registry.addInterceptor(new AuthorityInterceptor().setSessionUser(new SessionUser()));
	}

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/views/tellistindex.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 
}
