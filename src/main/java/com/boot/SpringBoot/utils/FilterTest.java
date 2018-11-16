package com.boot.SpringBoot.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FilterTest implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("=======初始化过滤器=========");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		long start = System.currentTimeMillis();

		HttpServletRequest hsrequest = (HttpServletRequest) request;
		System.out.println("=======经过过滤器的url : " + hsrequest.getRequestURI());
		chain.doFilter(request, response);

        System.out.println("=======过滤器耗时：" + (System.currentTimeMillis() - start));
	}

	@Override
	public void destroy()
	{
		System.out.println("=======销毁过滤器=========");
	}

}
