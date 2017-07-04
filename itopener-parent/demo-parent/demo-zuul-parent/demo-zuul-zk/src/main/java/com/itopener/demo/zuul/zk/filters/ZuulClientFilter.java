package com.itopener.demo.zuul.zk.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulClientFilter extends ZuulFilter {
	
	private final Logger logger = LoggerFactory.getLogger(ZuulClientFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object a = request.getParameter("a");
        if(a != null) {
        	logger.warn("param a is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return false;
        }
        logger.info("param a is : " + a);
        return true;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
