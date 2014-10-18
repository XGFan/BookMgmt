<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>navigator</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<div class="top">
		<!--学校Logo  -->
		<div class="logo">
			<img src="pic/top.png" />
		</div>
		<!--导航栏  -->
		<div class="navigator">
			<ul>
				<li class="optn"><a href="/class.jsp">班级信息 </a></li>
				<ul class="tip">
					<li><a href="/class.jsp">所有班级 </a></li>
					<li><a href="/class.jsp">查询班级</a></li>
					<li><a href="/class.jsp">添加班级</a></li>
					<li><a href="/class.jsp">修改班级</a></li>
					<li><a href="/class.jsp">删除班级</a></li>
				</ul>
				<li class="optn"><a href="/book/book.jsp">教材信息</a></li>
				<ul class="tip">
					<li><a href="/book/book.jsp">所有教材 </a></li>
					<li><a href="/book/book.jsp">查询教材</a></li>
					<li><a href="/book/book.jsp">添加教材</a></li>
					<li><a href="/book/book.jsp">修改教材</a></li>
					<li><a href="/book/book.jsp">删除教材</a></li>
				</ul>
				<li class="optn"><a href="/corplan.jsp">教学计划 </a></li>
				<ul class="tip">
					<li><a href="/corplan.jsp">所有计划</a></li>
					<li><a href="/corplan.jsp">查询计划</a></li>
					<li><a href="/corplan.jsp">添加计划</a></li>
					<li><a href="/corplan.jsp">修改计划</a></li>
					<li><a href="/corplan.jsp">删除计划</a></li>
				</ul>
				<li class="optn"><a href="/course.jsp">基础信息 </a></li>
				<ul class="tip">
					<li><a href="/course.jsp">课程信息</a></li>
					<li><a href="/commInfo/supplier.jsp">供应商</a></li>
					<li><a href="/college.jsp">院系信息</a></li>
					<li><a href="javascript:void(0)">校区信息</a></li>
				</ul>
				<li class="optn"><a href="/BKPurchase/bkpurchase.jsp">教材采购 </a></li>
				<ul class="tip">
					<li><a href="/BKPurchase/bkpurchase.jsp">采购生成</a></li>
					<li><a href="/BKPurchase/bkpurchase.jsp">采购修改</a></li>
					<li><a href="/BKPurchase/bkpurchase.jsp">采购删除</a></li>
					<li><a href="/BKPurchase/bkpurchase.jsp">生成采购单</a></li>
					<li><a href="/BKPurchase/bkpurchase.jsp">导出采购单</a></li>
				</ul>
				<li class="optn"><a href="/distributeBook.jsp">教材发放 </a></li>
				<ul class="tip">
					<li><a href="/distributeBook.jsp">生成发放表</a></li>
					<li><a href="/distributeBook.jsp">查询发放表</a></li>
					<li><a href="/distributeBook.jsp">打印</a></li>
				</ul>
				<li class="optn"><a href="javascript:void(0)">系统维护 </a></li>
				<ul class="tip">
					<li><a href="javascript:void(0)">数据导入/导出</a></li>
					<li><a href="javascript:void(0)">学期设定</a></li>
					<li><a href="javascript:void(0)">用户信息</a></li>
					<li><a href="javascript:void(0)">退出系统</a></li>
				</ul>
			</ul>
		</div>
	</div>
</body>
</html>
