package com.itopener.demo.async.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.itopener.demo.async.vo.UserVO;

@Service
public class AsyncService {
	
	private final Logger logger = LoggerFactory.getLogger(AsyncService.class);

	@Async
	public void async(){
		logger.info("async start");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("async exp", e);
		}
		logger.info("async end");
	}
	
	@Async
	public Future<UserVO> future1(long id){
		UserVO user = new UserVO();
		user.setId(id);
		user.setName("name" + id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("async exp", e);
		}
		return new AsyncResult<UserVO>(user);
	}
	
	@Async
	public Future<UserVO> future2(long id){
		UserVO user = new UserVO();
		user.setId(id);
		user.setName("name" + id);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error("async exp", e);
		}
		return new AsyncResult<UserVO>(user);
	}
	
	@Async
	public Future<UserVO> future3(long id){
		UserVO user = new UserVO();
		user.setId(id);
		user.setName("name" + id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("async exp", e);
		}
		return new AsyncResult<UserVO>(user);
	}
}
