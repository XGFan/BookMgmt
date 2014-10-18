<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="css/college.css" />

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
	charset="utf-8">
	
</script>
<script language="javascript" type="text/javascript" src="js/college.js"
	charset="utf-8">
	
</script>

</head>

<body class="body">
	<div class="total">
		<!--顶部信息 -->
		<jsp:include page="top.jsp" />
		<!--主工作区  -->
		<div class="main">
			<!--中间工作块，上面是查询条件，下面是查询结果  -->
			<div class="query">
				<!--查询条件  -->
				<div class="condition">
					<span class="header">学院专业查询</span>
					<form id="queryform" name="queryform" method="post" action="">
						<table>
							<tr>
								<td colspan="2"><font color="#68A8CC" size="4">请输入学院或专业</font>
								</td>
							</tr>
							<tr>
								<td width="80px">学院:</td>
								<td><select id="col-c" name="col-c">
										<option value="请选择">--请选择--</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>专业:</td>
								<td><input type="text" id="fuzzy-c" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input name="querybtna"
									class="btn" type="button" id="querybtn" value="查询" />
								</td>
							</tr>
						</table>
					</form>
				</div>

				<!--显示查询结果  -->
				<div class="result" id="result">
					<span class="header">查询结果</span> <span id="querystate"></span> <span><input
						type="button" class="btn" id="addbtn" value="添加新的专业" /> </span>
					<div id="queryresult"></div>
					<!-- 分页数据 -->
					<jsp:include page="page.jsp" />
				</div>
			</div>

			<!--修改模块，用于修改查询的结果  -->
			<div class="alter">
				<span class="header">院系专业修改</span>
				<form id="alterform" name="alterform" method="post" action="">
					<table>
						<tr>
							<td>&nbsp; <input type="hidden" id="idcm-a" />
							</td>
						</tr>
						<tr>
							<td>学院</td>
							<td><input type="text" id="col-a" />
							</td>
						</tr>
						<tr>
							<td>专业</td>
							<td><input type="text" id="major-a" />
							</td>
						</tr>
						<tr>
							<td>学期</td>
							<td><input type="text" id="semnum-a" />
							</td>
						</tr>
						<tr align="center">
							<td align="center"><input name="alterbtn" class="btn"
								type="button" id="alterbtn" value="修改" /></td>
							<td align="center"><input name="cancelbtn" class="btn"
								type="button" id="cancelbtn" value="取消" /></td>
						</tr>
					</table>

				</form>
			</div>
		</div>
		<!--底部信息栏  -->
		<jsp:include page="copyright.jsp" />
	</div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
</body>
</html>