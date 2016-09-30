<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            result[count++] = getLocalTime(key);
        }
        return result;
    }

    function get_data(value){
        var result = new Array();
        var count = 0;
        for(var key in value){
            result[count++] = parseInt(value[key])/1000;
        }
        return result;
    }

    var flag = 0;
    var series = new Array();

    var data = '<%=request.getAttribute("result_set")%>';
    var username = '<%=request.getAttribute("username")%>';

    var json_string=eval('(' + data + ')');
    var xAxis = get_key(json_string);
    var s_data = get_data(json_string);
    series.push({"name": username, "data": s_data});

    var json=
    {
        title: {
            text: "工作时间统计",
            x: -20 //center
        },
        xAxis: {
            categories: xAxis
        },
        yAxis: {
            title: {
                text: "时间（s）"
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