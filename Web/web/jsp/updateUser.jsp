<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wx
  Date: 15/11/30
  Time: 上午1:49
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
    <table cellpadding="5">
        <tr>
            <td>账号:</td>
            <td><input class="easyui-textbox" type="text" name="account"  value="${user.account}" readonly="true" data-options="required:true"/></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><input class="easyui-textbox" type="text" name="name" value="${user.name}" data-options="required:true"/></td>
        </tr>

        <tr>
            <td>电话:</td>
            <td><input class="easyui-numberbox" type="text" name="tel" value="${user.tel}" data-options="required:true,min:0,precision:0"/>
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <c:if test="${user.sex==0}">
                　  <td><input class="easyui-radio" type="radio" name="sex" value="0" checked="checked"/>男
                      <input class="easyui-radio" type="radio" name="sex" value="1"/>女
                    </td>
            </c:if>
            <c:if test="${user.sex==1}">
                　  <td><input class="easyui-radio" type="radio" name="sex" value="0" />男
                      <input class="easyui-radio" type="radio" name="sex" value="1" checked="checked"/>女
                  </td>
            </c:if>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input class="easyui-textbox" value="${user.password}" type="password" name="passWord"
                       data-options="required:true,min:0,precision:0"/></td>
        </tr>
        <tr>
            <td>更改区域:</td>
            <td>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="changeArea()">选择区域</a>
        </div>
            </td>
        </tr>



    </table>
</form>


<div style="text-align:center;padding:5px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
</div>
<script>


    function submitForm() {
        $('#ff').form('submit', {
            url: "../user.do/update.do",

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
    function changeArea()
    {
        parent.$("#add").window({
            title: "更改区域",
            width: 300,
            height: 500,
            shadow:true,
            content: '<iframe src="../jsp/updateArea.jsp?account=${user.account}"  frameborder="0" width="100%" height="100%"/>'
        });

    }




</script>

</body>
</html>
