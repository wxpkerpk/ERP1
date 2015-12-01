<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    if (request.getSession().getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath());
        return;
    }
%>
<html>
<head>
    <meta name="renderer" content="webkit">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/dividePage.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/Menu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/constant.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/AdminAccountManage.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <jsp:include page="topMenu.jsp"></jsp:include>
</div>
<div class="container clearfix">
    <jsp:include page="leftMenu.jsp"></jsp:include>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="#">首页</a><span
                    class="crumb-step">&gt;</span><span class="crumb-name">账号管理</span><span
                    class="crumb-step">&gt;</span><span class="crumb-name">管理员账号</span></div>
        </div>
        <div class="toRight">
            <input class="btn btn-primary btn2" style="margin-top:10px; margin-right:30px" name="sub"
                   onClick="AddManageAccount();" value="新增账号" type="button"/>
        </div>
        <div class="result-wrap">
            <table class="result-tab" id="adminTab">
                <tr>
                    <th width="200px">账号</th>
                    <th width="200px">级别</th>
                    <th width="200px">操作</th>
                </tr>
                </tr>
                <c:forEach items="${pageResult.list}" var="admin" begin="0" end="${pageResult.eachPageCount}">
                    <tr>
                        <td style="display: none">${admin.id}</td>
                        <td>${admin.account}</td>
                        <td>${admin.grade}</td>
                        <td><a class="link-del" href="#" onclick="Delete(this)">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table style="display: none;">
                <tr id="temp" style="display: none; text-align: center">
                    <td style="display: none" id="dataBaseId"></td>
                    <td id="account"></td>
                    <td id="grade"></td>
                    <td><a class="link-del" href="#" onclick="Delete(this)">删除</a></td>
                </tr>
            </table>
            <div class="list-page">
                <p>

                <div class="badoo"><span>共</span>
                    <span class="current" id="countPage">${pageResult.totalPageCount}</span>
                    <span>页</span>
                    <a href="#" onclick="BeforePage();">前一页</a></span>
                    <span class="current" id="currentPage">${pageResult.currentPage}</span>
                    <a href="#" onclick="NextPage();">下一页</a>
                    <input type="text" class="skipPage" id="skippage" style="width: 50px"/>
                    <a href="#" onclick="SkipPage();">转&nbsp;到</a>
                </div>
                </p>
            </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
