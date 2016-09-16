<%--
  Created by IntelliJ IDEA.
  User: gaohanqing
  Date: 16/9/16
  Time: 下午4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
        <%
            String username = (String)request.getAttribute("message");
        %>
    </head>
    <body>
        <h3>Hello <%=username%>!</h3>
    </body>
</html>
