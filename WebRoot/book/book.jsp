<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="../css/book.css" />
<%@ include file="../JsHeader.jsp"%>
<%@ include file="JsHeaderBook.jsp"%>
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
				<div class="condition">
					<span class="header">教材信息查询</span><font color="#68A8CC" size="4">请输入查询信息：</font>
					<form id="queryform" name="queryform" method="post" action="">
						<table>
							<tr>
								<td width="100px">教材名称:</td>
								<td><input type="text" id="bkname-a" name="bkname-a" />
								</td>
								<td>出版社:</td>
								<td><input type="text" id="publisher-a" name="publisher-a" /></td>
								<td>ISBN:</td>
								<td><input type="text" id="isbn-a" name="isbn-a" />
								</td>
								<td align="right"><input name="querybtn" class="btn"
									type="button" id="querybtn" value="查询" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<!--显示查询结果  -->
				<div class="result" id="result">
					<span class="header">查询结果</span> <span id="querystate"></span> <span
						style="float: right"><input name="add" type="button"
						class="btn" id="add" value="添加新的教材" /> </span>
					<div id="queryresult"></div>
					<jsp:include page="/page.jsp"></jsp:include>
				</div>
			</div>

			<!--修改模块，用于修改查询的结果  -->
			<div class="alter">
				<span class="header">教材信息修改</span>
				<form id="alterform" name="alterform" method="post" action="">
					<table>
						<tr class="altertable">
							<td>&nbsp; <input type="hidden" id="idbk-b" /></td>
						</tr>
						<tr>
							<td width="100px">教材名称：</td>
							<td align="right"><input type="text" id="bkname-b" />
							</td>
						</tr>
						<tr>
							<td>作者：</td>
							<td align="right"><input type="text" id="author-b" />
							</td>
						</tr>
						<tr>
							<td>版本：</td>
							<td align="right"><input type="text" id="edition-b" />
							</td>
						</tr>
						<tr>
							<td>出版社：</td>
							<td align="right"><select id="publisher-b" />
							</td>
						</tr>
						<tr>
							<td>ISBN：</td>
							<td align="right"><input type="text" id="isbn-b" />
							</td>
						</tr>
						<tr>
							<td>价格：</td>
							<td align="right"><input type="text" id="price-b" />
							</td>
						</tr>
						<div class="resultview" id="result">
							<span class="header">请选择要修改的关联课程</span> <span id="querystate"></span>
							课程名称：<input type="text" id="check-alter" /> <input
								name="checkbtn" class="btn" type="button" id="checkbtn-alter"
								value="查询" />
							<div id="checkresult"></div>
							<tr>
								<td align="center"><input name="alterbtn"
									class="btn-update" type="button" id="alterbtn" class="btn"
									value="修改" />
								</td>
								<td><input id="returnbtn" name="returnbtn"
									class="btn-return" type="button" value="返回" />
								</td>
							</tr>
						</div>
					</table>
				</form>
			</div>

			<!-- 添加模块，用于添加 -->
			<div class="add">
				<span class="header">添加新教材</span>
				<form id="addform" name="addform" method="post" action="">
					<table>
						<tr class="addtable">
							<td>&nbsp; <input type="hidden" id="idbk-a" /></td>
						</tr>
						<tr>
							<td width="100px">教材名称：</td>
							<td align="right"><input type="text" id="bkname-c" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="80px">学院：</td>
							<td><select id="col-d" name="col-d">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>作者：</td>
							<td align="right"><input type="text" id="author-c" />
							</td>
						</tr>
						<tr>
							<td>版本：</td>
							<td align="right"><input type="text" id="edition-c" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>专业：</td>
							<td><select id="major-d" name="major-d">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>出版社：</td>
							<td><select id="publisher-c" name="publisher-c">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>ISBN：</td>
							<td align="right"><input type="text" id="isbn-c" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>学期：</td>
							<td><select id="semester-d" name="semester-d">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>价格：</td>
							<td align="right"><input type="text" id="price-c" />
							</td>
						</tr>

						<div class="resultadd" id="result">
							<span class="header">请选择要关联的课程</span> 课程名称：<input type="text"
								id="check-add" /> <input name="checkbtn" class="btn"
								type="button" id="checkbtn-add" value="查询" /> <span
								id="querystate"></span>
							<div id="addresult"></div>
							<td align="center"><input name="alterbtn" class="btn-add "
								type="button" id="addbtn" value="添加" />
							</td>
							<td><input id="returnbtn-add" name="returnbtn-add"
								class="btn-return" type="button" value="返回" />
							</td>
						</div>
					</table>
				</form>
			</div>
		</div>
		<!--底部信息栏 -->
		<jsp:include page="../copyright.jsp" />
		<div id="dialog-modal" class="demo-description"></div>
		<div id="dialog-add" class="demo-description"></div>
		<div id="dialog-view" class="demo-description"></div>
		<div id="dialog-delete" class="demo-description"></div>
	</div>
</body>
</html>