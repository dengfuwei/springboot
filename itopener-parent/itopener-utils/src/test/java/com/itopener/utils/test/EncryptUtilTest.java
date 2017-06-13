package com.itopener.utils.test;

import org.junit.Test;

import com.itopener.utils.EncryptUtil;

public class EncryptUtilTest {

	@Test
	public void testMD5(){
		System.out.println(EncryptUtil.md5("123456"));
	}
}
