package com.itopener.demo.zipkin3.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午4:55:27
 * @version 1.0.0
 */
@Configuration
public class RestTemplateClientConfiguration {

	/**
	 * 1.使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
	 * 2.restTemplate如果不依赖有json messageConverter的包（如：jackson），会出现消息头为text/html无法转换成对象的异常，
	 * 原因是因为restTemplate检查有jackson的messageConverters会自动加载（详见RestTemplate.class构造方法），
	 * restTemplate请求的时候会将messageConverters里的mediaType拼装后放入http消息头的contentType（详见RestTemplate.doWithRequest方法）
	 * 如果messageConverters里的mediaType拼装后为空，则会按照text/html来处理，导致转换成对象出现异常
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		// FastJsonHttpMessageConverter4默认的mediaType是*/*，restTemplate请求不允许请求头信息中的ContentType为*，所以需要修改mediaType
		FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4 = new FastJsonHttpMessageConverter4();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		// fastJsonHttpMessageConverter4.getSupportedMediaTypes()方法获取的list不允许修改，所以只能使用set方法进行修改
		fastJsonHttpMessageConverter4.setSupportedMediaTypes(supportedMediaTypes);
		// 设置messageConverters，此方法会删掉原来的messageConverters，即只保留手动设置的messageConverters，并且是新创建restTemplateBuilder对象，所以需要接收
		restTemplateBuilder = restTemplateBuilder.messageConverters(fastJsonHttpMessageConverter4);
		return restTemplateBuilder.build();
	}
}
