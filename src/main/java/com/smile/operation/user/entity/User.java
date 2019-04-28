package com.smile.operation.user.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.smile.auth.entity.Role;
import com.smile.operation.common.BaseEntity;

/**
 * 用户实体
 * 
 * @author 许永强
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {
	private String username;
	private String password;
	@ManyToMany
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

}
