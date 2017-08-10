package com.ahhf.ljxbw.mapping;

import java.util.List;
import java.util.Map;

import com.ahhf.ljxbw.entity.User;
import com.ahhf.ljxbw.entity.UserCustom;
import com.ahhf.ljxbw.entity.UserQueryPOJO;

public interface UserMapper {
    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws Exception;

    //删除用户信息
    public void deleteUser(int id) throws Exception;

    //更新用户
    public void updateUser(User user)throws Exception;
    // 用户信息综合查询
    public List<UserCustom> findUserList(UserQueryPOJO userQueryVo) throws Exception;
    /**
     * 
    * @Title: findUsers 
    * @Description: TODO(查询所有的user) 
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<User>    返回类型 
    * @author ZWJ
    * @throws
     */
    public List<User> findUsers()throws Exception;
    /**
     * 
    * @Title: addUser 
    * @Description: TODO(添加用户) 
    * @param @param user
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @author ZWJ
    * @throws
     */
    
    public void addUser(User user)throws Exception;
    /**
     * 
    * @Title: login 
    * @Description: TODO(登录) 
    * @param @param param
    * @param @return
    * @param @throws Exception    设定文件 
    * @return User    返回类型 
    * @author ZWJ
    * @throws
     */
    public User login(Map<String, Object> param)throws Exception;
    
}
