package com.itopener.tools.log.appender.kafka.logback;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
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
