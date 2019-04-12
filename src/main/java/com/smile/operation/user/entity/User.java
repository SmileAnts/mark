package com.smile.operation.user.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.smile.auth.entity.Role;
/**
  *  用户实体
 * @author 许永强
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long uid;
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

	public Long getId() {
		return uid;
	}

	public void setId(Long uid) {
		this.uid = uid;
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
