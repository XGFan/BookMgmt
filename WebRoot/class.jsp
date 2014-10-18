<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css"
	href="css/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="css/jquery/demos.css" />
<link rel="stylesheet" type="text/css" href="css/bkmgmt.css" />
<link rel="stylesheet" type="text/css" href="css/class.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />


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
	src="js/jquery/jquery.ui.tabs.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.ui.dialog.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery.jqpagination.min.js"></script>

<!-- 自己写的一些js代码 -->
<script language="javascript" type="text/javascript"
	src="js/navigator.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="js/dialog.js"
	charset="utf-8"></script>
<script language="javascript" type="text/javascript"
	src="js/ClsEdTable.js"></script>
<script language="javascript" type="text/javascript" src="js/clspage.js"
	charset="utf-8">
	
</script>

</head>

<body class="body">
	<div class="total">

		<!--顶部信息 -->
		<%-- <jsp:include page="top.jsp" /> --%>
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
					<li class="optn"><a href="/BKPurchase/bkpurchase.jsp">教材采购
					</a></li>
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
					<li class="optn"><a href="#">系统维护 </a></li>
					<ul class="tip">
						<li><a href="javascript:void(0)">数据导入/导出</a></li>
						<li><a href="javascript:void(0)">学期设定</a></li>
						<li><a href="javascript:void(0)">用户信息</a></li>
						<li><a href="javascript:void(0)">退出系统</a></li>
					</ul>
				</ul>
			</div>
		</div>

		<!--主工作区  -->
		<div class="main">
			<!--左侧子功能导航  -->
			<!--<div class="menulist">
					<span class="header">功能导航</span>
					<ul>
						<li>
							<a href="#">班级管理</a>
						</li>
						<li>
							<a href="#">课程管理</a>
						</li>
						<li>
							<a href="#">教材管理</a>
						</li>
						<li>
							<a href="#">计划管理</a>
						</li>
					</ul>
				</div>-->
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="classquery">

				<!--查询条件  -->
				<div class="condition">
					<span class="header">班级信息查询</span>
					<div id="querytabs">
						<ul>
							<li><a href="#tabs-1" id="tabs-1a">模糊查询</a></li>
							<li><a href="#tabs-2" id="tabs-2a">精确查询</a></li>
						</ul>
						<div id="tabs-1">
							<table>
								<tr>
									<td><font color="#68A8CC" size="4">请输入查询条件：</font></td>
									<td><input type="text" id="fuzzy-cs" /> <br /></td>
									<td align="center"><input name="querybtn" class="btn"
										type="button" id="querybtn" value="查询" /></td>
								</tr>
							</table>
						</div>
						<div id="tabs-2">
							<form id="queryform" name="queryform" method="post" action="">
								<table style="left: 11px;">
									<tr>
										<td>年级</td>
										<td><select id="grade-cs" name="grade-cs">
												<option value="请选择">--请选择--</option>
										</select></td>
										<td>校区</td>
										<td><select id="campus-cs" name="campus-cs">
												<option value="请选择">--请选择--</option>
										</select></td>
										<td>学院</td>
										<td><select id="col-cs" name="col-cs">
												<option value="请选择">--请选择--</option>
										</select></td>
										<td>专业</td>
										<td><select id="major-cs" name="major-cs">
												<option value="请选择">--请选择--</option>
										</select></td>
									</tr>
								</table>
							</form>
						</div>
					</div>

				</div>

				<!--显示查询结果  -->
				<div class="result" id="result" style="top: 140px; height: 367px;">
					<span class="header">查询结果</span> <span id="querystate"></span> <span><input
						type="button" class="btn" id="addbtn" value="添加" /> <input
						type="button" class="btn" id="deletebtn" value="删除所选" /> </span>
					<div id="queryresult"></div>
					<table id="resulttable" border='1' width="100%">
					</table>

					<div class="pagination" id="pagination">
						<div class="pageview">
							共 <label id="lblToatlRecord"> 0 </label> 条数据 第[ <label
								id="lblCurentPage"> 0 </label> ]页/共[ <label id="lblTotalPage">
								0 </label> ]页
						</div>
						<div class="pagecontrol">
							<a id="first" href="javascript:void(0)">首页</a> <a id="previous"
								href="javascript:void(0)">上一页</a> <a id="next"
								href="javascript:void(0)"> 下一页</a> <a id="last"
								href="javascript:void(0)">末页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--底部信息栏  -->
		<div class="bottom">
			<hr size="1" color="#0066FF" />
			<!--版权信息  -->
			<table width="100%" border="0" cellpadding="0" cellspacing="4"
				class="copyright">
				<tr>
					<td align="center">
						<p align="center">
							地址:中国湖北省武汉市洪山区黄家湖西路1号&nbsp; 邮编:430065 <br />
							电话:0086-027-68890054&nbsp;&nbsp;E_mail:zysbk@hbtcm.edu.cn <br />
							版权所有:湖北中医药大学设备管理处
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">Powered by&nbsp; <strong><a
							href="http://www.hbtcm.edu.cn" target="_blank">HBTCM</a> </strong><strong><font
							color="#FF9900"></font> </strong>&nbsp; &copy; 2009-2013
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
</body>
</html>