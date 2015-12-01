<%--
  Created by IntelliJ IDEA.
  User: liujun
  Date: 2015/4/9
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/login/base.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/login/admin-all.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/login/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/login/login.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/login/chur.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/login/jquery.spritely-0.6.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/loginManage.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/constant.js"></script>
    <script type="text/javascript">
        function keydown(e) {
            var currKey = 0, e = e || event;
            currKey = e.keyCode || e.which || e.charCode;//支持IE、FF
            if (currKey == 13) {
                Login();
            }
        }
        document.onkeydown = keydown; </script>
</head>
<body>
<div id="clouds" class="stage"></div>
<div class="row-fluid">
    <h1>后台管理系统</h1>

    <p>
        <label>帐&nbsp;&nbsp;&nbsp;号：<input type="text" id="uid" style="height: 30px"/></label>
    </p>

    <p>
        <label>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwd" style="height: 30px"/></label>
    </p>

    <p class="tip">&nbsp;</p>
    <p>
        <input type="button" value=" 登 录 " onclick="Login();" style="width: 150px"
               class="btn btn-primary btn-large login"/>
    </p>
</div>
</body>
</html>
