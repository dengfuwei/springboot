package com.itopener.demo.druid.test;

import org.junit.Test;

import com.itopener.utils.HttpUtil;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class UserTest {
	
	private static final String URL = "http://localhost:8080/user/";

	@Test
	public void testGet(){
		HttpUtil.get(URL + 1);
	}
}
