package com.smile.operation.role.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotations.TableField;
import com.smile.operation.common.BaseEntity;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
	private String name; // 角色名称
	private String descr; // 角色描述
	@TableField(value = "create_time")
	private Date createTime; // 创建时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
