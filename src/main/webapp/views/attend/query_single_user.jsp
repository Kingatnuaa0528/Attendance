<%--
  Created by IntelliJ IDEA.
  User: gaohanqing
  Date: 16/9/13
  Time: 上午10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>单人结果显示</title>
        <style>

            div.pos_right
            {
                position:relative;
                left:400px;
            }
        </style>
    </head>
    <body>
        <form id="single_result" action="/QueryDuration" method="post">
            <table class="table" contenteditable="false">
                <tr>
                    <th>用户名</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                </tr>
                <tr>
                    <td><input type="text" id="username"></td>
                    <td><input type="date" id="starttime"></td>
                    <td><input type="date" id="endtime"></td>
                </tr>
            </table>
            <br>
            <hr align="left" width="32%" size=2>
            <div class="pos_right">
                <input type="submit" value="查询" >
            </div>
        </form>
    </body>
</html>