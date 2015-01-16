<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <head>
    <title></title>
    <link href="../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../easyui/plugins/jquery.form.js" type="text/javascript"></script>
    <script src="../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../easyui/plugins/jquery.form.js" type="text/javascript"></script>
    <script src="../js/class.js" type="text/javascript"></script>
  </head>
  
  
  <body style="width:100%;height: 100%;">
    <table id="dg1"></table>
	    <div id="tb1">
             <div>
		        <a href="javascript:addData()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
		        <a href="javascript:editData()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
		        <a href="javascript:deleteData()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
                <input type="text" class="easyui-textbox" id="searchs" name="searchs">
                <a href="javascript:searchData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" id="btn">高级查询</a>  
                <jsp:include page="../preciseQuery.jsp"></jsp:include>
             </div>
        </div>
<!------------------------------------- 添加 ------------------------------------------->       
    <div id="dlg" class="easyui-dialog" style="background-color:#E0EDFF; width: 500px; height: 225px;padding: 10px 20px;" closed="true" buttons="#dlgButtons" >
		<form id="fm" method="post">
			<table cellspacing="5px">
				<tr>
					<td>年级：</td>
					<td><input class="easyui-textbox" type="text" id="grade" name="grade" data-option="valueField:'id',textField:'text'"/></td>
					<td>学院：</td>
                    <td><input class="easyui-textbox" type="text" id="col" name="col" data-option="valueField:'id',textField:'text'"/></td>
                </tr>
		        <tr>
					<td>专业：</td>
					<td><input class="easyui-textbox" type="text" id="major" name="major" data-option="valueField:'id',textField:'text'" /></td>
					<td>校区：</td>
					<td><input class="easyui-textbox" type="text" id="campus" name="campus" data-option="valueField:'id',textField:'text'" /></td>
				</tr>
				<tr>
					<td>班数：</td>
					<td><input class="easyui-textbox" type="text" id="clsnum" name="clsnum" size="20px" required="true" /></td>
				</tr>
			</table>
		</form>
    </div>
	<div id="dlgButtons">
		<a href="javascript:saveData()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
<!------------------------------------- 编辑 ------------------------------------------->
    <div id="dlg1" class="easyui-dialog" style="background-color:#E0EDFF; width: 500px; height: 225px;padding: 10px 20px;" closed="true" buttons="#dlgButtons1" >
	     <form id="fm1">
			<table cellspacing="5px">
				<tr>
					<td>年级：</td>
					<td><input class="easyui-textbox" type="text" id="grade1" name="grade" data-option="valueField:'id',textField:'text'"/></td>
					<td>学院：</td>
                    <td><input class="easyui-textbox" type="text" id="col1" name="col" data-option="valueField:'id',textField:'text'"/></td>
                </tr>
		        <tr>
					<td>专业：</td>
					<td><input class="easyui-textbox" type="text" id="major1" name="major" data-option="valueField:'id',textField:'text'" /></td>
					<td>校区：</td>
					<td><input class="easyui-textbox" type="text" id="campus1" name="campus" data-option="valueField:'id',textField:'text'" /></td>
				</tr>
				<tr>
					<td>班数：</td>
					<td><input class="easyui-textbox" type="text" id="clsnum1" name="clsnum" size="20px" required="true" /></td>
				</tr>
			</table>	
          </form>
    </div>
	<div id="dlgButtons1">
		<a href="javascript:saveEditData()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeEditDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
  </body>
</html>
