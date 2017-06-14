package com.itopener.demo.rabbitmq.consumer.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
public class RabbitConfigure {
	
//	@Bean
//	public Queue queue(){
//		return new Queue(NormalConstant.DEFAULT_ROUTINGKEY);
//	}
//
//	@Bean
//	public Binding binding() {
//		return BindingBuilder.bind(new Queue(NormalConstant.QUEUE_ACTIVITY)).to(new DirectExchange(NormalConstant.EXCHANGE_ACTIVITY)).with(NormalConstant.ROUTINGKEY_ACTIVITY);
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
