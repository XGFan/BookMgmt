<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材采购管理系统</title>
<link rel="stylesheet" type="text/css" href="css/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="css/jquery/demos.css" />
<link rel="stylesheet" type="text/css" href="css/bkmgmt.css" />


<script language="javascript" type="text/javascript"
	src="js/jquery/jquery-1.8.3.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.core.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.widget.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.mouse.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.draggable.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.button.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.resizable.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.position.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.dialog.js"></script>

<!-- 自己写的一些js代码 -->
<script language="javascript" type="text/javascript"
	src="js/navigator.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="js/dialog.js"
	charset="utf-8">
	
</script>
<%--<script language="javascript" type="text/javascript"--%>
	<%--src="js/classquery.js" charset="utf-8"></script>--%>

</head>

<body class="body">
	<div class="total">
		<!--顶部信息 -->
		<div class="top">
			<!--顶部信息 -->
			<jsp:include page="top.jsp" />

		</div>
		<!--主工作区  -->
		<div class="main">
			<!--左侧子功能导航  -->
			<div class="menulist">
				<span class="header">功能导航</span>
				<ul>
					<li><a href="#">班级管理</a></li>
					<li><a href="#">课程管理</a></li>
					<li><a href="#">教材管理</a></li>
					<li><a href="#">计划管理</a></li>
				</ul>
			</div>
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="query">
				<div
					style="text-align: center; font-size: 75px; color: #0000ff; top: 3%; position: relative">
					<p>系统维护中，</p>
					<p>即将上线，</p>
					<p>敬请期待！</p>
				</div>
			</div>

		</div>
		<jsp:include page="copyright.jsp" />
	</div>
	<div id="dialog-modal" class="demo-description"></div>
</body>
</html>