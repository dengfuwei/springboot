package com.itopener.demo.session.redis.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.session.redis.vo.SessionVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("session")
public class SessionController {
	
	private final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@Resource
	private HttpServletRequest request;

	@RequestMapping("info")
	public ResultMap info() {
		SessionVO session = (SessionVO) request.getSession().getAttribute("sessionUser");
		if(session == null){
			logger.info("new session info...");
			session = new SessionVO();
			session.setId(new Random().nextLong());
			session.setName("name" + session.getId());
			session.setState((byte) 1);
			session.setTime(TimestampUtil.current());
			request.getSession().setAttribute("sessionUser", session);
		}
		return ResultMap.buildSuccess().put("sessionUser", session);
	}
	
	@RequestMapping("clear")
	public ResultMap clear(){
		request.getSession().removeAttribute("sessionUser");
		return ResultMap.buildSuccess();
	}
}
