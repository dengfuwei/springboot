package com.itopener.demo.rabbitmq.consumer.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Configuration
public class RabbitConfiguration {
	
//	@Bean
//	public Queue queue(){
//		return new Queue(NormalConstant.DEFAULT_ROUTINGKEY);
//	}
//
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
		Queue queue = new Queue(RabbitMQConsumerConstant.QUEUE_ITOPENER);
		DirectExchange exchange = new DirectExchange(RabbitMQConsumerConstant.EXCHANGE_ITOPENER);
		rabbitAdmin.declareQueue(queue);
		rabbitAdmin.declareExchange(exchange);
		rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(RabbitMQConsumerConstant.ROUTINGKEY_ITOPENER));
		return rabbitAdmin;
	}
	
//	@Bean
//	public Binding binding() {
//		return BindingBuilder.bind(new Queue(RabbitMQConsumerConstant.QUEUE_ITOPENER)).to(new DirectExchange(RabbitMQConsumerConstant.EXCHANGE_ITOPENER)).with(RabbitMQConsumerConstant.ROUTINGKEY_ITOPENER);
//	}
	
	@Bean
	public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate){
		return new RepublishMessageRecoverer(rabbitTemplate, "exchange-error", "routingkey-error");
	}
	
	@Bean("rabbitListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
//		factory.setMessageConverter(new Jackson2JsonMessageConverter());
//		factory.setAdviceChain(new MissingMessageIdAdvice(new MapRetryContextCache()));
		return factory;
	}

}
