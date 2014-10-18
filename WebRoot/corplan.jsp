<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="css/corplan.css" />


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
<script language="javascript" type="text/javascript" src="js/corplan.js"
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
					<span class="header">教学计划查询与修改</span>
					<form id="queryform" name="queryform" method="post" action="">
						<table>
							<tr>
								<td colspan="2"><font color="#68A8CC" size="4">请选择学院和专业</font>
								</td>
							</tr>
							<tr>
								<td width="80px">学院:</td>
								<td><select id="col-c" name="col-c">
										<option value="请选择">--请选择--</option>
								</select></td>
								<td width="15px"></td>
								<td>专业:</td>
								<td><select id="major-c" name="major-c">
										<option value="请选择">--请选择--</option>
								</select></td>
							</tr>
						</table>
					</form>
				</div>

				<!--显示查询结果  -->
				<div class="result" id="result">
					<span class="header">查询结果</span>
					<!--<span id="selectState"></span>-->
					<div id="toolkit">
						<table width="615" align="center">
							<tr>
								<td><font size="4">查询课程：</font></td>
								<td><input type="text" id="fuzzy-c" name="fuzzy-c" /></td>
							</tr>
							<tr>
								<td><font size="4">所有课程:</font></td>
								<td>&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td><font size="4">学期:</font></td>
								<td><select id="semester-c" name="semester-c">
										<option value="请选择">--请选择--</option>
								</select></td>
							</tr>
						</table>
					</div>
					<div id="corplanmain">
						<table width="615" align="center">
							<tr>
								<td><select name="course" id="course" multiple="multiple">
								</select></td>
								<td width="90px"><input type="button" name="addToCorplan"
									id="addToCorplan" value=">>" title="将选中的课程添加到该学期教学计划" /> <br />
									<br /> <!--<input type="button" name="addAllToCorplan" id="addAllToCorplan"
											value="添加所有到教学计划" title="添加所有到教学计划" />
										<br />
										<br />
										--> <input type="button" name="deleteCorplan"
									id="deleteCorplan" value="从计划删除" title="从计划中删除选中课程" /> <br />
									<br /></td>
								<td><select name="corplan" id="corplan" multiple="multiple">
								</select></td>
							</tr>
							<tr>
								<!-- <td><br /> <input type="button" name="addNewCourse" id="addNewCourse"
											value="新建课程" title="新建一门课程" />
										&nbsp;&nbsp;&nbsp;
										<input type="button" name="editCourse" id="editCourse"
											value="编辑课程" title="编辑所选课程" />
									</td>-->
								<!-- <td>
										<input type="button" id="dropAllCorplan" value="清空所有教学计划"
											title="清空所有教学计划" />
										&nbsp;&nbsp;&nbsp;
										<input type="button" id="initCorplan" value="初始化教学计划"
											title="初始化教学计划" />
									</td> -->
							</tr>
						</table>
					</div>
					<!-- 分页数据 -->
					<jsp:include page="page.jsp" />
				</div>
			</div>

			<!--修改模块，用于修改查询的结果  -->
			<div class="alter">
				<span class="header">课程信息修改</span>
				<table class="altertable">
					<tr>
						<td>&nbsp; <input type="hidden" id="idcor-a" />
						</td>
					</tr>
					<tr>
						<td width="80px">学院:</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="col-a" /></td>
					</tr>
					<tr>
						<td>专业:</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="major-a" /></td>
					</tr>
					<tr>
						<td>课程名称</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="corname-a" /></td>
					</tr>
					<tr>
						<td>学期</td>
					</tr>
					<tr>
						<td align="right"><select id="semester-a" name="semester-a">
								<option value="请选择">--请选择--</option>
						</select></td>
					</tr>
					<tr>
						<td align="center"><input name="alterbtn" class="btn"
							type="button" id="alterbtn" value="修改" /></td>
					</tr>
				</table>
			</div>
			<!--添加模块，用于添加新的课程信息  -->
			<div class="add">
				<span class="header">添加新的课程</span>
				<table>
					<tr class="addtable">
						<td>&nbsp; <input type="hidden" id="idcor-add" />
						</td>
					</tr>
					<tr>
						<td width="80px">学院:</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="col-add" /></td>
					</tr>
					<tr>
						<td>专业:</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="major-add" /></td>
					</tr>
					<tr>
						<td>课程名称</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="corname-add" /></td>
					</tr>
					<tr>
						<td>学期</td>
					</tr>
					<tr>
						<td align="right"><input type="text" id="semester-add" /></td>
					</tr>
					<tr>
						<td align="center"><input name="addbtn" class="btn"
							type="button" id="addbtn" value="添加" /></td>
					</tr>
				</table>
			</div>
		</div>
		<!--底部信息栏  -->
		<jsp:include page="copyright.jsp" />
	</div>
	<div id="dialog-modal" class="demo-description"></div>
	<div id="dialog-add" class="demo-description"></div>
	<div id="dialog-delete" class="demo-description"></div>
	<div id="mask">
		<span id="info"> 正在加载中，请稍候！ </span> <span id="loading"><img
			src="css/images/waiting.gif" /> </span>
	</div>
</body>
</html>