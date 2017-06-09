/**  
 * Project Name:msxf-tools-appender-kafka-logback 
 * File Name:Formatter.java  
 * Package Name:com.msxf.tools.appender.kafka.logback  
 * Date:2017年5月17日下午6:20:41 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.log.appender.kafka.logback.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by fuwei.deng on 2017年5月17日.
 */
public interface Formatter {

	String format(ILoggingEvent event);
}
