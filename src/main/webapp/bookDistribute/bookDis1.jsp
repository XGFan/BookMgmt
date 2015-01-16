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
    <script src="../js/print.js" type="text/javascript"></script>
  </head>
  <body style="width:100%;height: 100%;">
    <table id="dg1"></table>
	    <div id="tb1">
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print">打印</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" id="btn" iconCls="icon-search">查询</a>
				<jsp:include page="../preciseQuery.jsp" />
			</div>

        </div>
  </body>
</html>
