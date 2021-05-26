package com.book.dao;

import java.util.List;

import com.book.util.JdbcTemplate;

public interface BaseDao<E, I> {
	void insert(E entity);

	void update(E entity);

	void delete(I id);

	E findById(I id);

	List<E> findAll();
	
	void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
