<%--
  Created by IntelliJ IDEA.
  User: liujun
  Date: 2015/1/28
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/js/My97DatePicker4.8/skin/WdatePicker.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/Menu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/constant.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/updatePassword.js"></script>
</head>
<body>
<div class="search-wrap" style="margin-top:20px;">
    <div class="search-content">
        <table style="border: none;margin-left: auto;margin-right: auto;text-align: center">
            <tr style="height: 40px">
                <td >原&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="text" id="oldPassWord"  class="common-text"/></td>
            </tr>
            <tr style="height: 40px;width: 400px">
                <td>新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="newPassWord"  class="common-text"/></td>
            </tr>
            <tr style="height: 40px">
                <td>确认新密码：</td>
                <td><input type="password" id="firmNewPassword"  class="common-text"/></td>
            </tr>
            <tr style="height: 50px">
                <td colspan="2" style="text-align: center"><input class="btn btn-primary btn2" value="确定" type="button" onClick="UpdatePassWord();"></td>
            </tr>
        </table>
    </div>
</div>
</div>
</body>
</html>
