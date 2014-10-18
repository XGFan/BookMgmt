// JavaScript Document
var tag = true;// 定义一个标识符，来确定是模糊查询(true)还是精确查询(false)，默认是模糊查询(true)
var currentPage = 1; // 页索引
var totalPage = 1;// 总页数
var totalRecord = 1;// 总记录条数
$(function() {
	/*********************************1.与分页有关的事件及处理函数**************************************/
	// 第一页按钮click事件
	$("#first").click(function() {
		hideAddAlter();// 隐藏修改模块
		currentPage = 1;
		$("#lblCurentPage").text(1);
		if (tag == false)
			accurateQuery();
		else
			fuzzyQuery();
	});
	
	// 上一页按钮click事件
	$("#previous").click(function() {
		hideAddAlter();// 隐藏修改模块
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
		hideAddAlter();// 隐藏修改模块
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
		hideAddAlter();// 隐藏修改模块
		$("#lblCurentPage").text(totalPage);
		currentPage = totalPage;
		if (tag == false)
			accurateQuery();
		else
			fuzzyQuery();
	});

	// 模糊查询，根据学院或者专业或课程名
	function fuzzyQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_fuzzyQuery.action",
			data : "currentPage=" + currentPage + "&condition="
					+ $("#fuzzy-c").val() + "&col="
					+ $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text() + "&semester="
					+ $("#semester-c option:selected").text(),
			beforeSend : function() {
				$("#queryresult").empty();
			},
			success : function(data) {
				loadCourseData(data,"#queryresult");// 组织数据
				getFuzzyPagination();// 获取模糊查询的分页数据
			}
		});

	}
	
	// 获取模糊查询的分页信息
	function getFuzzyPagination() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_getfuzzyPagination.action",
			data : "condition=" + encodeURI($("#fuzzy-c").val()),
			success : function(data) {
				$.each(data, function(index, Info) {
					totalPage = Info["totalPage"];
					totalRecord = Info["totalRecord"];
				});
				$("#lblTotalPage").html(totalPage);
				$("#lblToatlRecord").html(totalRecord);
				bindPage();
			}
		});
	}

	// 获取某一条课程信息，通过学院、专业
	function accurateQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_accurateQuery.action",
			data : "currentPage=" + currentPage + "&col="
					+ $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text() + "&semester="
					+ $("#semester-c option:selected").text(),
			beforeSend : function() {
				$("#queryresult").empty();
			},
			success : function(data) {
				loadCourseData(data,"#queryresult");// 调用组织数据的方法
				getAccPagination();// 获取精确查询的分页数据
			}
		});
	}
	
	// AJAX方法获取后台分页对象的数据
	function getAccPagination() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_getAccPagination.action",
			data : "col=" + $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text() + "&semester="
					+ $("#semester-c option:selected").text(),
			beforeSend : function() {
				
			},
			success : function(data) {
				$.each(data, function(index, Info) {
					totalPage = Info["totalPage"];
					totalRecord = Info["totalRecord"];
				});
				$("#lblTotalPage").html(totalPage);
				$("#lblToatlRecord").html(totalRecord);
				bindPage();
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
		$("#last").attr("disabled",(currentPage == totalPage || $("#lblCurentPage").text() == "0") ? true
								: false);
	}
	
	/********************************2.与页面组件初始化等有关的处理函数**************************************/
	// 获取某一条专业信息，根据学院和专业，生成学期下拉列表
	function getMajorInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getColByColMajor.action",
			data : "col=" + $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text(),
			beforeSend : function() {
				//$("#queryresult").empty();
			},
			success : function(data) {
				loadSemester(data, "#semester-c", "NO");
			}
		});
	}
	
	/** 获取某一条专业信息，根据学院和专业，生成学期下拉列表 **/
	function getMajorInfo2(col, major, target, selectedSem) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getColByColMajor.action",
			data : "col=" + col + "&major=" + major,
			success : function(data) {
				loadSemester(data, target, selectedSem);
			}
		});
	}
	
	/** ajax函数，获取所有的学院名 **/
	function getAllCol() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getAllCol.action",
			data : null,
			beforeSend : function() {
				$("#queryresult").empty();
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
	
	/** ajax函数，获取所有的学院名，生成更改状态下的学院下拉列表 **/
	function getAllCol2(target, selectedCol) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getAllCol.action",
			data : null,
			success : function(data) {
				$(target).empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$(target).append(optionstr);
				// 将学期下拉列表的相应项设置为选中
				if(selectedCol!="NO"){
					var countDes = target+" option";
					var count = $(countDes).length;
					for (var i = 0; i < count; i++) {
						if ($(target).get(0).options[i].text == selectedCol) {
							$(target).get(0).options[i].selected = true;
							break;
						}
					}
					if(tag == false){
						var col = selectedCol;
						var selectedMajor = $("#major-c option:selected").text();
						getMajorByCol2(col, "#major-add", selectedMajor);
					}
				}
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
				$("#queryresult").empty();
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
					$("#major-c").append(optionstr);
				}
			}
		});
	}
	
	// ajax函数，通过学院名称来获取所有专业,生成更改状态下的专业下拉列表
	function getMajorByCol2(col, target, selectedMajor) {
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
						optionstr += "<option>" + Info + "</option>";// 生成专业的select列表
					});
					$(target).append(optionstr);
					// 将学期下拉列表的相应项设置为选中
					if(selectedMajor!="NO"){
						var countDes = target+" option";
						var count = $(countDes).length;
						for (var i = 0; i < count; i++) {
							if ($(target).get(0).options[i].text == selectedMajor) {
								$(target).get(0).options[i].selected = true;
								break;
							}
						}
						if(tag == false){
							var col = $("#col-add option:selected").text();
							var major = $("#major-add option:selected").text();
							var selectedSem = $("#semester-c option:selected").text();
							getMajorInfo2(col, major, "#semester-add", selectedSem);
						}
					}
				}
			}
		});
	}
	
	// 清空学院或者专业或者学期
	function clearData() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_clearData.action",
			data : "currentPage=" + currentPage + "&col="
					+ $("#col-c option:selected").text() + "&major="
					+ $("#major-c option:selected").text() + "&semester="
					+ $("#semester-c option:selected").text(),
			beforeSend : function() {
				$("#queryresult").empty();
			},
			success : function(data) {
				loadCourseData(data,"#queryresult");// 调用组织数据的方法
				getAccPagination();// 获取精确查询的分页数据
			}
		});
	}

	/** 隐藏添加和修改的对话框 **/
	function hideAddAlter() {
		$(".query").css("width", "100%");
		$(".query").css("display", "block");
		$(".alter").fadeOut(1000);
		$(".add").fadeOut(1000);
		$(".query").fadeIn(1000);
		$(".alter").css("display", "none");
		$(".add").css("display", "none");
	}
	
	/** 清除修改文本框的数据 **/
	function clearAlter() {
		$("#idcor-a").val("");
		$("#col-a option").selectedIndex = 0;
		$("#major-a option").selectedIndex = 0;
		$("#corname-a").val("");
		$("#semnum-a option").selectedIndex = 0;
	}
	
	/**显示信息**/
	function showInfo(content) {
		$("#dialog-view").dialog({
			width : 800,
			height : 500,
			modal : true,
			title : "教材列表",
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
	
	function showAddBookDiag(content) {
		$("#dialog-add").dialog({
			width : 500,
			height : 400,
			modal : true,
			title : "添加教材",
			hide : "slide",
			buttons : {
				'添加' : function() {
					var addResult = addNewBook();
					if(addResult == true){
						$(this).dialog("close");
					}
				},
				'取消' : function(){
					$(this).dialog("close");
				}
			},
			open : function(event, ui) {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}
	
	/** 弹出提示信息窗口 * */
	function sys_Alert(content) {
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
	
	/** 弹出提示信息窗口 * */
	function sys_Confirm(content ,sureCall ,params) {
		$("#dialog-delete").dialog({
			width : 450,
			height : 300,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				'确定' : function() {
					sureCall(params.idcor,params.corname);
					$(this).dialog("close");
				},
				'取消' : function() {
					$(this).dialog("close");
				}
			},
			open : function(event, ui) {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}
	
	/** 初始化学期列表 **/
	function initSemList() {
		$("#semester-c").empty();
		$("#semester-a").empty();
		var optionstr = "<option>--请选择--</option>";
		$("#semester-c").append(optionstr);
		$("#semester-a").append(optionstr);
	}

	/** 组织学期下拉列表框的数据,获取一个专业之后生成相应的学期数目 **/
	function loadSemester(data, target, selectedSem) {
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
			// 将学期下拉列表的相应项设置为选中
			if(selectedSem!="NO"){
				var countDes = target+" option";
				var count = $(countDes).length;
				for (var i = 0; i < count; i++) {
					if ($(target).get(0).options[i].text == selectedSem) {
						$(target).get(0).options[i].selected = true;
						break;
					}
				}
			}
			
		}
	}
	/*************************************3.与实际业务逻辑有关的函数*****************************************/
	/** 检查课程信息的各字段是否合法 * */
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
	
	function getBookInfoOfCourse(idcor) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_findByCourse.action",
			data : "idcor=" + idcor,
			beforeSend : function() {
				$("#checkresult").empty();
			},
			success : function(data) {
				loadBookData(data,"#checkresult");
				// getAccPagination(); //获取精确查询的分页数据
				// $(".result #pagination").css("display","none");
			}
		});
	}
	
	/** 初始化信息，打开页面时显示所有的学院专业 所有课程 **/
	function initCourseInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_list.action",
			data : null,
			beforeSend : function() {
				$("#queryresult").empty();
			},
			success : function(data) {
				loadCourseData(data,"#queryresult");// 调用组织数据的方法
				if(tag == true){
					getFuzzyPagination(); // 获取精确查询的分页数据
				}else{
					getAccPagination(); // 获取精确查询的分页数据
				}
				
			}
		});
	}
	
	/** 组织Ajax从服务器端返回的数据 **/
	function loadCourseData(data,target) {
		var strHTML = "<table id='resulttable'>";// 初始化内容变量
		if (data.length >= 1) {
			strHTML += "<tr align='center'><th>课程编号</th><th>学院</th><th>专业</th><th>课程名称</th><th>学期</th><th colspan='3'>操作</th></tr>";
			$.each(data,function(index, Info) {
				if (index % 2 == 0)// 实现奇数和偶数行的不同样式
					strHTML += "<tr class='trEven'><td width='10%' align='center'>"
							+ Info["idcor"] + "</td>";
				else
					strHTML += "<tr class='trOdd'><td width='10%' align='center'>"
							+ Info["idcor"] + "</td>";
				strHTML += "<td width='15%' align='center'>" + Info["col"]
						+ "</td>";
				strHTML += "<td width='22%' align='center'>" + Info["major"]
						+ "</td>";
				strHTML += "<td width='28%' align='left'>" + Info["corname"]
						+ "</td>";
				strHTML += "<td width='10%' align='center'>" + Info["semester"]
						+ "</td>";
				strHTML += "<td width='5%' id='viewbtn'><input class='btn' type='button' value='查看'/></td>";
				strHTML += "<td width='5%' id='tempidcor'><input class='btn' type='button' value='编辑'/></td>";
				strHTML += "<td width='5%' id='deletebtn'><input class='btn' type='button' value='删除'/></td></tr>";
			});
			$(".result #pagination").css("display", "block");// 显示分页
		} else {
			$(".result #pagination").css("display", "none");// 隐藏分页
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的课程信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$(target).html(strHTML);// 显示处理后的数据
	}
	
	/** 组织Ajax从服务器端返回的数据 **/
	function loadBookData(data,target) {
		var strHTML = "<table id='resulttable'>";
		if (data.length >= 1) {
			strHTML += "<tr width='1%' align='center'><th>全选<input type='checkbox' id='checkall' name='checkall'/></th><th>教材名称</th><th>作者</th><th>版本</th><th>出版社</th><th>ISBN</th></tr>";
			$.each(data,function(index, Info) {
				if (index % 2 == 0)
					strHTML += "<tr class='trEven'><td width='5%' align='center'><input type='checkbox' id='check' name='check' value='"+Info["idbk"]+"'/></td>";
				else
					strHTML += "<tr class='trOdd'><td width='5%' align='center'><input type='checkbox' id='check' name='check' value='"+Info["idbk"]+"'/></td>";
				strHTML += "<td width='35%' align='center'>"
						+ Info["bkname"] + "</td>";
				strHTML += "<td width='15%' align='center'>"
					+ Info["author"] + "</td>";
				strHTML += "<td width='10%' align='center'>"
					+ Info["edition"] + "</td>";
				strHTML += "<td width='20%' align='center'>"
						+ Info["publisher"] + "</td>";
				strHTML += "<td width='15%' align='center'>"
						+ Info["isbn"] + "</td></tr>";				
		  });
		} else {
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的教材信息，<br/>该课程未选用教材！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$(target).html(strHTML);
	}

	/** 添加新的课程 **/
	function addNewCourse() {
		var idbkStr = "";
		
		$("input[name='check']").each(function() { 
			// 遍历所有的name为check的checkbox
			
			if (this.checked) {
				idbkStr += $(this).val() + ",";
			}
		});
		idbkStr = idbkStr.substring(0,idbkStr.length-1);
		var col = $("#col-add").val();
		var major = $("#major-add").val();
		var corname = $("#corname-add").val();
		var semester = $("#semester-add").val();
		//是否有空项
		var valid = validateCourse(col, major, corname, semester);
		
		if (valid == false) {
			return false;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_addNewCourse.action",
			data : {
				"idbkStr" : idbkStr,
				"col" : col,
				"major" : major,
				"corname" : corname,
				"semester" : semester
			},
			beforeSend : function() {

			},
			success : function(data) {
				var resultTag = false;
				
				$.each(data, function(index, Info) {
					resultTag = Info["idcor"];
				});
				alert(resultTag);
				if (resultTag != "") {
					$("#checkresult").empty();
					$("#addbkresult").empty();
					$("#bkname-alter").val("");
					$("#bkname-add").val("");
					$("#corname-add").val("");
					showCourseInfo(col, major, resultTag, corname, semester, "课程'" + corname + "'添加成功！");
				} else{
					sys_Alert("添加课程--" + corname + "--失败！");
				}
			}
		});
	}

	function showCourseInfo(col, major, idcor, corname, semester, memo){
		var strHTML = "<p align='left'><font size='5' color='#ff0000'>"+memo+"<font><br/>";
		strHTML += "<font size='5' color='#0000ff'>"+col+"，"+major+"专业，第"+semester+"学期的课程<br/> '"+corname+"' 选用教材如下：</font>";
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_findByCourse.action",
			data : "idcor=" + idcor,
			success : function(data) {
					 strHTML += "<table id='resulttable'>";
					if (data.length >= 1) {
						strHTML += "<tr align='center'><th>教材名称</th><th>作者</th><th>版本</th><th>出版社</th><th>ISBN</th></tr>";
						$.each(data,function(index, Info) {
     						//if (index % 2 == 0)
							//	strHTML += "<tr class='trEven'><td width='5%' align='center'>"+Info["idbk"]+"</td>";
							//else
							//	strHTML += "<tr class='trOdd'><td width='5%' align='center'>"+Info["idbk"]+"</td>";
						   if (index % 2 == 0)
								strHTML += "<tr class='trEven'><td width='35%' align='left'>"+Info["bkname"]+"</td>";
							else
								strHTML += "<tr class='trOdd'><td width='35%' align='left'>"+Info["bkname"]+"</td>";							
							strHTML += "<td width='15%' align='center'>"
								+ Info["author"] + "</td>";
							strHTML += "<td width='10%' align='center'>"
								+ Info["edition"] + "</td>";
							strHTML += "<td width='20%' align='center'>"
									+ Info["publisher"] + "</td>";
							strHTML += "<td width='15%' align='center'>"
									+ Info["isbn"] + "</td></tr>";				
				     });
					} else {
						strHTML += "<tr><td><font size=\"5\" color=\"red\">"
								+ "未找到相应的教材信息，<br/>该课程未选用教材！" + "</font></td></tr>";
					}
					strHTML += "</table>";
					showInfo(strHTML);
			}
		});
	}
	/** 修改课程信息 **/
	function alterCourseInfo() {
		var idbkStr = "";
		$("input[name='check']").each(function() { 
			// 遍历所有的name为check的checkbox
			if (this.checked) {
				idbkStr += $(this).val() + ",";
			}
		});
		idbkStr = idbkStr.substring(0,idbkStr.length-1);
		var idcor = $("#idcor-a").val();
		var col = $("#col-a").val();
		var major = $("#major-a").val();
		var corname = $("#corname-a").val();
		var semester = $("#semester-a").val();
		
		var valid = validateCourse(col, major, corname, semester);
		if (valid == false) {
			return false;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_updateCourse.action",
			data : {"idbkStr":idbkStr,
				    "idcor":idcor, 
					"corname":corname,
					"semester":semester
			       },
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true) {
					$("#checkresult").empty();
					$("#addbkresult").empty();
					$("#bkname-alter").val("");
					$("#bkname-add").val("");
					showCourseInfo(col, major, idcor, corname, semester, "课程修改成功！");
				} else
					sys_Alert("修改失败！");
			}
		});
	}
	
	function deleteCourse(idcor,corname) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_deleteCourse.action",
			data : {
				"idcor" : idcor,
			},
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true) {
					sys_Alert("课程--" + corname + "--已删除！");
					if(tag == false){
						initPage(); // 初始化页码信息
						accurateQuery();
					}else{
						initPage(); // 初始化页码信息
						fuzzyQuery();
					}
				} else
					sys_Alert("课程--" + corname + "--删除失败，请重新操作！");
			}
		});
	}

	/** 根据教材名称查询教材信息，用于为课程选用教材 **/
	function checkBook(input,target) {
		var bkname = $(input).val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_findBookByBkname.action",
			data : {
				"bkname" : bkname
			},
			beforeSend : function() {
				
			},
			success : function(data) {
				loadBookData(data,target);// 调用组织数据的方法
			}
		});
	}

	// function addbkToCourse(){
	//  		
	// // var bkname = $("#add-c").val();
	//		 
	// $.ajax({
	// type: "post",
	// dataType: "json",
	// url: "book_searchAllBook.action",
	// data: null,
	// // beforeSend:function(){
	// //
	// // $("#queryresult").empty();;//清空result中的内容
	// // },
	// success: function(data){
	//		    	
	// loadcourseListData(data);//调用组织数据的方法
	// }
	// });
	// }

	/** 显示所有的教材信息 **/
	function initBookInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_findAllBook.action",
			data : null,
			success : function(data) {
				loadBookData(data,"#addbkresult");
			}
		});
	}
	
	/**添加新的教材，并关联选用该教材的课程，zhangchi,2014.4.5**/
	function addNewBook(){
		var idcorStr = "";
		var bkname = $("#bkname-b").val();
		var author = $("#author-b").val();
		var edition = $("#edition-b").val();
		var publisher = $("#publisher-b option:selected").text();
		var ISBN = $("#isbn-b").val();
		var price = $("#price-b").val();	
		//检查书本信息的输入
		var valid = checkInputInfo(bkname,author,publisher);
		if(valid == true){
			$.ajax({
				type : "post",
				dataType : "json",
				url : "book_insert.action",
				data : {
					"idcorStr": idcorStr,
					"bkname" : bkname,
					"author" : author,
					"edition" : edition,
					"publisher" : publisher,
					"ISBN" : ISBN,
					"price" : price
				},
				beforeSend : function() {
					
				},
				success : function() {
					sys_Alert("教材添加成功！");
				}
			});
			return true;
		}else{
			return false;
		}
	}
	
	/** 检测用户是否输入了书本基本信息，名称、作者、出版社为必须选择的。**/
	function checkInputInfo(bkname,author,publisher){
		/* 这里如果使用正则表达式效率更高一点，有待学习 */
		if ((bkname == "----全部----") || (bkname == "--请选择--") || (bkname == "")) {
			sys_Alert("请输入教材名称！");
			return false;
		}
		if ((author == "----全部----") || (author == "--请选择--") || (author == "")) {
			sys_Alert("请输入作者！");
			return false;
		}
		if ((publisher == "----全部----") || (publisher == "--请选择--")
				|| (publisher == "")) {
			sys_Alert("请选择出版社！");
			return false;
		}
		return true;
	}
	
	/**加载出版社信息**/
	function initPublisherInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_findAllPub.action",
			data : null,
			beforeSend : function() {
				// $("#queryresult").empty();;
			},
			success : function(data) {
				$("#publisher-b").empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info["publisher"] + "</option>";
				});
				$("#publisher-b").append(optionstr);
			}
		});
	}
	/***********************************4.页面组件的事件注册及处理******************************************/
	
	/*************页面打开时执行的函数*************/
	// 页面打开时读入所有记录
	initCourseInfo();
	// 精确查询页面打开时，读入所有的学院到学院下拉列表
	getAllCol();
	/*******************************************/


	/** 点击编辑之后将信息输出到右侧修改栏目中 **/
	$("#tempidcor").live("click", function() {
		// 获取到当前点击的课程的节点
		var $record = $(this).parent();
		/* 获取idcor节点 */
		var $idcor = $record.children(":first");
		/* 获取学院节点 */
		var $col = $idcor.next();
		var $major = $col.next();
		var $corname = $major.next();
		var $semester = $corname.next();
		
		$("#col-a").empty();
		$("#major-a").empty();
		$("#addbkresult").empty();
		/* 填充相应的值 */
		$("#idcor-a").attr("value", $idcor.text());
		$("#col-a").attr("value", $col.text());
		$("#major-a").attr("value", $major.text());
		$("#corname-a").attr("value", $corname.text());
		//置学院、专业为不可编辑状态
		$("#col-a").attr("disabled", "true");
		$("#major-a").attr("disabled", "true");
		/* 生成学期列表 */
		var col = $("#col-a").val();
		var major = $("#major-a").val();
		//加载学期下拉列表
		getMajorInfo2(col, major, "#semester-a", $semester.text());

		//修改模块显示
		$(".query").css("display", "none");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		$(".alter").css("left", "0%");
		$(".alter").css("width", "100%");
		$(".alter").fadeIn(1000);

		//载入该课程选用的教材
		var idcor = $idcor.text();
		getBookInfoOfCourse(idcor);
		$("#bkname-alter").val("");
	});
	
	$("#deletebtn").live("click", function() {
		var $record = $(this).parent();
		/* 获取idcor节点 */
		var $idcor = $record.children(":first");
		/* 获取学院节点 */
		var $col = $idcor.next();
		var $major = $col.next();
		var $corname = $major.next();
		var $semester = $corname.next();
		
		var col = $col.text();
		var major = $major.text();
		var idcor = $idcor.text();
		var corname = $corname.text();
		var semester = $semester.text();
		var tips = "确认删除<font size='5' color='blue'>" + col + "," + major + "专业，第" + semester;
		tips += "学期的课程</font>'<font color='red' size='5'>" + corname + "</font>'吗？";
		var param={'idcor':idcor,'corname':corname};
		sys_Confirm(tips,deleteCourse,param);
	});
	
	$("#viewbtn").live("click", function(){
		var $record = $(this).parent();
		/* 获取idcor节点 */
		var $idcor = $record.children(":first");
		/* 获取学院节点 */
		var $col = $idcor.next();
		var $major = $col.next();
		var $corname = $major.next();
		var $semester = $corname.next();
		
		var col = $col.text();
		var major = $major.text();
		var idcor = $idcor.text();
		var corname = $corname.text();
		var semester = $semester.text();
		
		showCourseInfo(col, major, idcor, corname, semester, "");
	});
	
	/** 注册修改按钮的事件 **/
	$("#alterbtn").click(function() {
		alterCourseInfo();// 调用修改课程信息的函数
	});
	
	$(":checkbox").live("click", function() {
		var name = $(this).attr("id");
		if (name == "checkall") {
			if (this.checked) {
				$("input[name='check']").each(function() { // 遍历所有的name为check的checkbox
					$(this).attr("checked", true);
				});
			} else { // 反之 取消全选
				$("input[name='check']").each(function() { // 遍历所有的name为check的checkbox
					$(this).attr("checked", false);
				});
			}
		} else {
			var flag = true;
			idbk = "";
			$("input[name='check']").each(function() { // 遍历所有的name为check的checkbox
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

	/** 添加新的课程信息 **/
	$("#add").click(function() {
		if(tag == true){
			//获取学院列表
			getAllCol2("#col-add", "NO");
			getMajorByCol2("","#major-add", "NO");
		}else{
			var selectedCol = $("#col-c option:selected").text();
			getAllCol2("#col-add", selectedCol);
		}
		/* 隐藏修改模块 */
		$(".query").css("display", "none");
		$(".alter").css("display", "none");
		$(".add").css("left", "0%");
		$(".add").css("width", "100%");
		$(".add").fadeIn(1000);
		$("#checkreuslt").empty();
		$("#corname-add").val("");
		$("#bkname-add").val("");
		$("#addbkresult").empty();
		
		
		//initBookInfo();
		//该语句已屏蔽，等用户搜索之后自动添加
	});
	
	$("#col-add").click(function() {
		var col = $("#col-add option:selected").text();
		getMajorByCol2(col, "#major-add", "NO");
	});
	
	$("#major-add").click(function() {
		var col = $("#col-add").val();
		var major = $("#major-add").val();
		var sem = "NO";
		if(tag == false){
			sem = $("#semester-c option:selected").text();
		}
		getMajorInfo2(col, major, "#semester-add", sem);
	});
	
	$("#addbtn").click(function() { // 点击添加按钮，添加新的课程及对应的教材		
		addNewCourse();
	});
	
	/** 注册返回按钮点击事件 **/
	$("#returnbtn").bind("click", function() {
		$(".query").css("display", "block");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		$(".alter").fadeOut(1000);	
		if(tag == false){
			initPage(); // 初始化页码信息
			accurateQuery();
		}else{
			initPage(); // 初始化页码信息
			fuzzyQuery();
		}
	});
	/** 注册返回按钮点击事件 **/
	$("#returnbtn-add").bind("click", function() {
		$(".query").css("display", "block");
		/* 隐藏添加模块 */
		$(".alter").css("display", "none");
		$(".add").fadeOut(1000);	
		if(tag == false){
			initPage(); // 初始化页码信息
			accurateQuery();
		}else{
			initPage(); // 初始化页码信息
			fuzzyQuery();
		}
	});
	
	/** 查询按钮点击事件 **/
	$("#querybtn").click(function() {
		hideAddAlter();// 隐藏修改模块
		// 点击查询之后就是重新开始，因此当前页置1
		initPage(); // 初始化页码信息
		tag = true;// 模糊查询
		fuzzyQuery(); // 调用模糊查询
		clearAlter();
	});
	
	$("#col-c").click(function() {
		hideAddAlter();// 隐藏修改栏
		tag = false; // 点击了学院之后就变成精确查询
	});
	
	$("#col-c").bind('change', function() {
		hideAddAlter();// 隐藏修改模块
		initPage(); // 初始化页码信息
		tag = false; // 点击之后就变成精确查询
		initSemList();// 清除学期列表
		clearAlter();// 清除修改修改框数据
		$("#major-c").empty();
		getMajorByCol();// 根据学院生成专业下拉列表
		accurateQuery();// 获取课程信息
	});
	
	$("#major-c").bind('change', function() {
		hideAddAlter();// 隐藏修改模块
		initPage(); // 初始化页码信息
		tag = false; // 点击了之后就变成精确查询
		getMajorInfo();// 根据学院专业来生成其学期列表
		/* 由于在点击编辑时生成学期下拉列表太慢，故在此一并生成 */
		//var col = $("#col-c").val();
		//var major = $("#major-c").val();
		//getMajorInfo2(col, major, "#semester-a", "NO");
		//getMajorInfo2(col, major, "#semester-add", "NO");
		clearAlter();// 清除修改修改框数据
		accurateQuery();// 获取课程信息
	});
	
	$("#semester-c").bind('change', function() {
		hideAddAlter();// 隐藏修改模块
		initPage(); // 初始化页码信息
		tag = false; // 点击了之后就变成精确查询
		clearAlter();// 清除修改修改框数据
		accurateQuery();// 获取课程信息
	});
	
	$("#fuzzy-c").bind('change click', function() {
		hideAddAlter();// 隐藏修改模块
		tag = true;// 模糊查询
	});
	
	$("#fuzzy-c").bind('change', function() {
		hideAddAlter();// 隐藏修改模块
		tag = true;// 模糊查询
		initPage(); // 初始化页码信息
		fuzzyQuery();
	});

	/** 添加教材，查询课程名称 **/
	$("#search-add").click(function() {
		checkBook("#bkname-add","#addbkresult");// 添加教材，查询课程
	});
	$("#search-alter").click(function() {
		checkBook("#bkname-alter","#checkresult");// 添加教材，查询课程
	});
	
	$("#addbkBtn-add").bind("click", function(){
		var content = "<table><tr><td width='120px'>教材名称：</td>";
		content += "<td align='right'><input type='text' id='bkname-b' /></td></tr>";
		content += "<tr><td>作者：</td><td align='right'><input type='text' id='author-b' /></td></tr>";
		content += "<tr><td>版本：</td><td align='right'><input type='text' id='edition-b' /></td></tr>";				
		content += "<tr><td>出版社：</td><td align='right'><select id='publisher-b' /></td></tr>";
		content += "<tr><td>ISBN：</td><td align='right'><input type='text' id='isbn-b' /></td></tr>";
		content += "<tr><td>价格：</td><td align='right'><input type='text' id='price-b' /></td></tr></table>";
		showAddBookDiag(content);
		initPublisherInfo();
	});
	
	$("#addbkBtn-alter").bind("click", function(){
		var content = "<table><tr><td width='120px'>教材名称：</td>";
		content += "<td align='right'><input type='text' id='bkname-b' /></td></tr>";
		content += "<tr><td>作者：</td><td align='right'><input type='text' id='author-b' /></td></tr>";
		content += "<tr><td>版本：</td><td align='right'><input type='text' id='edition-b' /></td></tr>";				
		content += "<tr><td>出版社：</td><td align='right'><select id='publisher-b' /></td></tr>";
		content += "<tr><td>ISBN：</td><td align='right'><input type='text' id='isbn-b' /></td></tr>";
		content += "<tr><td>价格：</td><td align='right'><input type='text' id='price-b' /></td></tr></table>";
		showAddBookDiag(content);
		initPublisherInfo();
	});
	
	$("#tabs-1a").click(function() {
		tag = true; // 模糊查询
		$("#fuzzy-c").val("");// 清空查询条件
		initPage(); // 初始化页码信息
		initCourseInfo();
	});
	
	$("#tabs-2a").click(function() {
		tag = false; // 精确查询
		initPage(); // 初始化页码信息
		initCourseInfo();
		getAllCol();
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
	});
	
});
