/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.itopener.demo.elasticjob.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.itopener.demo.elasticjob.vo.UserVO;
import com.itopener.utils.TimestampUtil;

@Component
public class SpringDataflowJob implements DataflowJob<UserVO> {
	
	private final Logger logger = LoggerFactory.getLogger(SpringDataflowJob.class);
    
    @Override
    public List<UserVO> fetchData(final ShardingContext shardingContext) {
    	logger.info(String.format("Item: %s | Time: %s | Thread: %s | %s",
                shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "DATAFLOW FETCH"));
        
        UserVO user = new UserVO();
		user.setCreateTime(TimestampUtil.current());
		user.setId(new Random().nextLong());
		user.setName("name" + user.getId());
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Random().nextInt() % 10 == 0 ? list : null;
    }
    
    @Override
    public void processData(final ShardingContext shardingContext, final List<UserVO> data) {
    	logger.info(String.format("Item: %s | Time: %s | Thread: %s | %s",
                shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "DATAFLOW PROCESS"));
        for (UserVO item : data) {
        	logger.info(JSON.toJSONString(item));
        }
    }
}
