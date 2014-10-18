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
		<link rel="stylesheet" type="text/css" href="css/bkdist.css" />
		<link rel="stylesheet" type="text/css" href="css/page.css" />
		<!-- <link rel="stylesheet" type="text/css" href="css/jqpagination.css" /> -->


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
		<script language="javascript" type="text/javascript"
			src="js/dialog.js" charset="utf-8"></script>
		<script language="javascript" type="text/javascript"
			src="js/distributionpage.js" charset="utf-8">
	
</script>

	</head>

	<body class="body">
		<div class="total">
			<!--顶部信息 -->
			<jsp:include page="top.jsp" />
			<div class="main">
				<div class="query">

					<!--查询条件  -->
					<div class="condition" style="top: 0px; height: 116px;">
						<span class="header">打印信息查询</span>
						<form id="queryform" name="queryform" method="post" action="">
							<table style="left: 11px; top: 86px; height: 31px;">
								<tr>
									<td>
										<font color="#68A8CC" size="4">查询条件：</font>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td>
										学院
									</td>
									<td>
										<select id="col-db" name="col-db">
											<option value="请选择">
												--请选择--
											</option>
										</select>
									</td>
									<td>
										专业
									</td>
									<td>
										<select id="major-db" name="major-db">
											<option value="请选择">
												--请选择--
											</option>
										</select>
									</td>
									<td>
										年级
									</td>
									<td>
										<select id="grade-db" name="grade-db">
											<option value="请选择">
												--请选择--
											</option>
										</select>
									</td>
									<td>
										校区
									</td>
									<td>
										<select id="campus-db" name="campus-db">
											<option value="请选择">
												--请选择--
											</option>
										</select>
									</td>
								</tr>
							</table>
						</form>
					</div>

					<!--显示查询结果  -->
					<div class="result" id="result"
						style="top: 120px; height: 367px; left: -1px;">
						<span class="header">查询结果</span>
						<span id="querystate"></span>
						<div id="queryresult"></div>
						<table id="resulttable" border='1' width="100%">
						</table>
						<br />
						<div class="pagination" id="pagination">
							<div class="pageview">
								共
								<label id="lblToatlRecord">
									0
								</label>
								条数据 第[
								<label id="lblCurentPage">
									0
								</label>
								]页/共[
								<label id="lblTotalPage">
									0
								</label>
								]页
							</div>
							<div class="pagecontrol">
								<a id="first" href="javascript:void(0)">首页</a>
								<a id="previous" href="javascript:void(0)">上一页</a>
								<a id="next" href="javascript:void(0)"> 下一页</a>
								<a id="last" href="javascript:void(0)">末页</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--底部信息栏 -->
			<jsp:include page="/copyright.jsp" />
		</div>
	</body>
</html>