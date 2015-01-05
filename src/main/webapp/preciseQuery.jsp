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
    <div id="preciseQuery" class="easyui-dialog" style="background-color:#E0EDFF; width: 500px; height: 225px;padding: 10px 20px",close="true">
        年级：<input class="easyui-combobox" id="gradea" name="grade" data-option="valueField:'id',textField:'text'"/>
		学院：<input class="easyui-combobox" id="cola" name="col" data-option="valueField:'id',textField:'text'"/>
		专业：<input class="easyui-combobox" id="majora" name="major" data-option="valueField:'id',textField:'text'" />
		校区：<input class="easyui-combobox" id="campusa" name="campus" data-option="valueField:'id',textField:'text'" />
    </div>
  </body>
</html>
