/**
 *
 */
$(function () {
    $('#tt').datagrid({
        idField: 'id',
        title: '人员列表',
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: true,
        checkbox: true,
        onRowContextMenu: function (e, rowIndex, rowData) {
            e.preventDefault();
            $(this).datagrid("clearSelections");
            $(this).datagrid('selectRow', rowIndex);
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        url: '../seller.controller/Search.controller',
        pagination: true,
        toolbar: [{
            iconCls: "icon-add",
            text: "添加人员",
            handler: addUser
        }, '-', {
            iconCls: "icon-edit",
            text: "更新人员",
            handler: updateUser
        }, '-', {
            iconCls: "icon-add",
            text: "删除人员",
            handler: deleteUser
        }, '-', {
            text: "账号<input name='account' id='account' class='easyui-textbox'  />"
        }, '-', {
            text: "姓名<input name='accountName' id='accountName' class='easyui-numberbox'  />"
        }, '-', {
            text: "职位<input name='position' id='position' class='easyui-textbox'  />"
        }, '-', {
            text: "<a id='ss' href='#' class='easyui-linkbutton' onclick='doSearch()' iconCls='icon-search'></a>"
        }
        ],

        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'name', title: '姓名', align: 'right', editor: 'text'},
            {field: 'account', title: '编号'},
            {
                field: 'sex', title: '性别', align: 'right', formatter: function (value, row, index) {
                if (value == "0") return "男";
                else return "女";
            }
            },
            {field: 'tel', title: 'TEL'},
            {field: 'position', title: '职务'}
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
    $('#ss').linkbutton({});

    $('#tel').numberbox({
        min: 0,
    });
    $('#po').textbox({});
    $('#name').textbox({
        iconCls: 'icon-man',
    });

});
function doSearch() {
    $('#tt').datagrid('load', {
        position: $('#position').val(),
        accountName: $('#accountName').val(),
        account: $('#account').val()
    });
}
function addUser() {
    parent.$("#add").window({
        title: "添加人员",
        width: 600,
        height: 500,
        content: '<iframe src="../jsp/addUser.jsp" frameborder="0" width="100%" height="100%"/>'
    });
}
function updateUser() {
    var rows = $('#tt').datagrid('getSelections');
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
            title: "更新人员",
            width: 600,
            height: 500,
            content: '<iframe src="../jsp/updateUser.jsp" frameborder="0" width="100%" height="100%"/>'
        });
    }
}
function deleteUser() {
    var rows = $('#tt').datagrid('getSelections');
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
                    ids += rows[i].tel + ',';
                }
                ids = ids.substring(0, ids.lastIndexOf(","));
                $.post('../seller.controller/deleteSeller.controller', {SellerTels: ids}, function (result) {
                    if (result.msg == "ok") {
                        $.messager.show({	// show error message
                            title: '删除成功',
                            msg: "成功删除所选人员"
                        });
                        $('#tt').datagrid('reload');	// reload the user data
                        $('#tt').datagrid('clearSelections');
                    } else {
                        $.messager.show({	// show error message
                            title: '删除失败',
                            msg: "人员删除失败"
                        });
                    }
                }, 'json');

            }
        });

    }
}
