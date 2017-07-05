package com.itopener.tools.zuul.route.admin.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itopener.tools.zuul.route.admin.service.IZuulRouteService;

@Configuration
public class ZuulRouteConfiguration {
	
	@Bean
	public ZuulRouteHelper zuulRouteHelper(List<IZuulRouteService> zuulRouteServiceList){
		ZuulRouteHelper zuulRouteHelper = new ZuulRouteHelper();
		zuulRouteHelper.setZuulRouteService(new HashMap<String, IZuulRouteService>());
		for(IZuulRouteService item : zuulRouteServiceList){
			zuulRouteHelper.getZuulRouteService().put(item.key(), item);
		}
		return zuulRouteHelper;
	}
}
