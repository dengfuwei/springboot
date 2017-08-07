package com.itopener.demo.druid.mapper;

import java.util.List;

import com.itopener.demo.druid.conditions.UserCondition;
import com.itopener.demo.druid.model.User;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public interface UserMapper {

	public int insert(User user);

	public List<User> select(UserCondition condition);

	public List<User> selectPage(UserCondition condition);

	public int selectCount(UserCondition condition);

	public User selectOne(UserCondition condition);

	public User selectById(long id);
	
	public User selectByIdLock(long id);

	public int update(User user);

	public int delete(User user);

}