/**
 *
 */
$(function () {
    $('#tt').treegrid({
        rownumbers: true,
        idField: 'id',
        title: '商品分类管理',
        treeField: 'name',
        fitColumns: true,
        striped: true,
        nowrap: true,
        lines: true,
        fit: true,
        url: '../materialType.do/getAll.do',
        onContextMenu: onContextMenu,
        toolbar: [{
            iconCls: "icon-add",
            text: "添加类型",
            handler: addType
        },'-', {
                iconCls: "icon-add",
                text: "添加品牌",
                handler: addBrand
            },'-', {
            iconCls: "icon-edit",
            text: "更新",
            handler: updateDept
        }, '-', {
            iconCls: "icon-add",
            text: "删除",
            handler: deleteDept
        }],
        columns: [[
            {field: 'name', title: '名称'},

        ]],
        onBeforeEdit: function (index, row) {
            row.editing = true;
            updateActions(index);
        },
        onAfterEdit: function (index, row) {
            row.editing = false;
            updateActions(index);
        },
        onCancelEdit: function (index, row) {
            row.editing = false;
            updateActions(index);
        }
    });
});
function addType() {
    parent.$("#add").window({
        title: "添加分类",
        shadow:true,
        width: 600,
        height: 500,
        content: '<iframe src="../jsp/addType.jsp" frameborder="0" width="100%" height="100%"/>'
    });
}
function addBrand() {
    parent.$("#add").window({
        title: "添加品牌",
        shadow:true,

        width: 600,
        height: 500,
        content: '<iframe src="../jsp/addBrand.jsp" frameborder="0" width="100%" height="100%"/>'
    });
}


function updateDept() {
    var rows = $('#tt').treegrid('getSelections');
    if (rows.length != 1) {
        $.messager.show({
            title: '错误信息',
            msg: '一次只能选择一条进行更新',
            showType: 'fade',
            timeout: 500,
            style: {
                right: '',
                bottom: ''
            }
        });
    } else {
        parent.$("#add").window({
            title: "更新分类",
            width: 600,
            shadow:true,
            height: 500,
            content: '<iframe src="../jsp/updateMaterialType.jsp" frameborder="0" width="100%" height="100%"/>'
        });
    }
}

function deleteDept() {
    var rows = $('#tt').treegrid('getSelections');
    if (rows.length == 0) {
        $.messager.show({
            title: '错误信息',
            msg: '您未选择任何记录',
            showType: 'fade',
            timeout: 500,
            style: {
                right: '',
                bottom: ''
            }
        });

    } else {
        $.messager.confirm("删除确认对话框", "是否删除选中记录？", function (r) {
            if (r) {
                var ids = '';
                for (var i = 0; i < rows.length; i++) {
                    ids += rows[i].id + ',';
                }
                ids = ids.substring(0, ids.lastIndexOf(","));
                $.post('../MaterialTypeController.controller/delete.controller', {id: ids}, function (result) {
                    if (result.msg == "ok") {
                        $('#tt').treegrid('reload');	// reload the user data
                        $.messager.show({	// show error message
                            title: '删除成功',
                            msg: "成功删除"
                        });
                        var layer=parent.$('iframe[title="商品分类管理"]').get(0)
                        if (layer) {
                           layer.contentWindow.$("#tt").treegrid('reload');
                        }

                        layer=parent.$('iframe[title="商品详情管理"]').get(0);
                        if (layer) {
                           layer.contentWindow.$("#tt").datagrid('reload');
                        }
                    } else {
                        $.messager.show({	// show error message
                            title: '删除失败',
                            msg: "删除失败"
                        });
                    }
                }, 'json');

            }
        });

    }
}
function onContextMenu(e, row) {
    e.preventDefault();
    $(this).treegrid('select', row.id);
    $('#mm').menu('show', {
        left: e.pageX,
        top: e.pageY
    });
}
function collapse() {
    var node = $('#tt').treegrid('getSelected');
    if (node) {
        $('#tt').treegrid('collapse', node.id);
    }
}
function expand() {
    var node = $('#tt').treegrid('getSelected');
    if (node) {
        $('#tt').treegrid('expand', node.id);
    }
}