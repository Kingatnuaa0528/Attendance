<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.HG.test.pojo.ResultType" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Multi Result</title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script language="JavaScript">
    function getLocalTime(timestamp) {
        return new Date(parseInt(timestamp)).toLocaleDateString();
    }

    function get_key(input){

        var result = new Array();
        var count=0;
        for(var key in input){
            result[count++] = key;
        }
        return result;
    }

    function get_data(value){
        var result = new Array();
        var count=0
        for(var key in value){
            document.write(type);
            if(type == '<%=ResultType.ALLCOMTIME%>' || type == '<%=ResultType.ALLLEAVETIME%>')
            result[count++] = new Date(parseInt(value[key])).toLocaleString();
            else
                result[count++] = value[key];
        }
        return result;
    }

    var flag = 0;
    var series = new Array();

    var data = '<%=request.getAttribute("result_set")%>';
    var type = '<%=request.getAttribute("type")%>';

    var json_string=eval('(' + data + ')');
    for(var k in json_string) {
        var v = json_string[k].value;
        if(flag == 0){
            var xAxis = get_key(v);
            flag = 1;
        }
        var s_data = get_data(json_string[k].value);
        series.push({"name": getLocalTime(json_string[k].key), "data": s_data});
    }

    switch (type)
    {
        case '<%=ResultType.ALLDURATION%>':
            var title = "工作时间统计";
            var yAxis_title = "时间（s）";
            break;
        case '<%=ResultType.ALLCOMTIME%>':
            var title = "到达时间统计";
            var yAxis_title = "时刻";
            break;
        case '<%=ResultType.ALLLEAVETIME%>':
            var title = "离开时间统计";
            var yAxis_title = "时刻";
            break;
    }

    var json=
    {
        title: {
            text: title,
            x: -20 //center
        },
        xAxis: {
            categories: xAxis
        },
        yAxis: {
            title: {
                text: yAxis_title
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: series
    };

    $('#container').highcharts(json);

</script>

</body>
</html>