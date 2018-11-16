package com.boot.SpringBoot.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.boot.SpringBoot.utils.FilterTest;
import com.boot.SpringBoot.utils.InterceptorTest;
import com.boot.SpringBoot.utils.ListenerTest;
import com.boot.SpringBoot.utils.ServletTest;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport
{
	/**
	 * 整合 Fastjson
	 * 
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters()
	{
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;

		return new HttpMessageConverters(converter);
	}

	/**
	 * 注册 Servlet
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<ServletTest> servletRegistrationBean()
	{
		return new ServletRegistrationBean<ServletTest>(new ServletTest(), "/servletTest");
	}

	/**
	 * 要使该过滤器生效，有两种方式：
	 * 
	 * 1) 使用 @Component 注解
	 * 
	 * 2) 添加到过滤器链中，此方式适用于使用第三方的过滤器。将过滤器写到 WebConfig 类中
	 */
	@Bean
	public FilterRegistrationBean<FilterTest> filterRegistrationBean()
	{
		FilterRegistrationBean<FilterTest> registrationBean = new FilterRegistrationBean<FilterTest>();

		FilterTest filterTest = new FilterTest();
		registrationBean.setFilter(filterTest);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		/*
		 * registrationBean.addUrlPatterns("/*");
		 * registrationBean.addInitParameter("paramName", "paramValue");
		 * registrationBean.setName("MyFilter"); registrationBean.setOrder(1);
		 */

		return registrationBean;
	}

	/**
	 * 注册监听器为 Bean
	 * 
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean()
	{
		return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}

	@Autowired
	private InterceptorTest interceptorTest;

	/**
	 * 编写拦截器后，我们还需要将其注册到拦截器链中
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(interceptorTest);
	}

	/**
	 * 我们的应用程序运行在一台负载均衡代理服务器后方，因此需要将代理服务器发来的请求包含的IP地址转换成真正的用户IP。 Tomcat 8
	 * 提供了对应的过滤器：RemoteIpFilter。通过将RemoteFilter这个过滤器加入过滤器调用链即可使用它。
	 * 
	 * @return
	 */
	@Bean
	public RemoteIpFilter remoteIpFilter()
	{
		return new RemoteIpFilter();
	}

}
