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
    <script src="../js/course.js" type="text/javascript"></script>
  </head>
  <body style="width: 100%;height: 100%;">
    <table id="dg1"></table>
	<div id="tb1">
		 <div>
			<a href="javascript:addCourse()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新课程</a>
			<!--<a href="javascript:editCourse()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑课程</a>
			<a href="javascript:#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除</a>
			<a href="javascript:moreInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">查看</a>-->
			<input type="text" id="searchs" name="searchs" size="20px">
			<a href="javascript:searchData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<a href="javascript:queryToggle()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">精确查询</a>
		 </div>
		 <div id="query" style="display:none">
			学院:<select class="easyui-combobox" id="college" name="college" panelHeight="auto" style="width:130px"><option value="">请选择...</option></select>
			专业:<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
			学期:<select class="easyui-combobox" id="item" name="item" panelHeight="auto" style="width:100px"><option value="">请选择...</option></select>
		 </div>
	</div>
	
	
	<!-- 课程、教材详细信息-->
    <div id="dlg1" class="easyui-dialog" style="width: 500px; height: 300px;padding: 10px 20px;" closed="true" buttons="#dlgButtons1" >
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'north',collapsible:false,border:false,fit:true" style="height:80%;width:40%;">
				课程：<br /><!--此处显示被选中的行信息-->
				选用教材如下：
			</div>
			<div data-options="region:'south',border:false" style="padding:0px;height:80%;width:60%">
				<table id="dg2"></table>
			</div>
		</div>
    </div>
	<div id="dlgButtons1">
		<a href="javascript:closeDialog1()" class="easyui-linkbutton" iconCls="icon-back">返回</a>
	</div>
	
	
	<!-- 新增课程-->
	<div id="dlg2" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#dlgButtons2'" style="width: 780px; height: 350px;padding: 0px 0px;">
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'center',collapsible:false,border:false,fit:true" style="height:80%;width:30%;">
				<div id="cinfo">
					<form id="info" method="post">
						<div><br /><br />
					　　　学院:<select class="easyui-combobox" id="col" name="col" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select><br /><br />
					　　　专业:<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select><br /><br />
							<label for="corname">　课程名称:</label><input class="easyui-textbox" id="corname" name="corname" style="width:150px" /><br /><br />
					　　　学期:<select class="easyui-combobox" id="sem" name="sem" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
						</div>
					</form>
				</div>
			</div>
			<div data-options="region:'east',border:false" style="padding:0px;height:80%;width:70%">
				<table id="dg3"></table>
				<div id="tb3">
					<div>
						&nbsp;教材名称:&nbsp;<input type="text" id="corname" name="corname" size="25px">
						<a href="javascript:searchBook()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新教材</a>
					</div>
				</div>
			</div>
		</div>
    </div>
	<div id="dlgButtons2">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">确定</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除关联</a>
		<a href="javascript:closeDialog2()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
	</div>
	
	<!-- 编辑教材信息-->
	<div id="dlg3" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#dlgButtons3'" style="width: 780px; height: 350px;padding: 0px 0px;">
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'center',collapsible:false,border:false,fit:true" style="height:80%;width:30%;">
				<div id="bkinfo">
					<form id="info" method="get">
						<div><br /><br />
					　　　学院:<input class="easyui-textbox" id="col" name="col" style="width:150px" editable="false" /><br /><br />
					　　　专业:<input class="easyui-textbox" id="major" name="major" style="width:150px" editable="false" /><br /><br />
							<label for="corname">　课程名称:</label><input class="easyui-textbox" id="corname" name="corname" style="width:150px" /><br /><br />
					　　　学期:<select class="easyui-combobox" id="sem" name="sem" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
						</div>
					</form>
				</div>
			</div>
			<div data-options="region:'east',border:false" style="padding:0px;height:80%;width:70%">
				<table id="dg4"></table>
				<div id="tb4">
					&nbsp;教材名称:&nbsp;<input type="text" id="corname" name="corname" size="25px">
					<a href="javascript:searchBook()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新教材</a>
				</div>
			</div>
		</div>
    </div>
	<div id="dlgButtons3">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">修改</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除关联</a>
		<a href="javascript:closeDialog3()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
	</div>
	
  </body>
</html>
