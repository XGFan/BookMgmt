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
    <script src="../js/college.js" type="text/javascript"></script>
  </head>
  <body style="width: 100%;height: 100%;">
    <table id="dg1"></table>
	    <div id="tb1">
            <div>
		        <a href="javascript:openDialog1()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新专业</a>
		        <a href="javascript:openDialog2()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
		        <a href="javascript:openDialog3()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除</a>
				<a href="javascript:queryToggle()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" id="querybtn">查询</a>
			</div>
			<div id="query" style="display:none">
		        学院：<select class="easyui-combobox" id="col" name="col" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
                专业：<input type="text" id="major" name="major" size="20px">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
            </div>
        </div>
		
		
	<!--添加新专业dialog-->
    <div id="dlg1" class="easyui-dialog" data-options=" closed:true, buttons:'#dlgButtons1'" style="width: 400px; height: 225px;padding: 10px 20px;">
		<form id="fm-add" method="post">
			<table cellspacing="5px">
				<tr>
					<td>学院：</td>
                    <td><input class="easyui-validatebox" id="col" name="col" /></td>
                </tr>
		        <tr>
					<td>专业：</td>
					<td><input class="easyui-validatebox" id="major" name="major" size="20px" /></td>
				</tr>
				<tr>
					<td>学期：</td>
					<td><input class="easyui-validatebox" id="sem" name="sem" size="20px" required="true" /></td>
				</tr>
			</table>
		</form>
    </div>
	<div id="dlgButtons1">
		<a href="javascript:addData()" class="easyui-linkbutton" iconCls="icon-ok">添加</a>
		<a href="javascript:closeDialog1()" class="easyui-linkbutton" iconCls="icon-clear">关闭</a>
	</div>
	
	
	<!--编辑dialog-->
	<div id="dlg2" class="easyui-dialog" data-options=" closed:true, buttons:'#dlgButtons2'" style="width: 400px; height: 225px;padding: 10px 20px;">
		<form id="fm-edit" method="post">
			<table cellspacing="9px">
				<tr>
					<td>学院：</td>
                    <td><input class="easyui-validatebox" id="col" name="col" /></td>
                </tr>
		        <tr>
					<td>专业：</td>
					<td><input class="easyui-validatebox" id="major" name="major" size="20px" /></td>
				</tr>
				<tr>
					<td>学期：</td>
					<td><input class="easyui-validatebox" id="sem" name="sem" size="20px" required="true" /></td>
				</tr>
			</table>
		</form>
    </div>
	<div id="dlgButtons2">
		<a href="javascript:saveData()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog2()" class="easyui-linkbutton" iconCls="icon-clear">关闭</a>
	</div>
	
	<!--删除dialog-->
	<div id="dlg3" class="easyui-dialog" data-options=" closed:true, buttons:'#dlgButtons3'" style="width: 400px; height: 225px;padding: 10px 20px;">
		<p align="center">删除该专业将会一并删除与该专业有关<br />的班级、课程、教学计划等信息。<br />确认删除吗？</p>
    </div>
	<div id="dlgButtons3">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok">确认</a>
		<a href="javascript:closeDialog3()" class="easyui-linkbutton" iconCls="icon-clear">取消</a>
	</div>
	
  </body>
</html>
