package com.itopener.tms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itopener.framework.base.BaseDao;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Configuration
public class DruidConfiguration {
	
	@Bean
	public BaseDao baseDao(){
		return new BaseDao();
	}
}
