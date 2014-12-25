<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登录页</title>
</head>

<body>
	<form id="loginForm" action="login.html" method="post" class="form-horizontal">
		<label for="loginName" class="control-label">用户名:</label>
		<input type="text" id="loginName" name="loginName"  value="${loginName}"/>
		<label for="password" class="control-label">密码:</label>
		<input type="password" id="password" name="password""/>
		<input id="submit_btn" type="submit" value="登录"/>
	 	<span>(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
	</form>
</body>
</html>
