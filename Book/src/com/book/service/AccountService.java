package com.book.service;

import java.util.List;

import com.book.dao.AccountDao;
import com.book.entity.Account;

public interface AccountService {
	void insert(Account account);

	List<Account> findAll();

	void setAccountDao(AccountDao accountDao);
}
