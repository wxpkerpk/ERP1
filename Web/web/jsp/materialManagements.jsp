<%--
  Created by IntelliJ IDEA.
  User: wx
  Date: 2015/8/8
  Time: 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/convert.js"></script>
    <%@ include file="head.jspf" %>
    <title>Insert title here</title>
    <script type="text/javascript" src="js/materialManagements.js">
    </script>
</head>
<body>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="addUser()" data-options="iconCls:'icon-add'">添加商品</div>
    <div onclick="deleteUser()" data-options="iconCls:'icon-remove'">删除</div>
    <div onclick="updateUser()" data-options="iconCls:'icon-edit'">编辑</div>
</div>
<table id="tt" class="easyui-datagrid">
</table>
</body>
</html>