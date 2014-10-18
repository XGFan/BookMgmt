// JavaScript Document
var tag = true;// 定义一个标识符，来确定是模糊查询还是精确查询，默认是模糊查询
var currentPage = 1; // 页索引
var totalPage = 1;// 总页数
var totalRecord = 1;// 总记录条数
var tempid;
var ccampus;
$(function() {
	// 第一页按钮click事件
	$("#first").click(function() {
		currentPage = 1;
		$("#lblCurentPage").text(1);
		if (tag == false)
			accurateQuery();
		else
			fuzzyQuery();
	});

	// 上一页按钮click事件
	$("#previous").click(function() {
		if (currentPage >= 2) {
			currentPage--;
			$("#lblCurentPage").text(currentPage);
		}
		if (tag == false)
			accurateQuery();
		else
			fuzzyQuery();
		if (currentPage == 1)
			sys_Alert('到顶啦！');
	});
	// 下一页按钮click事件
	$("#next").click(function() {
		if (currentPage < totalPage) {
			currentPage++;
			$("#lblCurentPage").text(currentPage);
		}
		if (tag == false)
			accurateQuery();
		else
			fuzzyQuery();
		if (currentPage == totalPage)
			sys_Alert('到底啦！');
	});
	// 最后一页按钮click事件
	$("#last").click(function() {
		$("#lblCurentPage").text(totalPage);
		currentPage = totalPage;
		if (tag == false)
			accurateQuery();
		else
			fuzzyQuery();
	});

	function accurateQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_accurateQuery.action",
			data : "currentPage=" + currentPage + "&grade="
					+ $("#grade-cs").val() + "&col=" + $("#col-cs").val()
					+ "&major=" + $("#major-cs").val() + "&campus="
					+ $("#campus-cs").val(),
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
	} 
	
	function fuzzyQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_fuzzyQuery.action",
			data : "currentPage=" + currentPage + "&condition="
					+ $("#fuzzy-cs").val(),
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

	// 初始化信息，打开页面时显示所有的学院专业
	function initInfo() {
		fuzzyQuery();
		bindPage();
	}
	
	// 初始化页码信息
	initPage();
	function initPage() {
		currentPage = 1; // 页索引
		totalPage = 1;// 总页数
		totalRecord = 1;// 总记录条数
	}

	function manage(data) {
		var strHTML = "<table id=\"resulttable\" border=\"1\" width=\"100%\">";// 初始化内容变量
		if (data.length >= 1) {
			strHTML += "<tr align=\"center\"><th><input type=\"checkbox\" id=\"checkall\" name=\"all\" value=\"all\"/>全选</th><th>年级</th><th>学院</th><th>专业名称</th><th>班号</th><th>校区</th><th>人数</th></tr>";
			$.each(data,function(index, Info) {
				if (index < data.length - 1) {
					if (index % 2 == 0)
						strHTML += "<tr class='trEven'>";
					else
						strHTML += "<tr class='trOdd'>";
					strHTML += "<td id=\""
							+ Info["idcls"]
							+ "\"><input name=\"checkoptions\" type=\"checkbox\" value=\""
							+ Info["idcls"]
							+ "\"/></td><td align=\"center\" width=\"10%\">"
							+ Info["grade"] + "</td>";
					strHTML += "<td align=\"center\" width=\"20%\">"
							+ Info["col"] + "</td>";
					strHTML += "<td align=\"center\" width=\"30%\">"
							+ Info["major"] + "</td>";
					strHTML += "<td align=\"center\" width=\"10%\">"
							+ Info["clsno"] + "</td>";
					strHTML += "<td align=\"center\" width=\"10%\" id=\"tempid\"><select class=\"ccampus\" id=\"ccampus"
							+ index
							+ "\" name=\"ccampus\"><option value=\""
							+ Info["campus"]
							+ "\">"
							+ Info["campus"]
							+ "</option>"
							+ "</td>";
					strHTML += "<td align=\"center\" id=\"temp\" class=\"editable simpleInput\" width=\"10%\">"
							+ Info["stunum"] + "</td>";

					strHTML += "</tr>";
				} else {
					var pagination = Info;
					totalPage = pagination["totalPage"];
					currentPage = pagination["currentPage"];
					totalRecord = pagination["totalRecord"];
					$("#lblToatlRecord").text(totalRecord);
					$("#lblCurentPage").text(currentPage);
					$("#lblTotalPage").text(totalPage);

					EdTable.initBindGridEvent();
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

	// 查询
	$("#querybtn").click(function() {
		currentPage = 1;
		fuzzyQuery(); // 调用模糊查询
	});
	$("#col-a").click(function() {
		tag = false; // 点击了学院之后就变成精确查询
	});
	$("#tabs-1a").click(function() {
		tag = true; // 模糊查询
		$("#fuzzy-c").val('');// 清空查询条件
		initPage(); // 初始化页码信息
		fuzzyQuery();
	});
	$("#tabs-2a").click(function() {
		tag = false; // 查询
		currentPage = 1;
		getAllCol();
		getAllCampus();
		getAllGrade();
		accurateQuery();
	});
	$("#fuzzy-cs").focus();
	$("#querytabs").tabs({
		// 设置各选项卡在切换时的动画效果
		fx : {
			opacity : "toggle",
			height : "toggle"
		},
		// 通过鼠标单击事件切换选项卡
		event : "click"
	// 通过移动鼠标事件切换选项卡

	});
	$("#grade-cs").bind('change', function() {
		currentPage = 1;
		accurateQuery();
	});
	$("#col-cs").bind('change', function() {
		currentPage = 1;
		getMajorByCol();
		accurateQuery();
	});
	$("#major-cs").bind('change', function() {
		currentPage = 1;
		accurateQuery();
	});
	$("#campus-cs").bind('change', function() {
		currentPage = 1;
		accurateQuery();
	});

	$("#addbtn").click(function() {
		var context = "<form id='addform' name='addform' method='post' action=''>";
		context += "<table id='addclass'><tr><td>年级</td><td><select id='grade' length='30'><option value=\"请选择\">--请选择--</option></select></td><td>校区</td><td><select id='campus'><option value=\"请选择\">--请选择--</option></select></td></tr>";
		context += "<tr><td>学院</td><td><select id='college'><option value=\"请选择\">--请选择--</option></select></td><td>专业</td><td><select id='major'><option value=\"请选择\">--请选择--</option></select></td></tr>";
		context += "<tr><td>个数</td><td><input type='text' id='classcount' size='2' maxlength='2'></td></tr></table></form>";
		sys_Confirm(context);
		var value = "";
		var currentYear = currentTime();
		var year;
		for ( var i = -7; i < 1; i++) {
			year = currentYear + i;
			value += "<option value=" + year + ">" + year
					+ "</option>";
		}
		$("#grade").append(value);
		getAllCampus();
		getAllCol();
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

	// 弹出添加学院的对话框
	function sys_Confirm(content) {// 弹出信息询问窗口
		$("#dialog-add").dialog({
			width : 500,
			height : 300,
			modal : true,
			title : "添加新班级",
			hide : "slide",
			buttons : {
				'添加' : function() {
					//$(this).dialog("close");
					checklegal();
				},
				'取消' : function() {
					$(this).dialog("close");
				}
			},
			open : function() {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
				$("#addclass").css("font-size", 16);
			}
		});
	}
	// 复选框问题
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
				$("#col-cs").empty();
				var optionstr = "<option value=\"请选择\">--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#col-cs").append(optionstr);
				$("#college").empty();
				$("#college").append(optionstr);
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
				$("#campus-cs").empty();
				var optionstr = "<option value=\"请选择\">--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#campus-cs").append(optionstr);
				$("#campus").empty();
				$("#campus").append(optionstr);
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
				$("#grade-cs").empty();
				var optionstr = "<option value=\"请选择\">--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#grade-cs").append(optionstr);
			}
		});
	}

	// 点击校区信息的下拉列表框
	$("#college").live("change",function() {
		if ($("#college option:selected").text() == "--请选择--") {
			$("#major").empty();
			var optionstr = "<option value=\"请选择\">--请选择--</option>";
			$("#major").append(optionstr);
		} else {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "college_getMajorByCol.action",
				data : "col="
						+ $("#college option:selected")
								.text(),
				success : function(data) {
					$("#major").empty();
					var optionstr = "<option value=\"请选择\">--请选择--</option>";
					if (!(data.length < 1)) {
						var optionstr = "<option value=\"请选择\">--请选择--</option>";
						$.each(data, function(index,
								Info) {
							optionstr += "<option>"
									+ Info
									+ "</option>";// 生成专业的select列表
						});
						$("#major").append(optionstr);
					}
				}
			});
		}			
	});
	// ajax函数，通过学院名称来获取所有专业
	function getMajorByCol() {
		if ($("#col-cs  option:selected").text() == "--请选择--") {
			$("#major-cs").empty();
			var optionstr = "<option value=\"请选择\">--请选择--</option>";
			$("#major-cs").append(optionstr);
		} else {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "college_getMajorByCol.action",
				data : "col=" + $("#col-cs option:selected").text(),
				beforeSend : function() {
					$("#queryresult").empty();
					;// 清空result中的内容
				},
				success : function(data) {
					$("#major-cs").empty();
					var optionstr = "<option value=\"请选择\">--请选择--</option>";
					if (!(data.length < 1)) {
						var optionstr = "<option value=\"请选择\">--请选择--</option>";
						$.each(data, function(index, Info) {
							optionstr += "<option>" + Info
									+ "</option>";// 生成专业的select列表
						});
						$("#major-cs").append(optionstr);
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

	function deleteClasses(data) {
		if (data != "")
			$.ajax({
				type : "post",
				dataType : "json",
				url : "class_deleteClasses.action",
				data : "idclses=" + data,
				success : function(data) {
					if (data == "true") {
						if (tag == false)
							accurateQuery();
						else
							fuzzyQuery();
					}
				}
			});
		else {
			sys_Alert("请选择要删除的项！");
		}
	}

	function currentTime() {
		var d = new Date(), str;
		str = d.getFullYear();
		return str;
	}

	function checklegal() {
		if ($("#classcount").val() == "" || $("#college").val() == "请选择"
				|| $("#grade").val() == "请选择" || $("#major").val() == "请选择"
				|| $("#campus").val() == "请选择") {
			//$("#dialog-modal").empty();
			//$("#dialog-modal").append("请填写完整的班级信息！");
			sys_Alert("请填写完整的班级信息！");
		} else {
			addClasses();
		}
	}

	function addClasses() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_addClasses.action",
			data : "addcampus=" + $("#campus option:selected").text()
					+ "&addgrade=" + $("#grade option:selected").text()
					+ "&addcol=" + $("#college option:selected").text()
					+ "&addmajor=" + $("#major option:selected").text()
					+ "&addclsnnum=" + $("#classcount").val(),
			success : function(data) {
				if (data == "true") {
					sys_Alert("班级添加成功！");
					/*if (tag == false){
						accurateQuery();
					}
					else{
						fuzzyQuery();
					}*/
				}
			}
		});
	}
	
	$(".ccampus").live("click", function() {
		var campus = $(this);
		var text = campus.find("option:selected").text();
		campus.empty();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "class_getAllCampus.action",
			data : null,
			success : function(data) {
				campus.empty();
				var optionstr = "<option>" + text + "</option>";
				$.each(data, function(index, Info) {
					if (Info != text)
						optionstr += "<option>" + Info + "</option>";
				});
				campus.append(optionstr);
			}
		});
	});

	$(".ccampus").live(
			"change",
			function() {
				var campus = $(this);
				ccampus = campus.find("option:selected").text();
				$.ajax({
					type : "post",
					dataType : "json",
					url : "class_editClass.action",
					data : "tempid=" + tempid + "&ccampus="
							+ campus.find("option:selected").text(),
					success : function(data) {
						if (tag == false)
							accurateQuery();
						else
							fuzzyQuery();
					}
				});
			});

	$("#tempid").live("click", function() {
		var $record = $(this).parent();
		var $idcls = $record.children(":first");
		tempid = $idcls.attr("id");
		ccampus = $record.children(":last").previous().text();
	});
});
