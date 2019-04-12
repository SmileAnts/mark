package com.smile.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smile.user.entity.User;

@Mapper
public interface UserDao {
	public Long login(@Param("user") User user);
	public User findByUserName(String username);
}
