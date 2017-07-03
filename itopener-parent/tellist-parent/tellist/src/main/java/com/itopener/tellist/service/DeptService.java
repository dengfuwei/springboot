package com.itopener.tellist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itopener.tellist.conditions.DeptCondition;
import com.itopener.tellist.dao.DeptDao;
import com.itopener.tellist.model.Dept;

@Service
public class DeptService {
	
	@Resource
	private DeptDao deptDao;

	@Transactional(readOnly = true)
	public List<Dept> list(DeptCondition condition){
		return deptDao.selectList(condition);
	}
	
	public void add(Dept dept){
		deptDao.insert(dept);
	}
	
	public void update(Dept dept){
		deptDao.update(dept);
	}
	
	public void delete(Dept dept){
		deptDao.delete(dept);
	}
	
	public Dept selectById(long id){
		return deptDao.selectById(id);
	}
}
