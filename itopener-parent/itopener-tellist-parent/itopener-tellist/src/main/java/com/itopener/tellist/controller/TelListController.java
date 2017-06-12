package com.itopener.tellist.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.tellist.conditions.DeptCondition;
import com.itopener.tellist.conditions.TelListCondition;
import com.itopener.tellist.model.Dept;
import com.itopener.tellist.model.TelList;
import com.itopener.tellist.service.DeptService;
import com.itopener.tellist.service.TelListService;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("tellist")
public class TelListController {
	
	private final Logger logger = LoggerFactory.getLogger(TelListController.class);

	@Resource
	private DeptService deptService;
	
	@Resource
	private TelListService telListService;
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public ResultMap listDept(){
		DeptCondition condition = new DeptCondition();
		condition.setOrderBy("serial_number asc");
		List<Dept> deptList = deptService.list(condition);
//		List<Dept> list = new ArrayList<>();
//		TelListUtil.handleTree(deptList, list, 0L);
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
	
	@RequestMapping(value = "save", method = RequestMethod.PUT)
	public ResultMap save(TelList telList){
		try {
			Dept dept = deptService.selectById(telList.getDeptId());
			if(dept == null){
				return ResultMap.buildFailed("没有查询到分部信息");
			}
			telList.setDeptName(dept.getName());
			Timestamp now = TimestampUtil.current();
			telList.setUpdateTime(now);
			telList.setUpdateUserId(0);
			telList.setUserId(0);
			if(telList.getId() > 0){
				telListService.update(telList);
			} else{
				telList.setCreateTime(now);
				telList.setCreateUserId(0);
				telListService.add(telList);
			}
			return ResultMap.buildSuccess();
		} catch (Exception e) {
			logger.error("保存出错", e);
		}
		return ResultMap.buildFailed("保存失败");
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResultMap delete(@PathVariable long id){
		TelList telList = new TelList();
		telList.setId(id);
		telListService.delete(telList);
		return ResultMap.buildSuccess();
	}
}
