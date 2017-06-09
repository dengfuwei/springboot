package com.itopener.demo.kafka.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Configuration
public class KafkaListenerConfigurer {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaListenerConfigurer.class);

	@KafkaListener(topics = KafkaConsumerConstant.KAFKA_TOPIC_ITOPENER_DEMO)
	public void process(String content) {
		logger.info("kafka received msg : " + content);
	}
	
	@KafkaListener(topics = KafkaConsumerConstant.KAFKA_TOPIC_LOGBACK_DEMO)
	public void processLogback(String content) {
		logger.info("kafka received log : " + content);
	}
	
}
