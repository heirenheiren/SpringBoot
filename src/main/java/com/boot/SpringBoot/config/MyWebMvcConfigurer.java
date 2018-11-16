package com.boot.SpringBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer
{

	@Override
	public void addFormatters(FormatterRegistry registry)
	{
		registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
	}

}
