package com.ahhf.ljxbw.dao;

import java.util.List;

import com.ahhf.ljxbw.entity.User;
/**
 * 
* @ClassName: UserDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Arvin 庐江小霸王
* @e-mail 15156980156@163.com 
* @date 2017年7月23日 下午4:42:07 
*
 */
public interface UserDao {

	// 根据id查询用户信息
	public User findUserById(int id) throws Exception;

	// 根据用户名列查询用户列表
	public List<User> findUserByName(String name) throws Exception;

	// 添加用户信息
	public void insertUser(User user) throws Exception;

	// 删除用户信息
	public void deleteUser(int id) throws Exception;
}
