package com.book.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.book.mapper.RowMapper;

public class JdbcTemplate {
	//返回一个int类型的数，也就是自动生成的下标，
	//需要更新操作的方法，在dao.impl中的insert,update,delete
	public Integer update(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer key = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < args.length; i++) {
				stmt.setObject(i + 1, args[i]);
			}
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(rs, stmt, null);
		}
		return key;
	}
	//返回一个实体类型，也就是从数据库中得到的数据封装成实体类型
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		T result = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stmt.setObject(i + 1, args[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = rowMapper.mapRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(rs, stmt, null);
		}
		return result;
	}
	
	//返回一个集合，用于查询语句获取的是多个值，将查询到的数据一个一个封装放入集合中，在返回集合
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<>();
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stmt.setObject(i + 1, args[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rowMapper.mapRow(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(rs, stmt, null);
		}
		return list;
	}
}
