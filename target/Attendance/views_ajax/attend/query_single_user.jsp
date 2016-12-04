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
        <script>
            function choose_skip_path(singleQForm)
            {
                var type = document.singleQForm.elements['type'].value;
                singleQForm.method="post";
                var action_path = "";
                if(type == "1") action_path="/QueryDuration";
                else if(type == "2") action_path="/QueryComeTime";
                else action_path="/QueryLeaveTime";

                singleQForm.action=action_path;
                return true;
            }
        </script>
        <!--style>

            div.pos_right
            {
                position:relative;
                left:400px;
            }
        </style-->
    </head>
    <body>
        <form name="singleQForm" onsubmit="return choose_skip_path(this);">
            用户名：<input type="text" name="username">
            开始时间：<input type="date" name="startTime">
            结束时间：<input type="date" name="endTime">
            <br>
            <input type="radio" name="type" value="1" />QueryDuration
            <input type="radio" name="type" value="2" />QueryComeTime
            <input type="radio" name="type" value="3" />QueryLeaveTime
            <br>
            <input type="submit" value="查询" >
        </form>
    </body>
</html>