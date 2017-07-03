package com.itopener.tellist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itopener.tellist.conditions.TelListCondition;
import com.itopener.tellist.dao.TelListDao;
import com.itopener.tellist.dao.UserDao;
import com.itopener.tellist.model.TelList;
import com.itopener.tellist.model.User;

@Service
public class TelListService {

	@Resource
	private TelListDao telListDao;
	
	@Resource
	private UserDao userDao;
	
	@Transactional(readOnly = true)
	public long count(TelListCondition condition){
		return telListDao.selectCount(condition);
	}
	
	@Transactional(readOnly = true)
	public List<TelList> page(TelListCondition condition){
		return telListDao.selectPage(condition);
	}
	
	@Transactional
	public void add(TelList telList, User user){
		userDao.insert(user);
		telList.setUserId(user.getId());
		telListDao.insert(telList);
	}
	
	public void update(TelList telList){
		telListDao.update(telList);
	}
	
	@Transactional
	public void delete(TelList telList, User user){
		telListDao.delete(telList);
		userDao.delete(user);
	}
	
	public TelList selectOne(TelListCondition condition){
		return telListDao.selectOne(condition);
	}
	
	public TelList selectById(long id){
		return telListDao.selectById(id);
	}
}
