package com.ahhf.ljxbw.utils;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @ClassName: SqlSessionFactoryUtil
 * @Description: TODO(session会话操作工具类)
 * @author Arvin 庐江小霸王
 * @e-mail 15156980156@163.com
 * @date 2017年7月23日 下午2:56:03
 *
 */
public class SqlSessionFactoryUtil {

	/**
	 * 配置文件的路径
	 */
	private static String CFG_FILE_PATH = "mybatis.xml";

	// 使用ThreadLocal管理Mybatis中SqlSession对象
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	// 读取配置文件的IO流对象
	private static Reader reader = null;
	// 会话工厂
	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			// 加载配置文件
			reader = Resources.getResourceAsReader(CFG_FILE_PATH);
			// 构建会话工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造器注入
	 */
	public SqlSessionFactoryUtil() {

	}

	/**
	 * 获取连接 
	 * 
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if (null == sqlSession) {// 没有获取到连接信息
			if (null == sqlSessionFactory) {// 没有打开的连接
				System.out.println("执行构建新的连接操作。。。");
				rebuildSqlSessionFactory();
			}
			sqlSession = (null != sqlSessionFactory) ? sqlSessionFactory.openSession() : null;
			// 保存会话信息
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}

	/**
	 * 构建新的连接信息 
	 * 
	 * @return void
	 */
	public static void rebuildSqlSessionFactory() {

		try {
			reader = Resources.getResourceAsReader(CFG_FILE_PATH);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 关闭连接信息 
	 * 
	 * @return void
	 */
	public static void closeSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		// 取消保存会话信息
		threadLocal.set(null);
		if (null != sqlSession) {
			System.out.println("执行关闭会话操作。。。");
			sqlSession.close();
		}
	}

	/**
	 * 获取工厂会话对象 
	 * 
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	/**
	 * 获取读取的配置文件的信息 
	 * 
	 * @return Reader
	 */
	public static Reader getCfgInfo() {
		return reader;
	}

}