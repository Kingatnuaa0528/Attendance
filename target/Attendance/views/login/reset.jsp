<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>重置密码</title>
    </head>
    <body>
    <form name="resetForm" action="${pageContext.request.contextPath}／resetPassword" onsubmit="return validate_reset_info(this);" method="post">
        用户名:<br>
        <input type="text" name="username" placeholder="用户名">
        <br>
        旧密码:<br>
        <input type="password" name="old_password" placeholder="旧密码">
        <br>
        新密码:<br>
        <input type="password" name="new_password" placeholder="新密码">
        <br>
        确认密码:<br>
        <input type="password" name="password_confirm" placeholder="确认密码">
        <br><br>
        <input type="submit" value="登陆">
    </form>

    <script type="text/javascript">
        function validate_reset_info(resetForm)
        {
            if(resetForm.username.value=="")
            {
                alert("用户名不能为空");
                return false;
            }
            else if(resetForm.old_password.value == resetForm.new_password.value)
            {
                alert("新旧密码不能一致");
                return false;
            }
            else if(resetForm.new_password.value != resetForm.password_confirm.value)
            {
                alert("新密码两次输入不一致");
                return false;
            }
            return true;
        }

    </script>
    </body>
</html>
