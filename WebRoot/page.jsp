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

<title>My JSP 'page.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/page.css">


</head>

<body>
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
</body>
</html>
