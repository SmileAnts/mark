package com.smile.operation.photo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smile.operation.common.BaseEntity;

@TableName(value = "photo")
public class Photo extends BaseEntity {
	private String name;
	@TableField(value = "create_time")
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date createTime;
	private String url;
	@TableField(value = "photo_code_id")
	private Long photoCodeId;
	@TableField(exist = false)
	private String photoName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPhotoCodeId() {
		return photoCodeId;
	}

	public void setPhotoCodeId(Long photoCodeId) {
		this.photoCodeId = photoCodeId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

}
