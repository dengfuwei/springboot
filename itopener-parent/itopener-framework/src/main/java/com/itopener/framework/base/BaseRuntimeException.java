/**  
 * Project Name:msxf-activity-common 
 * File Name:BaseRuntimeException.java  
 * Package Name:org.msxf.activity.common.exp  
 * Date:2017年3月29日下午3:25:32 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework.base;

/**  
 * @ClassName:BaseRuntimeException <br/> 
 * @Description <br/>
 * @date 2017年3月29日下午3:25:32 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
public class BaseRuntimeException extends RuntimeException {

	/** */
	private static final long serialVersionUID = 4347299585092122680L;

	public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }
}
