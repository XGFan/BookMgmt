<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="../css/supplier.css" />

<%@include file="../JsHeader.jsp"%>
<!-- 自己写的一些js代码 -->
<%@include file="JsHeaderSup.jsp"%>
</head>
<body class="body">
	<div class="total">
		<!--顶部信息 -->
		<jsp:include page="../top.jsp" />
		<!--主工作区  -->
		<div class="main">
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="query">
				<!--查询条件  -->
				<div class="condition" style="top: 0px; height: 95px;">
					<span class="header">供应商查询</span><br />
					<form action="#" name="form1">
						供应商：<select id="supplier" name="supplier">
							<option value="请选择">请选择</option>
						</select>&nbsp;&nbsp;出版社：<input type="text" id="publisher" /> <input type="button"
							id="querybtn" class="btn" value="查询" />
					</form>
				</div>

				<!--显示查询结果  -->
				<div class="result" id="result" style="top: 96px;">
					<span class="header">查询结果</span> <span id="querystate"></span> <span
						style="float: right"><input type="button" class="btn"
						id="addbtnsup" value="添加新的供应商" /> </span> <span style="float: right"><input
						type="button" class="btn" id="addbtnpub" value="添加新的出版社" /> </span>
					<div id="queryresult"></div>
					<!-- 分页数据 -->
					<jsp:include page="../page.jsp" />
					<span style="float: right"> 供应商<select id="supplier-d"
						name="supplier-d">
							<option value="请选择">请选择</option>
					</select> <input type="button" class="btn" id="delbtnsup" value="删除供应商" />
					</span>
				</div>
			</div>

			<!--添加模块，用于添加出版社-->
			<div class="addpub">
				<span class="header">添加出版社</span>
				<form id="addsupform" name="addsupform" method="post" action="">
					<table>
						<tr>
							<td>供应商</td>
							<td><select id="supplier-a" name="supplier-a">
									<option value="请选择">请选择</option>
							</select></td>
						</tr>
						<tr>
							<td>出版社</td>
							<td><input type="text" id="publisher-a" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input name="addpub"
								class="btn" type="button" id="addpub" value="添加" /> &nbsp; <input
								name="cancelbtn-add" class="btn" type="button"
								id="cancelbtn-add" value="取消" /></td>
						</tr>
					</table>
				</form>
			</div>

			<!--修改模块，用于修改查询的结果  -->
			<div class="alter">
				<span class="header">供应商信息修改</span>
				<form id="alterform" name="alterform" method="post" action="">
					<table>
						<tr class="altertable">
							<td>idsp</td>
							<td><input type="text" id="idsp-u" /></td>
						</tr>
						<tr>
							<td>供应商</td>
							<td><select id="supplier-u" name="supplier-u">
									<option value="请选择">请选择</option>
							</select></td>
						</tr>
						<tr>
							<td>出版社</td>
							<td><input type="text" id="publisher-u" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input name="alterbtn"
								class="btn" type="button" id="alterbtn" value="修改" />&nbsp; <input
								name="cancelbtn-alter" class="btn" type="button"
								id="cancelbtn-alter" value="取消" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!--底部信息栏  -->
		<jsp:include page="../copyright.jsp" />
	</div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
	<div id="dialog-delete" class="demo-description"></div>
</body>
</html>
