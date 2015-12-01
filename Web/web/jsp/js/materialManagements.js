/**
 *
 */
$(function () {
    $('#tt').datagrid({
        idField: 'id',
        title: '材料列表',
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
        url: '../material.do/search.controller',
        pagination: true,
        toolbar: [{
            iconCls: "icon-add",
            text: "添加材料",
            handler: addUser
        }, '-', {
            iconCls: "icon-edit",
            text: "更新材料",
            handler: updateUser
        }, '-', {
            iconCls: "icon-add",
            text: "删除材料",
            handler: deleteUser
        }, '-', {
            text: "类型<select name='parentId' id='parentId' ></select>"
        }, '-', {
            text: "关键词<input name='name' id='name1' class='easyui-textbox'  />"
        }, '-', {
            text: "<a id='ss' href='#' class='easyui-linkbutton' onclick='doSearch()' iconCls='icon-search'></a>"
        }
        ],

        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'name', title: '名称', align: 'right', editor: 'text'},
            {field: 'brand', title: '品牌', align: 'right', editor: 'text'},
            {field: 'keyWords', title: '关键词', editor: 'text', align: 'right'},
            {
                field: 'coverpictureurl', title: '封面', formatter: function (value, row, index) {
                return '<img style="width:120px;" src=" ' + value + '"/>'
            }
            },
            {field: 'descr', title: '描述', align: 'cent', editor: 'text'},
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
        url: '../MaterialTypeController.controller/getNode.controller',
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
        descr: $('#descr').val(),
        name: $('#name1').val(),
        brand: $('#brand').val(),
        keyword: $('#keyword').val()

    });
}
function addUser() {
    parent.$("#add").window({
        title: "添加材料",
        width: 600,
        height: 500,
        content: '<iframe src="../jsp/AddProduct.jsp" frameborder="0" width="100%" height="100%"/>'
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
        var rows = $('#tt').datagrid('getSelections');
        var id=rows[0].id;
        var url="../MaterialController.controller/GetMaterialEdit.controller?id="+id;
        parent.$("#add").window({
            title: "材料人员",
            width: 600,
            height: 500,
            content: '<iframe src="'+url+'" frameborder="0" width="100%" height="100%"/>'
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
                $.post('../MaterialController.controller/Delete.controller', {ids: ids}, function (result) {
                    if (result.msg == "ok") {
                        $.messager.show({	// show error message
                            title: '删除成功',
                            msg: "成功删除所选材料"
                        });
                        $('#tt').datagrid('reload');	// reload the user data
                        $('#tt').datagrid('clearSelections');
                    } else {
                        $.messager.show({	// show error message
                            title: '删除失败',
                            msg: "材料删除失败"
                        });
                    }
                }, 'json');

            }
        });

    }
}
