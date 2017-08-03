package com.ahhf.ljxbw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 * 
* @ClassName: BeanNameUrlHandlerMappingController 
* @Description: TODO(第一种直接url请求HandlerMapping方式) 
* @author Arvin 庐江小霸王
* @e-mail 15156980156@163.com 
* @date 2017年7月15日 下午3:35:00 
*
 */
public class BeanNameUrlHandlerMappingController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		System.err.println("beanNameUrlHandlerMapping---->AbstractController");
		return new ModelAndView("/deafult/deafult");
	}

}
