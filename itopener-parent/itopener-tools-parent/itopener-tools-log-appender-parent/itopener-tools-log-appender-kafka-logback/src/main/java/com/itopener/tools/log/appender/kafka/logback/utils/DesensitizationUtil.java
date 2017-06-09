/**  
 * Project Name:msxf-tools-appender-kafka-logback 
 * File DesensitizationUtil.java  
 * Package Name:com.msxf.tools.appender.kafka.logback.formatter
 * Date:2017年5月17日下午1:14:20 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.log.appender.kafka.logback.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuwei.deng on 2017年5月17日.
 */
public class DesensitizationUtil {

	private static final String MASK_STR = "**";

	private static String mask(String src, int start, int end) {
		if (src == null || src.trim().length() == 0) {
			return src;
		}
		int srclen = src.length();
		start = start < 0 ? (start + srclen) : start;
		end = end < 0 ? (end + srclen) : end;
		if (start > end || end < 0 || start >= srclen) {
			return src;
		}
		char[] val = new char[srclen];
		for (int i = 0; i < srclen; i++) {
			if (i >= start && i <= end) {
				val[i] = '*';
			} else {
				val[i] = src.charAt(i);
			}
		}
		return new String(val);
	}
	
	public static String hide(String info) {
		return MASK_STR;
	}
	
	public static String name(String info) {
		return mask(info, 0, -2);
	}
	
	public static String id(String id) {
		return mask(id, 2, -5);
	}
	
	public static String mobile(String mobile) {
		return mask(mobile, 2, -5);
	}
	
	public static String mail(String mail) {
		return mask(mail, 0, mail.indexOf("@")-1);
	}
	
	public static String tel(String tel) {
		return mask(tel, 0, -5);
	}
	
	public static String addr(String addr) {
		int pos = addr.indexOf("省");
		if (pos < 0) {
			pos = addr.indexOf("市");
		}
		if (pos < 0) {
			pos = addr.indexOf("自治区");
			if (pos >= 0) {
				pos += 3;
			}
		} else {
			pos += 1;
		}
		if (pos == addr.length()) {
			return addr;
		}
		if (pos > 0) {
			StringBuilder tmp = new StringBuilder();
			tmp.append(addr.substring(0, pos));
			tmp.append(MASK_STR);
			return tmp.toString();
		}
		return MASK_STR;
	}
	
	public static String bankCard(String bankCard) {
		return mask(bankCard, 0, -7);
	}
	
	public static String socialInsure(String insureAccount) {
		return mask(insureAccount, 2, -7);
	}
	
	public static String netId(String netId) {
		return mask(netId, 4, netId.length()-1);
	}
	
	public static String carEngineNo(String engineNo) {
		return mask(engineNo, 0, -5);
	}
	
	public static String carLicenseNo(String licenseNo) {
		return mask(licenseNo, 0, -5);
	}
	
	public static String bank(String bank) {
		return addr(bank);
	}
	
	public static String qq(String qq) {
		return mask(qq, 0, -5);
	}
	
	private static Pattern MOBILE = Pattern.compile("(\\+?86)?(1[3578][0-9]{9})");
	private static Pattern MAIL = Pattern.compile("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)");
	private static Pattern ID = Pattern.compile("[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}([0-9]|X|x)|[1-9]\\d{7}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}");
	private static Pattern SOCIAL_INSURE = Pattern.compile("[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}([0-9]|X|x)\\d{2}");
	private static Pattern BANK_CARD = Pattern.compile("[0-9]{19}|[0-9]{16}");
	private static Pattern TEL = Pattern.compile("(0[0-9]{2,3}\\-?)[0-9]{7,8}");
	private static Pattern ADDR = Pattern.compile("(北京|天津|上海|重庆)市?[a-zA-Z0-9\\u4e00-\\u9fa5]*|(黑龙江|吉林|辽宁|河南|河北|山东|山西|陕西|甘肃|青海|四川|湖北|湖南|安徽|江苏|浙江|江西|贵州|云南|广东|福建|海南|台湾)省?[a-zA-Z0-9\\u4e00-\\u9fa5]*|(新疆|西藏|宁夏|内蒙古|广西)(自治区)?[a-zA-Z0-9\\u4e00-\\u9fa5]*");
	
	private static Pattern SENSITIVE_INFO = Pattern.compile(SOCIAL_INSURE.pattern() + "|" + ID.pattern() + "|" + BANK_CARD.pattern() + "|" + MOBILE.pattern() + "|" + TEL.pattern() + "|" + MAIL.pattern() + "|" + ADDR.pattern());
	
	public static String filter(String info) {
		if (info == null) {
			return info;
		} else if (info.length() <= 20) {
			StringBuilder tmp = new StringBuilder();
			Matcher matcher = MOBILE.matcher(info);
			if (matcher.find()) {
				return tmp.append(mobile(matcher.group(2)))
						.append(info.substring(matcher.end(2))).toString();
			}
			matcher = ID.matcher(info);
			if (matcher.find()) {
				return tmp.append(info.substring(0, matcher.start())).append(id(matcher.group()))
						.append(info.substring(matcher.end())).toString();
			}
			matcher = BANK_CARD.matcher(info);
			if (matcher.find()) {
				return tmp.append(info.substring(0, matcher.start())).append(bankCard(matcher.group()))
						.append(info.substring(matcher.end())).toString();
			}
			matcher = TEL.matcher(info);
			if (matcher.find()) {
				return tmp.append(info.substring(0, matcher.start())).append(tel(matcher.group()))
						.append(info.substring(matcher.end())).toString();
			}
			matcher = ADDR.matcher(info);
			if (matcher.find()) {
				return tmp.append(info.substring(0, matcher.start())).append(addr(matcher.group()))
						.append(info.substring(matcher.end())).toString();
			}
			matcher = SOCIAL_INSURE.matcher(info);
			if (matcher.find()) {
				return tmp.append(info.substring(0, matcher.start())).append(socialInsure(matcher.group()))
						.append(info.substring(matcher.end())).toString();
			}
			matcher = MAIL.matcher(info);
			if (matcher.find()) {
				return tmp.append(info.substring(0, matcher.start())).append(mail(matcher.group()))
						.append(info.substring(matcher.end())).toString();
			}
		} else {
			StringBuilder tmp = new StringBuilder();
			Matcher matcher = SENSITIVE_INFO.matcher(info);
			int start = 0;
			while (matcher.find()) {
				tmp.append(info.substring(start, matcher.start()));
				String matchText = matcher.group();
				start = matcher.end();
				Matcher matcher2 = MOBILE.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(matchText.substring(0, matcher2.start(2))).append(mobile(matcher2.group(2)));
					continue;
				}
				matcher2 = ID.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(id(matchText));
					continue;
				}
				matcher2 = BANK_CARD.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(bankCard(matchText));
					continue;
				}
				matcher2 = TEL.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(tel(matchText));
					continue;
				}
				matcher2 = ADDR.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(addr(matchText));
					continue;
				}
				matcher2 = SOCIAL_INSURE.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(socialInsure(matchText));
					continue;
				}
				matcher2 = MAIL.matcher(matchText);
				if (matcher2.find()) {
					tmp.append(mail(matchText));
					continue;
				}
				//if reach here, append original text
				tmp.append(matchText);
			}
			tmp.append(info.substring(start));
			return tmp.toString();
		}
		return info;
	}
}
