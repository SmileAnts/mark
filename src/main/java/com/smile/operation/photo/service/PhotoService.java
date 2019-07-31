package com.smile.operation.photo.service;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smile.operation.photo.dao.PhotoMapper;
import com.smile.operation.photo.entity.Photo;
import com.smile.operation.photocode.service.PhotoCodeService;
import com.smile.operation.redis.RedisClient;
import com.smile.util.WrapperUtil;

@Service
public class PhotoService extends ServiceImpl<PhotoMapper, Photo> {
	@Autowired
	private PhotoCodeService photoService;
	@Autowired
	private RedisClient redis;

	/**
	 * 查询所有相片
	 * 
	 * @param photo
	 * @return
	 */
	public List<Photo> selectAll(Photo photo) {
		Validate.isTrue(photo != null, "photo is not null,select is false");
		List<Photo> photos = selectList(WrapperUtil.selectList(photo));
		photos.forEach(ph -> {
			ph.setPhotoName(photoService.selectById(ph.getPhotoCodeId()).getName());
		});
		return photos;
	}

	/**
	 * 根据相册id 查询相片
	 * 
	 * @param photoCodeId
	 * @return
	 */
	public List<Photo> selectByCode(Long photoCodeId) {
		Validate.isTrue(photoCodeId != null, "photoCodeId is not null");
		EntityWrapper<Photo> wrapper = WrapperUtil.selectList(new Photo());
		return selectList(wrapper.eq("photo_code_id", photoCodeId));
	}

	/**
	 * 保存相片
	 * 
	 * @param photo
	 * @return
	 */
	public Boolean save(Photo photo) {
		Validate.isTrue(photo != null, "photo is not null,save is false");
		if (photo.getId() == null) {
			photo.setId(redis.nextId());
		}
		return insertOrUpdate(photo);
	}

	/**
	 * 删除相片
	 * 
	 * @param ids
	 * @return
	 */
	public Boolean deleteIds(Long[] ids) {
		Validate.isTrue(ids != null && ids.length > 0, "ids is null or ids length is too small");
		for (Long id : ids) {
			deleteById(id);
		}
		return Boolean.TRUE;
	}
}
