package com.boot.SpringBoot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("freemarker")
public class FreemarkerController
{

	@RequestMapping("freemarker")
	public String freemarker(Map<String, Object> map)
	{
		map.put("msg", "Hello Freemarker");
		return "freemarker";
	}
}
