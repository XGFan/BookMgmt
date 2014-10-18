<%@ page language="java" import="java.util.*,com.util.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'pagelist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<s:bean id="pagination" name="Pagination"></s:bean>
	<s:if test="pagination.totalPage!=0">
		<table width="100%" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td valign="bottom" align="left" nowrap="nowrap" style="width: 40%;">
					总记录: <s:property value="pagination.totalRecord" />
					条&nbsp;&nbsp;每页: <s:property value="pagination.size" />
					条&nbsp;&nbsp;页码:第 <s:property value="pagination.currentPage" />
					页/共 <s:property value="pagination.totalPage" /></td>
				<td valign="bottom" align="right" nowrap="nowrap" style="width: 60%">
					<s:if test="pagination.currentPage == 1">
						<span class="current">首页</span>
						<span class="current">上一页</span>
					</s:if> <s:else>
						<s:a id="first" cssStyle="margin-right:5px;">首页</s:a>
						<s:a id="prior" cssStyle="margin-right:5px;">上一页</s:a>
					</s:else> <s:if
						test="pagination.currentPage == totalPage || pagination.currentPage == 0">
						<span class="current">下一页</span>
						<span class="current">末页</span>
					</s:if> <s:else>
						<s:a id="next" cssStyle="margin-right:5px;">下一页</s:a>&nbsp;&nbsp;
                            <s:a id="last" cssStyle="margin-right:5px;">末页</s:a>
					</s:else></td>
			</tr>
		</table>
	</s:if>
</body>
</html>
