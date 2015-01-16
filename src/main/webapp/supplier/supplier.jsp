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
    <script src="../js/supplier.js" type="text/javascript"></script>
  </head>
  <body style="width: 100%;height: 100%;">
     <table id="supplierTable"></table>
     <div id="supplierBtn">
        <a href="javascript:openAddSupDlg()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        <a href="javascript:updateAll()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
        <a href="javascript:deleteAll()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除</a>
	    <a href="javascript:queryToggleCol()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查询</a>	
	   	<div id="id="query" style="display:none"">
	   	       供应商:<select class="easyui-combobox" id="supname-s" name="supname-s" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
	        　       出版社:<input type="text" id="publiserName" name="publiserName" class="easyui-textbox">
	      <a href="javascript:searchInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:'true'">查询</a>	   
	   	</div>   
     </div>
     <!-- 增加供应商的dialog -->
     <div id="addSupDlg" class="easyui-dialog" style="width: 300px; height: 225px;padding: 10px 20px;" closed="true" buttons="#supDlgBtn" >
		<form id="fmSupplier" method="post">
			<table cellspacing="5px" padding-top="20px">
				<tr>
					<td>供应商:</td>
					<td><input class="easyui-textbox" type="text" name="supplier" size="20px"></td>					
                </tr>
                <tr>
                    <td>出版社:</td>
                    <td><input class="easyui-textbox" type="text" name="publiser"></td>
                </tr>
			</table>
		</form>
     </div>
	 <div id="supDlgBtn">
		<a href="javascript:saveAddData()" class="easyui-linkbutton" iconCls="icon-ok">添加</a>
		<a href="javascript:closeAddSupDlg()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	 </div>
  </body>
</html>
