<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchf.jsp' starting page</title>
    
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
	 <div>
	     <a href="javascript:editData2()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	     <a href="javascript:deleteData2()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	 </div>
     <div>&nbsp;年级:&nbsp;<select class="easyui-combobox" id="grade" name="grade" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
        &nbsp;学院:&nbsp;<select class="easyui-combobox" id="college" name="college" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
        &nbsp;专业:&nbsp;<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
        &nbsp;校区:&nbsp;<select class="easyui-combobox" id="campus" name="campus" panelHeigt="auto" style="width:150px"><option value="">请选择...</option></select>            
        <a href="javascript:searchData()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
     </div>
  </body>
</html>
