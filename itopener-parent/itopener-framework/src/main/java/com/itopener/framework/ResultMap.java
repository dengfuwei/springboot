/**  
 * Project Name:msxf-framework 
 * File Name:ResuleMap.java  
 * Package Name:com.msxf.framework  
 * Date:2017年5月5日上午9:36:35 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework;

import org.springframework.ui.ModelMap;

/**
 * Created by fuwei.deng on 2017年5月5日.
 */
public class ResultMap extends ModelMap {

	/** */
	private static final long serialVersionUID = 5898506914945717989L;
	
	public static final String CODE_SUCCESS = "success";
	
	public static final String CODE_FIALED = "failed";
	
	private final String FLAG_KEY = "flag";
	
	private final String MSG_KEY = "msg";

	public ResultMap(){
		put(FLAG_KEY, CODE_SUCCESS);
	}
	
	public ResultMap(String msg) {
		put(FLAG_KEY, CODE_FIALED);
		put(MSG_KEY, msg);
	}
	
	public ResultMap(String code, String msg){
		put(FLAG_KEY, code);
		put(MSG_KEY, msg);
	}
	
	public ResultMap setCode(String code) {
		put(FLAG_KEY, code);
		return this;
	}

	public ResultMap setMsg(String msg) {
		put(MSG_KEY, msg);
		return this;
	}
	
	public ResultMap put(String key, Object value){
		super.put(key, value);
		return this;
	}
	
	public static ResultMap buildSuccess(){
		return new ResultMap();
	}
	
	public static ResultMap buildFailed(String msg){
		return new ResultMap(msg);
	}
}
