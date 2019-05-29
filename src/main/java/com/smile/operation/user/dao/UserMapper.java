package com.smile.operation.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.smile.operation.user.entity.Users;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

	public Long login(Users user);

	public Users findByUserName(String username);
}
