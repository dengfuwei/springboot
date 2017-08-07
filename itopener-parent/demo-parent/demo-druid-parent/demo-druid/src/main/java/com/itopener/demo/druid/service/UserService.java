package com.itopener.demo.druid.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itopener.demo.druid.dao.UserDao;
import com.itopener.demo.druid.mapper.UserMapper;
import com.itopener.demo.druid.model.User;
import com.itopener.framework.base.BaseRuntimeException;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private UserMapper userMapper;
	
	@Transactional(readOnly = true)
	public User select(long id){
		long start = System.currentTimeMillis();
		List<User> users = new ArrayList<User>();
		for(int i=0; i<10000; i++){
			User user = userDao.selectById(id);
			users.add(user);
		}
		System.out.println(System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		users = new ArrayList<User>();
		for(int i=0; i<10000; i++){
			User user = userMapper.selectById(id);
			users.add(user);
		}
		System.out.println(System.currentTimeMillis() - start);
		return userDao.selectById(id);
	}
	
	public void add(User user){
		userDao.insert(user);
	}
	
	public void update(User user){
		userDao.update(user);
	}
	
	public void delete(User user){
		userDao.delete(user);
	}
	
	@Transactional
	public void transaction(User user){
		userDao.update(user);
		throw new BaseRuntimeException("抛出异常，让事务回滚");
	}
}
