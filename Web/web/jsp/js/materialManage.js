/**
 * Created by xing on 2015-06-29.
 */



function SearchAccount(count) {
    var keyWord = $("#keyWord").val();
    if (keyWord == "") {
        layer.msg('关键词不可为空', 1, 5);
        return;

    }
    state = 2;

    $.ajax(
        {
            type: "post",
            url: "/MaterialShow/MaterialController.controller/Search.controller",
            data: {"materialTypeId": 0, "keyword": keyWord, "currentPage": count},
            error: function () {
                layer.msg('系统错误', 1, 5);
            },
            success: function (data) {
                Binddata(data);
            }
        }
    );
}


function IntiPage(count) {
    $.ajax(
        {
            type: "get",
            url: "/MaterialShow/MaterialController.controller/orPage.controller?currentPage=" + count,
            error: function () {
                layer.msg('系统繁忙', 1, 5);
            },
            success: function (data) {
                var json = eval("(" + data + ")");
                if (json["msg"] == 'ok') {
                    Binddata(data);
                }
            }
        }
    );


}

function Binddata(data) {
    $("#materialTab tr").eq(0).nextAll().remove();
    var rows = $("#temp").clone();
    var json = eval("(" + data + ")");
    $.each(json["list"], function (i, n) {
        var row = rows.clone();
        row.find("#dataBaseId").text(n["id"]);
        row.find("#name").text(n["name"]);
        row.find("#coverpictureurl").children().attr("src", n["coverpictureurl"]);
        row.find("#descr").text(n["descr"]);
        row.find("#brand").text(n["brand"]);
        row.find("#standard").text(n["standard"]);
        row.css("display", "table-row");
        row.appendTo("#materialTab");
    });
    $("#currentPage").text(json["currentPage"]);
    $("#countPage").text(json["totalPage"]);
}

//下一页
function NextPage() {

    var currentPage = parseInt($("#currentPage").text());
    var countPage = parseInt($("#countPage").text());
    if (countPage <= currentPage) {
        layer.msg('最后一页', 1, 5);
        return;
    }
    currentPage = parseInt(currentPage) + parseInt(1);
    if (state == 0) {
        IntiPage(currentPage);
    } else if (state == 1) {
        getPageByType(currentPage, freshType);
    } else {
        SearchAccount(currentPage);

    }

}

//前一页
function BeforePage() {

    var currentPage = parseInt($("#currentPage").text());
    var countPage = parseInt($("#countPage").text());
    if (1 == currentPage) {
        layer.msg('最前一页', 1, 5);
        return;
    }
    currentPage = parseInt(currentPage) - parseInt(1);
    if (state == 0) {
        IntiPage(currentPage);
    } else if (state == 1) {
        getPageByType(currentPage, freshType);
    } else {
        SearchAccount(currentPage);

    }
}
function getPageByType(currentpage, typeid) {
    $.ajax(
        {
            type: "post",
            url: "/MaterialShow/MaterialController.controller/GetMaterials.controller",
            data: {"TypeId": typeid, "currentPage": currentpage},
            error: function () {
                layer.msg('系统繁忙', 1, 5);
            },
            success: function (data) {
                var json = eval("(" + data + ")");
                if (json["msg"] == 'ok') {
                    Binddata(data);
                }
            }
        }
    );


}


//跳页
function SkipPage() {
    var currentPage = parseInt($("#currentPage").text());
    var countPage = parseInt($("#countPage").text());
    var page = $("#skippage").val();
    if (countPage == 0) {
        layer.msg("无数据", 1, 5);
        return;
    }
    if (isNaN(page)) {
        layer.msg("不是页数", 1, 5);
        return;
    }
    page = parseInt(page);
    if (page < 1) {
        layer.msg('页数不能小于1', 1, 5);
        return;
    }
    if (page > countPage) {
        layer.msg("页数不能大于" + countPage, 1, 5);
        return;
    }

    if (state == 0) {
        IntiPage(page);
    } else if (state == 1) {
        getPageByType(page, freshType);
    } else {
        SearchAccount(page);

    }
}

function RefreshPage() {

    var currentPage = $("#currentPage").text();

    if (state == 0) {
        IntiPage(currentPage);
    } else if (state == 1) {
        getPageByType(currentPage, freshType);
    } else {
        SearchAccount(currentPage);

    }

}


function GetMaterialByType(TypeId) {


    $.ajax(
        {
            type: "post",
            url: "/MaterialShow/MaterialController.controller/GetMaterials.controller",
            data: {"TypeId": TypeId, "currentPage": 1},
            error: function () {
                layer.msg('系统繁忙', 1, 5);
            },
            success: function (data) {
                var json = eval("(" + data + ")");
                if (json["msg"] == 'ok') {
                    Binddata(data);
                    freshType = TypeId;
                    state = 1;
                }
            }
        }
    );
}

//弹出框


function InitAddAccount(a) {
    /* var tr = $(a).parent().parent();
     var id = $(tr).find("td:eq(0)").html();
     */

    if (fff == null) {

        layer.msg('您还没有选择任何类型', 1, 5);
        return false;

    }

    var pageii = $.layer({
        type: 2,
        border: [0],
        title: false,
        shift: 'top',
        shadeClose: true,
        closeBtn: true,
        iframe: {src: '/MaterialShow/MaterialController.controller/GetAdd.controller?mid=' + fff},
        area: ['620px', '610px']
    });
}


//禁用账号
function Delete(a) {
    $.layer({
        shade: [0],
        area: ['auto', 'auto'],
        dialog: {
            msg: '删除后将不可恢复,确认？',
            btns: 2,
            type: 4,
            btn: ['取消', '确定'],
            yes: function () {
            }, no: function () {
                var tr = $(a).parent().parent();
                var id = $(tr).find("td:eq(0)").html();
                $.ajax(
                    {
                        type: "post",
                        url: "/MaterialShow/MaterialController.controller/Delete.controller",
                        data: {'MaterialId': id},
                        error: function () {
                            layer.msg('删除失败', 1, 5);
                        },
                        success: function () {
                            layer.msg('操作成功', 1, 1);
                            RefreshPage();
                        }
                    }
                );
            }
        }
    });
}


function edit(a) {
    var tr = $(a).parent().parent();
    var id = $(tr).find("td:eq(0)").html();


    var pageii = $.layer({
        type: 2,
        border: [0],
        title: false,
        shift: 'top',
        shadeClose: true,
        closeBtn: true,
        iframe: {src: '/MaterialShow/MaterialController.controller/GetMaterialEdit.controller?mid=' + id},
        area: ['620px', '610px']
    });


}