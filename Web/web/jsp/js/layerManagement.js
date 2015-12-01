/**
 * Created by wx on 2015/8/11.
 */


/**
 *
 */
$(function () {
    $('#tt').datagrid({
        idField: 'id',
        title: '图层',
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
        url: '../layer.controller/search.controller',
        pagination: true,
        toolbar: [{
            iconCls: "icon-add",
            text: "添加图层",
            handler: addUser
        }, '-', {
            iconCls: "icon-add",
            text: "删除图层",
            handler: deleteUser
        }, '-', {
            text: "类型<select name='parentId' id='parentId' style='width: 400px' ></select>"
        }, '-', {
            text: "名称<input name='name' id='name1' class='easyui-textbox'  />"
        },'-',{
            text: "<a id='ss' href='#' class='easyui-linkbutton' onclick='doSearch()' iconCls='icon-search'></a>"
        }
        ],

        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'name', title: '名称', align: 'right', editor: 'text'},
            {
                field: 'coverUrl', title: '封面', formatter: function (value, row, index) {
                return '<img style="width:120px;" src=" ' + value + '"/>'
            }
            },
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
    $('#parentId').combotree({
        width: 200,
        url: '../type.controller/getForLayerManageSelect.controller',
        loadFilter: function (rows) {
            return convert(rows);
        }
    });
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
        parentId: $('#parentId').combotree('getValue'),
        name: $('#name1').val(),


    });
}
function addUser() {
    parent.$("#add").window({
        title: "添加材料",
        width: 600,
        height: 500,
        shadow:true,
        content: '<iframe src="../jsp/LayerAdd.jsp" frameborder="0" width="100%" height="100%"/>'
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
            content: '<iframe src="../MaterialController.controller/GetMaterialEdit.controller" frameborder="0" width="100%" height="100%"/>'
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
                    ids += rows[i].id + ',';
                }
                ids = ids.substring(0, ids.lastIndexOf(","));
                $.post('../layer.controller/delete.controller', {ids: ids}, function (result) {
                    if (result.msg == "ok") {
                        $.messager.show({	// show error message
                            title: '删除成功',
                            msg: "成功删除所选图层"
                        });
                        $('#tt').datagrid('reload');	// reload the user data
                        $('#tt').datagrid('clearSelections');
                    } else {
                        $.messager.show({	// show error message
                            title: '删除失败',
                            msg: "图层删除失败"
                        });
                    }
                }, 'json');

            }
        });

    }
}
