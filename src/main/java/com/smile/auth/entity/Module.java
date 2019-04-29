package com.smile.auth.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.transaction.annotation.Transactional;

import com.smile.operation.common.BaseEntity;

@Entity
public class Module extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String mname;
	@ManyToOne
	private Role role;

	@Transactional
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}
}
