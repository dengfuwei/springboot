package com.itopener.tools.log.appender.kafka.logback.utils;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
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
