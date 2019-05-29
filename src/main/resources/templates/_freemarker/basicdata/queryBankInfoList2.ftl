<!DOCTYPE html>
<html lang="en"">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>银行信息222</title>

    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${base}/webjars/js/jquery-ui/css/no-theme/jquery-ui.custom.min.css" />
    <link rel="stylesheet" type="text/css" href="${base}/webjars/js/jquery-easyui/themes/bootstrap/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${base}/webjars/js/nice-validator/jquery.validator.css" />
    <link rel="stylesheet" type="text/css" href="${base}/webjars/js/viewer/css/viewer.min.css" />
    <!-- customize -->
    <link rel="stylesheet" type="text/css" href="${base}/webjars/css/icons.css" />
    <link rel="stylesheet" type="text/css" href="${base}/webjars/css/commons.css" />

    <!-- jquery -->
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/jquery.min.js" ></script>
    <!-- jqueryui -->
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/jquery-ui/js/jquery-ui.custom.js" ></script>
    <!-- easyui -->
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/jquery-easyui/jquery.easyui.min.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/jquery-easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/ext.easyui.js" ></script>
    <!-- other -->
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/datepicker/WdatePicker.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/jquery.form.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/nice-validator/jquery.validator.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/nice-validator/local/zh-CN.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/viewer/js/viewer-jquery.min.js" ></script><!-- viewer -->
    <!-- echarts -->
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/echarts/echarts.min.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/echarts/ext.echarts.js" ></script>
    <!-- customize -->
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/commons.js" ></script>
    <script type="text/javascript" charset="utf-8" src="${base}/webjars/js/components.js" ></script>

    <script type="text/javascript">
        //权限判定
        $.canAdd = false;
        $.canUpdate = false;
        $.canDelete = false;
        $.canDisable = false;
        $.canEnable = false;


        var grid = null;
        var idFieldName = 'id';
        var nameFieldName = 'name';
        var entityName = '银行信息管理';

        $(document).ready(function () {
            var $editALink = $("#editALink");
            var $deleteALink = $("#deleteALink");
            var $enableALink = $("#enableALink");
            var $disableALink = $("#disableALink");

            grid = $('#grid').datagrid({
                url: '${base}/bankinfo/queryList',
                fit: true,
                fitColumns: true,
                border: false,
                idField: 'id',
                checkOnSelect: false,
                selectOnCheck: false,
                nowrap: true,
                striped: true,
                singleSelect: true,
                frozenColumns: [
                    [
                        {
                            field: 'row.id',
                            title: 'pk',
                            width: 150,
                            hidden: true
                        }
                    ]
                ],
                columns: [
                    [
                        {
                            field: 'logoFileId',
                            title: '图片地址',
                            width: 150,
                            align: 'center',
                            formatter: function (value, row, index) {
                                if ($.ObjectUtils.isEmpty(value)) {
                                    return "";
                                }
                                var img = $.formatString('<img class="imgViewer" data-original="${base}/filecontext/resource/{0}" src="${base}/filecontext/resource/thumbnail/{1}?height=25"/>', value, value);
                                return img;
                            }
                        },
                        {
                            field: 'name',
                            title: '银行名称',
                            width: 150
                        },
                        {
                            field: 'code',
                            title: '银行英文简称',
                            width: 150
                        },
                        {
                            field: 'aliases',
                            title: '别名集合',
                            width: 200
                        },
                        {
                            field: 'valid',
                            title: '是否有效',
                            width: 100
                            , formatter: function (value, row, index) {
                                var text = '';
                                if (value) {
                                    text = '是';
                                } else {
                                    text = '<font color="red">否</font>';
                                }
                                return text;
                            }
                        },
                        {
                            field: 'modifyAble',
                            title: '是否可编辑',
                            width: 100
                            , formatter: function (value, row, index) {
                                var text = '';
                                if (value) {
                                    text = '<font color="green">是</font>';
                                } else {
                                    text = '否';
                                }
                                return text;
                            }
                        },
                        {
                            field: 'personalLoginUrl',
                            title: '个人网银登录url',
                            //hidden : true,
                            width: 200
                        },
                        {
                            field: 'institutionLoginUrl',
                            title: '机构网银登录url',
                            //hidden : true,
                            width: 200
                        },
                        {
                            field: 'remark',
                            title: '备注',
                            width: 200
                        },
                        {
                            field: 'createDate',
                            title: '创建时间',
                            width: 200
                            , formatter: function (cellvalue, options, rowObject) {
                                var date = new Date();
                                date.setTime(cellvalue);
                                // return date.format('yyyy-MM-dd hh:mm:ss');
                                return cellvalue;
                                ;
                            }
                        },
                        {
                            field: 'lastUpdateDate',
                            title: '最后更新时间',
                            width: 200
                            , formatter: function (cellvalue, options, rowObject) {
                                var date = new Date();
                                date.setTime(cellvalue);
                                return date.format('yyyy-MM-dd hh:mm:ss');
                                ;
                            }
                        }
                        /*
                        <c:if test="${show_grid_action == true}">
                        ,{
                            field : 'action',
                            title : '操作',
                            width : 220,
                            formatter : function(value, row, index) {
                                var str = '&nbsp;';
                                if(!row.valid && $.canEnable){
                                    str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row[idFieldName], row[nameFieldName], '${base}/style/images/extjs_icons/control/control_play_blue.png');
                                    str += '&nbsp;';
                                }
                                if($.canUpdate){
                                    str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${base}/style/images/extjs_icons/pencil.png');
                                    str += '&nbsp;';
                                }

                                if($.canDelete){
                                    str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${base}/style/images/extjs_icons/pencil_delete.png');
                                    str += '&nbsp;';
                                }
                                if(row.valid && $.canDisable){
                                    str += $.formatString('<img onclick="disableFun(\'{0}\',\'{1}\');" src="{2}" title="禁用"/>', row[idFieldName], row[nameFieldName], '${base}/style/images/extjs_icons/control/control_stop_blue.png');
                                    str += '&nbsp;';
                                }
                                return str;
                            }
                        }
                        </c:if>
                        */
                    ]],
                toolbar: '#toolbar',
                onDblClickRow: function (index, row) {
                    //&& row.modifyAble
                    if ($.canUpdate) {
                        editFun(row[idFieldName], row[nameFieldName]);
                    }
                },
                onClickRow: function (index, row) {
                    $editALink.linkbutton('enable');
                    $deleteALink.linkbutton('enable');

                    if (row.modifyAble) {
                        $editALink.linkbutton('enable');
                        $deleteALink.linkbutton('enable');

                        if (row.valid) {
                            $enableALink.linkbutton('disable');
                            $enableALink.hide();
                            $disableALink.show();
                            $disableALink.linkbutton('enable');
                        } else {
                            $disableALink.linkbutton('disable');
                            $disableALink.hide();
                            $enableALink.show();
                            $enableALink.linkbutton('enable');
                        }
                    } else {
                        $enableALink.show();
                        $enableALink.linkbutton('disable');
                        $disableALink.show();
                        $disableALink.linkbutton('disable');

                        //$editALink.linkbutton('disable');
                        $deleteALink.linkbutton('disable');
                    }
                },
                onLoadSuccess: function () {
                    $(this).datagrid('unselectAll');
                    $(this).datagrid('tooltip');

                    $editALink.linkbutton('disable');
                    $deleteALink.linkbutton('disable');
                    $enableALink.show();
                    $disableALink.show();
                    $enableALink.linkbutton('disable');
                    $disableALink.linkbutton('disable');

                    $(".imgViewer").viewer({url: 'data-original'});
                }
            });
        });

        /*
         * 查询
         */
        function queryFun() {
            grid.datagrid('load', $('#queryForm').serializeObject());
            return false;
        }

        /*
         * 新增
         */
        function addFun() {
            DialogUtils.progress({
                text: '加载中，请等待....'
            });
            DialogUtils.openModalDialog(
                "addBankInfo",
                "新增银行",
                $.formatString("${base}/bankInfo/toAddBankInfo.action"),
                550, 278, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
        }

        /**
         * 编辑
         */
        function editFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return;
            }
            DialogUtils.progress({
                text: '加载中，请等待....'
            });
            DialogUtils.openModalDialog(
                "updateBankInfo",
                "编辑银行",
                $.formatString("${base}/bankInfo/toUpdateBankInfo.action?bankInfoId={0}", id),
                650, 450, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
        }

        /*
         * 删除
         */
        function deleteFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的BankInfo
            DialogUtils.confirm("确认提醒",
                $.formatString("是否确认删除{0}:[{1}]?", entityName, name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认删除指定的BankInfo
                        $.post(
                            '${base}/bankInfo/deleteById.action',
                            {bankInfoId: id},
                            function (data) {
                                DialogUtils.progress('close');
                                if (data) {
                                    DialogUtils.tip("删除" + entityName + "成功");
                                } else {
                                    $.formatString("删除{0}:{1}失败.指定{0}可能已经被其他管理员所删除.如果指定{0}依然存在，请联系系统管理员.", entityName, name);
                                }
                                grid.datagrid('load', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }

        /*
         * 禁用
         */
        function disableFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认禁用指定BankInfo
            DialogUtils.confirm(
                "确认提醒",
                $.formatString("是否确认禁用{0}:[{1}]?", entityName, name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认禁用指定BankInfo
                        $.post(
                            '${base}/bankInfo/disableById.action',
                            {bankInfoId: id},
                            function () {
                                DialogUtils.progress('close');
                                DialogUtils.tip("禁用" + entityName + "成功");
                                grid.datagrid('reload', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }

        /*
         * 启用
         */
        function enableFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认禁用指定BankInfo
            DialogUtils.confirm(
                "确认提醒",
                $.formatString("是否确认启用{0}:[{1}]?", entityName, name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认启用指定BankInfo
                        $.post(
                            '${base}/bankInfo/enableById.action',
                            {bankInfoId: id},
                            function () {
                                DialogUtils.progress('close');
                                DialogUtils.tip("启用" + entityName + "成功");
                                grid.datagrid('reload', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }
    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
    <!--//FIXME: 修改查询条件框体高度 -->
    <div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
        <form id="queryForm" class="form">
            <table class="table table-hover table-condensed">
                <tr>
                    <th>编码</th>
                    <td><input id="code" name="code"/></td>
                    <th>名称</th>
                    <td><input id="name" name="name"/></td>
                    <th>别名</th>
                    <td><input id="aliases" name="aliases"/></td>
                </tr>
                <tr>
                    <th>是否有效</th>
                    <td><input id="valid" name="valid"/></td>
                    <th>最小创建时间</th>
                    <td><input id="minCreateDate" name="minCreateDate"
                               readonly="readonly"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                    <th>最大创建时间</th>
                    <td><input id="maxCreateDate" name="maxCreateDate"
                               readonly="readonly"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="button operRow">
                        <a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);"
                           class="easyui-linkbutton" data-options="iconCls:'search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false">
        <table id="grid"></table>
    </div>


    <div id="productDg-toolbar" class="topjui-toolbar"
         data-options="grid:{
           type:'datagrid',
           id:'productDg'
       }">
        <a id="add" href="javascript:void(0)">新增</a>
        <a id="edit" href="javascript:void(0)">编辑</a>
        <a id="batchUpdate" href="javascript:void(0)">批量操作</a>
        <a id="delete" href="javascript:void(0)">删除</a>
        <a id="filter" href="javascript:void(0)">过滤</a>
        <a id="search" href="javascript:void(0)">查询</a>
        <a id="import" href="javascript:void(0)">导入</a>
        <a id="export" href="javascript:void(0)">导出</a>
        <a id="openTab" href="javascript:void(0)">标签页</a>
        <a id="openWindow" href="javascript:void(0)">新窗口</a>
        <a id="request" href="javascript:void(0)">普通请求</a>
        <a id="myFun" href="javascript:void(0)">自定义方法</a>
        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="menu:'#exportSubMenu',
       btnCls:'topjui-btn-blue',
       hasDownArrow:true,
       iconCls:'fa fa-list'">更多</a>
        <div id="exportSubMenu" class="topjui-toolbar"
             data-options="grid:{
           type:'datagrid',
           id:'productDg'
       }" style="width:150px;">
            <div data-toggle="topjui-menubutton"
                 data-options="method:'request',
             iconCls:'fa fa-file-pdf-o',
             url:_ctx + '/json/response/success.json?uuid={uuid}'">导出 PDF 报表
            </div>
            <div data-toggle="topjui-menubutton"
                 data-options="method:'export',
             iconCls:'fa fa-file-excel-o',
             url: _ctx + '/json/response/export.html'">导出EXCEL列表
            </div>
            <div data-toggle="topjui-menubutton"
                 data-options="method:'request',
             iconCls:'fa fa-file-excel-o',
             url:_ctx + '/json/response/success.json?uuid={uuid}'">导出EXCEL报表
            </div>
            <div data-toggle="topjui-menubutton"
                 data-options="method:'request',
             iconCls:'fa fa-file-word-o',
             url:_ctx + '/json/response/success.json?uuid={uuid}'">导出WORD报表
            </div>
        </div>
        <form id="queryForm" class="search-box">
            <input type="text" name="name" data-toggle="topjui-textbox"
                   data-options="id:'name',prompt:'产品名称',width:100">
            <input type="text" name="code" data-toggle="topjui-textbox"
                   data-options="id:'code',prompt:'产品型号',width:100">
            <a id="queryBtn" href="javascript:void(0)">查询</a>
        </form>
    </div>


    <div id="toolbar" style="display: none;">
        [#--[#if  authContext.hasAuth("add_bankInfo") ]--]
        [#--<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton"--]
        [#--data-options="plain:true,iconCls:'pencil_add'">新增</a>--]
        [#--[/#if]--]
        [#--<c:if test='${authContext.hasAuth("update_bankInfo") }'>--]
        [#--<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton"--]
        [#--data-options="plain:true,iconCls:'pencil'">编辑</a>--]
        [#--</c:if>--]
        [#--<c:if test='${authContext.hasAuth("delete_bankInfo") }'>--]
        [#--<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton"--]
        [#--data-options="plain:true,iconCls:'pencil_delete'">删除</a>--]
        [#--</c:if>--]
        [#--<c:if test='${authContext.hasAuth("enable_bankInfo") }'>--]
        [#--<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton"--]
        [#--data-options="plain:true,iconCls:'control_play_blue'">启用</a>--]
        [#--</c:if>--]
        [#--<c:if test='${authContext.hasAuth("disable_bankInfo") }'>--]
        [#--<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton"--]
        [#--data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>--]
        [#--</c:if>--]
        <a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);"
           class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
</div>
</body>