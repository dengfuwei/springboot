/**  
 * Project Name:msxf-activity-web 
 * File Name:LoginRequired.java  
 * Package Name:com.msxf.activity.interceptors  
 * Date:2017年3月28日下午3:39:45 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework.interceptors;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**  
 * @ClassName:LoginRequired <br/> 
 * @Description <br/>
 * @date 2017年3月28日下午3:39:45 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@Retention(value=RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
