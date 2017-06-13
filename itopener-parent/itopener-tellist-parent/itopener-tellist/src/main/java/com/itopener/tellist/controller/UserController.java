package com.itopener.tellist.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.tellist.conditions.TelListCondition;
import com.itopener.tellist.conditions.UserCondition;
import com.itopener.tellist.config.SessionInfo;
import com.itopener.tellist.config.TelListConstant;
import com.itopener.tellist.enums.UserStateEnum;
import com.itopener.tellist.model.TelList;
import com.itopener.tellist.model.User;
import com.itopener.tellist.service.TelListService;
import com.itopener.tellist.service.UserService;
import com.itopener.utils.EncryptUtil;

@RestController
@RequestMapping("user")
public class UserController {

	@Resource
	private HttpServletRequest request;
	
	@Resource
	private UserService userService;
	
	@Resource
	private TelListService telListService;
	
	@RequestMapping("login")
	public ResultMap login(User user){
		UserCondition condition = new UserCondition();
		condition.setLoginName(user.getLoginName());
		condition.setLoginPwd(EncryptUtil.md5(user.getLoginPwd()));
		user = userService.login(condition);
		if(user == null){
			return ResultMap.buildFailed("用户名或密码错误");
		}
		if(user.getState() != UserStateEnum.NORMAL.getCode()){
			return ResultMap.buildFailed("账号异常，禁止登陆");
		}
		TelListCondition telListCondition = new TelListCondition();
		telListCondition.setUserId(user.getId());
		TelList telList = telListService.selectOne(telListCondition);
		
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId(user.getId());
		sessionInfo.setUserName(telList == null || StringUtils.isEmpty(telList.getName()) ? user.getLoginName() : telList.getName());
		sessionInfo.setRole(user.getRole());
		sessionInfo.setDeptId(telList == null ? 0L : telList.getDeptId());
		
		request.getSession().setAttribute(TelListConstant.SESSION_INFO_KEY, sessionInfo);
		return ResultMap.buildSuccess().put("user", sessionInfo);
	}
	
	@RequestMapping("info")
	public ResultMap info(){
		return ResultMap.buildSuccess().put("user", request.getSession().getAttribute(TelListConstant.SESSION_INFO_KEY));
	}
	
	@RequestMapping("logout")
	public ResultMap logout(){
		request.getSession().removeAttribute(TelListConstant.SESSION_INFO_KEY);
		return ResultMap.buildSuccess();
	}
}
