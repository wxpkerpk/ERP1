<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/5/25 0025
  Time: 下午 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/main.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/constant.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/sellerAccountAdd.js"></script>
</head>
<body>
<div style="margin-left: 80px;margin-top: 35px;">
    <table class="search-tab">
       <tr>
            <td class="toRight">帐&nbsp;&nbsp;&nbsp;号：</td>
            <td><input type="text" class="common-text" id="account"/></td>
        </tr>
        <tr>
            <td class="toRight">姓&nbsp;&nbsp;&nbsp;名：</td>
            <td><input type="text" class="common-text" id="name"/></td>
        </tr>
        <tr>
            <td class="toRight">手机号：</td>
            <td><input type="text" class="common-text" id="tel"></td>
        </tr>
        <tr>
            <td class="toRight">密&nbsp;&nbsp;&nbsp;码：</td>
            <td><input type="text" class="common-text" id="passWord"></td>
        </tr>
        <tr>
            <td class="toRight">职&nbsp;&nbsp;&nbsp;位：</td>
            <td><input type="text" class="common-text" id="position"></td>
        </tr>
        <tr>
            <td class="toRight">性&nbsp;&nbsp;&nbsp;别：</td>
            <td><select id="sex" class="common-text">
                <option value="0">男</option>
                <option value="1">女</option>
            </select></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><label id="warn" style="color: red"></label></td>
        </tr>
        <tr class="toCenter">
            <td colspan="2" style="padding-top:10px"><input class="btn btn-primary btn2" id="closebtn"
                                                            onclick="Closeiframe();" value="取 消" type="button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                    class="btn btn-primary btn2" name="sub" onClick="AddSellerAccount();" value="确 定" type="button"></td>
        </tr>
    </table>
</div>
</body>
</html>
