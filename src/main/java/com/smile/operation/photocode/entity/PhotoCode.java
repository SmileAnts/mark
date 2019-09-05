package com.smile.operation.photocode.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smile.operation.common.BaseEntity;
import com.smile.operation.photo.entity.Photo;

@TableName(value = "photo_code")
public class PhotoCode extends BaseEntity {
	private String name;
	@TableField(value = "create_time")
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date createTime;
	@TableField(exist = false)
	private List<Photo> photos;

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

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}
