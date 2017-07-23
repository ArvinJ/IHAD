package com.ahhf.ljxbw.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * 
* @ClassName: TestJDBCMain 
* @Description: TODO(jdbc编程) 
* @author Arvin 庐江小霸王
* @e-mail 15156980156@163.com 
* @date 2017年7月23日 下午3:42:09 
*
*jdbc编程步骤
*
*加载数据库驱动
*创建并获取数据库链接
*创建jdbc statement对象
*设置sql语句
*设置sql语句中的参数(使用preparedStatement)
*通过statement执行sql并获取结果
*对sql执行结果进行解析处理
*释放资源(resultSet、preparedstatement、connection)
*
*
*问题总结

1.数据库连接，使用时就创建，不使用立即释放，对数据库进行频繁连接开启和关闭，造成数据库资源浪费，影响数据库性能。

设想：使用数据库连接池管理数据库连接。

2.将sql语句硬编码到java代码中，如果sql语句修改，需要重新编译java代码，不利于系统维护。

设想：将sql语句配置在xml配置文件中，即使sql变化，不需要对java代码进行重新编译。

3.向preparedStatement中设置参数，对占位符号位置和设置参数值，硬编码在java代码中，不利于系统维护。

设想：将sql语句及占位符号和参数全部配置在xml中。

4.从resutSet中遍历结果集数据时，存在硬编码，将获取表的字段进行硬编码，不利于系统维护。

设想：将查询的结果集，自动映射成java对象。
*
*
*
*
*
*
 */
public class JDBCTest {
	static Logger logger = (Logger) LoggerFactory.getLogger(JDBCTest.class);

	public static void main(String[] args) {
        //数据库连接
        Connection connection = null;
        //预编译的Statement，使用预编译的Statement提高数据库性能
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");

            //通过驱动管理类获取数据库链接
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/IHAD?characterEncoding=utf-8", "root", "yandzstory2017");
            //定义sql语句 ?表示占位符
            String sql = "select * from user where username = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "Amy");
            //向数据库发出sql执行查询，查询出结果集
            resultSet =  preparedStatement.executeQuery();
            //遍历查询结果集
            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+"  "+resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }


}
