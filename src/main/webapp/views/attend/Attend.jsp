<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到</title>
    <%
        String username = request.getParameter("username");
    %>
</head>
<body>
    <form action="/submitattend" method="post">
        <input type="hidden" id="username" value=<%=username%>>
        <input type="radio" name="type" value="1">到达
        <input type="radio" name="type" value="0">离开
        <input type="submit" value="签到">
    </form>
</body>
</html>
