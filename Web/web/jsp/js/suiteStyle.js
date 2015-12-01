/**
 * Created by wx on 2015/8/11.
 */
$(function () {
    $('#tt').treegrid({
        rownumbers: true,
        idField: 'id',
        title: '风格列表',
        treeField: 'name',
        fitColumns: true,
        striped: true,
        nowrap: true,
        lines: true,
        fit: true,
        url: '../type.controller/getNodeForStyleShow.controller',
        toolbar: [{
            iconCls: "icon-add",
            text: "添加类型",
            handler: addDept
        }, '-', {
            iconCls: "icon-edit",
            text: "更新类型",
            handler: updateDept
        }, '-', {
            iconCls: "icon-add",
            text: "删除类型",
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
function addDept() {
    parent.$("#add").window({
        title: "添加风格",
        width: 600,
        height: 500,
        content: '<iframe src="../jsp/addStyle.jsp" frameborder="0" width="100%" height="100%"/>'
    });
}
function addChildrenDept() {
    parent.$("#add").window({
        title: "添加分类",
        width: 600,
        height: 500,
        content: '<iframe src="../jsp/addStyle.jsp" frameborder="0" width="100%" height="100%"/>'
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
    } else if(rows[0].level==1)
        {
            $.messager.show({
                title: '错误信息',
                msg: '只能更新风格',
                showType: 'fade',
                timeout: 500,
                style: {
                    right: '',
                    bottom: ''
                }
            });

        }
        else{
        parent.$("#add").window({
            title: "更新分类",
            width: 600,
            height: 500,
            content: '<iframe src="../jsp/updateStyle.jsp" frameborder="0" width="100%" height="100%"/>'
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

    } else if(rows[0].level==1)
        {
            $.messager.show({
                title: '错误信息',
                msg: '只能删除风格',
                showType: 'fade',
                timeout: 500,
                style: {
                    right: '',
                    bottom: ''
                }
            });

        }
    else {
        $.messager.confirm("删除确认对话框", "是否删除选中记录？", function (r) {
            if (r) {
                var ids = '';
                for (var i = 0; i < rows.length; i++) {
                    ids += rows[i].id + ',';
                }
                ids = ids.substring(0, ids.lastIndexOf(","));
                $.post('../type.controller/delete.controller', {parentId: ids}, function (result) {
                    if (result.msg == "ok") {
                        $('#tt').treegrid('reload');	// reload the user data
                        $.messager.show({	// show error message
                            title: '删除成功',
                            msg: "成功删除记录"
                        });
                        if (parent.$('iframe[title="图层分类管理"]').get(0)) {
                            parent.$('iframe[title="图层分类管理"]').get(0).contentWindow.$("#tt").treegrid('reload');
                            parent.$('iframe[title="图层分类管理"]').get(0).contentWindow.$("#parentId").combotree('reload');
                        }
                        if (parent.$('iframe[title="图层管理"]').get(0)) {
                            parent.$('iframe[title="图层管理"]').get(0).contentWindow.$("#tt").datagrid('reload');
                            parent.$('iframe[title="图层管理"]').get(0).contentWindow.$("#parentId").combotree('reload');

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