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
    <script src="../js/book.js" type="text/javascript"></script>
  </head>
  <body style="width: 100%;height: 100%;">
    <table id="dg1"></table>
	    <div id="tb1">
			<div>
				<a href="javascript:addBook()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新教材</a>
				<a href="javascript:editBook()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑教材</a>
				<a href="javascript:#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除</a>
				<a href="javascript:moreInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">查看</a>
				<a href="javascript:queryToggle()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" id="querybtn">查询</a>
			</div>
			<div id="query" style="display:none">
				&nbsp;教材名称:&nbsp;<input type="text" id="bkns" name="bkns" size="20px">
				&nbsp;出版社:&nbsp;<input type="text" id="ps" name="ps" size="20px">
				&nbsp;ISBN:&nbsp;<input type="text" id="isbns" name="isbns" size="20px">
				<a href="javascript:searchBook()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</div>
        </div>
		
	<!-- 课程、教材详细信息-->
    <div id="dlg1" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#dlgButtons1'" style="width: 600px; height: 300px;padding: 10px 20px;">
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'north',collapsible:false,border:false,fit:true" style="height:80%;width:40%;">
				选用教材：<br />
				<!--此处显示被选中的行信息-->
				的课程如下：
			</div>
			<div data-options="region:'south',border:false" style="padding:0px;height:80%;width:60%">
				<table id="dg2"></table>
			</div>
		</div>
    </div>
	<div id="dlgButtons1">
		<a href="javascript:closeDialog1()" class="easyui-linkbutton" iconCls="icon-back">返回</a>
	</div>
	
	<!-- 新增教材-->
	<div id="dlg2" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#dlgButtons2'" style="width: 800px; height: 400px;padding: 0px 0px;">
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'center',collapsible:false,border:false,fit:true" style="height:80%;width:30%;">
				<div id="bkinfo">
					<form id="info" method="post">
						<div><br /><br />
							<label for="bkname">　教材名称:</label><input id="bkname" name="bkname" style="width:150px"><br /><br />
							<label for="writer">　　　作者:</label><input id="writer" name="writer" style="width:150px"><br /><br />
							<label for="version">　　　版本:</label><input id="version" name="version" style="width:150px"><br /><br />
							<label for="publisher">　　出版社:</label><input id="publisher" name="publisher" style="width:150px"><br /><br />
							<label for="isbn">　　　ISBN:</label><input id="isbn" name="isbn" style="width:150px"><br /><br />
							<label for="price">　　　价格:</label><input id="price" name="price" style="width:150px">
						</div>
					</form>
				</div>
			</div>
			<div data-options="region:'east',border:false" style="padding:0px;height:80%;width:70%">
				<table id="dg3"></table>
				<div id="tb3">
					<div>
						&nbsp;课程名称:&nbsp;<input type="text" id="clsname" name="clsname" size="20px">
						<a href="javascript:searchBook()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a href="javascript:queryToggle1()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">精确查询</a>
					</div>
					<div id="query1" style="display:none">
						学院:<select class="easyui-combobox" id="college" name="college" panelHeight="auto" style="width:130px"><option value="">请选择...</option></select>
						专业:<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
						学期:<select class="easyui-combobox" id="item" name="item" panelHeight="auto" style="width:100px"><option value="">请选择...</option></select>
					</div>
				</div>
			</div>
		</div>
    </div>
	<div id="dlgButtons2">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add">增加</a>
		<a href="javascript:closeDialog2()" class="easyui-linkbutton" iconCls="icon-back">返回</a>
	</div>
	
	
	
	<!-- 编辑教材信息-->
	<div id="dlg3" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#dlgButtons3'" style="width: 800px; height: 400px;padding: 0px 0px;">
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'center',collapsible:false,border:false,fit:true" style="height:80%;width:30%;">
				<div id="bkinfo">
					<form id="info" method="get">
						<div><br /><br />
							<label for="bkname">　教材名称:</label><input id="bkname" name="bkname" style="width:150px"><br /><br />
							<label for="writer">　　　作者:</label><input id="writer" name="writer" style="width:150px"><br /><br />
							<label for="version">　　　版本:</label><input id="version" name="version" style="width:150px"><br /><br />
							<label for="publisher">　　出版社:</label><input id="publisher" name="publisher" style="width:150px"><br /><br />
							<label for="isbn">　　　ISBN:</label><input id="isbn" name="isbn" style="width:150px"><br /><br />
							<label for="price">　　　价格:</label><input id="price" name="price" style="width:150px">
						</div>
					</form>
				</div>
			</div>
			<div data-options="region:'east',border:false" style="padding:0px;height:80%;width:70%">
				<table id="dg4"></table>
				<div id="tb4">
					<div>
						&nbsp;课程名称:&nbsp;<input type="text" id="clsname" name="clsname" size="20px">
						<a href="javascript:searchBook()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a href="javascript:queryToggle2()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">精确查询</a>
					</div>
					<div id="query2" style="display:none">
						学院:<select class="easyui-combobox" id="college" name="college" panelHeight="auto" style="width:130px"><option value="">请选择...</option></select>
						专业:<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
						学期:<select class="easyui-combobox" id="item" name="item" panelHeight="auto" style="width:100px"><option value="">请选择...</option></select>
					</div>
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
