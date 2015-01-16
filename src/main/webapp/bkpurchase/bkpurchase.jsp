<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <head>
    <title></title>
    <link href="../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../js/bkpurchase.js" type="text/javascript"></script>
  </head>
  <body style="width: 100%;height: 100%;">
    <table id="dg1"></table>
        <div id="tb1">
            <a href="javascript:queryToggleP()" class="easyui-linkbutton" data-options="iconCls:'icon-man'">采购管理</a>
            <div id="manager" style="display:none">
                <div>
                    购书时间：<input id="pdate" type="text" class="easyui-datebox" required="required"></input>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">购书</a>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">新生购书</a>
                </div>
                <div>
                    供应商：<select class="easyui-combobox" id="supname" name="supname" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                </div>
            </div>
        </div>
  </body>
</html>
