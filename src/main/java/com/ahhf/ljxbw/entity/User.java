package com.ahhf.ljxbw.entity;

import java.util.Date;

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
	// // 实体类的属性和表的字段名称一一对应
	private int id;
	private String username;
	private String password;
	private int sequence;
	private Date createDateTime;
	private Date updateDateTime;
	private int status;

	public User() {
	}

	public User( String username, String password, int sequence) {
		this.username = username;
		this.password = password;
		this.sequence = sequence;
		this.createDateTime = new Date();
		this.updateDateTime = new Date();
		this.status = 00;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", sequence=" + sequence
				+ ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + ", status=" + status
				+ "]";
	}

}
