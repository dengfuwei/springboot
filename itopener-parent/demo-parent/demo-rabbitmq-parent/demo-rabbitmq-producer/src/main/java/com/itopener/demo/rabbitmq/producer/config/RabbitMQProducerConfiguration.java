package com.itopener.demo.rabbitmq.producer.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Configuration
public class RabbitMQProducerConfiguration {

	/**
	 * @description 动态声明queue、exchange、routing
	 * @author fuwei.deng
	 * @date 2017年7月19日 下午4:18:33
	 * @version 1.0.0
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		Queue queue = new Queue(RabbitMQProducerConstant.QUEUE_ITOPENER);
		DirectExchange exchange = new DirectExchange(RabbitMQProducerConstant.EXCHANGE_ITOPENER);
		rabbitAdmin.declareQueue(queue);
		rabbitAdmin.declareExchange(exchange);
		rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(RabbitMQProducerConstant.ROUTINGKEY_ITOPENER));
		return rabbitAdmin;
	}
}
