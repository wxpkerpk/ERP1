/**
 *
 */$(function () {

    var dd = parent.$('iframe[title="销售人员管理"]').get(0).contentWindow.$("#tt");
    var row = dd.datagrid("getSelections");
    $('#ff').form('load', {
        tel: row[0].tel,
        account: row[0].account,
        password: row[0].password,
        name: row[0].name,
        sex: row[0].sex,
        position: row[0].position
    });

});
function submitForm() {

    $('#isSubmitPassWord').val(isSubmitPassWord);
    $('#ff').form('submit', {
        url: "..//seller.controller/Update.controller",
        onSubmit: function () {
            return true;
        },
        success: function () {
            parent.$('iframe[title="销售人员管理"]').get(0).contentWindow.$("#tt").datagrid('reload');
            parent.$('iframe[title="销售人员管理"]').get(0).contentWindow.$("#tt").datagrid('clearSelections');
            parent.$('#add').window('close');
        }
    });
}
var isSubmitPassWord=0;


function getPassWord()
{
    isSubmitPassWord=1;
}

function clearForm() {
    $('#ff').form('clear');
}
