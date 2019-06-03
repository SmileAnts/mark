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

	private String name;
	@TableField(value = "parent_id")
	private Integer parentId;
	@TableField(value = "create_time")
	private String createTime;
	private String url;
	private Integer sort;
	@TableField(exist = false)
	private List<Menu> list;

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

	public List<Menu> getList() {
		return list;
	}

	public void setList(List<Menu> list) {
		this.list = list;
	}

}
