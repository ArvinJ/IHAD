package com.ahhf.ljxbw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahhf.ljxbw.entity.User;
import com.ahhf.ljxbw.mapping.UserMapper;
import com.ahhf.ljxbw.utils.SqlSessionFactoryUtil;

@Controller
public class UserController {

	@RequestMapping(value = "user/login.html", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

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

		return user;
	}
}
