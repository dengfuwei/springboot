package com.itopener.tellist.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.itopener.framework.base.BaseDao;
import com.itopener.tellist.model.Dept;
import com.itopener.tellist.conditions.DeptCondition;

@Repository
public class DeptDao {

	private final String NAMESPACE = "com.itopener.tellist.mapper.DeptMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(Dept dept) {
		return baseDao.insert(NAMESPACE + "insert", dept);
	}

	public List<Dept> selectList(DeptCondition condition) {
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<Dept> selectPage(DeptCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getSize(), false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(DeptCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}

	public Dept selectOne(DeptCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}

	public Dept selectById(long id) {
		return baseDao.selectOne(NAMESPACE + "selectById", id);
	}

	public int update(Dept dept) {
		return baseDao.update(NAMESPACE + "update", dept);
	}

	public int delete(Dept dept) {
		return baseDao.update(NAMESPACE + "delete", dept);
	}

}