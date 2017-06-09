package com.itopener.demo.rabbitmq.sdk.vo;

import java.io.Serializable;


/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class User implements Serializable {

	/** */
	private static final long serialVersionUID = -7679966022668879878L;

	/** ID*/
	private long id;

	/** 名称*/
	private String name;

	/** 状态*/
	private byte status;

	/** 版本号*/
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}