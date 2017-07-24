package com.itopener.demo.rabbitmq.consumer.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;

/**
 * Created by fuwei.deng on 2017年7月24日.
 */
@RestController
@RequestMapping("rabbitmq/listener")
public class RabbitMQController {

    @Resource
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    
    @RequestMapping("stop")
    public ResultMap stop(){
    	rabbitListenerEndpointRegistry.stop();
    	return ResultMap.buildSuccess();
    }
    
    @RequestMapping("start")
    public ResultMap start(){
    	rabbitListenerEndpointRegistry.start();
    	return ResultMap.buildSuccess();
    }
    
    @RequestMapping("setup")
    public ResultMap setup(int consumer, int maxConsumer){
    	Set<String> containerIds = rabbitListenerEndpointRegistry.getListenerContainerIds();
    	SimpleMessageListenerContainer container = null;
    	for(String id : containerIds){
    		container = (SimpleMessageListenerContainer) rabbitListenerEndpointRegistry.getListenerContainer(id);
    		if(container != null){
    			container.setConcurrentConsumers(consumer);
    			container.setMaxConcurrentConsumers(maxConsumer);
    		}
    	}
    	return ResultMap.buildSuccess();
    }
    
}
