    <%--
  Created by IntelliJ IDEA.
  User: 邹子庆
  Date: 2021/7/22
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户top10页面</title>
</head>
<style>
    body {

        font-size: 16px;
    }

    .div_top_1 {
        height: 140px;
        width: 100%;
    }

    .div_top_2 {
        height: 50px;
        width: 100%;
    }

    .main {
        width: 417.683px;
        height: 440px;
        margin: 0 auto;

    }

    .login {

        width: 360px;
        height: 360px;
        margin: 0 auto
    }

    .div_login_head {
        height: 36px;
        margin: 0 auto;
        line-height: 36px;
        text-align: center;
        color: #666;
        border-bottom: 3px solid #21b351;
        font-size: 18px;
        line-height: 24px;
        margin-bottom: -1px;
        font-family: "PingFang SC", "Microsoft yahei", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    }

    .div_input_account {
        width: 360px;
        height: 40px;

    }

    .div_input_pwd {
        width: 360px;
        height: 24px;

    }

    .input_account, .input_pwd {
        width: 360px;
        height: 40px;
        border: none;
        border-radius: 0;
        outline: 0;
        font: inherit;
        font-size: .875rem;
    }

    .div_button_login {
        width: 360px;
        height: 40px;
        margin-top: 36px;
        text-align: center;

    }

    .button_login {
        width: 180px;
        height: 40px;
        background: #1fa54a;
        font-size: 16px;
        cursor: pointer;
        color: white;
        border: none;
        border-radius: 2px;
        outline: 0;

    }

    .div_empty {
        width: 360px;
        height: 24px;

    }
    .div_error {
        width: 360px;
        height: 24px;
        text-align: center;
    }
    .span_error{

        color: #e35b5a;
        font-size: 13px;
    }
</style>
<body style="background-image: url('img/preview.jpg')">
<div class="div_top_1">


</div>
<div class="main">
    <div class="login">
        <div class="div_top_2">
        </div>
        <div class="div_login_head">
            查看用户年度TOP10排行
        </div>

        <div class="div_empty">
        </div>
        <form action="/querylist" method="post">

            <div class="div_error">
                <span class="span_error"> ${errorMessage }</span>
            </div>
            <div class="div_empty">
            </div>
            <div class="div_empty">
            </div>
            <div class="div_empty">
            </div>
            <div class="div_button_login">
                <input class="button_login" type="submit" value="点击查询"/>
            </div>
            <div class="div_button_login">
              <button> <a href="http://localhost:8080/index.html">返回首页</a></button>
            </div>
        </form>
    </div>
</div>

</body>
</html>