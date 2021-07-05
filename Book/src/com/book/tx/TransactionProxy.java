package com.book.tx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.book.util.JdbcUtil;

public class TransactionProxy {
//将service.imp层的代码做了封装，将重复的代码封装起来了
	//似乎还用到了反射
	@SuppressWarnings("unchecked")
	public static <T> T addTransaction(T target) {
		return (T) Proxy.newProxyInstance(TransactionProxy.class.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					//Handler通知信息的
					//Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，   
					//如果参数为基本类型数据，必须转换为相应的包装类型的对象。invoke()方法的返回值总是对象，   
					//如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，   
					//再将其返回
					//关闭了conn,这个应该是用于关闭打开的资源，先判断是方法名然后再关闭对应的资源，就是相当于把原来serviceImpl里边的重复的代码封装
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
