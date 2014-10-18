// JavaScript Document
var tag = true;// 定义一个标识符，来确定是模糊查询还是精确查询，默认是模糊查询(true)
var currentPage = 1; // 页索引
var totalPage = 1;// 总页数
var totalRecord = 1;// 总记录条数
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

	// AJAX方法取得数据并显示到页面上，精确查询的分页
	function accurateQuery() {
		$.ajax({
			type : "post", // 使用get方法访问后台
			dataType : "json", // 返回json格式的数据
			url : "college_accurateQuery.action", // 要访问的后台地址
			data : "currentPage=" + currentPage + "&col="
					+ encodeURI($("#col-c").val()) + "&major="
					+ encodeURI($("#major-c").val()), // 要发送的数据
			beforeSend : function() {
				$("#queryresult").empty();
				;// 清空result中的内容
			},
			complete : function() {
				$("#querystate").hide();
			}, // AJAX请求完成时隐藏loading提示
			success : function(data) {
				loadCollegeData(data);
			},
			error : function() {
				sys_Alert("加载数据失败");
			} // 加载失败，请求错误处理
		// ajaxStop:$("#load").hide()
		});
		getAccPagination();
		bindPage();
	}

	// AJAX方法获取后台分页对象的数据
	function getAccPagination() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_accPageQuery.action",
			data : "col=" + encodeURI($("#col-c").val()),
			success : function(data) {
				$.each(data, function(index, Info) {
					totalPage = Info["totalPage"];
					totalRecord = Info["totalRecord"];
				});
				$("#lblTotalPage").html(totalPage);
				$("#lblToatlRecord").html(totalRecord);

			}
		});
	}
	// 模糊查询，根据学院或者专业
	function fuzzyQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_fuzzyQuery.action",
			data : "currentPage=" + currentPage + "&condition="
					+ $("#fuzzy-c").val(),
			beforeSend : function() {
			},
			success : function(data) {
				loadCollegeData(data);
			}
		});
		getFuzzyPagination();
		bindPage();
	}
	// 获取模糊查询的分页信息
	function getFuzzyPagination() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_fuzzyPageQuery.action",
			data : "condition=" + encodeURI($("#fuzzy-c").val()),
			success : function(data) {
				$.each(data, function(index, Info) {
					totalPage = Info["totalPage"];
					totalRecord = Info["totalRecord"];
				});
				$("#lblTotalPage").html(totalPage);
				$("#lblToatlRecord").html(totalRecord);
			}
		});
	}
	// 初始化页码信息
	function initPage() {
		currentPage = 1; // 页索引
		totalPage = 1;// 总页数
		totalRecord = 1;// 总记录条数
	}

	// 页脚属性设置，每次查询之后将分页信息与页面元素绑定
	function bindPage() {
		// 填充分布控件信息
		// var totalPage = parseInt($("#lblTotalPage").text()); //总页数
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
	// 初始化信息，打开页面时显示所有的学院专业
	function initInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_list.action",
			data : null,
			beforeSend : function() {
				$("#queryresult").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				loadCollegeData(data);// 调用组织数据的方法
			}
		});
		getFuzzyPagination();
		bindPage();
	}
	// ajax函数，获取所有的学院名
	function getAllCol() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getAllCol.action",
			data : null,
			beforeSend : function() {
				$("#queryresult").empty();
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

	// 获取某一条专业信息，根据学院和专业
	function getColInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getColByColMajor.action",
			data : "col=" + $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text(),
			beforeSend : function() {
				$("#queryresult").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				loadCollegeData(data);
				$(".result #pagination").css("display", "none");// 隐藏分页
			}
		});
	}

	// 清除修改文本框的数据
	function clearAlter() {
		$("#idcm-a").val("");
		$("#col-a").val("");
		$("#major-a").val("");
		$("#semnum-a").val("");
	}
	
	//隐藏修改模块
	function hideAlter(){
		$(".alter").fadeOut(1000);
		$(".query").css("width","100%");
	}
	
	//添加专业
	function addMajor(){
		var col = $("#col-add").val();
		var major = $("#major-add").val();
		var semnum = $("#semnum-add").val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_addMajor.action",
			data : {
				"col":col,
				"major":major,
				"semnum":semnum,
			},
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true){
					sys_Alert("添加专业成功！");
					initInfo();
					hideAlter();
				}
				else
					sys_Alert("添加专业失败！");
			}
		});
	}
	
	//删除某专业
	function deleteCollege(idcm){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_deleteCollege.action",
			data : {
				"idcm":idcm
			},
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true){
					sys_Alert("该专业已删除！");
					if (tag == false)
						accurateQuery();
					else
						fuzzyQuery();
				}
				else
					sys_Alert("删除专业失败！");
			}
		});
	}

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
	
	// 弹出添加供应商的对话框
	function sys_Confirm(content, functionCall, idcm) {// 弹出信息询问窗口
		$("#dialog-modal").dialog({
			width : 450,
			height : 300,
			modal : true,
			title : "温馨提示",
			hide : "slide",
			buttons : {
				"删除" : function() {
					$(this).dialog("close");
					functionCall(idcm);
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
	
	// 弹出添加学院的对话框
	function showAddDiag(content) {
		$("#dialog-modal").dialog({
			width : 450,
			height : 300,
			modal : true,
			title : "添加新的专业",
			hide : "slide",
			buttons : {
				'添加' : function() {
					addMajor();
					$(this).dialog("close");
				},
				'取消' : function() {
					$(this).dialog("close");
				}
			},
			open : function() {
				$(this).html("");
				$(this).append(content);
			}
		});
	}
	// 组织Ajax从服务器端返回的数据
	function loadCollegeData(data) {
		var strHTML = "<table id='resulttable'>";// 初始化内容变量
		if (data.length >= 1) {
			strHTML += "<tr align='center'><th>专业编号</th><th>学院</th><th>专业名称</th><th>总学期</th><th colspan='2'>操作</th></tr>";
			$.each(data,function(index, Info) {
				if (index % 2 == 0)// 实现奇数和偶数行的不同样式
					strHTML += "<tr class='trEven'><td width='10%'>"
							+ Info["idcm"] + "</td>";
				else
					strHTML += "<tr class='trOdd'><td width='10%'>"
							+ Info["idcm"] + "</td>";
				strHTML += "<td width='25%'>" + Info["col"]
						+ "</td>";
				strHTML += "<td width='35%'>" + Info["major"]
						+ "</td>";
				strHTML += "<td width='10%' align='center'>" + Info["semnum"]
						+ "</td>";
				strHTML += "<td width='10%' id='alterTD' align='center'><input class='btn' type='button' value='编辑'/></td>";
				strHTML += "<td width='10%' id='deleteTD' align='center'><input class='btn' type='button' value='删除'/></td></tr>";
			});			
			$(".result #pagination").css("display", "block");// 显示分页
		} else {
			$(".result #pagination").css("display", "none");// 隐藏分页
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的专业信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$("#queryresult").html(strHTML);// 显示处理后的数据
	}

	/* 与业务逻辑相关的一些事件 */
	// 页面打开时读入所有记录
	initInfo();
	// 精确查询页面打开时，读入所有的学院到学院下拉列表
	getAllCol();

	// 查询
	$("#querybtn").click(function() {
		// 点击查询之后就是重新开始，因此当前页置1
		initPage(); // 初始化页码信息
		tag = true;// 模糊查询
		// initInfo(); //初始化显示所有专业信息
		fuzzyQuery(); // 调用模糊查询
		clearAlter();// 清空编辑框的信息

	});
	
	$("#cancelbtn").click(function(){
		hideAlter();
	});
	
	$("#col-a").click(function() {
		tag = false; // 点击了学院之后就变成精确查询
	});

	$("#fuzzy-c").focus();
	
	$("#querytabs").tabs({
		// 设置各选项卡在切换时的动画效果
		fx : {
			opacity : "toggle",
			height : "toggle"
		},
		// 通过鼠标单击事件切换选项卡
		event : "click"
	// 通过移动鼠标事件切换选项卡
	// event:"mousemove"
	});
	
	$("#col-c").bind('change', function() {
		tag = false;// 精确查询
		initPage();
		accurateQuery();
		clearAlter();// 清空编辑框的信息
		hideAlter();
	});
	
	$("#major-c").bind('change', function() {
		tag = false;// 精确查询
		initPage();
		getColInfo();
		clearAlter();// 清空编辑框的信息
		hideAlter();
	});
	
	$("#fuzzy-c").bind('change click', function() {
		initPage();
		tag = true;// 模糊查询
	});
	
	// 点击编辑之后将信息输出到右侧修改栏目中
	$("#alterTD").live("click", function() {
		var $record = $(this).parent();
		var $idcm = $record.children(":first");
		var $col = $idcm.next();
		var $major = $col.next();
		var $semnum = $major.next();
		$("#idcm-a").attr("value", $idcm.text());
		$("#col-a").attr("value", $col.text());
		$("#major-a").attr("value", $major.text());
		$("#semnum-a").attr("value", $semnum.text());

		$(".query").css("width", "70%");
		$(".alter").css("width", "30%");
		$(".alter").css("left", "70%");
		$(".alter").fadeIn(1000);
	});
	
	// 点击编辑之后将信息输出到右侧修改栏目中
	$("#deleteTD").live("click", function() {
		var $record = $(this).parent();
		var $idcm = $record.children(":first");
		var $col = $idcm.next();
		var $major = $col.next();
		var $semnum = $major.next();
		$("#idcm-a").attr("value", $idcm.text());
		$("#col-a").attr("value", $col.text());
		$("#major-a").attr("value", $major.text());
		$("#semnum-a").attr("value", $semnum.text());
		
		var tips = "<font size='5'>删除该专业将会一并删除与该专业有关的班级、课程、教学计划等信息。<br/>确认删除吗？</font>";
		sys_Confirm(tips, deleteCollege , $idcm.text());
	});

	// 注册修改按钮的事件
	$("#alterbtn").click(function() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_update.action",
			data : "idcm=" + encodeURI($("#idcm-a").val()) + "&col="
					+ encodeURI($("#col-a").val()) + "&major="
					+ encodeURI($("#major-a").val()) + "&semnum="
					+ encodeURI($("#semnum-a").val()),
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true){
					sys_Alert("修改成功！");
					initInfo();
					hideAlter();
				}
				else
					sys_Alert("修改失败！");
			}
		});
	});
	
	/**为添加按钮注册事件**/
	$("#addbtn").click(function() {
		$("#addform").css("display","block");
		var content = "<table><tr><td>学院</td><td><input type='text' id='col-add' /></td></tr><tr><td>专业名称</td>" +
				"<td><input type='text' id='major-add' /></td></tr><tr><td>学期</td>" +
				"<td><input type='text' id='semnum-add' /></td></tr></table>";
		showAddDiag(content);
	});
});
