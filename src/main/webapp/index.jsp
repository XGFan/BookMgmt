<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <head>
    <title></title>
    <link href="easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="easyui/jquery.min.js" type="text/javascript"></script>
    <script src="easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="easyui/locale/easyui-lang-zh_CN.js"type="text/javascript"></script>
    <script src="js/index.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
  </head>
  
  <body class="easyui-layout" fit="true" style="overflow-y: hidden;">
   <!-- logo标志 -->
    <div data-options="region:'north',split:false" style="height:110px;">
      <div align="left" style="width:100%; height:100%; z-index:-1">
      <img src="images/top1.jpg" height="100%" width="100%">
      </div>
    </div>  
	<div region="center" id="mainPanle" style="background: #eee;overflow:hidden;">
		<div class="easyui-tabs" fit="true" border="false" id="tabs" showHeader="false">
			<div title="首页" >
				<div align="center" style="padding-top: 100px;"><font color="red" size="7">欢迎使用<br />湖北中医药大学<br />教材采购管理系统</font></div>
			</div>
		</div>
	</div>
	<div region="west" style="width: 14%;"  border="false" title="导航菜单" split="true" iconCls="icon-redo">
       <div class="easyui-accordion" fit="true" border="false">
            <div title="班级信息" style="overflow:auto; padding: 10px;" iconCls="icon-edit">
                <ul id="tree" class="tree"></ul>
            </div>
            <div title="教材信息" style="padding: 10px;" iconCls="icon-edit">
                <ul id="tree1" class="tree"></ul>
            </div>
			<div title="教学计划" style="padding: 10px;" iconCls="icon-edit">
                <ul id="tree2" class="tree"></ul>
            </div>
			<div title="基础信息" style="padding: 10px;" iconCls="icon-edit">
                <ul id="tree3" class="tree"></ul>
            </div>
			<div title="教材采购" style="padding: 10px;" iconCls="icon-edit">
                <ul id="tree4" class="tree"></ul>
            </div>
			<div title="教材发放" style="padding: 10px;" iconCls="icon-edit">
                <ul id="tree5" class="tree"></ul>
            </div>
       </div>
	</div>
	<div region="south" style="height: 25px; background: #D2E0F2;" align="center">版权所有<a href="#">ZSS</a></div>  
  </body>
</html>
