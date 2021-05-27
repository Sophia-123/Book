<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf8">
	<title>UserLogin</title>
</head>
<body>

	<h1>登录操作</h1><hr>
	<form action="login_check.jsp" method="post">
		<table border="1">
		<tr>
			<td colspan="2">用户登录</td>
		</tr>
		<tr>
			<td>登录账号：</td>
			<td><input type="text" name="username"></td>
		</tr>
		<tr>
			<td>登录密码：</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="登录">
				<input type="reset" value="重置">
			</td>
		</tr>
		</table>
	</form>

</body>
</html>