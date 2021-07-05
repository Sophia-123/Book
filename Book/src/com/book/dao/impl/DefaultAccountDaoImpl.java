package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.book.dao.AccountDao;
import com.book.entity.Account;
import com.book.entity.UserType;
import com.book.mapper.RowMapper;
import com.book.util.JdbcTemplate;

public class DefaultAccountDaoImpl implements AccountDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Account entity) {
		this.jdbcTemplate.update("insert into t_account values(?,?,?)", entity.getUsername(), entity.getPassword(),
				entity.getUserType().ordinal());
	}

	@Override
	public void update(Account entity) {
		this.jdbcTemplate.update("update t_account set password = ? ,usertype=? where username=?",entity.getPassword(),entity.getUserType().ordinal(),entity.getUsername());

	}

	@Override
	public void delete(String id) {
		this.jdbcTemplate.update("delete from t_account where username=?", id);

	}

	@Override
	public Account findById(String id) {
		return this.jdbcTemplate.queryForObject("select username,password,usertype from t_account where username = ?",new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs) throws SQLException {
				return new Account(rs.getString("username"), rs.getString("password"),
						UserType.values()[rs.getInt("usertype")]);
			}

		},id);
	}

	public List<Account> findAll() {
		return this.jdbcTemplate.query("select username,password,usertype from t_account", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs) throws SQLException {
				return new Account(rs.getString("username"), rs.getString("password"),
						UserType.values()[rs.getInt("usertype")]);
			}

		});
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
