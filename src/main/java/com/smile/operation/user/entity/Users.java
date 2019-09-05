package com.smile.operation.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotations.TableField;
import com.smile.operation.common.BaseEntity;

/**
 * 用户实体
 * 
 * @author 许永强
 */
@Entity
@Table(name = "users")
public class Users extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	@TableField(value = "regist_time")
	private Date registTime;
	@TableField(value = "is_locked")
	private Boolean locked;
	@TableField(value = "lock_time")
	private Date lockTime;

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

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

}
