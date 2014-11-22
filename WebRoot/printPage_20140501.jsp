<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java"
	import="com.bean.bookpurchaseview.Bookpurchaseview,com.bean.bookpurchaseview.Bookpurchaseview.BookpurchaseviewId,java.util.*;"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
       List<Bookpurchaseview> bkpurviews = (List<Bookpurchaseview>)request.getAttribute("bkpurviews");
       if(! (bkpurviews.size()>0)) return;
       BookpurchaseviewId bkpurviewid = bkpurviews.get(0).getId();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发书清单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" type="text/css" href="css/printPage.css" />
<script language="javascript" src="js/LodopFuncs.js"></script>
<script language="javascript" type="text/javascript">
	var LODOP; //声明为全局变量 
	function prn_print() {
		printPage();
		LODOP.PRINT();
	};
	function printPage() {
		LODOP = getLodop();
		LODOP.PRINT_INIT("发书清单");
		LODOP.SET_PRINT_STYLE("FontSize", 18);
		LODOP.SET_PRINT_STYLE("Bold", 1);
		LODOP.SET_PRINT_STYLEA(0, "Horient", 2);
		LODOP.SET_PRINT_PAGESIZE(2, 2100, 2970, "A4");
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", document
				.getElementById("printInfo").innerHTML);
	};
	function prn_printer() {
		printPage();
		LODOP.PRINTA();
	};
	function prn_preview() {
		LODOP = getLodop();
		LODOP.PRINT_INIT("打印当前网页");
		LODOP.SET_PRINT_STYLE("FontSize", 18);
		LODOP.SET_PRINT_STYLE("Bold", 1);
		LODOP.SET_PRINT_STYLEA(0, "Horient", 2);
		LODOP.SET_PRINT_PAGESIZE(2, 2100, 2970, "A4");
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", document
				.getElementById("printInfo").innerHTML);
		LODOP.PREVIEW();
	};
</script>
</head>

<body>
	<a href="javascript:prn_preview()">打印预览</a>
	<a href="javascript:prn_print()">直接打印</a>
	<a href="javascript:prn_printer()">选择打印机</a>
	<%
		String header = "";
		header += bkpurviewid.getCol() + bkpurviewid.getGrade()
				+ bkpurviewid.getMajor() + bkpurviewid.getClsno() + "班第"
				+ bkpurviewid.getSemester() + "学期教材发放清单";
	%>
	<div class="print" id="printInfo">
		<h2 align="center"><%=header%></h2>
		<div class="stuNum" align="center">
			<table>
				<tr>
					<td width="250"></td>
					<td width="200"></td>
					<td width="200"></td>
					<td width="200">参考数量： ________</td>
				</tr>
			</table>
		</div>
		<br />
		<div align="center">
			<div id="printDiv">
				<table align="center" class="printTalbe" cellspacing="0px">
					<tr align="center">
						<th width="5%" class="printTh">序号</th>
						<th width="30%" class="printTh">教材名称</th>
						<th width="16%" class="printTh">出版社/版本</th>
						<th width="14%" class="printTh">作者</th>
						<th width="5%" class="printTh">单价</th>
						<th width="30%" class="printTh">备注</th>
					</tr>
					<%
						int j = 1;
						for (int i = 0; i < bkpurviews.size(); i++) {
							Bookpurchaseview bkpurview = bkpurviews.get(i);
							BookpurchaseviewId bkpurvid = bkpurview.getId();
							if (!bkpurvid.getBkname().equals("")) {
					%>
					<tr>
						<td align="center" class="printTd"><%=j%></td>
						<td class="printTd"><%=bkpurvid.getBkname()%></td>
						<td class="printTd"><%=bkpurvid.getPublisher() + ""
							+ bkpurvid.getEdition() + "版"%></td>
						<td class="printTd"><%=bkpurvid.getAuthor()%></td>
						<td class="printTd">&nbsp;</td>
						<td class="printTd">&nbsp;</td>
					</tr>
					<%
						j++;
							}
						}
					%>
				</table>
			</div>
		</div>
		<br />
		<div align="center">
			<table class="memo">
				<tr>
					<td width="250">联系电话：</td>
					<td width="200">领书人：</td>
					<td width="200">发书人：</td>
					<td width="200">发书日期：</td>
				</tr>
			</table>
		</div>

	</div>
</body>
</html>
