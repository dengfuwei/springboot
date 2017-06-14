package com.itopener.demo.async.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.async.service.AsyncService;
import com.itopener.demo.async.vo.UserVO;
import com.itopener.framework.ResultMap;

@RestController
//@RequestMapping("async")
public class AsyncController {
	
	private final Logger logger = LoggerFactory.getLogger(AsyncController.class);
	
	@Resource
	private AsyncService asyncService;

	@RequestMapping("async")
	public ResultMap async(){
		asyncService.async();
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping("future")
	public ResultMap future(){
		Future<UserVO> task1 = asyncService.future1(new Random().nextLong());
        Future<UserVO> task2 = asyncService.future2(new Random().nextLong());
        Future<UserVO> task3 = asyncService.future3(new Random().nextLong());
        try {
			while(true) {
				//判断是否所有异步执行完成
			    if(task1.isDone() && task2.isDone() && task3.isDone()) {  
			        // 三个任务都调用完成，退出循环等待  
			        break;  
			    }  
			    Thread.sleep(100);  
			}
			List<UserVO> list = new ArrayList<>();
			list.add(task1.get());
			list.add(task2.get());
			list.add(task3.get());
			return ResultMap.buildSuccess().put("list", list);
		} catch (InterruptedException e) {
			logger.error("interrupted exp", e);
		} catch (ExecutionException e) {
			logger.error("execution exp", e);
		}
        return ResultMap.buildFailed("执行失败");
	}
}
