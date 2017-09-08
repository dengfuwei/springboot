package com.itopener.memo.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import com.itopener.framework.base.BaseDao;
import com.itopener.memo.model.Memo;
import com.itopener.memo.conditions.MemoCondition;

@Repository
public class MemoDao {

	private final String NAMESPACE = "com.itopener.memo.mapper.MemoMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(Memo memo) {
		return baseDao.insert(NAMESPACE + "insert", memo);
	}

	public List<Memo> selectList(MemoCondition condition) {
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<Memo> selectPage(MemoCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getSize(), false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(MemoCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}

	public Memo selectOne(MemoCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}

	public Memo selectById(long id) {
		return baseDao.selectOne(NAMESPACE + "selectById", id);
	}
	
	public List<Memo> selectByUserId(long id) {
		return baseDao.selectList(NAMESPACE + "selectByUserId", id);
	}

	public int update(Memo memo) {
		return baseDao.update(NAMESPACE + "update", memo);
	}

	public int delete(Memo memo) {
		return baseDao.update(NAMESPACE + "delete", memo);
	}

}