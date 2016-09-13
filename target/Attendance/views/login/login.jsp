<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>登陆</title>

        <script type="text/javascript">
            function validate_login_info(loginForm)
            {
                if(loginForm.username.value.length==0)
                {
                    alert("用户名不能为空");
                    //document.getElementByIdx("usrErr").innerHTML="<font color='red'>请输入3-16位的用户名！</font>";
                    return false;
                }
                else if(loginForm.password.value.length < 6)
                {
                    alert("密码长度不足6位");
                    return false;
                }
                //document.getElementByIdx("usrErr").innerHTML="";
                return true;
            }

        </script>

    </head>
    <body>
        <form name="loginForm" action="/login" method="post">
            用户名:<br>
            <input type="text" name="username" placeholder="用户名">
            <br>
            密码:<br>
            <input type="password" name="password" placeholder="密码">
            <br><br>
            <input type="submit" value="登陆">
            <input type="button" value="注册" onclick="window.location.href='register.jsp'">
        </form>
    </body>
</html>
