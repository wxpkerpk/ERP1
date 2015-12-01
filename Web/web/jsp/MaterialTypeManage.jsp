<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="head.jspf" %>
    <title>Insert title here</title>
    <script type="text/javascript" src="js/MaterialTypeManage.js"></script>
</head>
<body>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="addChildrenDept()" data-options="iconCls:'icon-add'">添加分类</div>
    <div onclick="addChildrenDept()" data-options="iconCls:'icon-add'">添加品牌</div>

    <div onclick="deleteDept()" data-options="iconCls:'icon-remove'">移除</div>
    <div onclick="updateDept()" data-options="iconCls:'icon-edit'">编辑</div>

    <div class="menu-sep"></div>

    <div onclick="collapse()">合并</div>
    <div onclick="expand()">扩展</div>
</div>
<table id="tt" class="easyui-treegrid">
</table>
</body>
</html>