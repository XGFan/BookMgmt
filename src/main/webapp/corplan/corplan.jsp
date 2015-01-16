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
	<link rel="stylesheet" type="text/css" href="../css/corplan.css" />
  </head>
  <body style="background-color:#ebf1f9;overflow-y: hidden;">
	<div id="toolkit" style="background-color:#ebf1f9;">
		<form action="" method="post">
			<table width="600px" align="center">
				<tr>
					<td><font size="2">查询课程：&nbsp;</font><input type="text" id="fuzzy-c" name="fuzzy-c" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td><font size="2">学院:</font></td>
					<td><select class="easyui-combobox" id="col" name="col" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select></td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td><font size="2">专业:</font></td>
					<td><select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select></td>
				</tr>
				<tr>
					<td><font size="2">所有课程：</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td><font size="2">学期:</font></td>
					<td><select class="easyui-combobox" id="sem" name="sem" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="corplanmain" style="background-color:#ebf1f9;">
		<table width="600px" align="center">
			<tr>
				<td>
				    <select name="course" id="course" multiple="multiple"></select>
				</td>
				<td width="90px">　
					<a href="#" class="easyui-linkbutton" id="addToCorplan">>></a> <br />
					<br />
					<a href="#" class="easyui-linkbutton" id="deleteCorplan">从计划删除</a> <br />
					<br />
				</td>
				<td>
					<select name="corplan" id="corplan" multiple="multiple"></select>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>
