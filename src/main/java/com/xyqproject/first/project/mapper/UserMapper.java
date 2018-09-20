package com.xyqproject.first.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xyqproject.first.project.entity.User;

@Mapper
public interface UserMapper {
	public Long login(@Param("user") User user);
	public User findByUserName(String username);
}
