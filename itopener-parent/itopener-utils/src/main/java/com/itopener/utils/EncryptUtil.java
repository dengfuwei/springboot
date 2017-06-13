package com.itopener.utils;

import java.security.MessageDigest;

/**
 * @author fuwei.deng
 * @date 2017年6月13日 下午4:48:48
 * @version 1.0.0
 */
public class EncryptUtil {

	/**
	 * @description MD5加密
	 * @author fuwei.deng
	 * @date 2017年6月13日 下午4:49:17
	 * @version 1.0.0
	 * @param in
	 * @return
	 */
	public static String md5(String in) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(in.trim().getBytes());
			byte[] b = md5.digest();
			return byte2hex(b);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}
}
