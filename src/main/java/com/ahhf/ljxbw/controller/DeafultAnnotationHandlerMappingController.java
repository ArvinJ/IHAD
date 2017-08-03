package com.ahhf.ljxbw.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/deafult")
/**
 * 
* @ClassName: DeafultAnnotationHandlerMappingController 
* @Description: TODO(第二种annotation请求HandlerMapping方式) 
* @author Arvin 庐江小霸王
* @e-mail 15156980156@163.com 
* @date 2017年7月15日 下午3:35:52 
*
 */
public class DeafultAnnotationHandlerMappingController {
	// RequestMapping表示用那个url来请求访问对应
	@RequestMapping({ "/deafultAnnotation.html", "/", "" })
	// RequestParam("Useranme") 表示接收url 传递过来的参数
	// ".../deafult/deafultAnnotation.html?Username=pro"
	// 若使用RequestRaram进行传参，要不是传递参数就会出现400 的请求出错
	// 参数必须要传递
	public String DeatfultAnnotaiton(@RequestParam("Username") String Name) {
		System.err.println("DeatfaultAnnotation");
		System.out.println(Name);
		return "/deafult/deafult";
	}

	@RequestMapping("secondDeafult.html")
	// 直接以Name 为传递的参数 ，在url请求是可传递可不传递不会报错。"../secondDeafult.html?Name=dfdfd"
	// 参数可以不传递
	public String secondDeafult(String Name) {
		System.out.println(Name);
		return "/deafult/deafult";
	}

	@RequestMapping("thirdDeafult.html")
	// 1.Controller --param---> View
	public String thirdDeafult(String Name, Map<String, Object> context) {
		System.out.println(Name);
		context.put("username", Name);
		return "/deafult/show";
	}

	@RequestMapping("forthDeafult.html")
	// 2. Controller --param---> View
	public String forthDeafult(String Name, Model model) {
		System.out.println(Name);
		model.addAttribute("username", Name);
		//此时用那个作为key?它默认是使用对象的类型作为key-->model.addAttribute("string",username)
		//model.addAttribute(new User());-->addAtt("user",new User());
		model.addAttribute(Name);
		return "/deafult/show";
	}

}
