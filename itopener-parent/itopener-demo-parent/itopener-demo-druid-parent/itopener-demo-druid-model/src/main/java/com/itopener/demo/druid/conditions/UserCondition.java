package com.itopener.demo.druid.conditions;

import com.itopener.demo.druid.model.User;
import com.itopener.framework.base.BaseCondition;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class UserCondition extends User implements BaseCondition {
	
	/** */
	private static final long serialVersionUID = 1292720196300671560L;

	private int pageNum;
	
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}