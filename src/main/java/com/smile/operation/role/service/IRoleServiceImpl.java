package com.smile.operation.role.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.role.dao.RoleMapper;
import com.smile.operation.role.entity.Role;

@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

}
