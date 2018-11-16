package com.boot.SpringBoot.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerTest implements ServletContextListener
{

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		System.out.println("=======监听器初始化=========");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		System.out.println("=======销毁监听器=========");
	}

}
