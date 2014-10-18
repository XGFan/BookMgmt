<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖北中医药大学教材管理系统</title>
<link rel="stylesheet" type="text/css" href="../css/addbook.css" />
<%@ include file="/JsHeader.jsp"%>
<%@ include file="/book/JsHeaderBook.jsp"%>
</head>
<body class="body">
	<div class="total">
		<!--顶部信息 -->
		<jsp:include page="../top.jsp" />
		<!--主工作区  -->
		<div class="main">
			<div class="addbk">
				<span class="header">添加新的教材信息</span> <font color="#68A8CC" size="5">请输入新添加教材信息：</font>
				<form id="addform" name="addform" method="post" action="">
					<table>
						<tr class="addtable">
							<td>&nbsp; <input type="hidden" id="idbk-c" />
							</td>
						</tr>
						<tr>
							<td width="80px">教材名称:</td>
							<td align="right"><input type="text" id="bkname-c" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="80px">学院:</td>
							<td><select id="col-d" name="col-d">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>作者:</td>
							<td align="right"><input type="text" id="author-c" />
							</td>
						</tr>
						<tr>
							<td>版本：</td>
							<td align="right"><input type="text" id="edition-c" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="80px">专业:</td>
							<td><select id="major-d" name="major-d">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>出版社：</td>
							<td><select id="idsp-c" name="idsp-c">
									<option value="请选择">--请选择--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>ISBN：</td>
							<td align="right"><input type="text" id="isbn-c" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="80px">学期:</td>
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
							<span class="header">请输入添加的关联课程</span> 课程名称：<input type="text"
								id="check-c" /> <input name="checkbtn" class="btn"
								type="button" id="checkbtn" value="查询" /> <span id="querystate"></span>
							<div id="addresult"></div>
							<tr>
								<td align="center"><input name="alterbtn" class="btn-add"
									type="button" id="addbtn" value="添加" />
								</td>
							</tr>
						</div>
					</table>
				</form>
			</div>
			<!--底部信息栏  -->
			<jsp:include page="../copyright.jsp" />
		</div>
		<div id="dialog-modal" class="demo-description"></div>
		<div id="dialog-add" class="demo-description"></div>
	</div>
</body>
</html>