package com.itopener.tools.log.appender.kafka.logback.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

import com.itopener.tools.log.appender.kafka.logback.utils.DesensitizationUtil;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class DesensitizationFormatter implements Formatter {

	@Override
	public String format(ILoggingEvent event) {
		return DesensitizationUtil.filter(event.getFormattedMessage());
	}

}
