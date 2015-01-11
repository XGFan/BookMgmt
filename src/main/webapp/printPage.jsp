<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java"
	import="com.bean.bookpurchaseview.Bookpurchaseview"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
       List<Bookpurchaseview> bkpurviews = (List<Bookpurchaseview>)request.getAttribute("bkpurviews");
       if(! (bkpurviews.size()>0)) return;
       Bookpurchaseview.BookpurchaseviewId bkpurviewid = bkpurviews.get(0).getId();
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
		<br />
		<div align="center"
			style="font-family:Arial;font-size:30px;margin-top:50px;letter-spacing:3px;"><%=header%></div>
		<br />
		<div class="stuNum" align="center">
			<table>
				<tr>
					<td width="300px"></td>
					<td width="300px"></td>
					<td width="300px"></td>
					<td width="250px"
						style="font-family: Arial;font-size: 22px;letter-spacing:1.5px;">参考数量：<%=bkpurviewid.getStunum() %></td>
				</tr>
			</table>
		</div>
		<br />
		<!-- 因为打印控件不支持外置的CSS文件，所以样式写在了标签里面 -->
		<div align="center">
			<div id="printDiv"
				style="border-left:1px solid #000000;border-top:1px solid #000000;border-right:2px solid #000000;
				border-bottom:2px solid #000000;text-align:center;width:1100px;padding:0px;margin:0px;align:center">

				<table align="center"
					style="border-collapse:collapse;width:100%;padding:0px;margin:0px;">
					<tr align="center">
						<th width="8%"
							style="height:35px;font-family: 楷体,楷体_GB2312;font-size: 28px;font-weight: bolder;border-top:1px solid gray;border-left:1px solid gray;">序号</th>
						<th width="33%"
							style="font-family: 楷体,楷体_GB2312;font-size: 28px;font-weight: bolder;border-top:1px solid gray;border-left:1px solid gray;">教材名称</th>
						<th width="22%"
							style="font-family: 楷体,楷体_GB2312;font-size: 28px;font-weight: bolder;border-top:1px solid gray;border-left:1px solid gray;">出版社/版本</th>
						<th width="14%"
							style="font-family: 楷体,楷体_GB2312;font-size: 28px;font-weight: bolder;border-top:1px solid gray;border-left:1px solid gray;">作者</th>
						<th width="8%"
							style="font-family: 楷体,楷体_GB2312;font-size: 28px;font-weight: bolder;border-top:1px solid gray;border-left:1px solid gray;">单价</th>
						<th width="15%"
							style="font-family: 楷体,楷体_GB2312;font-size: 28px;font-weight: bolder;border-top:1px solid gray;border-left:1px solid gray;">备注</th>
					</tr>
					<%
						int j = 1;
						for (int i = 0; i < bkpurviews.size(); i++) {
							Bookpurchaseview bkpurview = bkpurviews.get(i);
							Bookpurchaseview.BookpurchaseviewId bkpurvid = bkpurview.getId();
							if (!bkpurvid.getBkname().equals("")) {
					%>
					<tr>
						<td align="center"
							style="height:36px;letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;"><%=j%></td>
						<td
							style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;"><%=bkpurvid.getBkname()%></td>
						<td
							style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">
							<%
								String edition = bkpurvid.getPublisher();
										if (bkpurvid.getEdition() > 1) {
											edition += "/" + bkpurvid.getEdition() + "版";
										}
							%> <%=edition%></td>
						<td
							style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;text-align:center"><%=bkpurvid.getAuthor()%></td>
						<td
							style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">&nbsp;</td>
						<td
							style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">&nbsp;</td>

					</tr>

					<%
						j++;
							}
						}
					%>
					<tr>
						<td align="center" style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;"><%=j %></td>
						<td style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">实验报告本</td>
						<td style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">&nbsp;</td>
						<td style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">&nbsp;</td>
						<td style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">&nbsp;</td>
						<td style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 24px;border-top:1px solid gray;border-left:1px solid gray;">&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		<br />
		<div align="center">
			<table class="memo">
				<tr>
					<td width="350px"
						style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 25px;">联系电话：</td>
					<td width="220px"
						style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 25px;">领书人：</td>
					<td width="230px"
						style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 25px;">发书人：</td>
					<td width="200px"
						style="letter-spacing:1px;font-family: 楷体,楷体_GB2312;font-size: 25px;">发书日期：</td>
				</tr>
			</table>
		</div>

	</div>
</body>
</html>
