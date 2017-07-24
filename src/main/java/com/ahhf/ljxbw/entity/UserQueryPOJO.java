package com.ahhf.ljxbw.entity;
/**
 * 
* @ClassName: UserQueryPOJO 
* @Description: TODO(传递pojo的包装对象) 
* @author wenjin.zhu
* @date 2017年7月24日 下午3:10:28 
*
 */
public class UserQueryPOJO {
	// 在这里包装所需要的查询条件

	// 用户查询条件
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	// 可以包装其它的查询条件，订单、商品
	// ....

}
