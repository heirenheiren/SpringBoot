package com.boot.SpringBoot.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.SpringBoot.domain.User;

@Controller
@RequestMapping("fastjson")
public class FastJsonController
{

	@RequestMapping("/fastjson")
	@ResponseBody
	public User fastjson()
	{
		User user = new User();

		user.setUid(1);
		user.setUname("jack");
		user.setUpass("jack123");
		user.setBirthday(new Date());

		// 模拟异常
        //int i = 1/0;
        
		return user;
	}
}
