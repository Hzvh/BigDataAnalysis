<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 邹子庆
  Date: 2021/7/22
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top10</title>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/dark.js"></script>
    <script type="text/javascript" src="../js/infographic.js"></script>
    <script type="text/javascript" src="../js/macarons.js"></script>
    <script type="text/javascript" src="../js/roma.js"></script>
    <script type="text/javascript" src="../js/shine.js"></script>
    <script type="text/javascript" src="../js/vintage.js"></script>
</head>
<body style="height: 100%; margin: 0">
<div id="container1" style="height:100%; width: 100%;"></div>

<script type="text/javascript">

    var date = "${requestScope.date}".split(",")
    var duration = "${requestScope.duration}".split(",")
    var pieData=converterFun(duration,date)
    function converterFun(duration,date){
        var array =[]
        for(var i =0;i<duration.length;i++){
            var map ={}
            map['value'] = parseFloat(duration[i])
            map['name'] =date[i]
            array.push(map)

        }
        return array
    }

callog1();

function callog1() {
var dom = document.getElementById("container1");
var myChart = echarts.init(dom,'dark');
myChart.showLoading();
var option = {
tooltip: {
trigger: 'axis'
},
legend: {
data: ['通话次数']
},
toolbox: {
show: true,
feature: {
dataZoom: {
yAxisIndex: 'none'
},
dataView: {readOnly: false},
magicType: {type: ['bar']},
restore: {},
saveAsImage: {}
},
    showBackground: true,
    backgroundStyle: {
        color: 'rgba(180, 180, 180, 0.2)'
    },
},
grid:{
x:100,
y:100,
borderWidth:1
},
xAxis: {
data: date,
},
yAxis: {},
series: [
{
name: '通话时长',
type: 'bar',
data: pieData.sort(function (a, b) {
if (a.value >b.value)
return -1;
else
return 1;
}),
    itemStyle: {
        normal: {
            //这里是重点
            color: function(params) {
                //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
                var colorList = ['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83', '#ca8622','#00008B','#778899', '#FFC0CB'];
                return colorList[params.dataIndex]
            }
        }
    }
},

]
};
if (option && typeof option === "object") {
myChart.setOption(option, true);
}
myChart.hideLoading()
}
</script>
</body>
</html>
