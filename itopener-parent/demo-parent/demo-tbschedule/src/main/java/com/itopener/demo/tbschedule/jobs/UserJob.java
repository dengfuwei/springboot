package com.itopener.demo.tbschedule.jobs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.itopener.demo.tbschedule.vo.UserVO;
import com.itopener.utils.TimestampUtil;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

@Component
public class UserJob implements IScheduleTaskDealSingle<UserVO> {
	
	private final Logger logger = LoggerFactory.getLogger(UserJob.class);

	@Override
	public Comparator<UserVO> getComparator() {
		return null;
	}

	@Override
	public List<UserVO> selectTasks(String arg0, String arg1, int arg2, List<TaskItemDefine> arg3, int arg4) throws Exception {
		logger.info("selectTasks参数，arg0:" + arg0 + "; arg1:" + arg1 + "; arg3:" + JSON.toJSONString(arg3) + "; arg4:" + arg4);
		UserVO user = new UserVO();
		user.setCreateTime(TimestampUtil.current());
		user.setId(new Random().nextLong());
		user.setName("name" + user.getId());
		List<UserVO> list = new ArrayList<UserVO>();
		list.add(user);
		return new Random().nextInt() % 10 == 0 ? list : null;
	}

	@Override
	public boolean execute(UserVO user, String arg1) throws Exception {
		logger.info("execute参数,user:" + JSON.toJSONString(user) + "; arg1:" + arg1);
		return false;
	}

}
