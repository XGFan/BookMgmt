// JavaScript Document
$(function() {
	/** ************************函数，Ajax函数*************************************** */
	// 模糊查询，根据学院或者专业或课程名，查询课程信息
	function fuzzyQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_fuzzyQueryByCorname.action",
			data : "corname=" + $("#fuzzy-c").val() + "&col="
					+ $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text(),
			beforeSend : function() {
				$("#course").empty();
			},
			success : function(data) {
				loadCourseData(data);
			}
		});
	}
	
	// 获取某一条课程信息，通过学院、专业、学期
	function accurateCourseQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "corplan_accurateCourseQuery.action",
			data : "col=" + $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text() + "&semester="
					+ $("#semester-c option:selected").text(),
			beforeSend : function() {
				$("#course").empty();
			},
			success : function(data) {
				loadCourseData(data);
			}
		});
	}
	
	// 获取某一条教学计划信息，通过学院、专业 学期
	function accurateCorplanQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "corplan_accurateCorplanQuery.action",
			data : "col=" + $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text() + "&semester="
					+ $("#semester-c option:selected").text(),
			beforeSend : function() {
				$("#corplan").empty();
			},
			success : function(data) {
				loadCorplanData(data);
			}
		});
	}
	
	// 获取某一条专业信息，根据学院和专业，生成学期下拉列表
	function getMajorInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getColByColMajor.action",
			data : "col=" + $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text(),
			beforeSend : function() {
				$("#course").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				loadSemester(data, "#semester-c");

			}
		});
	}
	
	// 获取某一条专业信息，根据学院和专业，生成学期下拉列表
	function getMajorInfo2(col, major, target) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getColByColMajor.action",
			data : "col=" + col + "&major=" + major,
			success : function(data) {
				loadSemester(data, target);
			}
		});
	}
	
	// 初始化信息，打开页面时显示所有的学院专业
	function initInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "corplan_list.action",
			data : null,
			beforeSend : function() {
				$("#course").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				loadCourseData(data);// 调用组织数据的方法
			}
		});
	}
	
	// ajax函数，获取所有的学院名
	function getAllCol() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getAllCol.action",
			data : null,
			beforeSend : function() {
				$("#course").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				$("#col-c").empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#col-c").append(optionstr);
			}
		});
	}
	
	// ajax函数，获取所有的学院名，生成更新课程信息状态下的学院下拉列表
	function getAllCol2(target) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getAllCol.action",
			data : null,
			success : function(data) {
				$(target).empty();
				var optionstr = "";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$(target).append(optionstr);
			}
		});
	}
	
	// ajax函数，通过学院名称来获取所有专业
	function getMajorByCol() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getMajorByCol.action",
			data : "col=" + $("#col-c option:selected").text(),
			beforeSend : function() {
				// sys_Alert($("#col-c option:selected").text()+"ok");
				$("#course").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				$("#major-c").empty();
				if (data.length < 1) {
					$("#major-c").append("<option>----全部----</option>");
				} else {
					var optionstr = "<option>--请选择--</option>";
					$.each(data, function(index, Info) {
						optionstr += "<option>" + Info + "</option>";// 生成专业的select列表
					});
					accurateCourseQuery();// 处理分页
					$("#major-c").append(optionstr);
					loadData(data);
				}
			}
		});
	}
	
	// ajax函数，通过学院名称来获取所有专业,生成更新课程信息时的专业下拉列表
	function getMajorByCol2(col, target) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getMajorByCol.action",
			data : "col=" + col,
			beforeSend : function() {
				$(target).empty();
			},
			success : function(data) {
				$(target).empty();
				if (data.length < 1) {
					$("#major-c").append("<option>----全部----</option>");
				} else {
					var optionstr = "<option>--请选择--</option>";
					$.each(data, function(index, Info) {
						optionstr += "<option>" + Info["major"] + "</option>";// 生成专业的select列表
					});
					$(target).append(optionstr);
				}
			}
		});
	}
	
	// 组织Ajax从服务器端返回的数据
	function loadCourseData(data) {
		if (data.length >= 1) {
			$("#course").empty();// 清空课程列表
			var optionstr = '';
			$.each(data, function(index, Info) {
				optionstr += "<option value='" + Info["idcor"] + "'>"
						+ Info["corname"] + "</option>";// 生成专业的select列表
			});
			$("#course").append(optionstr);
		} 
	}
	
	// 组织Ajax从服务器端返回的数据
	function loadCorplanData(data) {
		if (data.length >= 1) {
			var optionstr = '';
			$("#corplan").empty();// 清空课程列表
			$.each(data, function(index, Info) {
				optionstr += "<option value='" + Info["idcor"] + "'>"
						+ Info["corname"] + "</option>";// 生成专业的select列表
			});
			$("#corplan").append(optionstr);
		} 
	}
	
	/** ************************按钮等的处理事件函数*************************************** */
	// 清除修改文本框的数据
	function clearAlter() {
		$("#idcor-a").val("");
		$("#col-a").val("");
		$("#major-a").val("");
		$("#corname-a").val("");
		$("#semnum-a").val("");
	}
	/* 清空corplan数据 */
	function clearCorplan() {
		$("#corplan").empty();
	}
	/* 清空函数，用于清空一些数据 */
	function clear() {
		clearAlter();
		clearCorplan();
	}
	// 初始化学期列表
	function initSemList() {
		$("#semester-c").empty();
		var optionstr = "<option>--请选择--</option>";
		$("#semester-c").append(optionstr);
	}
	// 组织学期下拉列表框的数据,获取一个专业之后生成相应的学期数目
	function loadSemester(data, target) {
		$(target).empty();
		if (data.length < 1) {
			$(target).append("<option>--请选择--</option>");
		} else {
			var optionstr = "<option>--请选择--</option>";
			var i = 0;// 用于保存学制
			$.each(data, function(index, Info) {
				i = Info["semnum"];
			});
			for (var j = 1; j <= i; j++) {
				optionstr = optionstr + "<option>" + j + "</option>";
			}
			$(target).append(optionstr);
		}
	}

	/* 将一个列表框中的数据复制到另外一个列表框中，若有重复，则不复制 */
	function listToList(fromid, toid, isAll) {
		var col = $("#col-c").val();
		var major = $("#major-c").val();
		var corname = "";
		var semester = $("#semester-c").val();
		if ($("#col-c option:selected").text() == '----全部----') {
			sys_Alert('请选择学院');
			return false;
		}

		if ($("#major-c option:selected").text() == '--请选择--') {
			sys_Alert('请选择专业');
			return false;
		}

		if ($("#semester-c option:selected").text() == '--请选择--') {
			sys_Alert('请选择一个学期然后进行操作');
			return false;
		}
		if (isAll == true) {
			$("#" + fromid + " option").each(
					function() {
						var value = $(this).val();
						var text = $(this).text();
						var tag = true;
						// 如果corplan中不包含这门课程，才可以添加
						if ($("#" + toid + ":not(:has(option[value=" + value
								+ "]))").length > 0) {
							tag = false;
						}
						if (tag == false) {
							var str = "<option value='" + value + "'>" + text
									+ "</option>";
							$("#" + toid + ":not(:has(option[value="
									+ value + "]))").append(str);
						}
						if (tag == true) {
							sys_Alert("课程**" + text + "**已存在！");
						}
					});
		} else if (isAll == false) {
			if ($("#" + fromid + " option:selected").length == 0) {
				sys_Alert('请选择一门课程！');
				return false;
			}
			$("#" + fromid + " option:selected").each(
					function() {
						var value = $(this).val();
						var text = $(this).text();
						var tag = true;
						// 如果corplan中不包含这门课程，才可以添加
						if ($("#" + toid + ":not(:has(option[value=" + value
								+ "]))").length > 0) {
							tag = false;
						}
						if (tag == false) {
							var str = "<option value='" + value + "'>" + text
									+ "</option>";
							$("#" + toid + ":not(:has(option[value="
									+ value + "]))").append(str);
							corname = text;
							addCorplan(col, major, corname, semester);
						}
						if (tag == true) {
							sys_Alert("课程**" + text + "**已存在！");
						}
					});
		}
	}
	/* 检查课程信息的各字段是否合法 */
	function validateCourse(col, major, corname, semester) {
		/* 这里如果使用正则表达式效率更高一点，有待学习 */
		if ((col == "----全部----") || (col == "--请选择--") || (col == "")) {
			sys_Alert("请选择学院");
			$("#col-add").focus();
			return false;
		}
		if ((major == "----全部----") || (major == "--请选择--") || (major == "")) {
			sys_Alert("请选择专业");
			$("#major-add").focus();
			return false;
		}
		if ((corname == "----全部----") || (corname == "--请选择--")
				|| (corname == "")) {
			sys_Alert("请输入课程名称");
			$("#corname-add").focus();
			return false;
		}
		if ((semester == "----全部----") || (semester == "--请选择--")
				|| (semester == "")) {
			sys_Alert("请选择学期");
			$("#semester-add").focus();
			return false;
		}
		return true;
	}
	
	/* 添加新的课程 */
	function addNewCourse() {
		var col = $("#col-add").val();
		var major = $("#major-add").val();
		var corname = $("#corname-add").val();
		var semester = $("#semester-add").val();
		var tag = validateCourse(col, major, corname, semester);
		if (tag == false) {
			return false;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_addNewCourse.action",
			data : {
				"col" : col,
				"major" : major,
				"corname" : corname,
				"semester" : semester
			},
			beforeSend : function() {

			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				if (tag == true) {
					sys_Alert("添加课程--" + corname + "--成功！");
					// 更新信息
					accurateCourseQuery();
					accurateCorplanQuery();
					hideAddAlter();// 隐藏修改栏
				} else
					sys_Alert("添加课程--" + corname + "--失败！");
			}
		});
	}
	
	/* 修改课程信息 */
	function alterCourseInfo() {
		var col = $("#col-a").val();
		var major = $("#major-a").val();
		var corname = $("#corname-a").val();
		var semester = $("#semester-a").val();
		var tag = validateCourse(col, major, corname, semester);
		if (tag == false) {
			return false;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_updateCourse.action",
			data : "idcor=" + encodeURI($("#idcor-a").val()) + "&col="
					+ encodeURI($("#col-a").val()) + "&major="
					+ encodeURI($("#major-a").val()) + "&corname="
					+ encodeURI($("#corname-a").val()) + "&semester="
					+ encodeURI($("#semester-a option:selected").text()),
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				if (tag == true) {
					sys_Alert("修改成功！");
					// 刷新显示信息
					accurateCourseQuery();
					accurateCorplanQuery();
					hideAddAlter();// 隐藏修改模块
				} else
					sys_Alert("修改失败！");
			}
		});
	}

	/* 添加新的教学计划，该课程不存在与专业的所有课程中，或者暂时未找到 */
	function addNewCorplan() {
		var col = $("#col-add").val();
		var major = $("#major-add").val();
		var corname = $("#corname-add").val();
		var semester = $("#semester-add").val();
		var tag = validateCourse(col, major, corname, semester);
		if (tag == false) {
			return false;
		}
		$.ajax({
			type : "post",
			url : "corplan_addNewCorplan.action",
			dataType : "json",
			data : {
				"col" : col,
				"major" : major,
				"corname" : corname,
				"semester" : semester
			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				if (tag == true) {
					sys_Alert("添加成功！");
					accurateCourseQuery();
					accurateCorplanQuery();
					hideAddAlter();// 隐藏修改模块
				} else
					sys_Alert("添加失败，请检查该课程是否存在于该学期教学计划中！");
			}
		});
	}
	
	function addCorplan(col, major, corname, semester) {
		var tag = validateCourse(col, major, corname, semester);
		if (tag == false) {
			return false;
		}
		$.ajax({
			type : "post",
			url : "corplan_addNewCorplan.action",
			dataType : "json",
			data : {
				"col" : col,
				"major" : major,
				"corname" : corname,
				"semester" : semester
			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				if (tag == true) {
					sys_Alert("添加成功！");
					accurateCorplanQuery();
					hideAddAlter();// 隐藏修改栏
				} else
					sys_Alert("添加失败，请检查该课程是否存在于该学期教学计划中！");
			}
		});
	}
	
	/* 删除教学计划 */
	function deleteCorplan(col, major, semester, idcor, corname) {
		var tag = validateCourse(col, major, corname, semester);
		if (tag == false) {
			return false;
		}
		$.ajax({
			type : "post",
			url : "corplan_deleteCorplan.action",
			dataType : "json",
			data : {
				"col" : col,
				"major" : major,
				"semester" : semester,
				"corname" : corname,
				"idcor" : idcor
			},
			beforeSend : function() {
				showMask();
			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				hideMask();
				if (tag == true) {
					sys_Alert("删除成功！");
					accurateCorplanQuery();
					hideAddAlter();// 隐藏修改模块
				} else
					sys_Alert("删除失败，请刷新后重新操作!");
			}
		});
	}
	
	/* 从教学计划列表框中删除教学一条或者多条教学计划 */
	function deleteCorplanFromList() {
		if ($("#corplan option:selected").length == 0) {
			sys_Alert('请选择一门课程！');
			return false;
		}
		var tips = "确认删除该教学计划吗？";
		sys_Confirm(tips);
	}
	
	/* 清空所有教学计划 */
	function dropAllCorplan() {
		$.ajax({
			type : "post",
			url : "corplan_dropAllCorplan.action",
			dataType : "json",
			data : null,
			beforeSend : function() {
				showMask();
			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				hideMask();
				if (tag == true)
					sys_Alert("所有教学计划已清空！");
				else
					sys_Alert("清空失败，请重新操作");
			}
		});
	}
	
	/* 初始化教学计划 */
	function initCorplan() {
		$.ajax({
			type : "post",
			url : "corplan_initCorplan.action",
			dataType : "json",
			data : null,
			beforeSend : function() {
				showMask();
			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				hideMask();
				if (tag == true)
					sys_Alert("教学计划初始化成功！");
				else
					sys_Alert("初始化失败，请重新操作");
			}
		});
	}

	/* 将选中的一门课程添加到教学计划中去 */
	function addToCorplan() {
		listToList("course", "corplan", false);
	}
	/* 将所有课程添加到教学计划中去 */
	function addAllCourse() {
		listToList("course", "corplan", true);
	}
	/* 隐藏添加和修改的对话框 */
	function hideAddAlter() {
		$(".query").css("width", "100%");
		$(".alter").fadeOut(1000);
		$(".add").fadeOut(1000);
		$(".alter").css("display", "none");
		$(".add").css("display", "none");
	}

	/* 显示出等待的图片 */
	function showMask() {
		$("#mask").css("display", "block");
	}
	/* 隐藏出等待的图片 */
	function hideMask() {
		$("#mask").css("display", "none");
	}
	
	// 弹出对话框的函数
	function sys_Alert(content) {// 弹出提示信息窗口
		$("#dialog-modal").dialog({
			width : 350,
			height : 250,
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

	// 删除教学计划确认对话框以及删除函数
	function sys_Confirm(content) {// 弹出信息询问窗口
		$("#dialog-delete").dialog({
			width : 350,
			height : 250,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				'删除' : function() {
					$(this).dialog("close");
					var col = $("#col-c option:selected").text();
					var major = $("#major-c option:selected").text();
					var semester = $("#semester-c option:selected").text();
					$("#corplan option:selected").each(function() {
						var idcor = $(this).val();
						var corname = $(this).text();
						deleteCorplan(col, major, semester, idcor, corname);
					});
				},
				'取消' : function() {
					$(this).dialog("close");
				}
			},
			open : function() {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}

	/** ****************************与页面逻辑有关的一些事件*********************************************** */
	// 页面打开时读入所有记录
	initInfo();
	// 精确查询页面打开时，读入所有的学院到学院下拉列表
	getAllCol();

	// 查询
	$("#querybtn").click(function() {
		// 点击查询之后就是重新开始，因此当前页置1
		hideAddAlter();// 隐藏修改栏
		tag = true;// 模糊查询
		clear();// 清空教学计划
		fuzzyQuery(); // 调用模糊查询

	});
	
	$("#col-c").click(function() {
		hideAddAlter();// 隐藏修改栏
		tag = false; // 点击了学院之后就变成精确查询
	});
	
	$("#col-c").bind('change', function() {
		hideAddAlter();// 隐藏修改栏
		tag = false; // 点击之后就变成精确查询
		initSemList();// 清除学期列表
		clear();// 清除修改修改框数据
		getMajorByCol();// 根据学院生成专业下拉列表
		accurateCourseQuery();// 获取课程信息
		var col = $("#col-c option:selected").text();
		if (col == '----全部----') {
			col = '湖北中医药大学';
		}
		// $("#selectState").html("左侧为--<font
		// color='red'>"+col+"</font>--的所有课程:");

		// alert($("#col-c option:selected").text());
	});
	
	/* 专业下拉列表框的改变事件 */
	$("#major-c").bind('change', function() {
		hideAddAlter();// 隐藏修改栏
		tag = false; // 点击了之后就变成精确查询
		getMajorInfo();// 根据学院专业来生成其学期列表
		clear();// 清除修改修改框数据
		accurateCourseQuery();// 获取课程信息
		var col = $("#col-c option:selected").text();
		var major = $("#major-c option:selected").text();
		if (col == '----全部----') {
			col = '湖北中医药大学';
		}
		if (major == '--请选择--') {
			major = '所有专业';
		}
		// $("#selectState").html("左侧为--<font color='red'>"+col+"</font>-><font
		// color='green'>"+major+"专业</font>--的所有课程:");
	});
	
	/* 学期下拉列表框的改变事件 */
	$("#semester-c").bind('change', function() {
		hideAddAlter();// 隐藏修改栏
		tag = false; // 点击了之后就变成精确查询
		clear();// 清除修改修改框数据
		// accurateCourseQuery();//获取课程信息
		accurateCorplanQuery();// 获取教学计划信息
		var col = $("#col-c option:selected").text();
		var major = $("#major-c option:selected").text();
		var semester = $("#semester-c option:selected").text();
		if (col == '----全部----') {
			col = '湖北中医药大学';
		}
		if (major == '--请选择--') {
			major = '所有专业';
		}
		if (semester == '--请选择--') {
			semester = '所有学期';
		} else {
			semester = '第' + semester + '学期';
		}
		// $("#selectState").html("右侧为--<font color='red'>"+col+"</font>-><font
		// color='green'>"+major+"专业</font>-><font
		// color='purple'>"+semester+"</font>--的所有课程:");
	});

	/* 标示为模糊查询 */
	$("#fuzzy-c").bind('change click', function() {
		hideAddAlter();// 隐藏修改栏
	});
	
	$("#fuzzy-c").bind('change', function() {
		hideAddAlter();// 隐藏修改栏
		fuzzyQuery();
		tag = true;// 模糊查询
	});
	
	/* 初始化所有教学计划 */
	$("#initCorplan").click(function() {
		initCorplan();
	});
	
	/* 清空所有教学计划 */
	$("#dropAllCorplan").click(function() {
		dropAllCorplan();
	});
	
	/* 为添加教学计划中的添加课程注册事件 */
	$("#addToCorplan").click(function() {
		addToCorplan();
	});
	
	/* 从教学计划中删除该课程 */
	$("#deleteCorplan").click(function() {
		deleteCorplanFromList();
	});
	
	// 注册修改按钮的事件
	$("#alterbtn").click(function() {
		alterCourseInfo();// 调用修改课程信息的函数
		// 刷新显示信息
	});
	
	/* 注册添加课程按钮的事件 */
	$("#addbtn").click(function() {
		addNewCorplan();
	});

});
