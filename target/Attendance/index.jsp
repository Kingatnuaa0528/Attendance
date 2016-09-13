<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆</title>
</head>
<body>
<form action="login" method="post">
    用户名:<br>
    <input type="text" name="username" placeholder="用户名">
    <br>
    密码:<br>
    <input type="password" name="password" placeholder="密码">
    <br><br>
    <input type="submit" value="登陆">
    <input type="button" value="注册" onclick="window.location.href='register.jsp'">
</form>
<!--p>
    <input type="button" value="注册" onclick="window.location.href='register.jsp'">
</p-->
</body>
</html>
