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
    <table id="courseTable"></table>
	<div id="tb1">
		 <div>
			<a href="javascript:openAddCouDlg()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新课程</a>
			<a href="javascript:editCourse()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑课程</a>
			<a href="javascript:deleteCourse()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除</a>
			<a href="javascript:moreInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">查看</a>
			<input type="text" class="easyui-textbox" id="courseData" name="courseData" size="20px">
			<a href="javascript:searchCourseData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<a href="javascript:queryToggle()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">高级查询</a>
		 </div>
		 <div id="query" style="display:none">
			学院:<select class="easyui-combobox" id="college" name="college" panelHeight="auto" style="width:130px"><option value="">请选择...</option></select>
			专业:<select class="easyui-combobox" id="major" name="major" panelHeight="auto" style="width:150px"><option value="">请选择...</option></select>
			学期:<select class="easyui-combobox" id="item" name="item" panelHeight="auto" style="width:100px"><option value="">请选择...</option></select>
		    <a href="javascript:searchCourseData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		 </div>
	</div>
	
	
	<!-- 课程、教材详细信息
   <div id="dlg1" class="easyui-dialog" style="width: 500px; height: 300px;padding: 10px 20px;" closed="true" buttons="#dlgButtons1" >
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'north',collapsible:false,border:false,fit:true" style="height:80%;width:40%;">
				课程：<br />
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
	
	-->
 
	
	
	<!------------------------------ 新增课程------------------------------------>
	<div id="addCourseDlg" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#addDlgBtn'" style="width: 830px; height: 400px;padding: 0px 0px;">
		<div class="easyui-layout" data-options="split:true,fit:true" style="height:100%;width:100%">
			<div data-options="region:'center',collapsible:false,border:false,fit:true" style="height:80%;width:30%;">
				<div id="cinfo">
					<form id="infoCourse" method="post">
						<div><br /><br />
					　　　学院:<input class="easyui-textbox" id="col" name="col" panelHeight="auto" style="width:150px" /> <br /><br />
					　　　专业:<input class="easyui-textbox" id="major" name="major" panelHeight="auto" style="width:150px" /><br /><br />
					        　课程名称:<input class="easyui-textbox" id="corname" name="corname" style="width:150px" /><br /><br />
					　　　学期:<input class="easyui-textbox" id="semester" name="semester" panelHeight="auto" style="width:150px" />
						</div>
					</form>
				</div>
			</div>
			<div data-options="region:'east',border:false" style="padding:0px;height:80%;width:70%">
				<table id="bookTable"></table>
				<div id="bookBtn">
					<div>
						&nbsp;教材名称:&nbsp;<input type="text" class="easyui-textbox" id="courseName" name="courseName" size="25px">
						<a href="javascript:searchBooks()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					</div>
				</div>
			</div>
		</div>
    </div>
	<div id="addDlgBtn">
		<a href="javascript:saveAddCourse()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">确定</a>
		<a href="javascript:closeAddCouDlg()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
	</div>
	
	<!--
	<div id="dlg4" class="easyui-dialog" data-options="border:false,closed:true,buttons:'#dlgButtons4'" style="width: 300px; height: 350px;padding: 0px 0px;">
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
	<div id="dlgButtons4">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add">增加</a>
		<a href="javascript:closeDialog4()" class="easyui-linkbutton" iconCls="icon-back">返回</a>
	</div>	
	-->

	
	
	<!-- 编辑教材信息
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
					<a href="javascript:addBook()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新教材</a>
				</div>
			</div>
		</div>
    </div>
	<div id="dlgButtons3">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">修改</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除关联</a>
		<a href="javascript:closeDialog3()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
	</div>	
	-->

	
  </body>
</html>
