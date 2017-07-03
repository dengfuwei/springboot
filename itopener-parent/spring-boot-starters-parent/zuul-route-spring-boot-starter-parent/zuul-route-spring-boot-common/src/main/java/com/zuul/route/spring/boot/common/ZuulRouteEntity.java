package com.zuul.route.spring.boot.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ZuulRouteEntity implements Serializable {

	/** */
	private static final long serialVersionUID = -8909855285961467412L;

	/**
	 * The ID of the route (the same as its map key by default).
	 */
	private String id;

	/**
	 * The path (pattern) for the route, e.g. /foo/**.
	 */
	private String path;

	/**
	 * The service ID (if any) to map to this route. You can specify a
	 * physical URL or a service, but not both.
	 */
	private String serviceId;

	/**
	 * A full physical URL to map to the route. An alternative is to use a
	 * service ID and service discovery to find the physical address.
	 */
	private String url;

	/**
	 * Flag to determine whether the prefix for this route (the path, minus
	 * pattern patcher) should be stripped before forwarding.
	 */
	private boolean stripPrefix;

	/**
	 * Flag to indicate that this route should be retryable (if supported).
	 * Generally retry requires a service ID and ribbon.
	 */
	private boolean retryable;

	/**
	 * List of sensitive headers that are not passed to downstream requests.
	 * Defaults to a "safe" set of headers that commonly contain user
	 * credentials. It's OK to remove those from the list if the downstream
	 * service is part of the same system as the proxy, so they are sharing
	 * authentication data. If using a physical URL outside your own domain,
	 * then generally it would be a bad idea to leak user credentials.
	 */
	private Set<String> sensitiveHeaders = new LinkedHashSet<>();

	/** 字符串格式，与sensitiveHeaders对应，多个用逗号隔开 */
	private String sensitiveHeader;

	private boolean customSensitiveHeaders;

	/** 是否可用 */
	private boolean enable;

	/** 路由器名称 */
	private String routerName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isStripPrefix() {
		return stripPrefix;
	}

	public void setStripPrefix(boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}

	public boolean isRetryable() {
		return retryable;
	}

	public void setRetryable(boolean retryable) {
		this.retryable = retryable;
	}

	public Set<String> getSensitiveHeaders() {
		return sensitiveHeaders;
	}

	public void setSensitiveHeaders(Set<String> sensitiveHeaders) {
		this.sensitiveHeaders = sensitiveHeaders;
		StringBuilder sb = new StringBuilder("");
		if (!CollectionUtils.isEmpty(sensitiveHeaders)) {
			for (String item : sensitiveHeaders) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(item);
			}
		}
		this.sensitiveHeader = sb.toString();
	}

	public String getSensitiveHeader() {
		return sensitiveHeader;
	}

	public void setSensitiveHeader(String sensitiveHeader) {
		this.sensitiveHeader = sensitiveHeader;
		if (!StringUtils.isEmpty(sensitiveHeader)) {
			this.sensitiveHeaders = new LinkedHashSet<>(Arrays.asList(sensitiveHeader.split(",")));
		} else {
			this.sensitiveHeaders = new LinkedHashSet<String>();
		}
	}

	public boolean isCustomSensitiveHeaders() {
		return customSensitiveHeaders;
	}

	public void setCustomSensitiveHeaders(boolean customSensitiveHeaders) {
		this.customSensitiveHeaders = customSensitiveHeaders;
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

	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}
}
