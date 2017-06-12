package com.itopener.framework.base;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public interface BaseCondition {

	public int getPage();
	
	public BaseCondition setPage(int page);
	
	public int getSize();
	
	public BaseCondition setSize(int size);
	
	public String getOrderBy();
	
	public BaseCondition setOrderBy(String orderBy);
}
