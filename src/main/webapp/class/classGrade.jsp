<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <head>
    <title></title>
    <link href="../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="../easyui/demo/demo.css" rel="stylesheet" type="text/css">
    <link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../js/classGrade.js" type="text/javascript"></script>
  </head>
  
<body>
	<table id="tt"></table>
	<div id="cBkTbn">
	  <a href="javascript:refresh()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
	  <input type="text" class="easyui-textbox" id="searchGrade">
	  <a href="javascript:fuzzyGrade()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
</body>
</html>
