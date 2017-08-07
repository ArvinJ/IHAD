package com.ahhf.ljxbw.filter;

import java.io.IOException;
import java.io.PrintWriter;

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

public class LogFilter implements Filter {
	static Logger logger = LoggerFactory.getLogger(LogFilter.class);
	public FilterConfig config;

	public void destroy() {
		this.config = null;
		logger.info("end do the logging filter!");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// 设置跨域请求
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		// "before the log filter!
		// 将请求转换成HttpServletRequest 请求
		HttpServletRequest request = (HttpServletRequest) req;
		// 请求的uri
		StringBuffer uri = request.getRequestURL();
		String path = request.getContextPath(); // /bandServer
		String protAndPath = request.getServerPort() == 80 ? "" : ":" + request.getServerPort(); // :8080
		String basePath = request.getScheme() + "://" + request.getServerName() + protAndPath + path; // http://localhost:8080/BandServer/

		logger.info(basePath + request.getServletPath());

		// 记录日志
		logger.info("已经截获到用户的请求的地址:" + request.getServletPath());
		
		 // 不过滤的uri
        String[] notFilter = new String[] { "login.html", "index.html" };
  
        // uri中包含background 或 action 时才进行过滤
        if (uri.indexOf("background") != -1 || uri.indexOf("druid")!=-1 || uri.indexOf("action")!=-1) {
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (uri.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if (doFilter) {
                // 执行过滤
                // 从session中获取登录者实体
                Object obj = request.getSession().getAttribute("loginedUser");
                if (null == obj) {
                    // 如果session中不存在登录者实体，则弹出框提示重新登录
                    // 设置request和response的字符集，防止乱码
                	response.setCharacterEncoding("UTF-8");
                	request.setCharacterEncoding("UTF-8");
                    
                    PrintWriter out = response.getWriter();
                    String loginPage = basePath+"/user/login";
                    StringBuilder builder = new StringBuilder();
                    builder.append("<script type=\"text/javascript\">");
                    builder.append("alert('not login');");
                    builder.append("window.top.location.href='");
                    builder.append(loginPage);
                    builder.append("';");
                    builder.append("</script>");
                    out.print(builder.toString());
                } else {
                    // 如果session中存在登录者实体，则继续
                	chain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
            	chain.doFilter(request, response);
            }
        } else {
            // 如果uri中不包含background，则继续
        	chain.doFilter(request, response);
        }
		// "after the log filter!"
	}

	public void init(FilterConfig config) throws ServletException {
		logger.info("begin do the log filter!");
		this.config = config;
	}

	
	public static void main(String[] args) {
		System.out.println("ssssss".indexOf("ss")!=-1);
	}
}