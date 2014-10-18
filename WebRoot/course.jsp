<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="css/course.css" />

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
<script language="javascript" type="text/javascript" src="js/course.js"></script>

</head>

<body class="body">
	<div class="total">
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
						<li><a href="/BKPurchase/bkpurchase.jsp"">采购生成</a></li>
						<li><a href="/BKPurchase/bkpurchase.jsp"">采购修改</a></li>
						<li><a href="/BKPurchase/bkpurchase.jsp"">采购删除</a></li>
						<li><a href="/BKPurchase/bkpurchase.jsp"">生成采购单</a>
						</li>
						<li><a href="/BKPurchase/bkpurchase.jsp"">导出采购单</a>
						</li>
					</ul>
					<li class="optn"><a href="/distributeBook.jsp">教材发放
					</a></li>
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
		<!--主工作区  -->
		<div class="main">
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="query">
				<!--查询条件  -->
				<div class="condition">
					<span class="header">课程信息查询</span>
					<div id="querytabs">
						<ul>
							<li><a href="#tabs-1" id="tabs-1a">模糊查询</a></li>
							<li><a href="#tabs-2" id="tabs-2a">精确查询</a></li>
						</ul>
						<div id="tabs-1">
							<table>
								<tr>
									<td><font color="#68A8CC" size="4">请输入查询条件：</font></td>
									<td><input type="text" id="fuzzy-c" /> <br /></td>
									<td align="center"><input name="querybtn" class="btn"
										type="button" id="querybtn" value="查询" /></td>
								</tr>
							</table>
						</div>
						<div id="tabs-2">
							<form id="queryform" name="queryform" method="post" action="">
								<table style="left: 11px;">
									<tr>
										<td>学院</td>
										<td><select id="col-c" name="col-c">
												<option value="请选择">--请选择--</option>
										</select></td>
										<td>专业</td>
										<td><select id="major-c" name="major-c">
												<option value="请选择">--请选择--</option>
										</select></td>
										<td>学期</td>
										<td><select id="semester-c" name="semester-c">
												<option value="请选择">--请选择--</option>
										</select></td>
									</tr>
								</table>
							</form>
						</div>
					</div>

				</div>

				<!--显示查询结果  -->
				<div class="result" id="result">
					<span class="header">查询结果</span> <span id="querystate"></span> <span
						style="float: right"><input type="button" class="btn"
						id="add" value="添加新的课程" /> </span>
					<div id="queryresult"></div>

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

			<!--修改模块，用于修改查询的结果  -->
			<div class="alter">
				<span class="header">课程信息修改</span>
				<form id="alterform" name="alterform" method="post" action="">
					<table>
						<tr class="altertable">
							<td>&nbsp; <input type="hidden" id="idcor-a" />
							</td>
						</tr>
						<tr>
							<td width="80px">学院:</td>
							<td align="right"><input type="text" id="col-a" /></td>
						</tr>
						<tr>
							<td>专业:</td>
							<td align="right"><input type="text" id="major-a" /></td>
						</tr>
						<tr>
							<td>课程名称</td>
							<td align="right"><input type="text" id="corname-a" /></td>
						</tr>
						<tr>
							<td>学期</td>
							<td align="right"><select id="semester-a" name="semester-a">
									<option value="请选择">--请选择--</option>
							</select></td>
						</tr>
						<div class="resultview" id="result">
							<span class="header">请选择要修改的关联教材</span> <span id="querystate"></span>
							教材名称：<input type="text" id="bkname-alter" /> <input
								name="search-alter" id="search-alter" class="btn" type="button"
								value="查询" />
							<!--  <a href="book/addbook.jsp">添加教材 </a> -->
							<input type="button" id="addbkBtn-alter" class="btn" value="添加教材" />
							<div id="checkresult"></div>
							<tr>
								<td align="center"><input name="alterbtn"
									class="btn-update" type="button" id="alterbtn" value="修改" /> <input
									name="returnbtn" class="btn-return" type="button"
									id="returnbtn" value="返回" /></td>
							</tr>
						</div>
					</table>
				</form>
			</div>
			<!--添加模块，用于添加新的课程  -->
			<div class="add">
				<span class="header">添加新的课程</span>
				<form id="alterform" name="alterform" method="post" action="">
					<table>
						<tr class="altertable">
							<td>&nbsp;<input type="hidden" id="idcor-add" />
							</td>
						</tr>
						<tr>
							<td width="80px">学 院:</td>
							<td align="right"><select id="col-add">
									<option value="请选择">--请选择--</option>
							</select></td>
						</tr>
						<tr>
							<td>专 业:</td>
							<td align="right"><select id="major-add">
									<option value="请选择">--请选择--</option>
							</select></td>
						</tr>
						<tr>
							<td width="100px">课程名称：</td>
							<td align="right"><input type="text" id="corname-add" /></td>
						</tr>
						<tr>
							<td>学 期:</td>
							<td align="right"><select id="semester-add"
								name="semester-add">
									<option value="请选择">--请选择--</option>
							</select></td>
						</tr>
						<div class="resultbkadd" id="result">
							<span class="header">请选择关联教材</span> 教材名称：<input type="text"
								id="bkname-add" /> <input name="search-add" id="search-add"
								class="btn" type="button" value="查询" />
							<!--  <a
								href="book/addbook.jsp">添加教材 </a>  -->
							<input type="button" id="addbkBtn-add" class="btn" value="添加教材" />
							<div id="addbkresult"></div>
							<tr>
								<td align="center"><input name="addbtn" id="addbtn"
									class="btn-add" type="button" value="添加" /></td>
								<td><input id="returnbtn-add" name="returnbtn-add"
									class="btn-return" type="button" value="返回" /></td>
							</tr>
						</div>
					</table>
				</form>
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
							版权所有:湖北中医药大学设备管理处®
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">Powered by&nbsp; <strong><a
							href="http://www.hbtcm.edu.cn" target="_blank">HBTCM</a> </strong><strong><font
							color="#FF9900"></font> </strong>&nbsp; &copy; 2009-2013</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-view" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
	<div id="dialog-delete" class="demo-description"></div>
</body>
</html>