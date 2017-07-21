package com.itopener.tools.eureka.admin.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.utils.HttpUtil;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("eureka")
public class EurekaClientController {

	@Resource
	private EurekaClient eurekaClient;
	
	/**
	 * @description 获取服务数量和节点数量
	 * @author fuwei.deng
	 * @date 2017年7月21日 下午3:36:24
	 * @version 1.0.0
	 * @return
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ResultMap home(){
		List<Application> apps = eurekaClient.getApplications().getRegisteredApplications();
		int appCount = apps.size();
		int nodeCount = 0;
		for(Application app : apps){
			nodeCount += app.getInstancesAsIsFromEureka().size();
		}
		return ResultMap.buildSuccess().put("appCount", appCount).put("nodeCount", nodeCount);
	}
	
	/**
	 * @description 获取所有服务节点
	 * @author fuwei.deng
	 * @date 2017年7月21日 下午3:36:38
	 * @version 1.0.0
	 * @return
	 */
	@RequestMapping(value = "apps", method = RequestMethod.GET)
	public ResultMap apps(){
		List<Application> apps = eurekaClient.getApplications().getRegisteredApplications();
		Collections.sort(apps, new Comparator<Application>() {
	        public int compare(Application l, Application r) {
	            return l.getName().compareTo(r.getName());
	        }
	    });
		for(Application app : apps){
			Collections.sort(app.getInstances(), new Comparator<InstanceInfo>() {
		        public int compare(InstanceInfo l, InstanceInfo r) {
		            return l.getPort() - r.getPort();
		        }
		    });
		}
		return ResultMap.buildSuccess().put("list", apps);
	}
	
	@RequestMapping(value = "status/{appName}", method = RequestMethod.POST)
	public ResultMap status(@PathVariable String appName, String instanceId, String status){
		Application application = eurekaClient.getApplication(appName);
		InstanceInfo instanceInfo = application.getByInstanceId(instanceId);
		instanceInfo.setStatus(InstanceStatus.toEnum(status));
		HttpUtil.post(instanceInfo.getHomePageUrl() + "eureka-client/status", "status=" + status);
		
		List<InstanceInfo> instanceInfos = application.getInstances();
		for(InstanceInfo item : instanceInfos){
			HttpUtil.post(item.getHomePageUrl() + "eureka-client/status/" + appName, "instanceId=" + instanceId + "&status=" + status);
		}
//		Set<String> regions = eurekaClient.getAllKnownRegions();
//		for(String region : regions){
//			Applications applications = eurekaClient.getApplicationsForARegion(region);
//			List<Application> apps = applications.getRegisteredApplications();
//			for(Application app : apps){
//				eurekaClient.getApplications().addApplication(app);
//			}
//		}
		return ResultMap.buildSuccess();
	}
}
