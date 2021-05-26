package com.book.service.impl;

import java.util.List;

import com.book.dao.AccountDao;
import com.book.entity.Account;
import com.book.service.AccountService;

public class DefaultAccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	@Override
	public void insert(Account account) {
		this.accountDao.insert(account);
	}

	@Override
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public List<Account> findAll() {
		return this.accountDao.findAll();
	}

}
