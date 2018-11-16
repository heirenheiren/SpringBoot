package com.boot.SpringBoot.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义 Servlet：代码注册通过ServletRegistrationBean 获得控制
 * @author Administrator
 *
 */
public class ServletTest extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7637133620158432490L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().print("hello word");  
        resp.getWriter().flush();  
        resp.getWriter().close();  
		//这里报死循环有内存溢出java.lang.StackOverflowError: null
		this.doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");    
        PrintWriter out = resp.getWriter();    
        out.println("<html>");    
        out.println("<head>");    
        out.println("<title>Hello World</title>");    
        out.println("</head>");    
        out.println("<body>");    
        out.println("<h1>welcome this is my servlet!!!</h1>");    
        out.println("</body>");    
        out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("自定义 Servlet");
        this.doPost(req, resp);
	}

}
