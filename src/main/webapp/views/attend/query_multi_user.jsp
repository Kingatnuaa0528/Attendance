<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>MultiUser</title>
        <meta charset="utf-8">
        <script>
            function choose_skip_path(multiQForm)
            {
                var type = document.multiQForm.elements['type'].value;
                multiQForm.method="post";
                var action_path = "";
                if(type == "1") action_path="/QueryAllDuration";
                else if(type == "2") action_path="/QueryAllComeTime";
                else action_path="/QueryAllLeaveTime";

                multiQForm.action=action_path;
                return true;
            }
        </script>
    </head>
    <body>
        <form name="multiQForm" onsubmit="return choose_skip_path(this);">
            startTime:
            <input type="date" name="startTime">
            endTime:
            <input type="date" name="endTime" >
            <!--input type="hidden" id="endTime"-->
            <input type="radio" name="type" value="1" />QueryAllDuration
            <input type="radio" name="type" value="2" />QueryAllComeTime
            <input type="radio" name="type" value="3" />QueryAllLeaveTime
            <input type="submit" value="查询">
        </form>
    </body>
</html>