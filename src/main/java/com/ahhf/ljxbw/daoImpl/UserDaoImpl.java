package com.ahhf.ljxbw.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ahhf.ljxbw.dao.UserDao;
import com.ahhf.ljxbw.entity.User;

public class UserDaoImpl implements UserDao {
	// 需要向dao实现类中注入SqlSessionFactory
	// 这里通过构造方法注入
	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("com.ahhf.ljxbw.mapping.userMapper.findUserById", id);
		// 释放资源
		sqlSession.close();
		return user;
	}

	public List<User> findUserByName(String name) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> list = sqlSession.selectList("com.ahhf.ljxbw.mapping.userMapper.findUserByName", name);

		// 释放资源
		sqlSession.close();

		return list;
	}

	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 执行插入操作
		sqlSession.insert("com.ahhf.ljxbw.mapping.userMapper.insertUser", user);

		// 提交事务
		sqlSession.commit();

		// 释放资源
		sqlSession.close();
	}

	public void deleteUser(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 执行插入操作
		sqlSession.delete("com.ahhf.ljxbw.mapping.userMapper.deleteUser", id);

		// 提交事务
		sqlSession.commit();

		// 释放资源
		sqlSession.close();
	}

}
