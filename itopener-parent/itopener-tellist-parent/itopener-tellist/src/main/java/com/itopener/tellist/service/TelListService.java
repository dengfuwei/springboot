package com.itopener.tellist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itopener.tellist.conditions.TelListCondition;
import com.itopener.tellist.dao.TelListDao;
import com.itopener.tellist.model.TelList;

@Service
public class TelListService {

	@Resource
	private TelListDao telListDao;
	
	@Transactional(readOnly = true)
	public long count(TelListCondition condition){
		return telListDao.selectCount(condition);
	}
	
	@Transactional(readOnly = true)
	public List<TelList> page(TelListCondition condition){
		return telListDao.selectPage(condition);
	}
	
	public void add(TelList telList){
		telListDao.insert(telList);
	}
	
	public void update(TelList telList){
		telListDao.update(telList);
	}
	
	public void delete(TelList telList){
		telListDao.delete(telList);
	}
}
