package com.book.service;

import java.util.List;

import com.book.dao.AccountDao;
import com.book.entity.Account;
//使用接口是为了解耦合，业务处理服务Server
public interface AccountService {
	//业务方法
	void insert(Account account);
	void update(Account account);
	void delete(String id);
	Account findById(String id);
	List<Account> findAll();
	
	//数据访问方法
	void setAccountDao(AccountDao accountDao);
}
