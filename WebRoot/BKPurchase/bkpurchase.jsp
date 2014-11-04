<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="../css/bkpurchase.css" />

<%@include file="../JsHeader.jsp"%>
<!-- 自己写的一些js代码 -->
<%@include file="JsHeaderBKPur.jsp"%>
</head>
<body class="body">
	<div class="total">
		<!--顶部信息 -->
		<jsp:include page="../top.jsp" />
		<!--主工作区  -->
		<div class="main">
			<!--左侧子功能导航  -->
			<!-- <div class="menulist">
				<span class="header">功能导航</span>
				<ul>
					<li><a href="#">班级管理</a>
					</li>
					<li><a href="#">课程管理</a>
					</li>
					<li><a href="#">教材管理</a>
					</li>
					<li><a href="#">计划管理</a>
					</li>
				</ul>
			</div> -->
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="query">
				<!--查询条件  -->
				<div class="condition">
					<span class="header">购书表查询</span>
					<div id="conDiv">
						<form action="#" name="form1">
							<font color="#68A8CC" size="4">购书时间</font><input type="text"
								id="bkpubchase" disabled="disabled" />&nbsp;&nbsp;&nbsp; <input
								type="button" id="editbtn" class="btn" value="编辑" />&nbsp;&nbsp;&nbsp;
							<input type="button" id="bkpurbtn" class="btn" value="购书" />&nbsp;&nbsp;&nbsp;
							<input type="button" id="freshManBKpurBtn" class="btn"
								value="新生购书" /><br> <br> <font color="#68A8CC"
								size="4">供应商：</font><select id="supplier" name="supplier">
								<option value="请选择">请选择</option>
							</select>&nbsp;&nbsp;&nbsp; <input type="button" id="querybtn" value="查询" />
						</form>
					</div>
				</div>

				<!--显示查询结果  -->
				<div class="result" id="result">
					<span class="header">查询结果</span> <span id="querystate"></span>
					<div id="queryresult"></div>
					<!-- 分页数据 -->
					<jsp:include page="../page.jsp" />
					<span style="float: right"> </span>
				</div>
			</div>

			<!--修改模块，用于修改查询的结果  -->
			<div class="alter">
				<span class="header">购书时间修改</span>
				<form id="alterform" name="alterform" method="post" action="">
					<table>
						<tr class="altertable">
							<td>购书时间</td>
							<td><select id="bkpubchase-u" name="bkpubchase-u">
									<option value="请选择">请选择</option>
							</select>
							</td>
						</tr>
						<tr>
							<td align="center"><input name="alterbtn" class="btn"
								type="button" id="alterbtn" value="确定" /><br> <input
								name="cancelbtn" class="btn" type="button" id="cancelbtn"
								value="取消" /><br>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!--底部信息栏  -->
		<div><jsp:include page="../copyright.jsp" /></div>
	</div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
	<div id="mask">
		<span id="info"> 正在生成采购清单中，请稍候！ </span> <span id="loading">
		<img src="../css/jquery/images/waiting.gif" /> </span>
	</div>
</body>
</html>
