package com.ahhf.ljxbw.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.ahhf.ljxbw.entity.User;
import com.ahhf.ljxbw.entity.UserCustom;
import com.ahhf.ljxbw.entity.UserQueryPOJO;
import com.ahhf.ljxbw.mapping.UserMapper;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	// 注解Before是在执行本类所有测试方法之前先调用这个方法
	@Before
	public void setup() throws Exception {
		// 创建SqlSessionFactory
		String resource = "mybatis.xml";

		// 将配置文件加载成流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂，传入mybatis配置文件的信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 创建UserMapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 调用userMapper的方法
		User user = userMapper.findUserById(1);

		System.out.println(user.getUsername());
	}

	// 用户信息的综合 查询
	@Test
		public void testFindUserList() throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			//创建UserMapper对象，mybatis自动生成mapper代理对象
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

			//创建包装对象，设置查询条件
			UserQueryPOJO userQueryVo = new UserQueryPOJO();
			UserCustom userCustom = new UserCustom();
			//由于这里使用动态sql，如果不设置某个值，条件不会拼接在sql中
			//userCustom.setPassword("123");
			userCustom.setUsername("ss");
			userQueryVo.setUserCustom(userCustom);
			//调用userMapper的方法
			List<UserCustom> list = userMapper.findUserList(userQueryVo);
			System.out.println(list);


		}
}
