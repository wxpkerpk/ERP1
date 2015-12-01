<%--
  Created by IntelliJ IDEA.
  User: wx
  Date: 15/11/30
  Time: 上午3:04
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
</head>
<body>

<form id="ff" method="post">
    <input  id="account" name="account"  value="<%=request.getParameter("account")%>" style="display: none">
    <table cellpadding="5">
        <tr>
            <td>地区:</td>
            <td><input class="easyui-combotree"  id="area" name="area" data-options="required:true" style="width:200px;">
        </tr>
        <tr>
            <td>网点:</td>
            <td><table>
                <tr>
                    <td>
                        <input class="easyui-combobox"  id="premises" name="premises"  style="width:140px;">
                    </td>
                    <td>
                        <a id="addPremises" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
                    </td>
                </tr>
            </table>
            </td>
        </tr>
    </table>
</form>


<div style="text-align:center;padding:5px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
</div>
<script>

    $('#area').combotree({
        width: 200,
        panelHeight:600,

        url: '../user.do/getAllArea.do',
        method:'get',
        onClick: function(node){ // 在用户点击的时候提示
            getPremises(node.id);
        },
        loadFilter: function (rows) {
            return convert(rows);
        }
    });

    function getPremises( id)
    {
        $('#premises').combobox({
            url:'../premises.do/getByAddress.do?address='+id,
            method:'get',
            valueField:'id',
            textField:'name'
        });

    }

    $(function(){
        $('#addPremises').bind('click', function(){
            var address=$('#area').combobox('getValues');
            var name= $('#premises').combobox('getValues');
            $.ajax({
                type: 'POST',
                url: "../premises.do/add.do",
                data: {"address":address,"name":name},
                success: function(date)
                {
                    var date = eval('(' + date + ')');
                    if(date.msg=='ok'){
                        $.messager.show({	// show success message
                            title: '成功',
                            msg: "网点添加成功"
                        });
                        $('#premises').combobox({
                            url:'../premises.do/getByAddress.do?address='+address,
                            method:'get',
                            valueField:'id',
                            textField:'name'
                        });

                    }else {

                        $.messager.show({
                            title: '成功',
                            msg: "网点添加成功"
                        });
                    }

                }
            });
        });
    });

    function submitForm() {

        $('#ff').form('submit', {
            url: "../user.do/updateArea.do?",
            method:'post',


            success: function (data) {
                var data = eval('(' + data + ')');

                if (data.msg == 'ok') {
                    $.messager.show({	// show success message
                        title: '成功',
                        msg: "提交成功"
                    });
                    var ifr1 = parent.$('iframe[title="人员管理"]').get(0);
                    if (ifr1) {
                        ifr1.contentWindow.$("#listDetail").datagrid('reload');
                        ifr1.contentWindow.$("#listDetail").datagrid('clearSelections');
                        parent.$('#add').window('close');
                    }
                }
                else if (data.state == '500') {
                    $.messager.show({	// show error message
                        title: '失败',
                        msg: "服务器出错"
                    });
                    parent.$('#add').window('close');

                }


            }
        });
    }




</script>

</body>
</html>
