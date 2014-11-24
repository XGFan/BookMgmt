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
    <script src="../js/class.js" type="text/javascript"></script>
  </head>
  
 <body class="easyui-layout" style="overflow-y: hidden;margin: 5px;">
    <div region="center" style="width: 500px; height: 300px" split="true" fit="true">
       <div class="easyui-tabs" fit="true">
            <div title="年级信息" fit="true" iconCls="icon-edit">  
                <table id="dg1"></table>
            </div>
            <div title="专业信息" fit="true" iconCls="icon-lock">
                <table id="dg2"></table>
            </div>
            <div title="分班信息" fit="true" iconCls="icon-lock">
            <table id="dg3"></table>
            </div>    
       </div>
        
	    <div id="tb1">
             <div>
		        <a href="javascript:addData()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		        <a href="javascript:deleteData()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		        <a href="javascript:editData()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
             </div>
             <div>&nbsp;年级:&nbsp;<select class="easyui-combobox" id="grade" name="grade" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
                  &nbsp;学院:&nbsp;<select class="easyui-combobox" id="college" name="college" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
                  &nbsp;专业:&nbsp;<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
                  &nbsp;校区:&nbsp;<select class="easyui-combobox" id="campus" name="campus" panelHeigt="auto" style="width:150px"><option value="">请选择...</option></select>            
                  <a href="javascript:searchData()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
             </div>
	    </div>
	    <div id="tb2">
	        <div><jsp:include page="searchf.jsp"></jsp:include></div>
	    </div>
	    <div id="tb3">
	        <div><jsp:include page="searchf.jsp"></jsp:include></div>
	    </div>
    </div>
    <div id="dlg" class="easyui-dialog" style="background-color:#E0EDFF; width: 500px; height: 245px;padding: 10px 20px;" closed="true" buttons="#dlgButtons">
		<form id="fm" method="post">
			<table cellspacing="5px">
				<tr>
					<td>年级：</td>
					<td><input type="text" class="easyui-validatebox" id="grade" name="grade" size="20px" required="true"/></td>
					<td>学院：</td>
                    <td><input type="text" class="easyui-validatebox" id="college" name="college" size="20px" required="true"/></td>
                </tr>
		        <tr>
					<td>专业：</td>
					<td><input type="text" class="easyui-validatebox" id="major" name="major" size="20px" required="true" /></td>

					<td>校区：</td>
					<td><input type="text" class="easyui-validatebox" id="campus" name="campus" size="20px" required="true" /></td>
				</tr>
				<tr>
					<td>班数：</td>
					<td><input type="text" class="easyui-validatebox" id="clsnum" name="clsno" size="20px" required="true" /></td>
					<td>班号：</td>
					<td><input type="text" class="easyui-validatebox" id="idcls" name="idcls" size="20px" required="true" /></td>
				</tr>
				<tr>
					<td>人数：</td>
					<td><input type="text" class="easyui-validatebox" id="stunum" name="stunum" required="true" /></td>
				</tr>
			</table>
		</form>
    </div>
	<div id="dlgButtons">
		<a href="javascript:saveData()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
  </body>
</html>
