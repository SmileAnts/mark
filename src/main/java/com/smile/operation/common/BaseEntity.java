package com.smile.operation.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotations.TableField;

@Entity
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -763618247875550322L;
	@Id
	@TableField(value = "id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
