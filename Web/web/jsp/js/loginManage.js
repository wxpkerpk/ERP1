/**
 * Created by liujun on 2015/1/27.
 */
$(function () {
    $('#clouds').pan({fps: 20, speed: 0.7, dir: 'right', depth: 10});

});

function Login() {
    if ($('#uid').val() == "" || $('#pwd').val() == "" || $('#code').val() == "") {
        $('.tip').html('用户名或密码不可为空！')
    }
    else {
        var userName = $("#uid").val();
        var passWord = $("#pwd").val();
        $.ajax(
            {
                type: "post",
                url: "/MaterialShow/BackStageLoginManageController.controller/AdminLogin.controller",
                data: {"UserName": userName, "PassWord": passWord},
                error: function () {
                    layer.msg("系统繁忙", 1, 18);
                },
                success: function (data) {
                    var json = eval("(" + data + ")");
                    if (json["msg"] == '帐号不存在') {
                        layer.msg("账号不存在", 1, 5)
                    }
                    else if (json["msg"] == '密码错误') {
                        layer.msg("用户名或密码错误", 1, 5)
                    }
                    if (json["msg"] == '登录成功') {
                        location.href="/MaterialShow/jsp/index.jsp";
                    }

                }
            }
        );
    }
}