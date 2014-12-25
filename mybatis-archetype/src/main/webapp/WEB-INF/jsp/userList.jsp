<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message">${message}</div>
	</c:if>
	<table id="contentTable">
		<thead>
			<tr>
				<th>登录名</th>
				<th>用户名</th>
				<th>添加时间</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.loginName}</td>
				<td>${user.fullName}</td>
				<td><fmt:formatDate value="${user.addTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><a href="${ctx}/user/delete/${user.userId}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
