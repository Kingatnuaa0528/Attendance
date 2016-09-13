<%--
  Created by IntelliJ IDEA.
  User: gaohanqing
  Date: 16/9/11
  Time: 下午3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>注册</title>
    </head>
    <body>
        <form name="registerForm" action="/register" onsubmit="return validate_register_info(this);"  method="post">
            用户名:<br>
            <input type="text" name="username" placeholder="用户名">
            <br>
            密码:<br>
            <input type="password" name="password" placeholder="密码">
            <br><br>
            <input type="submit" value="注册">
        </form>

        <script type="text/javascript">
            function validate_register_info(registerForm)
            {
                if(registerForm.username.value=="")
                {
                    alert("用户名不能为空");
                    return false;
                }
                else if(registerForm.password.value.length < 6)
                {
                    alert("密码长度不足6位");
                    return false;
                }
                return true;
            }

        </script>
    </body>
</html>
