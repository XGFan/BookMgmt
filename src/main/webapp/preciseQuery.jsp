<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'preciseQuery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    <div id="preciseQuery" style="display: none;">
		年级：<input class="easyui-textbox" type="text" id="gradea" name="gradea" data-option="valueField:'id',textField:'text'"/>
		学院：<input class="easyui-textbox" type="text" id="cola" name="cola" data-option="valueField:'id',textField:'text'"/>
        专业：<input class="easyui-textbox" type="text" id="majora" name="majora" data-option="valueField:'id',textField:'text'" />
		校区：<input class="easyui-textbox" type="text" id="campusa" name="campusa" data-option="valueField:'id',textField:'text'" />
		<a href="javascript:searchData()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
  </body>
</html>
