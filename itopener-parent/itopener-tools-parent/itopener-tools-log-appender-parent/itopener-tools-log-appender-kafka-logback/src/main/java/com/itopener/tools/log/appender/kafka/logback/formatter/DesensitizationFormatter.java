/**  
 * Project Name:msxf-tools-appender-kafka-logback 
 * File Name:DesensitizationFormatter.java  
 * Package Name:com.msxf.tools.appender.kafka.logback.formatter  
 * Date:2017年5月18日下午1:28:58 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.log.appender.kafka.logback.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

import com.itopener.tools.log.appender.kafka.logback.utils.DesensitizationUtil;

/**
 * Created by fuwei.deng on 2017年5月18日.
 */
public class DesensitizationFormatter implements Formatter {

	@Override
	public String format(ILoggingEvent event) {
		return DesensitizationUtil.filter(event.getFormattedMessage());
	}

}
