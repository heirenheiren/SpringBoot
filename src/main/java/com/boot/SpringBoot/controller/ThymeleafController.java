package com.boot.SpringBoot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController
{

	@RequestMapping("thymeleaf")
	public String thymeleaf(Map<String, Object> map)
	{
		map.put("msg", "Hello Thymeleaf");
		return "thymeleaf";
	}
}