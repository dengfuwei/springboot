package com.itopener.demo.rabbitmq.consumer.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.itopener.demo.rabbitmq.sdk.vo.User;
import com.rabbitmq.client.Channel;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Component
public class RabbitListenerConfigure {
	
	private final Logger logger = LoggerFactory.getLogger(RabbitListenerConfigure.class);

//	@RabbitListener(queues=NormalConstant.DEFAULT_ROUTINGKEY)
//	public AcknowledgeMode processDefaultQueueMessage(User user){
//		logger.info("default:" + JSON.toJSONString(user));
//		return null;
//	}
	
	@RabbitListener(queues="queue-error")
	public void processErrorMessage(User user, Channel channel, Message message){
		logger.info("error queue msg:" + JSON.toJSONString(user));
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (IOException e) {
			logger.error("接收异常消息失败", e);
			try {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
			} catch (IOException e1) {
				logger.error("反馈失败时出错", e);
			}
		}
	}
	
	@RabbitListener(queues=RabbitMQConsumerConstant.QUEUE_ITOPENER)
	public void processQueueMessage(User user, Channel channel, Message message){
		logger.info("queue msg:" + JSON.toJSONString(user));
		try {
//			logger.info("=======================" + message.getMessageProperties().getMessageId());
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		} catch (IOException e) {
			throw new RuntimeException("error:" + user.getId() + ";content count:" + JSON.toJSONString(message.getMessageProperties().getHeaders()));
		}
//		logger.info(String.valueOf(message.getBody()));
	}
}
