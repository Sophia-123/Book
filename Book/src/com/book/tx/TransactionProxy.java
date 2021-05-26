package com.book.tx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.book.util.JdbcUtil;

public class TransactionProxy {

	@SuppressWarnings("unchecked")
	public static <T> T addTransaction(T target) {
		return (T) Proxy.newProxyInstance(TransactionProxy.class.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						String methodName = method.getName();
						if (methodName.equals("insert") || methodName.equals("update") || methodName.equals("delete")) {
							Connection conn = null;
							try {
								conn = JdbcUtil.getConnection();
								Object result = method.invoke(target, args);
								conn.commit();
								return result;
							} catch (Exception e) {
								e.printStackTrace();
								try {
									conn.rollback();
								} catch (Exception ex) {
								}
								throw new RuntimeException(e.getCause().getMessage());
							} finally {
								JdbcUtil.release(null, null, conn);
							}
						} else if (methodName.startsWith("find")) {
							Connection conn = null;
							try {
								conn = JdbcUtil.getConnection();
								return method.invoke(target, args);
							} catch (Exception e) {
								e.printStackTrace();
								throw new RuntimeException(e.getCause().getMessage());
							} finally {
								JdbcUtil.release(null, null, conn);
							}
						} else {
							return method.invoke(target, args);
						}
					}
				});

	}

}
