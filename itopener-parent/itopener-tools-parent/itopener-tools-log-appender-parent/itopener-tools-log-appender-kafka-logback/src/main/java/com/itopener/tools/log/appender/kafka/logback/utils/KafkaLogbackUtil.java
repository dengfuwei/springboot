/**  
 * Project Name:msxf-tools-appender-kafka-logback 
 * File Name:ResourcePathUtil.java  
 * Package Name:com.msxf.tools.appender.kafka.logback  
 * Date:2017年5月18日上午10:13:33 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.log.appender.kafka.logback.utils;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by fuwei.deng on 2017年5月18日.
 */
public class KafkaLogbackUtil {
	
	private static KafkaTemplate<Object, Object> kafkaTemplate;;

	public static String resourceToPath(Resource resource) {
		try {
			return resource.getFile().getAbsolutePath();
		}
		catch (IOException ex) {
			throw new IllegalStateException(
					"Resource '" + resource + "' must be on a file system", ex);
		}
	}
	
	public static KafkaTemplate<Object, Object> getKafkaTemplate(){
		return kafkaTemplate;
	}
	
	public static void setKafkaTemplate(KafkaTemplate<Object, Object> kafkaTemplateParam){
		kafkaTemplate = kafkaTemplateParam;
	}
}
