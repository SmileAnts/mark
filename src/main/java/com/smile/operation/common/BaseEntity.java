package com.smile.operation.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) // 将Long转为String
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -763618247875550322L;
	@Id
	@TableField(value = "id")
	@JsonSerialize(using = ToStringSerializer.class) // 将Long转为String
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
