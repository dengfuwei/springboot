package com.itopener.tools.zuul.route.admin.model;

import java.io.Serializable;

/**
 * @desc 
 * @author fuwei.deng
 * @date 2017-07-03 19:22:13
 */
public class ZuulRouteConfig implements Serializable {

	/** */
	private static final long serialVersionUID = -5561776198520639729L;

	/** */
	private String id;

	/** */
	private String path;

	/** */
	private String serviceId;

	/** */
	private String url;

	/** */
	private boolean stripPrefix;

	/** */
	private boolean retryable;

	/** */
	private String sensitiveHeaders;

	/** */
	private boolean customSensitiveHeaders;

	/** */
	private boolean enable;

	/** */
	private String routerName;

	public String getId() {
		return id;
	}

	public ZuulRouteConfig setId(String id) {
		this.id = id;
		return this;
	}

	public String getPath() {
		return path;
	}

	public ZuulRouteConfig setPath(String path) {
		this.path = path;
		return this;
	}

	public String getServiceId() {
		return serviceId;
	}

	public ZuulRouteConfig setServiceId(String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public ZuulRouteConfig setUrl(String url) {
		this.url = url;
		return this;
	}

	public boolean getStripPrefix() {
		return stripPrefix;
	}

	public ZuulRouteConfig setStripPrefix(boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
		return this;
	}

	public boolean getRetryable() {
		return retryable;
	}

	public ZuulRouteConfig setRetryable(boolean retryable) {
		this.retryable = retryable;
		return this;
	}

	public String getSensitiveHeaders() {
		return sensitiveHeaders;
	}

	public ZuulRouteConfig setSensitiveHeaders(String sensitiveHeaders) {
		this.sensitiveHeaders = sensitiveHeaders;
		return this;
	}

	public boolean getCustomSensitiveHeaders() {
		return customSensitiveHeaders;
	}

	public ZuulRouteConfig setCustomSensitiveHeaders(boolean customSensitiveHeaders) {
		this.customSensitiveHeaders = customSensitiveHeaders;
		return this;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRouterName() {
		return routerName;
	}

	public ZuulRouteConfig setRouterName(String routerName) {
		this.routerName = routerName;
		return this;
	}
}