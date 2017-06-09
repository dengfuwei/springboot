/**  
 * Project Name:msxf-tools-appender-kafka-logback 
 * File Name:Ssl.java  
 * Package Name:com.msxf.tools.appender.kafka.logback  
 * Date:2017年5月18日上午10:14:40 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.log.appender.kafka.logback;


/**
 * Created by fuwei.deng on 2017年5月18日.
 */
public class Ssl<E> {

	/**
	 * Password of the private key in the key store file.
	 */
	private String keyPassword;

	/**
	 * Location of the key store file.
	 */
	private String keystoreLocation;

	/**
	 * Store password for the key store file.
	 */
	private String keystorePassword;

	/**
	 * Location of the trust store file.
	 */
	private String truststoreLocation;

	/**
	 * Store password for the trust store file.
	 */
	private String truststorePassword;

	public String getKeyPassword() {
		return this.keyPassword;
	}

	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

	public String getKeystoreLocation() {
		return this.keystoreLocation;
	}

	public void setKeystoreLocation(String keystoreLocation) {
		this.keystoreLocation = keystoreLocation;
	}

	public String getKeystorePassword() {
		return this.keystorePassword;
	}

	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	public String getTruststoreLocation() {
		return this.truststoreLocation;
	}

	public void setTruststoreLocation(String truststoreLocation) {
		this.truststoreLocation = truststoreLocation;
	}

	public String getTruststorePassword() {
		return this.truststorePassword;
	}

	public void setTruststorePassword(String truststorePassword) {
		this.truststorePassword = truststorePassword;
	}
}
