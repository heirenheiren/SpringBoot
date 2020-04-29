package com.boot.SpringBoot;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.boot.SpringBoot.utils.FilterTest;
import com.boot.SpringBoot.utils.ListenerTest;
import com.boot.SpringBoot.utils.ServletTest;

/**
 * https://www.cnblogs.com/storml/p/8611388.html
 * https://www.cnblogs.com/moonlightL/p/7891803.html
 * https://docs.spring.io/spring-boot/docs/1.5.8.RELEASE/reference/html/
 * https://blog.csdn.net/buzaiguihun/article/details/53033023
 * https://www.cnblogs.com/panxuejun/p/6705167.html?utm_source=itdadao&utm_medium=referral
 * https://blog.csdn.net/saytime/article/details/74937664
 * @author Administrator
 *         https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/
 *         index.html https://www.cnblogs.com/cmfwm/p/8109433.html
 */
@SpringBootApplication
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringbootApplication extends SpringBootServletInitializer
{

	private static Logger logger = LoggerFactory.getLogger(SpringbootApplication.class);
	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		// 配置 Servlet
		servletContext.addServlet("servletTest", new ServletTest()).addMapping("/servletTest");
		// 配置过滤器
		servletContext.addFilter("filterTest", new FilterTest())
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		// 配置监听器
		servletContext.addListener(new ListenerTest());
	}*/

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		logger.debug("configure start");
		return builder.sources(SpringbootApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
