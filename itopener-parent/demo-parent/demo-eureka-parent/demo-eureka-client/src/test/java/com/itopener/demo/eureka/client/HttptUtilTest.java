package com.itopener.demo.eureka.client;

import org.junit.Test;

import com.itopener.utils.HttpUtil;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

public class HttptUtilTest {

	@Test
	public void test(){
//		Map<String, String> param = new HashMap<String, String>();
//		param.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
		HttpUtil.post("http://localhost:8082/service-registry/status", "status=" + InstanceStatus.OUT_OF_SERVICE.name());
		HttpUtil.post("http://localhost:8080/refresh", "");
	}
}
