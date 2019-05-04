package com.smile.operation.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.smile.operation.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	public Long login(@Param("user") User user);

	public User findByUserName(@Param("username") String username);
}
