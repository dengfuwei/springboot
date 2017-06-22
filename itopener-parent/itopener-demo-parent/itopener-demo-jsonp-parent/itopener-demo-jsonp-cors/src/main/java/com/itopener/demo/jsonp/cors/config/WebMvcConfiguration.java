package com.itopener.demo.jsonp.cors.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
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
		// FastJsonHttpMessageConverter4默认的mediaType是*/*，restTemplate请求不允许请求头信息中的ContentType为*，所以需要修改mediaType
		FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4 = new FastJsonHttpMessageConverter4();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		// fastJsonHttpMessageConverter4.getSupportedMediaTypes()方法获取的list不允许修改，所以只能使用set方法进行修改
		fastJsonHttpMessageConverter4.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(fastJsonHttpMessageConverter4);
//		converters.add(new FastJsonHttpMessageConverter4());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("添加拦截器");
		registry.addInterceptor(new PerformanceInterceptor());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
//			.allowCredentials(true)
			.allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString());
	}
	
}
