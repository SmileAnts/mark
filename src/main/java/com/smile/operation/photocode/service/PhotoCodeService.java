package com.smile.operation.photocode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.photocode.dao.PhotoCodeMapper;
import com.smile.operation.photocode.entity.PhotoCode;
import com.smile.operation.redis.RedisClient;

@Service
public class PhotoCodeService extends ServiceImpl<PhotoCodeMapper, PhotoCode> {
	@Autowired
	private RedisClient redis;

	/**
	 * 新增相册，有id进行更新，没有id利用redis添加id进行新增
	 * 
	 * @param photocode
	 * @return
	 */
	public Boolean save(PhotoCode photocode) {
		if (photocode.getId() == null) {
			photocode.setId(redis.nextId());
		}
		return insertOrUpdate(photocode);
	}

	@Transactional
	public Boolean deleteIds(Long[] ids) {
		for (Long id : ids) {
			deleteById(id);
		}
		return Boolean.TRUE;
	}
}
