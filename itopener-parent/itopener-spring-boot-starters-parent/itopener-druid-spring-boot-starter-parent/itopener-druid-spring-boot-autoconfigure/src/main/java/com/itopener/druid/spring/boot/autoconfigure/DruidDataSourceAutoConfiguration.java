/**  
 * Project Name:msxf-activity-web 
 * File Name:DruidConfig.java  
 * Package Name:com.msxf.activity.config  
 * Date:2017年3月27日下午6:31:22 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.druid.spring.boot.autoconfigure;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**  
 * @ClassName:DruidConfig <br/> 
 * @Description <br/>
 * @date 2017年3月27日下午6:31:22 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@Configuration
public class DruidDataSourceAutoConfiguration {
	
	@Primary
	@Bean(initMethod="init", destroyMethod="close")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(){
		return new DruidDataSource();
	}
}
