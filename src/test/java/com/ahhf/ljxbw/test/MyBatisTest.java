package com.ahhf.ljxbw.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ahhf.ljxbw.entity.User;
import com.ahhf.ljxbw.utils.SqlSessionFactoryUtil;
/**
 * 
* @ClassName: MyBatisTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Arvin 庐江小霸王
* @e-mail 15156980156@163.com 
* @date 2017年7月23日 下午3:57:31 
*
*mybatis框架执行过程

1、配置mybatis的配置文件，SqlMapConfig.xml（名称不固定）

2、通过配置文件，加载mybatis运行环境，创建SqlSessionFactory会话工厂(SqlSessionFactory在实际使用时按单例方式)

3、通过SqlSessionFactory创建SqlSession。SqlSession是一个面向用户接口（提供操作数据库方法），实现对象是线程不安全的，建议sqlSession应用场合在方法体内。

4、调用sqlSession的方法去操作数据。如果需要提交事务，需要执行SqlSession的commit()方法。

5、释放资源，关闭SqlSession
*
*
*
*
*
*
*
*
*
*
 */
public class MyBatisTest {
	public static void main(String[] args) throws IOException {
		// mybatis的配置文件
		String resource = "mybatis.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = MyBatisTest.class.getClassLoader().getResourceAsStream(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		// 创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession();
		/**
		 * 映射sql的标识字符串， com.ahhf.ljxbw.mapping.userMapper是userMapper.
		 * xml文件中mapper标签的namespace属性的值，
		 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "com.ahhf.ljxbw.mapping.userMapper.getUser";// 映射sql的标识字符串
		String statement1 = "com.ahhf.ljxbw.mapping.userMapper.selectUserByName";// 映射sql的标识字符串
		
		// 执行查询返回一个唯一user对象的sql
		User user = session.selectOne(statement, 1);
		List<User> userlist = new ArrayList<User>();
		userlist = session.selectList(statement1, "Amy");
		for (User user2 : userlist) {
			System.out.println(user2.toString());
		}
		session.close();
		
	}

	@Test
	public void addUser() {
		User u = new User();
		u.setUsername("Bob");
		u.setPassword("123456");
		u.setSequence(1);
		SqlSessionFactoryUtil.getSqlSession().insert("com.ahhf.ljxbw.mapping.userMapper.addUser", u);
		// 关闭连接信息
		SqlSessionFactoryUtil.getSqlSession().commit();
		SqlSessionFactoryUtil.getSqlSession().close();

	}

	@Test
	public void del() {

		SqlSessionFactoryUtil.getSqlSession().delete("com.ahhf.ljxbw.mapping.userMapper.deleteUser", 1);
		// 关闭连接信息
		SqlSessionFactoryUtil.getSqlSession().commit();
		SqlSessionFactoryUtil.getSqlSession().close();

	}

	@Test
	public void update() {
		User u = new User();
		u.setId(1);
		u.setUsername("Arvin");
		u.setPassword("11233");
		u.setSequence(1);
		SqlSessionFactoryUtil.getSqlSession().update("com.ahhf.ljxbw.mapping.userMapper.updateUser", u);
		// 关闭连接信息
		SqlSessionFactoryUtil.getSqlSession().commit();
		SqlSessionFactoryUtil.getSqlSession().close();

	}
	
	@Test
	public void selectByUserName(){
		SqlSessionFactoryUtil.getSqlSession().select("com.ahhf.ljxbw.mapping.userMapper.selectUserByName", "Amy", null);
	}

}
