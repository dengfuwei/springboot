package com.itopener.zuul.route.db.spring.boot.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteLocator;

/**
 * @author fuwei.deng
 * @date 2017年6月30日 上午11:11:19
 * @version 1.0.0
 */
public class ZuulDBRouteLocator extends ZuulRouteLocator {

	public final static Logger logger = LoggerFactory.getLogger(ZuulDBRouteLocator.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ZuulDBRouteProperties zuulDBRouteProperties;

	public ZuulDBRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
	}

	@Override
	public Map<String, ZuulRoute> loadLocateRoute() {
		List<ZuulRouteEntity> locateRouteList = new ArrayList<ZuulRouteEntity>();
		try {
			locateRouteList = jdbcTemplate.query(
					"select * from " + zuulDBRouteProperties.getTableName() + " where enable = true",
					new BeanPropertyRowMapper<>(ZuulRouteEntity.class));
		} catch (DataAccessException e) {
			logger.error("load zuul route from db exception", e);
		}
		return handle(locateRouteList);
	}

}