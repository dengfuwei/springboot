package com.itopener.demo.rabbitmq.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;

import com.rabbitmq.client.Channel;

public class RabbitMQListenerConfiguration2 {

	@Bean
	public Queue queue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", RabbitMQConsumerConstant.DEAD_EXCHANGE_ITOPENER);
		args.put("x-dead-letter-routing-key", RabbitMQConsumerConstant.DEAD_ROUTINGKEY_ITOPENER);
		Queue queue = new Queue(RabbitMQConsumerConstant.QUEUE_ITOPENER, true, false, false, args);
		return queue;
	}

	@RabbitListener(queues = RabbitMQConsumerConstant.QUEUE_ITOPENER)
	public void process(Channel channel, Message message) throws Exception {
		try{
			//TODO 业务处理
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch(Exception e) {
			// 放入死信队列,basicNack的requque传false，会自动放入绑定的死信队列
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		}
	}
}
