package com.boot.SpringBoot.utils;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class InterceptorTest implements HandlerInterceptor
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{

		System.out.println("========preHandle========="+((HandlerMethod) handler).getBean().getClass().getName());
		System.out.println("========preHandle========="+((HandlerMethod) handler).getMethod().getName());

		String ip = getIpAddr(request);
		String ipStr = PropertiesUtils.getProperty("ipWhiteList"); // 获取可以访问系统的白名单
		String[] ipArr = ipStr.split("\\|");
		List<String> ipList = java.util.Arrays.asList(ipArr);

		if (ipList.contains(ip))
		{
			System.out.println("the request ip is : " + ip);
			request.setAttribute("startTime", System.currentTimeMillis());
			return true;
		}
		else
		{
			System.out.println(ip + " is not contains ipWhiteList .......");
			response.setHeader("Content-type", "text/html;charset=UTF-8");// 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
			String data = "您好，ip为" + ip + ",暂时没有访问权限，请联系管理员开通访问权限。";
			OutputStream stream = response.getOutputStream();
			stream.write(data.getBytes("UTF-8"));
			request.setAttribute("startTime", System.currentTimeMillis());
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{

		System.out.println("========postHandle=========");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception
	{
		System.out.println("========afterCompletion=========");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
	}

	/**
	 * 获取访问的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
