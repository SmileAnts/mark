package com.smile.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.smile.operation.common.BaseEntity;

/**
 * sql 查询拼接类
 * 
 * @author 许永强
 *
 */
public class WrapperUtil {

	public static <T extends BaseEntity> EntityWrapper<T> selectList(T entity, String... params) {
		EntityWrapper<T> wrpper = new EntityWrapper<T>(entity);
		if (params.length > 0) {
			wrpper.setSqlSelect(params);
		}
		return wrpper;
	}

	public static <T extends BaseEntity> EntityWrapper<T> selectOne(T entity, String... params) {
		EntityWrapper<T> wrpper = new EntityWrapper<T>(entity);
		wrpper.eq("id", entity.getId());
		if (params.length > 0) {
			wrpper.setSqlSelect(params);
		}
		return wrpper;
	}

}
