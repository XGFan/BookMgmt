// JavaScript Document
//进行查询的js代码
$(function() {
	function sys_Alert(content) {// 弹出提示信息窗口
		$("#dialog-modal").dialog({
			width : 400,
			height : 200,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				'确定' : function() {
					$(this).dialog("close");
				}
			},
			open : function(event, ui) {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}

	function sys_Confirm(content) {// 弹出信息询问窗口
		// alert(content);
		$("#dialog-modal").dialog({
			width : 400,
			height : 200,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				"确定" : function() {
					// $("#spnName").remove();
					$(this).dialog("close");
				},
			},
			open : function() {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}

	function add_Dialog(content) {// 弹出信息询问窗口
		// alert(content);
		$("#dialog-add").dialog({
			width : 500,
			height : 350,
			modal : true,
			title : "添加新的专业",
			hide : "slide",
			buttons : {
				"确定" : function() {
					// $("#spnName").remove();
					$(this).dailog("close");
				},
			},
			open : function() {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}

	$("#querybtn")
			.click(
					function() {
						$
								.ajax({
									type : "post",
									url : "college_list.action",
									dataType : "json",
									data : "col="
											+ encodeURI($("#col-c").val())
											+ "&major="
											+ encodeURI($("#major-c").val()),
									beforeSend : function() {
										alert($("#col-c").val());
										$("#querystate").empty();// 清空result中的内容
										$("#querystate").html(
												"------正在查询中，请稍后...");// 显示处理后的数
									},
									success : function(data) {
										$("#querystate").empty();// 清空result中的内容
										$("#querystate").html(
												"------查询结束！--------");// 显示处理后的数
										alert(data.length);// 输出返回数据的长度
										var strHTML = "";// 初始化内容变量
										strHTML = "<table id=\"resulttable\" border='1'><tr align=\"center\"><th>专业编号</th><th>学院</th><th>专业名称</th><th>学制</th><th>操作</th>";
										$
												.each(
														data,
														function(index, Info) {
															if (Info["idcm"] == "空") {
																strHTML = "<tr><td><font size=\"5\" color=\"red\">"
																		+ "未找到相应的专业信息，<br/>请修改查询条件后重新查询！"
																		+ "</font></td></tr>";
																// return false;
															} else {
																strHTML += "<tr width='100%'><td align='center' width='20%'>"
																		+ Info["idcm"]
																		+ "</td>";
																strHTML += "<td align='center' width='25%'>"
																		+ Info["col"]
																		+ "</td>";
																strHTML += "<td align='center' width='35%'>"
																		+ Info["major"]
																		+ "</td>";
																strHTML += "<td align='center' width='10%'>"
																		+ Info["semnum"]
																		+ "</td>";
																strHTML += "<td align='center' width='10%' id='tempidcm'><input class='btn' type='button' value='编辑'/></td></tr>";
															}
														});
										strHTML += "</table>";
										// $("#result").html(strHTML);//显示处理后的数据
										$("#queryresult").html(strHTML);// 显示处理后的数据
									},
									error : function() {
										$("#querystate").empty();// 清空result中的内容
										$("#querystate").html(
												"------查询失败！--------");// 显示处理后的数
									}
								});

						$("#alterbtn").click(function() {
							sys_Alert("修改成功！");
						});
						$("#tempidcm").live("click", function() {
							var $record = $(this).parent();
							var $idcm = $record.children(":first");
							var $col = $idcm.next();
							var $major = $col.next();
							var $semnum = $major.next();
							$("#idcm-a").attr("value", $idcm.text());
							$("#col-a").attr("value", $col.text());
							$("#major-a").attr("value", $major.text());
							$("#semnum-a").attr("value", $semnum.text());
							$(".alter").addClass("alterfocus");
							// sys_Alert("请在右侧编辑栏对班级信息进行编辑!");
							sys_Alert($idcm.text());
							sys_Alert("修改成功！");
							// alert("获取到了");
						});
					});
	$("#addbtn")
			.click(
					function() {
						var context = "<form id='alterform' name='alterform' method='post' action=''><table><tr><td><input type='hidden' id='idcm-a' /></td></tr><tr><td>学院</td><td><input type='text' id='col-a' /></td></tr><tr><td>专业编号</td><td><input type='text' id='major-a' /></td></tr><tr><td>学期</td><td><input type='text' id='semnum-a' /></td></tr></table></form>";
						add_Dialog(context);
					});
});
