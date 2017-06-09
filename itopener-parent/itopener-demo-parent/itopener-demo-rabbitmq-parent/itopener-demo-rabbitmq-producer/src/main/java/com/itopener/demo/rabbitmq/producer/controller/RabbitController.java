package com.itopener.demo.rabbitmq.producer.controller;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.rabbitmq.producer.config.RabbitMQProducerConstant;
import com.itopener.demo.rabbitmq.sdk.enums.UserStatusEnum;
import com.itopener.demo.rabbitmq.sdk.vo.User;
import com.itopener.framework.ResultMap;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@RestController
@RequestMapping("rabbit")
public class RabbitController {
	
	@Resource
	private RabbitTemplate rabbitTemplate;

	@RequestMapping("send/{name}")
	public ResultMap user(@PathVariable String name){
		User user = new User();
		user.setId(System.currentTimeMillis());
		user.setName(name);
		user.setStatus(UserStatusEnum.NORMAL.getCode());
		user.setVersion(1L);
//		MessageProperties prop = new MessageProperties();
//		prop.setConsumerTag("123");
//		prop.setMessageId("12345");
//		Message message = new Message(JSON.toJSONString(user).getBytes(), prop);
//		amqpTemplate.send(NormalConstant.EXCHANGE_ACTIVITY, NormalConstant.ROUTINGKEY_ACTIVITY, message);
		rabbitTemplate.convertAndSend(RabbitMQProducerConstant.EXCHANGE_ITOPENER, RabbitMQProducerConstant.ROUTINGKEY_ITOPENER, user);
		return ResultMap.buildSuccess();
	}
	
}
