// JavaScript Document
var tag = true;// 定义一个标识符，来确定是模糊查询还是精确查询，默认是模糊查询
var currentPage = 1; // 页索引
var totalPage = 1;// 总页数
var totalRecord = 1;// 总记录条数
$(function() {
	// 第一页按钮click事件
	$("#first").click(function() {
		currentPage = 1;
		$("#lblCurentPage").text(1);
			accurateQuery();
	});

	// 上一页按钮click事件
	$("#previous").click(function() {
		if (currentPage >= 2) {
			currentPage--;
			$("#lblCurentPage").text(currentPage);
		}
			accurateQuery();
		if (currentPage == 1)
			sys_Alert('到顶啦！');
	});
	// 下一页按钮click事件
	$("#next").click(function() {
		if (currentPage < totalPage) {
			currentPage++;
			$("#lblCurentPage").text(currentPage);
		}
			accurateQuery();
		if (currentPage == totalPage)
			sys_Alert('到底啦！');
	});
	// 最后一页按钮click事件
	$("#last").click(function() {
		$("#lblCurentPage").text(totalPage);
		currentPage = totalPage;
			accurateQuery();
	});

	function accurateQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_accurateQuery.action",
			data : "currentPage=" + currentPage + "&grade="
					+ $("#grade-db").val() + "&col=" + $("#col-db").val()
					+ "&major=" + $("#major-db").val() + "&campus="
					+ $("#campus-db").val(),
			beforeSend : function() {
				$("#resulttable").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				$("#querystate").empty();// 清空result中的内容
				// $("#querystate").html("------查询结束！--------");// 显示处理后的数
				manage(data);
			}
		});
	}

	// 页脚属性设置，每次查询之后将分页信息与页面元素绑定
	function bindPage() {
		// 填充分布控件信息
		if (totalPage == 0) {
			$("#lblCurentPage").text(0);
		} else {
			if (currentPage > totalPage) {
				$("#lblCurentPage").text(totalPage);
			} else {
				$("#lblCurentPage").text(currentPage); // 当前页
			}
		}
		$("#first").attr(
				"disabled",
				(currentPage == 1 || $("#lblCurentPage").text() == "0") ? true
						: false);
		$("#previous").attr(
				"disabled",
				(currentPage <= 1 || $("#lblCurentPage").text() == "0") ? true
						: false);
		$("#next").attr("disabled", (currentPage >= totalPage) ? true : false);
		$("#last")
				.attr(
						"disabled",
						(currentPage == totalPage || $("#lblCurentPage").text() == "0") ? true
								: false);
	} // 页脚属性设置，每次查询之后将分页信息与页面元素绑定

	// 初始化信息，打开页面时显示所有的学院专业
	function initInfo() {
		accurateQuery();
		bindPage();
	}
	// 初始化页码信息
	initPage();
	function initPage() {
		currentPage = 1; // 页索引
		totalPage = 1;// 总页数
		totalRecord = 1;// 总记录条数
		accurateQuery();
		getAllCol();
		getAllCampus();
		getAllGrade();
	}

	function manage(data) {
		var strHTML = "<table id=\"resulttable\" border=\"1\" width=\"100%\">";// 初始化内容变量
		if (data.length >= 1) {
			
			strHTML += "<tr align=\"center\"><th>年级</th><th>学院</th><th>专业名称</th><th>班号</th><th>校区</th><th>操作</th></tr>";
			$.each(data,function(index, Info) {
								if (index < data.length - 1) {
									if (index % 2 == 0)
										strHTML += "<tr class='trEven'>";
									else
										strHTML += "<tr class='trOdd'>";
									strHTML += "<td align=\"center\" width=\"10%\">"
										    + Info["grade"] + "</td>";
									strHTML += "<td align=\"center\" width=\"20%\">"
											+ Info["col"] + "</td>";
									strHTML += "<td align=\"center\" width=\"30%\">"
											+ Info["major"] + "</td>";
									strHTML += "<td align=\"center\" width=\"10%\">"
											+ Info["clsno"] + "</td>";
									strHTML += "<td align=\"center\" width=\"10%\">"
											+ Info["campus"]
											+ "</td>";
									strHTML += "<td align=\"center\" width=\"10%\">"
											+ "<a href=\"bkdist_printBKDistInfo?idcls="+Info["idcls"]+"\" target=\"_blank\">打印</a>"
										    + "</td>";
									strHTML += "</tr>";
								} else {
									var pagination = Info;
									totalPage = pagination["totalPage"];
									currentPage = pagination["currentPage"];
									totalRecord = pagination["totalRecord"];
									$("#lblToatlRecord").text(totalRecord);
									$("#lblCurentPage").text(currentPage);
									$("#lblTotalPage").text(totalPage);
								}
							});
			$(".result #pagination").css("display", "block");// 显示分页
		} else {
			$(".result #pagination").css("display", "none");// 隐藏分页
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的班级信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += // "</table>";
		$("#queryresult").html(strHTML);// 显示处理后的数据
	}

	// 页面打开时读入所有记录
	initInfo();

	$("#grade-db").bind('change', function() {
		currentPage = 1;
		accurateQuery();
	});
	$("#col-db").bind('change', function() {
		currentPage = 1;
		getMajorByCol();
		accurateQuery();
	});
	$("#major-db").bind('change', function() {
		currentPage = 1;
		accurateQuery();
	});
	$("#campus-db").bind('change', function() {
		currentPage = 1;
		accurateQuery();
	});

	// 弹出对话框的函数
	function sys_Alert(content) {// 弹出提示信息窗口
		$("#dialog-modal").dialog({
			width : 300,
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

	$(":checkbox").live("click", function() {
		var name = $(this).attr("name");
		if (name == "all") {
			if (this.checked) {
				$("input[name='checkoptions']").each(function() { // 遍历所有的name为checkhehe的
					// checkbox
					$(this).attr("checked", true);
				});
			} else { // 反之 取消全选
				$("input[name='checkoptions']").each(function() { // 遍历所有的name为checkhehe的
					// checkbox
					$(this).attr("checked", false);
				});
			}
		} else {
			var flag = true;
			$("input[name='checkoptions']").each(function() { // 遍历所有的name为checkhehe的
				// checkbox
				if (!this.checked) {
					flag = false;
				}
			});
			if (flag == true) {
				$("#checkall").attr("checked", true);
			} else {
				$("#checkall").attr("checked", false);
			}
		}
	});

	function getAllCol() { // 获取所有学院
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_getAllCol.action",
			data : null,
			success : function(data) {
				$("#col-db").empty();
				var optionstr = "<option value=\"请选择\">--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#col-db").append(optionstr);
			}
		});
	}

	function getAllCampus() { // 获取所有校区
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_getAllCampus.action",
			data : null,
			success : function(data) {
				$("#campus-db").empty();
				var optionstr = "<option value=\"请选择\">--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#campus-db").append(optionstr);
			}
		});
	}

	function getAllGrade() { // 获取所有年级
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_getAllGrade.action",
			data : null,
			success : function(data) {
				$("#grade-db").empty();
				var optionstr = "<option value=\"请选择\">--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#grade-db").append(optionstr);
			}
		});
	}

	function getMajorByCol() {
		if ($("#col-db  option:selected").text() == "--请选择--") {
			$("#major-db").empty();
			var optionstr = "<option value=\"请选择\">--请选择--</option>";
			$("#major-db").append(optionstr);
		} else {
			$.ajax({type : "post",
						dataType : "json",
						url : "college_getMajorByCol.action",
						data : "col=" + $("#col-db option:selected").text(),
						beforeSend : function() {
							$("#queryresult").empty();
							;// 清空result中的内容
						},
						success : function(data) {
							$("#major-db").empty();
							var optionstr = "<option value=\"请选择\">--请选择--</option>";
							if (!(data.length < 1)) {
								var optionstr = "<option value=\"请选择\">--请选择--</option>";
								$.each(data, function(index, Info) {
									optionstr += "<option>" + Info
											+ "</option>";// 生成专业的select列表
								});
								$("#major-db").append(optionstr);
								accurateQuery();// 处理分页
								$(".result #pagination")
										.css("display", "block");// 显示分页
							}
						}
					});
		}
	}

	$("#deletebtn").click(function() {
		var idclses = "";
		$("input[name='checkoptions']").each(function() { // 遍历所有的name为checkoptions的
			// checkbox
			if (this.checked) {
				idclses += $(this).val() + ",";
			}
		});
		idclses = idclses.substring(0, idclses.length - 1);
		deleteClasses(idclses);
	});
});