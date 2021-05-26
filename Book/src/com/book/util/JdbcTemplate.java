package com.book.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.book.mapper.RowMapper;

public class JdbcTemplate {

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
