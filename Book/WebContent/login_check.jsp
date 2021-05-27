<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
	<title>checkAccount</title>
</head>
<body>
<%!
	//public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://8.142.36.228:3306/book";
	public static final String DBUSER = "sophia";
	public static final String DBPASS = "123";
%>
<%
	Connection conn = null;	//Connection接口：数据库连接
	PreparedStatement pstmt = null;// Statement接口 ： 把sql语句发给数据库的
	ResultSet rs = null;//ResultSet接口：用于保存查询结果，如果只是增删改，就不需要这个接口
	boolean flag = false;
	String name = null;
	// 获得执行sql的对象
	Statement statement = null;
	
	//创建数据库是就创建一个用户来使用，在写一个项目时就创建一个数据库和它相应的用户
	//create database book  创建数据库
	//create user book@'%' identified by 'book'; 创建用户，%表示任意ip地址可以登录这个用户
	//grant all on book.* to book@'%'; 授权
	//varchar 看变长 char 定长
	//数据库中能用枚举的就用int 能用int就用int 便于查询
%>
<%
	try {
		//加载驱动
		Class.forName(DBDRIVER);
		
		////建立连接
		conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		
		//编写SQL语句
		String sql = "SELECT * FROM t_account WHERE username=? AND password=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,request.getParameter("username"));
		pstmt.setString(2,request.getParameter("password"));
		rs = pstmt.executeQuery();
		if (rs.next()) {
			name = rs.getString(1);
			flag = true;
		}
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {}
	}
%>
<%
	if (flag) {
%>
		<jsp:forward page="login_success.jsp">
			<jsp:param name="username" value="<%=name%>"/>
		</jsp:forward>
<%
	} else {
%>
		<jsp:forward page="login_failure.jsp"/>
<%	
	}
%>
</body>
</html>

