/**
 * Created by xing on 2015-07-02.
 */

//关闭弹出框
function convert(rows) {
    function exists(rows, _parentId) {
        for (var i = 0; i < rows.length; i++) {
            if (rows[i].id == _parentId) return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        if (!exists(rows, row._parentId)) {
            nodes.push({
                id: row.id,
                text: row.name
            });
        }
    }

    var toDo = [];
    for (var i = 0; i < nodes.length; i++) {
        toDo.push(nodes[i]);
    }
    while (toDo.length) {
        var node = toDo.shift();	// the parent Node
        // get the children nodes
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row._parentId == node.id) {
                var child = {id: row.id, text: row.name};
                if (node.children) {
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}



$(function () {
    $('#parentId').combotree({
        url: "../materialType.do/getAllNode.do",
        method:'get',
        loadFilter: function (rows) {
            return convert(rows);
        }
    });

});

function submitForm() {
    $.messager.progress({
        msg:"正在上传",
        interval:300,
    });
    $('#ff').form('submit', {
        url: "../material.do/add.do",
        onSubmit: function () {
            var t = $('#tree').combotree('tree');
            if (t.tree('getSelected')) {
                if (t.tree('isLeaf', t.tree('getSelected').target)) {
                    return true;
                }
                else {
                    $.messager.show({
                        title: "失败",
                        msg: "该分类不是品牌，无法添加商品"
                    });
                    return false;
                }
            }
            else {
                return true;
            }
        },
        success: function (data) {
            var data = eval('(' + data + ')');

            if (data.msg == 'ok') {
                $.messager.show({	// show success message
                    title: '成功',
                    msg: "服务器出错"
                });
                var ifr1 = parent.$('iframe[title="材料详情管理"]').get(0);
                if (ifr1) {
                    parent.$('iframe[title="材料详情管理"]').get(0).contentWindow.$("#tt").datagrid('reload');
                    parent.$('iframe[title="材料详情管理"]').get(0).contentWindow.$("#tt").datagrid('clearSelections');
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

function clearForm() {
    $('#ff').form('clear');
}


function AddImage() {
    var rows = $("#imgTemp").clone();
    rows.css("display", "table-row");
    rows.appendTo("#imgTab");
}
function AddProductImage() {
    var rows = $("#productImgTemp").clone();
    rows.css("display", "table-row");
    rows.appendTo("#productImgsTab");
}
function AddParam() {
    var rows = $("#paramTemp").clone();
    rows.css("display", "table-row");
    rows.appendTo("#parameters");
}
function RemoveParam(a) {
    var tr = $(a).parent().parent();
    $(tr).remove();
}
function RemoveImge(a) {
    var tr = $(a).parent().parent();
    $(tr).remove();
}

function check(mId) {

    $("#mid").val(mId);


    var goodsName = $("#name").val();
    if (goodsName == '') {
        layer.msg("材料名不能为空", 1, 5);
        return false;
    }

    var coverImage = $("#coverImage").val();
    if (coverImage == '') {
        layer.msg("封面图片不能为空", 1, 5);
        return false;
    }
    return true;

}
function ImgScan(a) {

    var objUrl = getObjectURL(a.files[0]);
    if (objUrl) {
        var tr = $(a).parent().parent();
        $(tr).find("img").css("display", "block")
        $(tr).find("img").attr("src", objUrl);
        // $("#img0").attr("src", objUrl) ;
    }
}
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}


