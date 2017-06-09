package com.itopener.tools.log.appender.kafka.logback.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public interface Formatter {

	String format(ILoggingEvent event);
}
