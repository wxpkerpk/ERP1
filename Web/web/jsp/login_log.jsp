<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/7/9 0009
  Time: 下午 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath());
        return;
    }
%>
<html>
<head>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/dividePage.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/jsp/js/My97DatePicker4.8/skin/WdatePicker.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/constant.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/My97DatePicker4.8/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/DateFormat.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/Menu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/login_log.js"></script>

</head>
<body>
<div class="search-wrap">
    <div class="search-content">
        <table class="centerTable">
            <tr>
                <td width="60" style="text-align:right">手机号:</td>
                <td><input class="common-text" id="account" type="text"></td>
                <td width="100px">操作时间:</td>
                <td><input class="Wdate" type="text"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"
                           style="width: 180px; height: 23px" id="startTime"></td>
                <td>到</td>
                <td><input class="Wdate" style="width: 180px; height: 23px" type="text"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" id="endTime"></td>
                <td style=" padding-left:30px">
                    <input class="btn btn-primary btn2" name="sub" value="查询" type="button"
                           onclick="Search();">
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="result-wrap">
    <div class="result-content">
        <table class="result-tab" id="sellerTab" style="text-align: center">
            <tr>
                <td width="140px">账号</td>
                <td width="140px">手机号</td>
                <td width="150px">姓名</td>
                <td width="150px">职位</td>
                <td width="200px">时间</td>
                <td width="180px">IP地址</td>
                <td width="100px">登录次数</td>
            </tr>
        </table>
        <table style="display: none;">
            <tr id="temp" style="display: none; text-align: center">
                <td id="accounts"></td>
                <td id="mobile"></td>
                <td id="name"></td>
                <td id="position"></td>
                <td id="logTime"></td>
                <td id="ipAddress"></td>
                <td id="times"></td>
            </tr>
        </table>
        <div class="list-page">
            <p>

            <div class="badoo"><span>共</span>
                <span class="current" id="countPage">0</span>
                <span>页</span>
                <a href="#" onclick="BeforePage();">前一页</a></span>
                <span class="current" id="currentPage">0</span>
                <a href="#" onclick="NextPage();">下一页</a>
                <input type="text" class="skipPage" id="skippage" style="width: 50px"/>
                <a href="#" onclick="SkipPage();">转&nbsp;到</a>
            </div>
            </p>
        </div>
    </div>
</div>
</div>
</div>
</div>
</body>
</html>

