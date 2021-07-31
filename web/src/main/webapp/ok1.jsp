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

    <title>每天主叫通话记录页面</title>
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
            查看用户某月每天主叫通话记录统计
        </div>

        <div class="div_empty">
        </div>
        <form action="/queryCallLogList1" method="post">
            <div class="div_input_account">
                <input class="input_account" style="text-align: center" type="text" name="telephone" placeholder="请输入电话号码" value="${telephoneame }"/>
            </div>
            <div class="div_empty">
            </div>
            <div class="div_input_pwd">
                <input class="input_pwd" style="text-align: center" type="year" name="year" placeholder="请输入年份" value="${year}"/>
            </div>
            <div class="div_empty">
            </div>
            <div class="div_input_pwd">
                <input class="input_account" style="text-align: center" type="text" placeholder="请输入月份" name="month"  value="${month}"/>
            </div>
            <div class="div_error">
                <span class="span_error"> ${errorMessage }</span>
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