<%--
  Created by IntelliJ IDEA.
  User: wx
  Date: 15/11/24
  Time: 上午2:23
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
    <script type="text/javascript" src="<%=path %>/jquery-easyui-1.4.3/src/datagrid-detailview.js"></script>
    <title>Insert title here</title>
    <script>
        $(function(){
            $("#listDetail").datagrid({
                heigth:700,
                idField:'id',
                url:'../oa/receiptNoteDetailAction.do?action=page',
                singleSelect:false,
                fitColumns:true,
                nowrap:true,

                columns:[[
                    {field:'id',checkbox:true},
                    {field:'name',title:'用品名称',width:100,editor:'text',sortable:true},
                    {field:'produceType',title:'用品型号',width:100,editor:'text',sortable:true},
                    {field:'prickle',title:'计量单位',width:100,editor:'text',sortable:true},
                    {field:'count',title:'入库数量',width:100,editor:'text',sortable:true},
                    {field:'price',title:'参考单价',width:100,editor:'text',sortable:true},
                    {field:'subtotal',title:'入库金额',width:100,editor:'text',sortable:true},
                    {field:'operate',title:'操作',align:'center',width:$(this).width()*0.1,
                        formatter:function(value, row, index){
                            var str = '<a href="#" name="opera" class="easyui-linkbutton" ></a>';
                            return str;
                        }}

                ]],
                pagination: true,
                toolbar: [{
                    iconCls: "icon-add",
                    text: "添加",
                }, '-', {
                    iconCls: "icon-edit",
                    text: "更新",
                }, '-', {
                    iconCls: "icon-add",
                    text: "删除",
                }, '-', {
                    text: "类型<select name='parentId' id='parentId' ></select>"
                }, '-', {
                    text: "关键词<input name='name' id='name1' class='easyui-textbox'  />"
                }, '-', {
                    text: "<a id='ss' href='#' class='easyui-linkbutton' onclick='doSearch()' iconCls='icon-search'></a>"
                }
                ],

                view: detailview,
                detailFormatter:function(index,row){
                    return '<div id="detailForm-'+index+'" style="line-height:500px;"></div>';
                },
                onExpandRow: function(index,row){
                    var id= $(this).datagrid('getRows')[index].id;
                    $('#detailForm-'+index).panel({
                        doSize:true,
                        border:false,
                        cache:false,
                        href:'../oa/suppliesmgm/DE_ReceiptNoteDetail.jsp?rkdId='+_rkdId+'&id='+id+'&index='+index,
                        onLoad:function(){
                            $('#listDetail').datagrid('fixDetailRowHeight',index);
                            $('#listDetail').datagrid('selectRow',index);
                        }
                    });
                    $('#listDetail').datagrid('fixDetailRowHeight',index);
                },
                onDblClickRow:function(index,row){
                    $('#listDetail').datagrid('expandRow', index);
                    $('#listDetail').datagrid('fitColumns',index);
                    $('#listDetail').datagrid('selectRow', index);
                },
                onLoadSuccess:function(data){
                    $("a[name='opera']").linkbutton({text:'下订单',plain:true,iconCls:'icon-add'});
                },
            });
        });

    </script>

</head>
<body>
<table id="listDetail">


</table>

</body>


</html>

