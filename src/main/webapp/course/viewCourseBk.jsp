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
    <script src="../js/viewCourseBk.js" type="text/javascript"></script>
  </head>
  
  <body>
    <table id="courseBook"></table>
    <div id="viewBtn">
       	<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回上一步</a>
                       请输入课程编号<input type="text" class="easyui-textbox" id="searchCourseBks" name="searchCourseBks" />
        <a href="javascript:searchCourseData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
    </div>
  </body>
</html>
