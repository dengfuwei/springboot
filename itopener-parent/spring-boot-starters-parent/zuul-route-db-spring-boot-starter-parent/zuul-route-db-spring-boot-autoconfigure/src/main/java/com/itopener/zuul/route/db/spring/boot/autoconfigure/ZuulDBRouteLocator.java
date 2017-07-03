package com.itopener.zuul.route.db.spring.boot.autoconfigure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 上午11:11:19
 * @version 1.0.0
 */
public class ZuulDBRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(ZuulDBRouteLocator.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ZuulProperties properties;

	@Autowired
	private ZuulDBRouteProperties zuulDBRouteProperties;

	public ZuulDBRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		this.properties = properties;
		logger.info("servletPath:{}", servletPath);
	}

	@Override
	public void refresh() {
		doRefresh();
	}

	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
		// 从application.properties中加载路由信息
		// routesMap.putAll(super.locateRoutes());
		// 从db中加载路由信息
		routesMap.putAll(locateRoutesFromDB());
		// 优化一下配置
		LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
		for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
			String path = entry.getKey();
			// Prepend with slash if not already present.
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (StringUtils.hasText(this.properties.getPrefix())) {
				path = this.properties.getPrefix() + path;
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		return values;
	}

	private Map<String, ZuulRoute> locateRoutesFromDB() {
		Map<String, ZuulRoute> routes = new LinkedHashMap<>();
		List<ZuulRouteEntity> results = new ArrayList<ZuulRouteEntity>();
		try {
			results = jdbcTemplate.query(
					"select * from " + zuulDBRouteProperties.getTableName() + " where enabled = true",
					new BeanPropertyRowMapper<>(ZuulRouteEntity.class));
		} catch (DataAccessException e) {
			logger.error("load zuul route from db exception", e);
		}
		for (ZuulRouteEntity result : results) {
			if (StringUtils.isEmpty(result.getPath())
					|| (StringUtils.isEmpty(result.getUrl()) && StringUtils.isEmpty(result.getServiceId()))) {
				continue;
			}
			ZuulRoute zuulRoute = new ZuulRoute();
			try {
				if(!CollectionUtils.isEmpty(result.getSensitiveHeaders())){
					zuulRoute.setCustomSensitiveHeaders(result.isCustomSensitiveHeaders());
					zuulRoute.setSensitiveHeaders(result.getSensitiveHeaders());
				}
				zuulRoute.setId(result.getId());
//				zuulRoute.setLocation("");
				zuulRoute.setPath(result.getPath());
				zuulRoute.setRetryable(result.isRetryable());
				zuulRoute.setServiceId(result.getServiceId());
				zuulRoute.setStripPrefix(result.isStripPrefix());
				zuulRoute.setUrl(result.getUrl());
			} catch (Exception e) {
				logger.error("=============load zuul route info from db with error==============", e);
			}
			routes.put(zuulRoute.getPath(), zuulRoute);
		}
		return routes;
	}

	@Override
	public Route getMatchingRoute(String path) {
		Route route = super.getMatchingRoute(path);
		// TODO 增加自定义路由规则判断
		return route;
	}

	@Override
	public int getOrder() {
		return -1;
	}

	public static class ZuulRouteEntity {

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
}