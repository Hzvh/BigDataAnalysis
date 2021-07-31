<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%--<head>--%>
    <%----%>

    <%--<title>ok页面</title>--%>
<%--</head>--%>
<head>
    <base href="<%=basePath%>">
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>显示通话记录</title>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/dark.js"></script>
    <script type="text/javascript" src="../js/infographic.js"></script>
    <script type="text/javascript" src="../js/macarons.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.2.0.min.js"></script>--%>
    <%--<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>--%>
</head>

<body style="height: 100%; margin: 0">
<div id="container1" style="height: 100%; width: 50%; float:left"></div>
<div id="container2" style="height: 100%; width: 50%; float:right"></div>


<script type="text/javascript">
    var telephone = "${requestScope.telephone}"
    var name = "${requestScope.name}"

    var date = "${requestScope.date}"
    var count = "${requestScope.count}"

    var duration = "${requestScope.duration}"
    callog1();
    callog2();

    function callog1() {
        var dom = document.getElementById("container1");
        var myChart = echarts.init(dom,'macarons');
        myChart.showLoading();
        var option = {
            title: {
                text: '通话记录查询人：' + name,
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
            grid:{
                x:100,
                y:100,
                borderWidth:1
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['通话次数', '通话时长']
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                data: date.split(","),
            },
            yAxis: {},
            series: [
                {
                    name: '通话次数',
                    type: 'line',
                    data: count.split(","),
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name: '通话时长',
                    type: 'line',
                    data: duration.split(","),
                    markPoint: {
                        data: [
                            {name: '月最低', value: -2, xAxis: 1, yAxis: -1.5}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'},
                            [{
                                symbol: 'none',
                                x: '90%',
                                yAxis: 'max'
                            }, {
                                symbol: 'circle',
                                label: {
                                    normal: {
                                        position: 'start',
                                        formatter: '最大值'
                                    }
                                },
                                type: 'max',
                                name: '最高点'
                            }]
                        ]
                    }
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
        var myChart = echarts.init(dom,'infographic');
        myChart.showLoading();
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['通话次数', '通话时长']
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            grid:{
                x:100,
                y:100,
                borderWidth:1
            },
            xAxis: {
                data: date.split(","),
            },
            yAxis: {},
            series: [
                {
                    name: '通话次数',
                    type: 'bar',
                    data: count.split(","),
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name: '通话时长',
                    type: 'bar',
                    data: duration.split(","),
                    markPoint: {
                        data: [
                            {name: '月最低', value: -2, xAxis: 1, yAxis: -1.5}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'},
                            [{
                                symbol: 'none',
                                x: '90%',
                                yAxis: 'max'
                            }, {
                                symbol: 'circle',
                                label: {
                                    normal: {
                                        position: 'start',
                                        formatter: '最大值'
                                    }
                                },
                                type: 'max',
                                name: '最高点'
                            }]
                        ]
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