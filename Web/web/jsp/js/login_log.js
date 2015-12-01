/**
 * Created by Administrator on 2015/7/9 0009.
 */
function Check() {
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    if (startTime != '' && endTime != '') {
        if (startTime > endTime) {
            layer.msg("开始时间不能大于结束时间", 1, 5);
            return false;
        }
    }
    return true;
}

function Search() {
    if (Check()) {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var account = $('#account').val();
        var currentPage = 1;
        $.ajax(
            {
                type: "post",
                url: "../LoginLogController.controller/Search.controller",
                data: {"startTime": startTime, "endTime": endTime, "account": account, "currentPage": currentPage},
                error: function () {
                    layer.msg('系统繁忙', 1, 5);
                },
                success: function (data) {
                    Binddata(data);
                }
            }
        );
    }
}

function Binddata(data) {
    $("#sellerTab tr").eq(0).nextAll().remove();
    var rows = $("#temp").clone();
    var json = eval("(" + data + ")");
    $.each(json["list"], function (i, n) {
        var row = rows.clone();
        row.find("#logTime").text(getFormatDateByLong(n["loginTime"], "yyyy年MM月dd日 hh:mm"));
        row.find("#accounts").text(n["seller"].account);
        row.find("#mobile").text(n["seller"].tel);
        row.find("#name").text(n["seller"].name);
        row.find("#times").text(n["seller"].loginTimes);
        row.find("#position").text(n["seller"].position);
        row.find("#ipAddress").text(n["loginIp"]);
        row.css("display", "table-row");
        row.appendTo("#sellerTab");
    });
    $("#currentPage").text(json["currentPage"]);
    $("#countPage").text(json["totalPage"]);
}

//下一页
function NextPage() {
    if (Check()) {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var account = $('#account').val();
        var currentPage = parseInt($("#currentPage").text());
        var countPage = parseInt($("#countPage").text());
        if (countPage <= currentPage) {
            layer.msg('最后一页', 1, 5);
            return;
        }
        currentPage = parseInt(currentPage) + parseInt(1);
        $.ajax(
            {
                type: "post",
                url: "../LoginLogController.controller/Search.controller",
                data: {
                    "startTime": startTime,
                    "endTime": endTime,
                    "account": account,
                    "currentPage": currentPage
                },
                error: function () {
                    layer.msg('系统繁忙', 1, 5);
                },
                success: function (data) {
                    Binddata(data);
                }
            }
        );
    }

}

//前一页
function BeforePage() {
    if (Check()) {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var account = $('#account').val();
        var currentPage = parseInt($("#currentPage").text());
        var countPage = parseInt($("#countPage").text());
        if (1 == currentPage || 0 == currentPage) {
            layer.msg('最前一页', 1, 5);
            return;
        }
        currentPage = parseInt(currentPage) - parseInt(1);
        $.ajax(
            {
                type: "post",
                url: "../LoginLogController.controller/Search.controller",
                data: {
                    "startTime": startTime,
                    "endTime": endTime,
                    "account": account,
                    "currentPage": currentPage
                },
                error: function () {
                    layer.msg('系统繁忙', 1, 5);
                },
                success: function (data) {
                    Binddata(data);
                }
            }
        );
    }
}


//跳页
function SkipPage() {
    if (Check()) {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var account = $('#account').val();
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
        $.ajax(
            {
                type: "post",
                url: "../LoginLogController.controller/Search.controller",
                data: {
                    "startTime": startTime,
                    "endTime": endTime,
                    "account": account,
                    "currentPage": page
                },
                error: function () {
                    layer.msg('系统繁忙', 1, 5);
                },
                success: function (data) {

                    Binddata(data);
                }
            }
        );
    }
}