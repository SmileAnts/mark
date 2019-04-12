package com.smile.auth.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.smile.operation.user.entity.User;

@Entity
public class Role {
	@Id
	private Long rid;
	private String roleName;
	@ManyToMany
	private List<User> users;
	@OneToMany
	private Set<Module> permissions;

	public Long getId() {
		return rid;
	}

	public void setId(Long rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Set<Module> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Module> permissions) {
		this.permissions = permissions;
	}

}
