package com.smile.operation.menu.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
	private Long parentId;
	@TableField(value = "create_time")
	private Date createTime;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
		if (StringUtils.isBlank(url)) {
			this.url = "javascript:;";
		} else {
			this.url = url;
		}
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
