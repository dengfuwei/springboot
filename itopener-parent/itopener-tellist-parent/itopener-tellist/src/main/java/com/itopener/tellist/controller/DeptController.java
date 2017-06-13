package com.itopener.tellist.controller;

import java.sql.Timestamp;
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
import com.itopener.tellist.enums.UserRoleConstant;
import com.itopener.tellist.model.Dept;
import com.itopener.tellist.service.DeptService;
import com.itopener.tellist.utils.TelListUtil;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("dept")
public class DeptController {
	
	private final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private DeptService deptService;

	@RequestMapping("list")
	public ResultMap listDept(){
		DeptCondition condition = new DeptCondition();
		condition.setOrderBy("serial_number asc");
		
		long deptId = TelListUtil.getSessionDeptId(request);
		if(deptId > 0){
			condition.setId(deptId);
		}
		
		List<Dept> deptList = deptService.list(condition);
		return ResultMap.buildSuccess().put("list", deptList);
	}
	
	@AuthorityRequired(role = {UserRoleConstant.SUPER_MANAGER})
	@RequestMapping(value = "save", method = RequestMethod.PUT)
	public ResultMap save(Dept dept){
		try {
			if(dept.getId() == dept.getParentId()){
				return ResultMap.buildFailed("上级分部不能是当前分部");
			}
			Timestamp now = TimestampUtil.current();
			dept.setUpdateTime(now);
			dept.setUpdateUserId(0);
			if(dept.getId() > 0){
				deptService.update(dept);
			} else{
				dept.setCreateTime(now);
				dept.setCreateUserId(0);
				deptService.add(dept);
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
		Dept dept = new Dept();
		dept.setId(id);
		deptService.delete(dept);
		return ResultMap.buildSuccess();
	}
}
