<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            String username = (String)request.getAttribute("username");
        %>
    </head>
    <body>
        <h3>Hello <%=username%>!</h3>
        <!--form action="/views/attend/attend.jsp" method="post">
            <input type="hidden" id="username" value=<%=username%>>
            <input type="submit" value="签到">
        </form-->
        <button onclick="location.href='/views/attend/attend.jsp'">Attend</button>
        <button onclick="location.href='/views/attend/query_single_user.jsp'">SingleUser</button>
        <button onclick="location.href='/views/attend/query_multi_user.jsp'">MultiUser</button>
    </body>
</html>
