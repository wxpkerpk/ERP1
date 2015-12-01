/**
 * Created by liujun on 2015/1/28.
 */
function Check() {
    var oldPassWord = $("#oldPassWord").val();
    var newPassWord = $("#newPassWord").val();
    var firmNewPassword = $("#firmNewPassword").val();
    if (oldPassWord == '') {
        layer.msg("原密码不能为空", 1, 5);
        return false;
    }
    var oldPassWord = $("#oldPassWord").val();
    if (newPassWord == '') {
        layer.msg("新密码不能为空", 1, 5);
        return false;
    }

    if (firmNewPassword == '') {
        layer.msg("新密码不能为空", 1, 5);
        return false;
    }
    if (newPassWord != firmNewPassword) {
        layer.msg("两次输入密码不一致", 1, 5);
        return false;
    }
    return true;
}
function UpdatePassWord() {
    if (Check()) {
        var oldPassWord = $("#oldPassWord").val();
        var newPassWord = $("#newPassWord").val();
        var firmNewPassword = $("#firmNewPassword").val();
        $.ajax(
            {
                method: "Post",
                url: "../BackStageLoginManageController.controller/Update.controller",
                data: {"oldPassword": oldPassWord, "newPassword": firmNewPassword, "userName": 'admin'},
                error: function () {
                    layer.msg("系统繁忙", 1, 5);
                },
                success: function (data) {
                    var json = eval("(" + data + ")");
                    if (json["msg"] == '原密码错误') {
                        layer.msg("原密码错误", 1, 5);
                    }
                    else if (json["msg"] == '修改成功') {
                        layer.msg("修改成功", 1, 1);
                    }
                }
            }
        );
    }
}