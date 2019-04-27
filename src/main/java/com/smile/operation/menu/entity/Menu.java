package com.smile.operation.menu.entity;

import com.smile.operation.common.BaseController;

/**
 * 菜单
 * 
 * @author smile
 *
 */
public class Menu extends BaseController {

	private String name;
	private Long parentId;
	private String createTime;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
