package com.itopener.tellist.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.framework.interceptors.AuthorityRequired;
import com.itopener.tellist.conditions.DeptCondition;
import com.itopener.tellist.conditions.TelListCondition;
import com.itopener.tellist.config.TelListConstant;
import com.itopener.tellist.enums.UserRoleConstant;
import com.itopener.tellist.enums.UserStateEnum;
import com.itopener.tellist.model.Dept;
import com.itopener.tellist.model.TelList;
import com.itopener.tellist.model.User;
import com.itopener.tellist.service.DeptService;
import com.itopener.tellist.service.TelListService;
import com.itopener.tellist.utils.TelListUtil;
import com.itopener.utils.EncryptUtil;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("tellist")
public class TelListController {
	
	private final Logger logger = LoggerFactory.getLogger(TelListController.class);
	
	@Resource
	private HttpServletRequest request;

	@Resource
	private DeptService deptService;
	
	@Resource
	private TelListService telListService;
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public ResultMap listDept(){
		DeptCondition condition = new DeptCondition();
		condition.setOrderBy("serial_number asc");
		List<Dept> deptList = deptService.list(condition);
		return ResultMap.buildSuccess().put("list", deptList);
	}
	
	@RequestMapping(value = "list/{page}/{size}", method = RequestMethod.GET)
	public ResultMap page(TelListCondition condition){
		condition.setOrderBy("serial_number asc");
		long count = telListService.count(condition);
		List<TelList> list = new ArrayList<TelList>();
		if(count > 0){
			list = telListService.page(condition);
		}
		return ResultMap.buildSuccess().put("list", list).put("count", count);
	}
	
	@AuthorityRequired(role = {UserRoleConstant.SUPER_MANAGER})
	@RequestMapping(value = "save", method = RequestMethod.PUT)
	public ResultMap save(TelList telList){
		try {
			Dept dept = deptService.selectById(telList.getDeptId());
			if(dept == null){
				return ResultMap.buildFailed("没有查询到分部信息");
			}
			long userId = TelListUtil.getSessionUserId(request);
			telList.setDeptName(dept.getName());
			Timestamp now = TimestampUtil.current();
			telList.setUpdateTime(now);
			telList.setUpdateUserId(userId);
			telList.setUserId(0);
			if(telList.getId() > 0){
				telList.setEmail(telList.getEmail() + TelListConstant.EMAIL_SUFFIX);
				telListService.update(telList);
			} else{
				User user = new User();
				user.setCreateTime(now);
				user.setCreateUserId(userId);
				user.setLoginName(telList.getEmail());
				user.setLoginPwd(EncryptUtil.md5(TelListConstant.DEFAULT_PWD));
				user.setRole(UserRoleConstant.NORMAL);
				user.setState(UserStateEnum.NORMAL.getCode());
				user.setUpdateTime(now);
				user.setUpdateUserId(userId);
				
				telList.setEmail(telList.getEmail() + TelListConstant.EMAIL_SUFFIX);
				telList.setCreateTime(now);
				telList.setCreateUserId(userId);
				telListService.add(telList, user);
			}
			return ResultMap.buildSuccess();
		} catch (Exception e) {
			logger.error("保存出错", e);
		}
		return ResultMap.buildFailed("保存失败");
	}
	
	@AuthorityRequired(role = {UserRoleConstant.SUPER_MANAGER})
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResultMap delete(@PathVariable long id){
		TelList telList = telListService.selectById(id);
		if(telList == null){
			return ResultMap.buildFailed("没有查询到通讯录信息");
		}
		User user = new User();
		user.setId(telList.getUserId());
		telListService.delete(telList, user);
		return ResultMap.buildSuccess();
	}
}
