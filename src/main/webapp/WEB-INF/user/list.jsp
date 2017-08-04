<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/main.css"
	type="text/css">
<title>userList</title>
</head>
<body>
	list
	<br>
	<a href="<%=request.getContextPath()%>/user/add">添加用户</a>
	<br>
	<c:forEach items="${users}" var="user">

		<a href="${user.username }">${user.username}</a>--${user.password } <a
			href="${user.username }/update">修改</a>
		<br>
		<a href="${user.username }/delete">删除</a>
		<br>

	</c:forEach>

</body>
</html>