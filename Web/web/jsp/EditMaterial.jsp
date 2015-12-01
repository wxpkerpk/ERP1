<%--
  Created by IntelliJ IDEA.
  User: liujun
  Date: 2015/4/10
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title></title>
    <%@ include file="head.jspf" %>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/css/main.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/constant.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/edit.js"></script>
    <script type="text/javascript">
        var mId = "${mid}";
        function Closeiframe() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);

        }
        $("#mid").val(mId);
        window.onload = function () {
            var a = "${tag}";
            if (a == '1') {
                layer.msg("保存成功", 1, 1);
            }
            else if (a == '0') {
                layer.msg("保存失败", 1, 5)
            }
        }
    </script>
</head>
<body>
<div class="search-content" style="margin-left:50px; margin-top:10px">
    <form id="ff" method="post"
          enctype="multipart/form-data" >
        <input type="text" id="mid" name="mid" style="display: none">

        <div class="divCenter" style="text-align: center">
            <label style="font-size: 18px;margin-left:auto;margin-right: auto;"><b>基本信息</b></label>
            <table class="search-tab" style="margin-bottom: 10px;margin-top: 10px">
                <tr>

                    <td class="toRight">名称:</td>
                    <td><input type="text" id="name" name="name" value=${material.name}></td>
                    <td class="toRight">品牌:</td>
                    <td><input type="text" id="brand" name="brand" value=${material.brand}></td>

                </tr>
                <tr>
                    <td class="toRight">规格:</td>
                    <td><input type="text" id="standard" name="standard" value=${material.standard}></td>


                </tr>

            </table>
        </div>

        <div class="divCenter" style="text-align: center;">


            <div class="divCenter" style="text-align: center;">
                <label style="font-size: 18px;margin-left:auto;margin-right: auto;border-bottom: 10px;border-top: 5px"><b>图片信息</b></label>
                <table class="search-tab" id="imgTab" style="margin-top: 10px;margin-bottom: 15px;">
                    <tr style="height: 40px;">
                        <td style="text-align: right">封面图片:</td>
                        <td><input type="file" name="coverImage" id="coverImage"
                                   onchange="ImgScan(this);"
                                   class="common-text"/></td>
                        <td><img src="${material.thumbCoverpictureurl}"
                                 style="height: 110px;width: 160px;margin-left: 5px;margin-bottom: 5px;"></td>

                    </tr>

                </table>

                <table class="search-tab" id="productImgsTab" style="margin-top: 10px;margin-bottom: 15px;">
                    <tr>
                        <td><input class="btn btn-primary btn2" name="sub" onclick="AddProductImage();" value="新增产品图片"
                                   type="button" style="margin-bottom: 15px;">
                        </td>
                    </tr>
                    <c:forEach var="picture" items="${list}">
                        <tr style="height: 40px;">
                            <td></td>
                            <td style="text-align: right;width: 160px">产品图片:</td>
                            <td><img src="${picture.url}"
                                     style="height: 110px;width: 160px;margin-left: 5px;margin-bottom: 5px;">
                            </td>
                            <td><input type="button" style="margin-left: 15px" class="btn btn-primary btn2"
                                       onclick="RemoveImge(this);" value="删 除"></td>
                            <td><input type="text" name="productImgName" value="${picture.url}" style="display: none">
                            </td>
                        </tr>
                    </c:forEach>
                    <tr style="height: 40px;display: none" id="productImgTemp">
                        <td style="text-align: right">图片:</td>
                        <td><input type="file" name="productImg"
                                   onchange="ImgScan(this);"
                                   class="common-text" multiple/></td>
                        <td><img src=""
                                 style="height: 110px;width: 160px;display: none;margin-left: 5px;margin-bottom: 5px;">
                        </td>
                        <td><input type="button" style="margin-left: 15px" class="btn btn-primary btn2" name="sub"
                                   onclick="RemoveImge(this);"
                                   value="删 除"></td>
                    </tr>
                </table>
            </div>
        </div>

    </form>


</div>
<div style="text-align:center;padding:30px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
</div>
</body>
</html>
