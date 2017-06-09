/**  
 * Project Name:msxf-utils 
 * File Name:TimestampUtil.java  
 * Package Name:com.msxf.utils  
 * Date:2017年5月11日上午10:45:07 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package org.itopener.utils;

import java.sql.Timestamp;

/**
 * Created by fuwei.deng on 2017年5月11日.
 */
public class TimestampUtil {

	public static final Timestamp current(){
		return new Timestamp(System.currentTimeMillis());
	}
}
