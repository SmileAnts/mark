package com.smile.operation.menu.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.smile.operation.common.BaseEntity;

/**
 * 菜单
 * 
 * @author 许永强
 *
 */
@TableName(value = "menu")
public class Menu extends BaseEntity {
	private static final long serialVersionUID = 2948894578410132924L;
	private String name;
	@TableField(value = "parent_id")
	private Integer parentId;
	@TableField(value = "create_time")
	private String createTime;
	private String url;
	private Integer sort;
	@TableField(exist = false)
	private List<Menu> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

}
