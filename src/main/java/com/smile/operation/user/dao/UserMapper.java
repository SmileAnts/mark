package com.smile.operation.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.smile.operation.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	public Long login(User user);

	public User findByUserName(String username);
}
