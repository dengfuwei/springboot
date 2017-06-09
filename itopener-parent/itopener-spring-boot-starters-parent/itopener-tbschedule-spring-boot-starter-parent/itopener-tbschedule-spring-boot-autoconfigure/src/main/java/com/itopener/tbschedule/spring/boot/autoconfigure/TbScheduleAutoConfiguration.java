/**  
 * Project Name:msxf-tbschedule-spring-boot-autoconfigure 
 * File Name:TbScheduleAutoConfiguration.java  
 * Package Name:com.msxf.tbschedule.spring.boot.autoconfigure  
 * Date:2017年4月1日下午5:59:02 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tbschedule.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;

/**  
 * @ClassName:TbScheduleAutoConfiguration <br/> 
 * @Description <br/>
 * @date 2017年4月1日下午5:59:02 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@Configuration
@EnableConfigurationProperties(TbScheduleProperties.class)
public class TbScheduleAutoConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(TbScheduleAutoConfiguration.class);
	
	@Autowired
	private TbScheduleProperties tbScheduleProperties;
	
	@Bean(initMethod="init")
	public TBScheduleManagerFactory tbScheduleManagerFactory(){
		TBScheduleManagerFactory tbScheduleManagerFactory = new TBScheduleManagerFactory();
		tbScheduleManagerFactory.setZkConfig(tbScheduleProperties.getZkConfig());
		logger.info("tbschedule manager factory init success.");
		return tbScheduleManagerFactory;
	}
	
}
