<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <head>
    <title></title>
    <link href="../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../easyui/plugins/jquery.form.js" type="text/javascript"></script>
    <script src="../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../js/book.js" type="text/javascript"></script>
  </head>
  <body style="width: 100%;height: 100%;">
    <table id="tableBook"></table>
	    <div id="tb1">
			<div>
				<a href="javascript:addBookData()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加新教材</a>
				<a href="javascript:editBookData()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑教材</a>
				<a href="javascript:deleteBookData()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">删除</a>
				<a href="javascript:moreInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">查看</a>
				&nbsp;教材名称:&nbsp;<input type="text" class="easyui-textbox" id="fuzzybkns" name="fuzzybkns" />
				<a href="javascript:searchBooks()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:'true'">查询</a>				
			</div>
        </div>
	<!-------------------------------------------- 新增教材---------------------------------------------->
    <div id="addBookDlg" class="easyui-dialog" style="background-color:#E0EDFF; width: 550px; height: 195px;padding: 10px 20px;" closed="true" buttons="#addBkdlgBtn" >
		<form id="addBookinfo" method="post">
			<table cellspacing="5px">
				<tr>
					<td>教材名称:</td>
					<td><input class="easyui-textbox" type="text" id="bkname" name="bkname"/></td>
					<td>&nbsp;&nbsp;作&nbsp;&nbsp;者:</td>
                    <td><input class="easyui-textbox" type="text" id="author" name="author" /></td>
                </tr>
		        <tr>
					<td>版&nbsp;&nbsp;&nbsp;&nbsp;本:</td>
					<td><input class="easyui-textbox" type="text" id="edition" name="edition"  /></td>
					<td>&nbsp;&nbsp;出版社：</td>
					<td><input class="easyui-textbox" type="text" id="publisher" name="publisher" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;ISBN:</td>
					<td><input class="easyui-textbox" type="text" id="isbn" name="isbn" /></td>
					<td>&nbsp;&nbsp;价&nbsp;&nbsp;格:</td>
					<td><input class="easyui-textbox" type="text" id="price" name="price"/></td>
				</tr>
			</table>
		</form>
    </div>
	<div id="addBkdlgBtn">
		<a href="javascript:savaAddBkData()" class="easyui-linkbutton" iconCls="icon-add">增加</a>
		<a href="javascript:closeAddBkDlg()" class="easyui-linkbutton" iconCls="icon-back">返回</a>
	</div> 
	<!----------------------------------- 编辑教材信息----------------------------------------->
    <div id="editBookDlg" class="easyui-dialog" style="background-color:#E0EDFF; width: 550px; height: 195px;padding: 10px 20px;" closed="true" buttons="#editBkdlgBtn" >
		<form id="editBookinfo">
			<table cellspacing="5px">
				<tr>
					<td>教材名称：</td>
					<td><input class="easyui-textbox" type="text" id="bkname" name="bkname"/></td>
					<td>作&nbsp;&nbsp;者：</td>
                    <td><input class="easyui-textbox" type="text" id="author" name="author" /></td>
                </tr>
		        <tr>
					<td>版&nbsp;&nbsp;本：</td>
					<td><input class="easyui-textbox" type="text" id="edition" name="edition"  /></td>
					<td>&nbsp;出版社：</td>
					<td><input class="easyui-textbox" type="text" id="publisher" name="publisher" /></td>
				</tr>
				<tr>
					<td>ISBN：</td>
					<td><input class="easyui-textbox" type="text" id="isbn" name="isbn" required="true" /></td>
					<td>价&nbsp;&nbsp;格:</td>
					<td><input class="easyui-textbox" type="text" id="price" name="price"/></td>
				</tr>
			</table>
		</form>
    </div>
	<div id="editBkdlgBtn">
		<a href="javascript:savaEditBkData()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">保存修改</a>
		<a href="javascript:closeEditBkDlg()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
	</div>
	
  </body>
</html>
