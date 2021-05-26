package com.book.test;

import java.sql.SQLException;

import com.book.dao.AccountDao;
import com.book.dao.impl.DefaultAccountDaoImpl;
import com.book.entity.Account;
import com.book.entity.UserType;
import com.book.service.AccountService;
import com.book.service.impl.DefaultAccountServiceImpl;
import com.book.tx.TransactionProxy;
import com.book.util.JdbcTemplate;

public class Test {
	public static void main(String[] args) throws SQLException {
		AccountService accountService = TransactionProxy.addTransaction(new DefaultAccountServiceImpl());
		AccountDao accountDao = new DefaultAccountDaoImpl();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		accountDao.setJdbcTemplate(jdbcTemplate);
		accountService.setAccountDao(accountDao);

//		Account account = new Account("admin", "admin", UserType.ADMIN);
//		accountService.insert(account);
//		System.out.println("OK");

		accountService.findAll().forEach(System.out::println);
	}
}
