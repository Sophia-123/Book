package com.book.test;

import java.sql.SQLException;

import com.book.dao.AccountDao;
import com.book.dao.BookDao;
import com.book.dao.BookPictureDao;
import com.book.dao.BookTypeDao;
import com.book.dao.CartDao;
import com.book.dao.CustomDao;
import com.book.dao.OrderDao;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.DefaultAccountDaoImpl;
import com.book.dao.impl.DefaultBookDaoImpl;
import com.book.dao.impl.DefaultBookPictureDaoImpl;
import com.book.dao.impl.DefaultBookTypeDaoImpl;
import com.book.dao.impl.DefaultCartDaoImpl;
import com.book.dao.impl.DefaultCustomDaoImpl;
import com.book.dao.impl.DefaultOrderDaoImpl;
import com.book.dao.impl.DefaultOrderItemDaoImpl;
import com.book.entity.OrderItem;
import com.book.service.AccountService;
import com.book.service.BookPictureService;
import com.book.service.BookService;
import com.book.service.BookTypeService;
import com.book.service.CartService;
import com.book.service.CustomService;
import com.book.service.OrderItemService;
import com.book.service.OrderService;
import com.book.service.impl.DefaultAccountServiceImpl;
import com.book.service.impl.DefaultBookPictureServiceImpl;
import com.book.service.impl.DefaultBookServiceImpl;
import com.book.service.impl.DefaultBookTypeServiceImpl;
import com.book.service.impl.DefaultCartServiceImpl;
import com.book.service.impl.DefaultCustomServiceImpl;
import com.book.service.impl.DefaultOrderItemServiceImpl;
import com.book.service.impl.DefaultOrderServiceImpl;
import com.book.tx.TransactionProxy;
import com.book.util.JdbcTemplate;

public class Test {
	public static void main(String[] args) throws SQLException {
//		Connection conn = JdbcUtil.getConnection();
//		System.out.println(conn);
		AccountService accountService = TransactionProxy.addTransaction(new DefaultAccountServiceImpl());//调用之后会关闭相应的资源
		AccountDao accountDao = new DefaultAccountDaoImpl();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		accountDao.setJdbcTemplate(jdbcTemplate);//进一步简化了代码，提高代码复用率
		accountService.setAccountDao(accountDao);
		
		CustomService customService = TransactionProxy.addTransaction(new DefaultCustomServiceImpl());
		CustomDao customDao = new DefaultCustomDaoImpl();
		customDao.setJdbcTemplate(jdbcTemplate);
		customService.setCustomDao(customDao);
		
		BookTypeService bookTypeService = TransactionProxy.addTransaction(new DefaultBookTypeServiceImpl());
		BookTypeDao bookTypeDao = new DefaultBookTypeDaoImpl();
		bookTypeDao.setJdbcTemplate(jdbcTemplate);
		bookTypeService.setBookTypeDao(bookTypeDao);
		
		BookService bookService = TransactionProxy.addTransaction(new DefaultBookServiceImpl());
		BookDao bookDao = new DefaultBookDaoImpl();
		bookDao.setJdbcTemplate(jdbcTemplate);
		bookService.setBookDao(bookDao);
		
		OrderService orderService = TransactionProxy.addTransaction(new DefaultOrderServiceImpl());
		OrderDao orderDao = new DefaultOrderDaoImpl();
		orderDao.setJdbcTemplate(jdbcTemplate);
		orderService.setOrderDao(orderDao);
		
		CartService cartService = TransactionProxy.addTransaction(new DefaultCartServiceImpl());
		CartDao cartDao = new DefaultCartDaoImpl();
		cartDao.setJdbcTemplate(jdbcTemplate);
		cartService.setCartDao(cartDao);
		
		BookPictureService bookPictureService = TransactionProxy.addTransaction(new DefaultBookPictureServiceImpl());
		BookPictureDao bookPictureDao = new DefaultBookPictureDaoImpl();
		bookPictureDao.setJdbcTemplate(jdbcTemplate);
		bookPictureService.setBookPictureDao(bookPictureDao);
		
		OrderItemService orderItemService = TransactionProxy.addTransaction(new DefaultOrderItemServiceImpl());
		OrderItemDao orderItemDao = new DefaultOrderItemDaoImpl();
		orderItemDao.setJdbcTemplate(jdbcTemplate);
		orderItemService.setOrderItemDao(orderItemDao);
		
//		OrderItem oi = new OrderItem(null,orderService.findById(2),bookService.findById(1),100,99.9);
//		orderItemService.insert(oi);
//		OrderItem oi1 = orderItemService.findById(1);
//		System.out.println(oi1);
//		oi1.setPrice(66.6);
//		orderItemService.update(oi1);
//		orderItemService.findAll().forEach(System.out::println);
//		orderItemService.findByBookId(1).forEach(System.out::println);
//		orderItemService.findByOrderId(2).forEach(System.out::println);;
		
//		BookPicture bp = new BookPicture(null,bookService.findById(3),"d:",3.0);
//		bookPictureService.insert(bp);
//		bookPictureService.findAll().forEach(System.out::println);
//		BookPicture bp1 = bookPictureService.findById(2);
//		bp1.setPath("c:");
//		bookPictureService.update(bp1);
//		bookPictureService.findByBookId(3).forEach(System.out::println);;
//		bookPictureService.delete(3);
		
//		Cart c = new Cart(null,orderService.findById(2),bookService.findById(3),1,new Date(1));
//		cartService.insert(c);
//		Cart c1 = cartService.findById(2);
//		System.out.println(c1);
//		c1.setAddDate(new Date(3));
//		c1.setTotal(10);
//		cartService.update(c1);
//		cartService.findAll().forEach(System.out::println);\
//		cartService.findByBookId(3).forEach(System.out::println);
//		cartService.findByOrderUserName("sophia2").forEach(System.out::println);;
//		cartService.delete(2);
		
//		Order o = new Order(null,customService.findById("sophia2"),new Date(1),new Date(2),Status.UNPAID);
//		orderService.insert(o);
//		Order o1 = orderService.findById(3);
//		System.out.println(o1);
//		o1.setStatus(Status.TRANSIING);
//		orderService.update(o1);
//		orderService.findAll().forEach(System.out::println);
//		orderService.findByCustomId("sophia2").forEach(System.out::println);
//		orderService.delete(3);
		
				
		//存在问题：自动生成的数字不是顺序生成的
//		Book b = new Book(null,"乾隆皇帝3",99.9,"清华大学出版社","匿名",new Date(1),bookTypeService.findById(1),100,"講述了乾隆皇帝的一生","1111111111114");
//		bookService.insert(b);
//		Book b1 = bookService.findById(3);
//		System.out.println(b1);
//		b1.setAuthor("MRS");
//		bookService.update(b1);
//		bookService.findAll().forEach(System.out::println);
//		bookService.findByBookTypeId(2).forEach(System.out::println);;
//		bookService.delete(8);
		
		
//		BookType bt = new BookType(null,"历史2","讲述关于历史人物事件的");		
//		bookTypeService.insert(bt);
//		System.out.println(bookTypeService.findById(2));
//		BookType bt = bookTypeService.findById(2);
//		bt.setId(3);//id是主键，如果更改了id那么就会导致更新失败会回滚，如果非要更改id，就必须在sql语句是传入id的参数
//		bt.setDetail("就是讲历史的");
//		bookTypeService.update(bt);
//		bookTypeService.findAll().forEach(System.out::println);
//		System.out.println("ok");
		
//		Custom c = new Custom(accountService.findById("sophia2"),"王雪","王雨伞","19885620684","贵州",new Date(0),"1.jpg1.jpg");
//		customService.insert(c);
//		System.out.println(customService.findById("sophia"));
//		customService.findAll().forEach(System.out::println);
//		Custom c = customService.findById("sophia");
//		c.setNickName("山雨点点");
//		customService.update(c);
//		System.out.println(customService.findByAccountId("sophia"));
//		customService.delete("sophia");
		
//		System.out.println("ok");
		
		
//		Account account = new Account("sophia2", "sophia2", UserType.CUSTOM);
//		accountService.insert(account);
//		Account account = accountService.findById("admin");
//		System.out.println(accountService.findById("admin"));
//		account.setPassword("123");
//		accountService.update(account);
//		accountService.delete("sophia1");
//		System.out.println("OK");

//		accountService.findAll().forEach(System.out::println);
		
		System.out.println("ok");
	}
}
