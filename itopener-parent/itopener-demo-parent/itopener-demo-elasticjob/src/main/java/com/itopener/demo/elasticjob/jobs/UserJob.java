package com.itopener.demo.elasticjob.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

@Component
public class UserJob implements SimpleJob {
	
	private final Logger logger = LoggerFactory.getLogger(UserJob.class);

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.info("context:" + JSON.toJSONString(shardingContext));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
