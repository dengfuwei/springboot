package com.itopener.tools.eureka.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("eureka")
public class EurekaClientController {

	@Resource
	private EurekaClient client;
	
	@RequestMapping("services")
	public ResultMap services(){
		List<Application> list = client.getApplications().getRegisteredApplications();
		return ResultMap.buildSuccess().put("list", list);
	}
	
	@RequestMapping("pause")
	public ResultMap pause(){
		List<Application> list = client.getApplications().getRegisteredApplications();
		list.get(0).getInstances().get(0).setOverriddenStatus(InstanceStatus.OUT_OF_SERVICE);
		list.get(0).getInstances().get(0).setStatusWithoutDirty(InstanceStatus.OUT_OF_SERVICE);
		client.getApplicationInfoManager().refreshDataCenterInfoIfRequired();
		client.getApplicationInfoManager().refreshLeaseInfoIfRequired();
//		client.getApplicationInfoManager().getInfo().setIsDirty(false);
//		client.getApplicationInfoManager().getInfo().setOverriddenStatus(InstanceStatus.UNKNOWN);
//		client.getApplicationInfoManager().unregisterStatusChangeListener("statusChangeListener");
		return ResultMap.buildSuccess();
	}
}
