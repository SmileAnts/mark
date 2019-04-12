package com.smile.auth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.transaction.annotation.Transactional;

@Entity
public class Module {
	@Id
	private Long mid;
	private String mname;
	@ManyToOne
	private Role role;

	public Long getId() {
		return mid;
	}

	public void setId(Long mid) {
		this.mid = mid;
	}

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
