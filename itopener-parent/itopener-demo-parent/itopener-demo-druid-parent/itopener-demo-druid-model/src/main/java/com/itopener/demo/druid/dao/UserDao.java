package com.itopener.demo.druid.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.itopener.demo.druid.conditions.UserCondition;
import com.itopener.demo.druid.model.User;
import com.itopener.framework.base.BaseDao;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Repository
public class UserDao {

	private final String NAMESPACE = "com.itopener.demo.druid.mapper.UserMapper.";

	@Resource
	private BaseDao baseDao;

	public int insert(User user) {
		return baseDao.insert(NAMESPACE + "insert", user);
	}

	public List<User> selectList(UserCondition condition) {
		return baseDao.selectList(NAMESPACE + "select", condition);
	}

	public List<User> selectPage(UserCondition condition) {
		return baseDao.selectPage(NAMESPACE + "select", condition, condition.getPageNum(), condition.getPageSize());
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
	
	public User selectByIdLock(long id) {
		return baseDao.selectOne(NAMESPACE + "selectByIdLock", id);
	}

	public int update(User user) {
		return baseDao.update(NAMESPACE + "update", user);
	}

	public int delete(User user) {
		return baseDao.update(NAMESPACE + "delete", user);
	}

}