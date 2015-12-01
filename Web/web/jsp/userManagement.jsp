<%--
  Created by IntelliJ IDEA.
  User: wx
  Date: 15/11/26
  Time: 上午1:41
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
                url:'../user.do/search.do',
                title: '人员列表',
                fit: true,
                striped: true,
                fitColumns: true,
                nowrap: true,
                checkbox: true,
                method:'get',
                queryParams:{
                    keyWord:"",
                    type:-1
                },

                columns:[[
                    {field:'id',checkbox:true},
                    {field:'name',title:'姓名',width:100,editor:'text'},
                    {field:'account',title:'账号',width:100,editor:'text'},
                    {field:'type',title:'用户类型',width:100,editor:'text',
                        formatter:function(value, row, index){
                            if(row.type=='0')
                            {
                                return '管理员';
                            }else if(row.type==1)
                            {
                                return '客服';
                            }else if(row.type==2)
                            {
                                return '师傅';

                            }else if(row.type==3){
                                return '厂商';
                            }
                        }
                    },
                    {field:'address',title:'所属地区',width:100,editor:'text',
                        formatter:function(value, row, index){
                            if(row.address&&row.address.areaname)
                                return row.address.areaname;
                            else
                                return '无';
                        }
                    },
                    {field:'premises',title:'所属网点',width:100,editor:'text',
                        formatter:function(value, row, index){
                            if(row.premises&&row.premises.name)
                                return row.premises.name;
                            else
                                return '无';
                        }
                    },
                  ]],
                pagination: true,
                toolbar: [{
                    iconCls: "icon-add",
                    text: "添加",
                    handler:addUser
                },  '-', {
                    iconCls: "icon-add",
                    text: "删除",
                    handler:deleteUser
                }, '-', {
                    text: "类型<select id='type' class='easyui-combobox' name='type' style='width:200px;'><option value='-1'>全部</option><option value='0'>管理员</option> <option value='1'>客服</option><option value='2'>师傅</option><option value='3'>厂家</option></select> "
                }, '-', {
                    text: "关键词<input name='keyWord' id='keyWord' class='easyui-textbox'  />"
                }, '-', {
                    text: "<a id='ss' href='#' class='easyui-linkbutton' onclick='doSearch()' iconCls='icon-search'></a>"
                }
                ],
                view: detailview,
                onExpandRow: function(index,row){
                    var id= $(this).datagrid('getRows')[index].id;
                    $('#detailForm-'+index).panel({
                        doSize:true,
                        border:false,
                        cache:false,
                        href:'../user.do/getUpdate.do?id='+id,
                        onLoad:function(){
                            $('#listDetail').datagrid('fixDetailRowHeight',index);
                            $('#listDetail').datagrid('selectRow',index);
                        }
                    });
                    $('#listDetail').datagrid('fixDetailRowHeight',index);
                },
                detailFormatter:function(index,row){
                    return '<div id="detailForm-'+index+'""></div>';
                },
                onDblClickRow:function(index,row){
                    $('#listDetail').datagrid('expandRow', index);
                    $('#listDetail').datagrid('fitColumns',index);
                    $('#listDetail').datagrid('selectRow', index);
                },
                onBeforeEdit: function (index, row) {
                    row.editing = true;
                    updateActions(index);
                },
                onAfterEdit: function (index, row) {
                    row.editing = false;
                    updateActions(index);
                },
                onCancelEdit: function (index, row) {
                    row.editing = false;
                    updateActions(index);
                },
                onLoadSuccess:function(data){

                },

            });
            $('#ss').linkbutton({});


        });


        function deleteUser() {
            var rows = $('#listDetail').datagrid('getSelections');
            if (rows.length == 0) {
                $.messager.show({
                    title: '错误信息',
                    msg: '您未选择任何记录',
                    showType: 'fade',
                    timeout: 500,
                    style: {
                        right: '',
                        bottom: ''
                    }
                });

            } else {
                $.messager.confirm("删除确认对话框", "是否删除选中记录？", function (r) {
                    if (r) {
                        var ids = '';
                        for (var i = 0; i < rows.length; i++) {
                            ids += rows[i].id + ',';
                        }
                        ids = ids.substring(0, ids.lastIndexOf(","));
                        $.post('../user.do/delete.do', {ids: ids}, function (result) {
                            if (result.msg == "ok") {
                                $.messager.show({	// show error message
                                    title: '删除成功',
                                    msg: "成功删除所选人员"
                                });
                                $('#listDetail').datagrid('reload');	// reload the user data
                                $('#listDetail').datagrid('clearSelections');
                            } else {
                                $.messager.show({	// show error message
                                    title: '删除失败',
                                    msg: "材料删除失败"
                                });
                            }
                        }, 'json');

                    }
                });

            }
        }

        function doSearch() {
            $('#listDetail').datagrid('load', {
                keyWord: $('#keyWord').val(),
                type:$('#type').val()
            });
        }

        function addUser()
        {

            parent.$("#add").window({
                title: "添加人员",
                width: 400,
                height: 550,
                shadow:true,
                content: '<iframe src="../jsp/addUser.jsp" frameborder="0" width="100%" height="100%"/>'
            });
        }

    </script>

</head>
<body>
<table id="listDetail">


</table>

</body>


</html>