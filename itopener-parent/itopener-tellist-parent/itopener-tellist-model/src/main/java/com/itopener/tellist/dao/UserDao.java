package com.itopener.tellist.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.itopener.framework.base.BaseDao;
import com.itopener.tellist.model.User;
import com.itopener.tellist.conditions.UserCondition;

@Repository
public class UserDao {

	private final String NAMESPACE = "com.itopener.tellist.mapper.UserMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(User user) {
		return baseDao.insert(NAMESPACE + "insert", user);
	}

	public List<User> selectList(UserCondition condition) {
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<User> selectPage(UserCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getSize(), false);
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public int selectCount(UserCondition condition) {
		return baseDao.selectOne(NAMESPACE + "selectCount", condition);
	}

	public User selectOne(UserCondition condition) {
		return baseDao.selectOne(NAMESPACE + "select", condition);
	}

	public User selectById(long id) {
		return baseDao.selectOne(NAMESPACE + "selectById", id);
	}

	public int update(User user) {
		return baseDao.update(NAMESPACE + "update", user);
	}

	public int delete(User user) {
		return baseDao.update(NAMESPACE + "delete", user);
	}

}