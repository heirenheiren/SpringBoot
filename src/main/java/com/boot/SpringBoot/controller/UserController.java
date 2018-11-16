package com.boot.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.SpringBoot.service.itf.UserFegin;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;

import io.swagger.annotations.Api;

@Api(value = "test the swagger API", tags = { "测试接口" })
@RestController
@RequestMapping("userController")
public class UserController
{
	@Autowired
	private UserFegin userFegin;

	@Autowired
	private EurekaClient eurekaClient;
	
	//@Autowired
	//private DiscoveryClient discoveryClient;

	@RequestMapping(method = RequestMethod.GET, value = "/findByUserId")
	public Object findByUserId()
	{
		return userFegin.findByUserId("1");
	}

	@GetMapping("eureka-instance")
	public String serviceUrl()
	{
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("springboot", false);
		return instance.getHomePageUrl();
	}
	
}
