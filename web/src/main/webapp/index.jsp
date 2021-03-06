<%--
  Created by IntelliJ IDEA.
  User: 邹子庆
  Date: 2021/7/22
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta  http-equiv="Content-Type" charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta http-equiv="Window-target" content="_top">
    <meta name="author" content="" />
    <title>首页面</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css">
</head>
<style>
    li{
        list-style-type: none;
    }
</style>
<body>
<audio controls autoplay>
    <source src="mp3/yinyue.mp3">
</audio>
<div class="d-flex" id="wrapper">
    <!-- Sidebar-->
    <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading border-bottom bg-light">系统菜单</div>
        <div class="list-group list-group-flush">
            <li>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="http://localhost:8080/ok.jsp" target="main">查看用户某年每月通话记录统计数据分析</a>
            </li>
            <li>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="${pageContext.request.contextPath}/querylist" target="main">查看某年每月通话记录Top10用户</a>
            </li>

            <li>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="http://localhost:8080/ok1.jsp" target="main">查看用户某月每天主叫通话记录统计数据分析</a>
            </li>

            <li>
                <a class="list-group-item list-group-item-action list-group-item-light p-3" href="http://localhost:8080/ok2.jsp" target="main">查看用户亲密度统计数据分析</a>
            </li>

            <li class="nav-item dropdown">
                <a class="list-group-item list-group-item-action list-group-item-light p-3" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">查看数据库详细数据信息</a>
                <!--<a class="" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">管理员</a>-->
                <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="http://localhost:8080/index.html">查看call表  &nbsp;  &nbsp;&nbsp;&nbsp;&nbsp; </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="http://localhost:8080/allbook.jsp">查看contacts表    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#!">查看dimension_date表    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#!">查看intimacy表    </a>
                </div>
            </li>
        </div>

    </div>
    <!-- Page content wrapper-->
    <div id="page-content-wrapper">
        <!-- Top navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="container-fluid">
                <button class="btn btn-primary" id="sidebarToggle">切换菜单</button>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <h1 style="font-family: 幼圆">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电信客服大数据可视化平台</h1>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                        <li class="nav-item active"><a class="nav-link" href="http://localhost:8080/index.html">首页</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Link</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">管理员</a>
                            <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="http://localhost:8080/index.html">Action</a>
                                <a class="dropdown-item" href="#!">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#!">Something else here</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <img src="images/2.jpg" width="100%" height="80%">

        <%--<iframe  name="main"  width="100%" height="100%" scrolling="auto" ></iframe>--%>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body>
</html>
