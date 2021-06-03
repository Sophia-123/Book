package com.book.dao;

import java.util.List;

import com.book.util.JdbcTemplate;
//只声明方法，不声明类型，---》范型
//E:entity,表示实体类的类型
//I:id,表示实体类的id类型
//范型不能使用基本类型，所以在定义的时候要使用包装类型
public interface BaseDao<E, I> {
	//
	void insert(E entity);

	void update(E entity);

	void delete(I id);

	E findById(I id);

	List<E> findAll();
	
	void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
