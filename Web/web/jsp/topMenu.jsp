<%--
  Created by IntelliJ IDEA.
  User: liujun
  Date: 2015/5/3
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page  language="java" %>
<%--<head>  <meta name="renderer" content="webkit"></head>--%>
<div class="topbar-inner clearfix">
  <div class="topbar-logo-wrap clearfix">
    <h1 class="topbar-logo none"><a href="index.jsp" class="navbar-brand">后台管理</a></h1>
    <ul class="navbar-list clearfix">
      <li><a class="on" href="<%=request.getContextPath()%>/jsp/index.jsp"><img style="width: 30px;height: 30px;" src="<%=request.getContextPath()%>/jsp/images/icon/1.ico">首页</a></li>
    </ul>
  </div>
  <div class="top-info-wrap">
    <ul class="top-info-list clearfix">
      <li><a href="<%=request.getContextPath()%>/jsp/updatePassWord.jsp"><img style="width: 30px;height: 30px;" src="<%=request.getContextPath()%>/jsp/images/icon/17.ico">修改密码</a></li>
      <li><a href="<%=request.getContextPath()%>/BackStageLoginManageController.controller/LoginOut.controller"><img style="width: 30px;height: 30px;" src="<%=request.getContextPath()%>/jsp/images/icon/16.ico">退出</a></li>
    </ul>
  </div>
</div>
