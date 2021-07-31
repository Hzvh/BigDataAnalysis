<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%--<head>--%>
    <%----%>

    <%--<title>ok页面</title>--%>
<%--</head>--%>
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>显示通话记录</title>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
        <script type="text/javascript" src="../js/echarts.min.js"></script>
        <script type="text/javascript" src="../js/dark.js"></script>
        <script type="text/javascript" src="../js/infographic.js"></script>
        <script type="text/javascript" src="../js/macarons.js"></script>
        <script type="text/javascript" src="../js/roma.js"></script>
        <script type="text/javascript" src="../js/shine.js"></script>
        <script type="text/javascript" src="../js/vintage.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.2.0.min.js"></script>--%>
    <%--<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>--%>
</head>

<body style="height: 100%; margin: 0">
<div id="container1" style="height: 100%; width: 50%; float:left"></div>
<div id="container2" style="height: 100%; width: 50%; float:right"></div>

<script type="text/javascript">

    var id = "${requestScope.id}"
    var telephone="${requestScope.telephone}"
    var name1 = "${requestScope.name1}"
    var date = "${requestScope.date}".split(",")
    var count = "${requestScope.count}".split(",")
    var rank = "${requestScope.rank}".split(",")
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



    var lineStyle = {
        normal: {
            width: 1,
            opacity: 0.5
        }
    };

    var cuontData=converterFun1(count,date)
    function converterFun1(count,date){
        var array =[]
        for(var i =0;i<count.length;i++){
            var map ={}
            map['value'] = parseFloat(count[i])
            map['name'] =date[i]
            array.push(map)

        }
        return array
    }


    callog1();
    callog2();

    function callog1() {
        var dom = document.getElementById("container1");
        var myChart = echarts.init(dom,'vintage');
        myChart.showLoading();
        var option = {
            color: [ '#FFE434','#67F9D8', '#56A3F1', '#FF917C'],
            title: {
                text: '亲密度查询人：' + name1,
                textStyle: {
                    //文字颜色
                    color: '#2c2c2c',
                    //字体风格,'normal','italic','oblique'
                    fontStyle: 'normal',
                    //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                    fontWeight: 'bold',
                    //字体系列
                    fontFamily: 'sans-serif',
                    //字体大小
                    fontSize: 20
                },
                itemGap: 12,
                subtext: '手机号码：' + telephone,
                subtextStyle: {
                    //文字颜色
                    color: '#2c2c2c',
                    //字体风格,'normal','italic','oblique'
                    fontStyle: 'normal',
                    //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                    fontWeight: 'bold',
                    //字体系列
                    fontFamily: 'sans-serif',
                    //字体大小
                    fontSize: 18
                }
            },
            legend: {
                data: ['亲密度等级']
            },
            radar: [
                {
                    indicator: (function (){
                        var res = [];
                        for (var i = 0; i <date.length; i++) {
                            res.push({text: date[i]});
                        }
                        return res;
                    })(),
                    center: ['50%', '50%'],
                    radius: 240,
                    startAngle: 90,
                    splitNumber: 4,
                    shape: 'circle',
                    name: {
                        formatter: '【{value}】',
                        textStyle: {
                            color: '#56A3F1'
                        }
                    },
                    splitArea: {
                        areaStyle: {
                            color: ['#77EADF', '#26C3BE', '#64AFE9', '#428BD4'],
                            shadowColor: 'rgba(0, 0, 0, 0.2)',
                            shadowBlur: 10
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(211, 253, 250, 0.8)'
                        }
                    },
                    splitLine: {
                        lineStyle: {
                            color: 'rgba(211, 253, 250, 0.8)'
                        }
                    }
                },
            ],
            series: [
                {
                    name: '亲密度等级',
                    type: 'radar',
                    emphasis: {
                        lineStyle: {
                            width: 4
                        }
                    },
                    data:[{value:(function (){
                        var res = [];
                        for (var i = 0; i <rank.length; i++) {
                            res.push(rank[i]);
                        }
                        return res;
                    })()}],
                },
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
        myChart.hideLoading()
    }

    function callog2() {
        var dom = document.getElementById("container2");
        var myChart = echarts.init(dom,'vintage');
        myChart.showLoading();
        var option = {
            title: {
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend:{
                data:['通话时长']
            },
            series: [
                {
                    name:'通话时长',
                    type: 'pie',
                    radius: ['50%','70%'],
                    data: pieData.sort(function (a, b) {
                        if (a.value>b.value)
                            return -1;
                        else
                            return 1;
                    }),
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
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