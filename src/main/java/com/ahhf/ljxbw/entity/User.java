package com.ahhf.ljxbw.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @ClassName: User
 * @Description: TODO(user表所对应的实体类)
 * @author Arvin 庐江小霸王
 * @e-mail 15156980156@163.com
 * @date 2017年7月23日 下午2:29:08
 *
 */
public class User {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// // 实体类的属性和表的字段名称一一对应
	private Integer id;
	private String username;
	private String password;
	private int sequence;
	private String email;
	private String createDateTime;
	private String updateDateTime;
	private int status;

	public User() {
	}
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.createDateTime = df.format(new Date());
		this.updateDateTime = df.format(new Date());
		this.status = 00;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotEmpty(message = "用户名不能为空")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 6, max = 10, message = "密码的长度应该在6和10之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@NotEmpty(message = "邮箱不能为空")
	@Email(message = "邮箱格式不正确")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	/**
	 * 
	 * @Title: getCreateDateTime @Description: TODO(@JsonIgnore
	 *         注解使用在属性对应的get方法头上【返回user的json字符串中过滤createDateTime】
	 *         ) @param @return 设定文件 @return String 返回类型 @author ZWJ @throws
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@JsonIgnore
	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [df=" + df + ", id=" + id + ", username=" + username + ", password=" + password + ", sequence="
				+ sequence + ", email=" + email + ", createDateTime=" + createDateTime + ", updateDateTime="
				+ updateDateTime + ", status=" + status + "]";
	}

}
