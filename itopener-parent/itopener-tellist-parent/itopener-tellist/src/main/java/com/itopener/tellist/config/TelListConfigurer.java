package com.itopener.tellist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itopener.framework.base.BaseDao;

/**
 * @author fuwei.deng
 * @date 2017年6月11日 下午5:27:59
 * @version 1.0.0
 */
@Configuration
public class TelListConfigurer {

	@Bean
	public BaseDao baseDao(){
		return new BaseDao();
	}
}
