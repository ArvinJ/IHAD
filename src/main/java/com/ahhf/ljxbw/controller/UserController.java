package com.ahhf.ljxbw.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ahhf.ljxbw.entity.User;
import com.ahhf.ljxbw.exception.UserException;
import com.ahhf.ljxbw.mapping.UserMapper;
import com.ahhf.ljxbw.utils.SqlSessionFactoryUtil;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping(value="/user")
public class UserController {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	@RequestMapping(value = "/data")
	@ResponseBody
	public Map<String, Object> data() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 20; i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", "name" + i);
			data.add(m);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		return map;
	}
	

	@RequestMapping(value = "user/userInfo.action")
	@ResponseBody
	public User UserInfo() throws Exception {
		// String resource = "mybatis.xml";
		// // 将配置文件加载成流
		// InputStream inputStream = Resources.getResourceAsStream(resource);
		// // 创建会话工厂，传入mybatis配置文件的信息
		// SqlSessionFactory sqlSessionFactory = new
		// SqlSessionFactoryBuilder().build(inputStream);
		// SqlSession sqlSession = sqlSessionFactory.openSession();

		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建UserMapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 调用userMapper的方法
		User user = userMapper.findUserById(1);
		//String formatDate = df.format(user.getUpdateDateTime());
		return user;
	}

	private Map<String, User> users1 = new HashMap<String, User>();
	
	public UserMapper findUserMapper()  {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建UserMapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		return userMapper;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
 	public String list(Model model) throws Exception {
		
		// 调用userMapper的方法
		List<User> users = findUserMapper().findUsers();
		
		model.addAttribute("users", users);
		return "/user/list";
	}

	// 链接到add页面是get请求 一般 GET 请求 默认到页面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new User());
		return "/user/add";
	}

	// 添加用户，post请求
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated User user, BindingResult br) throws Exception {// 一定要紧跟Validate之后写验证结果
		if (br.hasErrors()) {
			// 如果有错,直接返回到add.jsp去显示
			return "/user/add";
		}
//		user.setId(null);
		//findUserMapper().insertUser(user);
		user.setEmail("xxxx@zwj.com");
		user.setStatus(0);
		user.setCreateDateTime(sdf.format(new Date()));
		findUserMapper().addUser(user);
		return "redirect:/user/users";
	}
	
	/*	
	//在具体添加用户时，是post请求，就访问以下代码
		@RequestMapping(value="/add",method=RequestMethod.POST)
		public String add(@Validated User user,BindingResult br,@RequestParam("attachs")MultipartFile[] attachs,HttpServletRequest req) throws IOException {//一定要紧跟Validate之后写验证结果类
			if(br.hasErrors()) {
				//如果有错误直接跳转到add视图
				return "user/add";
			}
			String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
			System.out.println(realpath);
			for(MultipartFile attach:attachs) {
				if(attach.isEmpty()) continue;
				File f = new File(realpath+"/"+attach.getOriginalFilename());
				FileUtils.copyInputStreamToFile(attach.getInputStream(),f);
			}
			users.put(user.getUsername(), user);
			return "redirect:/user/users";
		}
	
	
*/
	/*// 添加用户含上传文档，post请求
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public String add(@Validated User user, BindingResult br,MultipartFile attach,HttpServletRequest req) throws IOException {// 一定要紧跟Validate之后写验证结果
			System.out.println(br.hasErrors());
			if (br.hasErrors()) {
				// 如果有错,直接返回到add.jsp去显示
				return "/user/add";
			}
			System.out.println("ariginalName"+attach.getOriginalFilename());
			String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
			System.out.println("path:"+realpath);
			File f = new File(realpath+"/"+attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(),f);
			users1.put(user.getUsername(), user);
			return "redirect:/user/users";
		}*/
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String show(@PathVariable String username, Model model) {
		System.out.println("username" + username);
		// model.addAttribute(users.get(username));
		model.addAttribute("user", users1.get(username));
		return "user/show";
	}

	@RequestMapping(value="/{username}",method=RequestMethod.GET,params="json")
	@ResponseBody
	public User show(@PathVariable String username) {
		System.out.println("ddddddddddddd"+users1.get(username).toString());
		return users1.get(username);
	}
	
	
	
	@RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
	public String update(@PathVariable String username, Model model) {
		// model.addAttribute(users.get(username));
		model.addAttribute("user", users1.get(username));
		return "user/update";
	}

	@RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
	public String update(@Validated User user, BindingResult br, @PathVariable String username) {
		System.out.println("update----"+username);
		if (br.hasErrors())
			return "/user/update";
		System.out.println(user.toString());
		users1.put(username, user);
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/{username}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String username) {
		System.out.println("del--"+username);
		users1.remove(username);
		return "redirect:/user/users";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/user/login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session, Model model) throws Exception {
		logger.info("username:" + username + "password:" + password);
		if (username == null || username == "") {
			model.addAttribute("error", "用户名不能为空！");
			return "/user/login";
		} else if (password == null || password == "") {
			model.addAttribute("error", "密码不能为空！");
			return "/user/login";
		}

//		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		// 创建UserMapper代理对象
//		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("username", username.trim());
		hmap.put("password", password.trim());
		// hmap.put("password",MD5Util.string2MD5(password.trim()));
		User user = findUserMapper().login(hmap);

		if (user == null) {
			model.addAttribute("error", "用户名或密码错误！");
			return "user/login";
		} else {
			session.setAttribute("loginUser", user);
			return "redirect:/user/users";
		}

	}

}
