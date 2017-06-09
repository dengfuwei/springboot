package org.itopener.utils;

import java.sql.Timestamp;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class TimestampUtil {

	public static final Timestamp current(){
		return new Timestamp(System.currentTimeMillis());
	}
}
