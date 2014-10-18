<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/supplier.css" />

<%@include file="../JsHeader.jsp" %>
<!-- 自己写的一些js代码 -->
<%@include file="JsHeaderSup.jsp" %>
</head>
<body class="body">
	<div class="total">
		<!--顶部信息 -->
		<jsp:include page="../top.jsp" />
		<!--主工作区  -->
		<div class="main">
			<!--左侧子功能导航  -->
			<div class="menulist">
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
			</div>
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="query">

				<!--查询条件  -->
				<div class="condition">
					<span class="header">购书表查询</span>
					 <form action="#" name="form1" >
								购书时间<input type="text" id="bkpubchase" /><br><br>
								<input type="button" id="querybtn" value="编辑"/>
								<input type="button" id="querybtn" value="确定购书"/>
   					 </form>
				</div>
				

				<!--显示查询结果  -->
				<div class="result" id="result">
					<span class="header">查询结果</span> <span id="querystate"></span> 
					<div id="queryresult"></div>
					<!-- 分页数据 -->
					<jsp:include page="../page.jsp" />
					<span style="float: right">
					</span>
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
							<td align="center">
							<input name="alterbtn" class="btn" type="button" id="alterbtn" value="确定" /><br>
							<input name="deletebtn" class="btn" type="button" id="deletebtn" value="删除" />
							</td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
	<!--底部信息栏  -->
	<div><jsp:include page="../copyright.jsp" /></div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
</body>
</html>
