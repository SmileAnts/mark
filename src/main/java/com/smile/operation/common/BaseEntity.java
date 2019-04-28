package com.smile.operation.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -763618247875550322L;
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
