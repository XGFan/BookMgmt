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
  <body class="easyui-layout">
    <div data-options="region:'north',title:'教材信息修改',split:false,collapsible:false" style="height:34%;">
		<div id="cbkinfo">
			<form id="edit" method="get">
				<div><br />
					<label for="bkname">　教材名称:</label><input class="easyui-textbox" id="bkname" name="bkname" style="width:200px">
					<label for="writer">　　作者:</label><input class="easyui-textbox" id="writer" name="writer" style="width:200px">
					<br /><br />
					<label for="version">　　　版本:</label><input class="easyui-textbox" id="version" name="version" style="width:200px">
					<label for="publisher">　出版社:</label><input class="easyui-textbox" id="publisher" name="publisher" style="width:200px">
					<br /><br />
					<label for="isbn">　　　ISBN:</label><input class="easyui-textbox" id="isbn" name="isbn" style="width:200px">
					<label for="price">　　价格:</label><input class="easyui-textbox" id="price" name="price" style="width:200px"><br />
				</div>
			</form>
		</div>
    </div>
	<div data-options="region:'center',border:false" style="padding:0px;">
		<table id="dg4"></table>
		<div id="tb4">
			&nbsp;课程名称:&nbsp;<input type="text" id="clsname" name="clsname" size="25px">
			<a href="javascript:searchCourse()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</div>
	</div>
    
	<div data-options="region:'south',split:false,border:false" style="height:8%;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">增加关联</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除关联</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">返回</a>
	</div>
  </body>
</html>
