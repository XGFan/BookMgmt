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

<title>My JSP 'copyright.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
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
					</p></td>
			</tr>
			<tr>
				<td align="center">Powered by&nbsp; <strong><a
						href="http://www.hbtcm.edu.cn" target="_blank">HBTCM</a> </strong><strong><font
						color="#FF9900"></font> </strong>&nbsp; &copy; 2009-2013</td>
			</tr>
		</table>
	</div>
</body>
</html>
