/**  
 * Project Name:msxf-tools-appender-kafka-logback 
 * File Name:MessageFormatter.java  
 * Package Name:com.msxf.tools.appender.kafka.logback  
 * Date:2017年5月17日下午6:21:22 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.log.appender.kafka.logback.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by fuwei.deng on 2017年5月17日.
 */
public class MessageFormatter implements Formatter {

	@Override
	public String format(ILoggingEvent event) {
		return event.getFormattedMessage();
	}

}
