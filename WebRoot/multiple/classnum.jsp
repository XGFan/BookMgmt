<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级信息管理</title>
    <link href="../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <link href="../easyui/demo/demo.css" rel="stylesheet" type="text/css" />
    <script src="../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../js/classnum.js" type="text/javascript"></script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="班级信息" class="easyui-datagrid"  
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: 'class.json',
				method: 'get',
			    fitColumns:true,
			    pagination:true,
			    rownumbers:true,
			    fit:true,
			    singleSelect:true
			">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th field="grade" width="15%">年级</th>
				<th field="college" width="20%">学院</th>
				<th field="major" width="25%">专业名称</th>
				<th field="campus" width="15%">校区</th>
				<th field="stunum" width="15%" editor="numberbox">人数</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openGradeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteGrade()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
            <lable for="s_gradeName">&nbsp;班级名称：&nbsp;</lable>
            <input type="text" name="s_gradeName" id="s_gradeName" />
            <a href="javascript:searchGrade()" class="easyui-linkbutton" iconCls="icon-search" plain="true">
                搜索
            </a>
            <a href="javascript:clearSearchGrade()" class="easyui-linkbutton" iconCls="icon-clear" plain="true">
                清空
            </a>
        </div>
	</div>


	<div id="dlg" class="easyui-dialog" style="width: 570px;height: 250px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px">
				<tr>
					<td>年级：</td>
					<td>
                        <input type="text" id="grade" name="grade" editable="false" size="20px" />
                    </td>
                </tr>
				<tr>
					<td>学院：</td>
                    <td>
                        <input type="text" id="college" name="college" editable="true" size="20px" />
					</td>
                </tr>
		        <tr>
					<td>专业：</td>
					<td>
                        <input type="text" id="major" name="major" editable="false" size="20px" />
                    </td>
				</tr>
				<tr>
					<td>校区：</td>
					<td>
                        <inpit type="class" id="campus" name="campus" editable="true" size="20px" />
					</td>
				</tr>
				<tr>
					<td>人数：</td>
					<td>
                        <input type="text" name="classnum" id="classnum" class="easyui-validatebox" required="true" />
                    </td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveClassNum()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeGradeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>	
</body>
</html>