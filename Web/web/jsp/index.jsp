<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<%@ include file="head.jspf" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<style type="text/css">
		#menu {
			width:100%;

		}
		#menu ul {
			list-style:none;
			padding: 0px;
			margin: 0px;
		}
		#menu ul li{
			border-bottom:1px solid #fff;
			list-style: none;
			padding: 0px;
			margin: 0px;
		}

		#menu ul li a{
			background-color:#E0ECFF;
			display: block;
			color: #0E2D5F;

			font-size: 15px;
			text-decoration: none;
			padding: 5px;
		}

		#menu ul li a:HOVER {
			background-color:#00BFFF;
		}
	</style>
	<script type="text/javascript">
		function addTab(title, url){
			if ($('#tt').tabs('exists', title)){
				$('#tt').tabs('select', title);
			} else {
				var content = '<iframe title="'+title+'"" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		}
	</script>
	<title>万家和</title>
</head>
<body class="easyui-layout" >
<div data-options="region:'north',border:false,title:'欢迎来到后台管理系统'" style="height:60px;padding:5px">
	<table style="width: 100%">
		<td >
		</td>
		<td align="right">
			<a href="#" onclick="addTab('修改密码','updatePassWord.jsp')"><img style="width: 30px;height: 15px;" src="<%=request.getContextPath()%>/jsp/images/icon/17.ico">修改密码</a>
			<a href="<%=request.getContextPath()%>/BackStageLoginManageController.controller/LoginOut.controller"><img style="width: 20px;height: 15px;" src="<%=request.getContextPath()%>/jsp/images/icon/16.ico">退出</a>
		</td>

	</table>
</div>
<!--菜单列表  -->
<div  data-options="region:'west',title:'基本操作'" style="width:150px;">
	<div id="aa" class="easyui-accordion" data-options="fit:true">
		<div title="后台管理系统" id="menu">
			<ul>
				<li><a href="#" onclick="addTab('人员管理','userManagement.jsp')">&nbsp;&nbsp;&nbsp;人员管理</a></li>
			</ul>
		</div>
		<div title="订单管理" id="menu">
			<ul>
				<li><a href="#" onclick="addTab('订单详情管理','orderManagement.jsp')">&nbsp;&nbsp;&nbsp;订单详情管理</a></li>
				<li><a href="#" onclick="addTab('订单统计','materialManagements.jsp')">&nbsp;&nbsp;&nbsp;订单统计</a></li>

			</ul>
		</div>
		<div title="网点管理" id="menu">
			<ul>
				<li><a href="#" onclick="addTab('网点详情管理','userdata.jsp')">&nbsp;&nbsp;&nbsp;网点详情管理</a></li>
			</ul>
		</div>

		<div title="警告管理" id="menu">
			<ul>
				<li><a href="#" onclick="addTab('警告详情管理','userdata.jsp')">&nbsp;&nbsp;&nbsp;警告详情管理</a></li>
			</ul>
		</div>
</div>
</div>

<div data-options="region:'center',title:'操作页面'">
	<div id="tt" class="easyui-tabs" data-options="fit:true">
		<div title="系统信息">
			这里输入使用说明
		</div>
	</div>
</div>
<div id="add" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,iconCls:'icon-add'" ></div>
</body>
</html>