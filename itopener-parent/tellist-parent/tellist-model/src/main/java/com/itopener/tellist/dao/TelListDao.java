package com.itopener.tellist.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.itopener.framework.base.BaseDao;
import com.itopener.tellist.model.TelList;
import com.itopener.tellist.conditions.TelListCondition;

@Repository
public class TelListDao {

	private final String NAMESPACE = "com.itopener.tellist.mapper.TelListMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(TelList telList) {
		return baseDao.insert(NAMESPACE + "insert", telList);
	}

	public List<TelList> selectList(TelListCondition condition) {
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<TelList> selectPage(TelListCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getSize(), false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(TelListCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}

	public TelList selectOne(TelListCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}

	public TelList selectById(long id) {
		return baseDao.selectOne(NAMESPACE + "selectById", id);
	}

	public int update(TelList telList) {
		return baseDao.update(NAMESPACE + "update", telList);
	}

	public int delete(TelList telList) {
		return baseDao.update(NAMESPACE + "delete", telList);
	}

}