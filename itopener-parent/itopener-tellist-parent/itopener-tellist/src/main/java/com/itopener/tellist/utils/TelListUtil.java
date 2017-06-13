package com.itopener.tellist.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;

import com.itopener.tellist.config.SessionInfo;
import com.itopener.tellist.config.TelListConstant;
import com.itopener.tellist.model.Dept;

public class TelListUtil {

	public static void handleTree(List<Dept> srcList, List<Dept> distList, long parentId){
		for(Dept item : srcList){
			if(item.getParentId() == parentId){
				distList.add(item);
			}
		}
		
		if(!CollectionUtils.isEmpty(distList)){
			for(Dept item : distList){
				item.setList(new ArrayList<>());
				handleTree(srcList, item.getList(), item.getId());
			}
		}
	}
	
	public static long getSessionUserId(HttpServletRequest request){
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(TelListConstant.SESSION_INFO_KEY);
		return sessionInfo == null ? 0L : sessionInfo.getUserId();
	}
	
	public static long getSessionDeptId(HttpServletRequest request){
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(TelListConstant.SESSION_INFO_KEY);
		return sessionInfo == null ? 0L : sessionInfo.getDeptId();
	}
}
