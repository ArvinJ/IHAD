package com.ahhf.ljxbw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ahhf.ljxbw.test.LogBackTest;

public class LogFilter implements Filter {
	static Logger logger = LoggerFactory.getLogger(LogFilter.class);
	public FilterConfig config;

	public void destroy() {
		this.config = null;
		System.out.println("end do the logging filter!");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		//设置跨域请求  
        HttpServletResponse response = (HttpServletResponse) res;   
        response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		//"before the log filter!
		// 将请求转换成HttpServletRequest 请求
		HttpServletRequest hreq = (HttpServletRequest) req;
		
		StringBuffer url = hreq.getRequestURL();
		String path = hreq.getContextPath(); // /bandServer
		String protAndPath = hreq.getServerPort() == 80 ? "" : ":" + hreq.getServerPort(); // :8080
		String basePath = hreq.getScheme() + "://" + hreq.getServerName() + protAndPath + path ; // http://localhost:8080/BandServer/
		
		logger.info(basePath+hreq.getServletPath());
		
		// 记录日志
		logger.info("已经截获到用户的请求的地址:" + hreq.getServletPath());
		try {
			// Filter 只是链式处理，请求依然转发到目的地址。
			chain.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//"after the log filter!"
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("begin do the log filter!");
		this.config = config;
	}

}