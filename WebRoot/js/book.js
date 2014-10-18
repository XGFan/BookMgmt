// JavaScript Document
var currentPage = 1; // 页索引
var totalPage = 1;// 总页数
var totalRecord = 1;// 总记录条数
var tag = false;// 定义一个标识符，来确定是否精确查询，默认是精确查询
var AlterAllTag = false;// 定义一个标识符，来确定是否修改所有与教材的关联
var idcorStr ="";
var idcor = "";
$(function() {
	/***************************************与分页有关的事件、函数*********************************/
	// 第一页按钮click事件
	$("#first").click(function() {
		// hideAddAlter();//隐藏修改模块
		currentPage = 1;
		$("#lblCurentPage").text(1);
		// if(tag == false)
		accurateBookQuery();
		// else
		// fuzzyQuery();
	});
	
	// 上一页按钮click事件
	$("#previous").click(function() {
		// hideAddAlter();//隐藏修改模块
		if (currentPage >= 2) {
			currentPage--;
			$("#lblCurentPage").text(currentPage);
		}
		// if(tag == false)
		accurateBookQuery();
		/*
		 * else fuzzyQuery();
		 */
		if (currentPage == 1)
			sys_Alert('到顶啦！');
	});
	
	// 下一页按钮click事件
	$("#next").click(function() {
		// hideAddAlter();//隐藏修改模块
		if (currentPage < totalPage) {
			currentPage++;
			$("#lblCurentPage").text(currentPage);
		}
		// if(tag == false)
		accurateBookQuery();
		/*
		 * else fuzzyQuery();
		 */
		if (currentPage == totalPage)
			sys_Alert('到底啦！');
	});

	// 最后一页按钮click事件
	$("#last").click(function() {
		// hideAddAlter();//隐藏修改模块
		$("#lblCurentPage").text(totalPage);
		currentPage = totalPage;
		// if(tag == false)
		accurateBookQuery();
		/*
		 * else fuzzyQuery();
		 */
	});
	
	// 初始化页码信息
	function initPage() {
		currentPage = 1; // 页索引
		totalPage = 1;// 总页数
		totalRecord = 1;// 总记录条数
	}

	// 页脚属性设置，每次查询之后将分页信息与页面元素绑定
	function bindPage() {
		// 填充分页控件信息
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
				.attr("disabled",(currentPage == totalPage || $("#lblCurentPage").text() == "0") ? true
								: false);
	}
	
	// 获取精确查询的分页数据
	function getAccPagination() {
		var ISBN = $("#isbn-a").val();
		var bkname = $("#bkname-a").val();
		var publisher = $("#publisher-a").val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_getAccPagination.action",
			data : {
				"bkname" : bkname,
				"publisher" : publisher,
				"ISBN" : ISBN
			},
			beforeSend : function() {
				// $("#queryresult").empty();;//清空result中的内容
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
	
	// 获取模糊查询的分页信息
	function getFuzzyPagination() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_getfuzzyPagination.action",
			data : null,
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
	
	/***************************************与页面组件初始化有关的函数*********************************/
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
				$("#col-d").empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#col-d").append(optionstr);
			}
		});
	}

	// ajax函数，通过学院名称来获取所有专业,生成更改状态下的专业下拉列表
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
					$("#major-d").append("<option>----全部----</option>");
				} else {
					var optionstr = "<option>--请选择--</option>";
					$.each(data, function(index, Info) {
						optionstr += "<option>" + Info + "</option>";// 生成专业的select列表
					});
					// $(".result #pagination").css("display","block");//显示分页
					$(target).append(optionstr);
				}
			}
		});
	}
	
	// 获取某一条专业信息，根据学院和专业，生成学期下拉列表
	function getMajorInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "college_getColByColMajor.action",
			data : "col=" + $("#col-d option:selected").text() + "&major="
					+ $("#major-d option:selected").text(),
			beforeSend : function() {
				$("#queryresult").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				loadSemester(data, "#semester-d");
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
	
	/** 初始化学期列表 **/
	function initSemList() {
		$("#semester-d").empty();
		var optionstr = "<option>--请选择--</option>";
		$("#semester-d").append(optionstr);
	}
	
	/** 隐藏添加和修改的对话框 **/
	function hideAddAlter() {
		$(".query").css("width", "84%");
		$(".alter").fadeOut(1000);
		$(".add").fadeOut(1000);
		$(".alter").css("display", "none");
		$(".add").css("display", "none");
	}
	
	/** 组织学期下拉列表框的数据,获取一个专业之后生成相应的学期数目 **/
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
	
	/******************************与实际业务逻辑有关的函数**************************************/
	function accurateCourseQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "corplan_accurateCourseQuery.action",
			data : "col=" + $("#col-d option:selected").text() + "&major="
					+ $("#major-d option:selected").text() + "&semester="
					+ $("#semester-d option:selected").text(),
			beforeSend : function() {
				$("#course").empty();
			},
			success : function(data) {
				loadCourseData(data,"#addresult");
			}
		});
	}

	// 精确查询教材信息，@params,isbn,bkname,publisher
	function accurateBookQuery() {
		var ISBN = $("#isbn-a").val();
		var bkname = $("#bkname-a").val();
		var publisher = $("#publisher-a").val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_accurateBookQuery.action",
			data : {
				"currentPage" : currentPage,
				"bkname" : bkname,
				"publisher" : publisher,
				"ISBN" : ISBN
			},
			beforeSend : function() {
				$("#queryresult").empty();
				;// 清空result中的内容
			},
			success : function(data) {
				loadBookData(data);// 调用组织数据的方法
				getAccPagination();// 获取精确查询的分页数据
			}
		});
	}


	/** 删除一条教材记录 **/
	function deleteBook(idbk) {
		// 获取到当前点击的教材的节点
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_deleteBook.action",
			data : {
				"idbk" : idbk
			},
			beforeSend : function() {
				// $("#queryresult").empty();;//清空result中的内容
			},
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true) {
					sys_Alert("教材已经删除！");
					accurateBookQuery();
				} else{
					sys_Alert("删除失败，请重新再试！");
				}
			}
		});

	}
	
	/** 初始化加载所有教材信息 **/
	function initBookInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_searchAllBook.action",
			data : null,
			beforeSend : function() {
				$("#queryresult").empty();
			},
			success : function(data) {
				loadBookData(data);
				getAccPagination(); // 获取精确查询的分页数据
				// $(".result #pagination").css("display","none");
			}
		});

	}
	
	/**  载入教材信息 **/
	function loadBookData(data) {
		var strHTML = "<table id='resulttable'>";
		if (data.length >= 1) {
			strHTML += "<tr align='center'><th>教材编号</th><th>教材名称</th><th>作者</th><th>版本</th><th>出版社</th><th>ISBN</th><th>价格</th><th colspan='3'>操作</th></tr>";
			$.each(data,function(index, Info) {
				if (index % 2 == 0)
					strHTML += "<tr class='trEven'><td width='12%' align='center'>"
							+ Info["idbk"] + "</td>";
				else
					strHTML += "<tr class='trOdd'><td width='12%' align='center'>"
							+ Info["idbk"] + "</td>";
				strHTML += "<td width='28%' align='left'>" + Info["bkname"]
						+ "</td>";
				strHTML += "<td width='12%' align='center'>" + Info["author"]
						+ "</td>";
				strHTML += "<td width='5%' align='center'>" + Info["edition"]
						+ "</td>";
				strHTML += "<td width='12%' align='center'>" + Info["publisher"]
						+ "</td>";
				strHTML += "<td width='8%' align='center'>" + Info["isbn"]
						+ "</td>";
				strHTML += "<td width='8%' align='center'>" + Info["price"]
						+ "</td>";
				strHTML += "<td width='5%' id='viewbook' align='center'><input  class='btn' type='button' value='查看' /></td>";
				strHTML += "<td width='5%' id='tempidbook' align='center'><input  class='btn' type='button' value='编辑' /></td>";
				strHTML += "<td width='5%' id='deleteidbook' align='center'><input  class='btn' type='button' value='删除' /></td></tr>";
			});
			$(".result #pagination").css("display", "block");// 显示分页
		} else {
			$(".result #pagination").css("display","none");
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的教材信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$("#queryresult").html(strHTML);
	}
	
	/** 将服务器端返回的课程数据(JSON)组织后显示到特定的Dom元素上面 **/
	function loadCourseData(data,target) {
		//标识是否全选课程
		AlterAllTag = false;
		var strHTML = "<table id='resulttable'>";
		if (data.length >= 1) {
			strHTML += "<tr align='center'><th>全选<input type='checkbox' id='checkall' name='checkall'/></th><th>课程编号</th><th>课程名称</th><th>院系</th><th>专业</th><th>学期</th></tr>";
			$.each(data,function(index, Info) {
				if (index % 2 == 0)
					strHTML += "<tr class='trEven'><td align='center' width='5%'><input type='checkbox' id='check' name='check' value='"+Info["idcor"]+"'/></td>";
				else
					strHTML += "<tr class='trOdd'><td align='center' width='5%'><input type='checkbox' id='check' name='check' value='"+Info["idcor"]+"'/></td>";
				strHTML += "<td width='12%' align='center'>"
						+ Info["idcor"] + "</td>";
				strHTML += "<td width='35%' align='center'>"
						+ Info["corname"] + "</td>";
				strHTML += "<td width='18%' align='center'>"
						+ Info["col"] + "</td>";
				strHTML += "<td width='22%' align='center'>"
						+ Info["major"] + "</td>";
				strHTML += "<td width='8%' align='center'>"
						+ Info["semester"] + "</td></tr>";
			});
			// $(".result #pagination").css("display","block");//显示分页
		} else {
			// $(".result #pagination").css("display","none");
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的课程信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$(target).html(strHTML);
	}

	/** 修改教材信息 2014-4-7,zhangchi**/
	function alterBookInfo(idbk,bkname,author,edition,publisher,ISBN,price){
	    idcorStr = "";
		$("input[name='check']").each(function() { 
			// 遍历所有的name为check的checkbox
			if (this.checked) {
				idcorStr += $(this).val() + ",";
			}
		});
		idcorStr = idcorStr.substring(0,idcorStr.length-1);
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_insert.action",
			data : {
				"idcorStr" : idcorStr,
				"idbk" : idbk,
				"bkname" : bkname,
				"author" : author,
				"edition" : edition,
				"publisher" : publisher,
				"ISBN" : ISBN,
				"price" : price
			},
			beforeSend : function() {
				// $("#queryresult").empty();;//清空result中的内容
			},
			success : function() {
				sys_Alert("修改成功！");
			}
		});
	}
	
	/**添加新的教材，并关联选用该教材的课程，zhangchi,2014.4.5**/
	function addNewBook(){
		idcorStr = "";
		$("input[name='check']").each(function() { 
			// 遍历所有的name为check的checkbox
			if (this.checked) {
				idcorStr += $(this).val() + ",";
			}
		});
		idcorStr = idcorStr.substring(0,idcorStr.length-1);
		
		var bkname = $("#bkname-c").val();
		var author = $("#author-c").val();
		var edition = $("#edition-c").val();
		var publisher = $("#publisher-c option:selected").text();
		var ISBN = $("#isbn-c").val();
		var price = $("#price-c").val();	
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
					//清理输入框
					$("#bkname-c").val("");
					$("#author-c").val("");
					$("#edition-c").val("");
					$('#publisher-c option:first').attr('selected','selected');
					$("#isbn-c").val("");
					$("#price-c").val("");
					$("#check-c").val("");
				}
			});
		}
	}
	
	/** 选中所有课程，则更改所有选用这本教材的课程 **/
	function alterAll(idbk,bkname,author,edition,publisher,ISBN,price) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_modifyAll.action",
			data : {
				"idbk" : idbk,
				"bkname" : bkname,
				"author" : author,
				"edition" : edition,
				"publisher" : publisher,
				"ISBN" : ISBN,
				"price" : price
			},
			success : function(data) {
				var resultTag = false;
				$.each(data, function(index, Info) {
					resultTag = Info["result"];
				});
				if (resultTag == true) {
					sys_Alert("修改成功！");
				}
			}
		});

	}
	
	/** 根据idbk获取选用该教材的课程信息 **/
	function loadCourseByBook(value) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_findCourseByIdbk.action",
			data : "idbk=" + value,
			beforeSend : function() {
				$("#checkresult").empty();
			},
			success : function(data) {
				loadCourseData(data,"#checkresult");
			}
		});
	}
	
	/** 显示所有的课程信息 **/
	function initCourseInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_findAll.action",
			data : null,
			success : function(data) {
				loadCourseData(data,"#addresult");
			}
		});
	}
	
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
				$("#publisher-c").empty();
				$("#publisher-b").empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info["publisher"] + "</option>";
				});
				$("#publisher-c").append(optionstr);
				$("#publisher-b").append(optionstr);
			}
		});
	}

	/**添加教材时搜索课程的函数**/
	function checkCourse(condition,target) {
		var corname = $(condition).val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "course_findByCorname.action",
			data : {
				"corname" : corname
			},
			beforeSend : function() {
				$("#queryresult").empty();
			},
			success : function(data) {
				loadCourseData(data,target);
			}
		});
	}
	
	function showBookInfo(idbk, bkname, author, edition, publisher, ISBN, price){
		var strHTML = "<p align='left'><font size='5' color='#ff0000'>选用教材<font><br/>";
		strHTML += "<font size='5' color='#0000ff'>"+bkname+"，"+publisher+"，第"+edition+"版，作者："+author+"<br/>";
		strHTML += "<font size='5' color='#ff0000'>的课程如下：</font>";
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_findCourseByIdbk.action",
			data : "idbk=" + idbk,
			success : function(data) {
				strHTML += "<table id='resulttable'>";
				 if (data.length >= 1) {
						strHTML += "<tr align='center'><th>课程编号</th><th>课程名称</th><th>院系</th><th>专业</th><th>学期</th></tr>";
						$.each(data,function(index, Info) {
							if (index % 2 == 0)
								strHTML += "<tr class='trEven'><td align='center' width='15%'>"+Info["idcor"]+"</td>";
							else
								strHTML += "<tr class='trOdd'><td align='center' width='15%'>"+Info["idcor"]+"</td>";
							strHTML += "<td width='30%' align='center'>"
									+ Info["corname"] + "</td>";
							strHTML += "<td width='20%' align='center'>"
									+ Info["col"] + "</td>";
							strHTML += "<td width='25%' align='center'>"
									+ Info["major"] + "</td>";
							strHTML += "<td width='10%' align='center'>"
									+ Info["semester"] + "</td></tr>";
						});
					} else {
						strHTML += "<tr><td><font size=\"5\" color=\"red\">"
								+ "未找到相应的课程信息，<br/>没有课程选用该教材！" + "</font></td></tr>";
					} 
					strHTML += "</table>";
					showInfo(strHTML);
			}
		});
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
	
	/** 弹出提示信息窗口 **/
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
					sureCall(params.idbk);
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
	
	/*********************************注册页面组件的各种事件**************************************/
	
	initBookInfo();// 初始化教材信息
	getAllCol();// 初始化学院下拉列表
	initPublisherInfo();//初始化出版社下拉列表

	// initCourseInfo();
	// 2014.4.2 屏蔽，打开页面初始化课程信息即可，否则发送请求太多导致打开页面时速度很慢。--张驰

	// 查询
	$("#querybtn").click(function() {
		// hideAddAlter();//隐藏修改模块
		// 点击查询之后就是重新开始，因此当前页置1
		initPage(); // 初始化页码信息
		// tag = true;// 精确查询
		// $("#isbn-a").setAttr("9787122025074");
		accurateBookQuery(); // 调用精确查询
		// clearAlter();
	});

	$("#col-d").bind('change',function() {
		accurateCourseQuery();
		initSemList();
		var col = $("#col-d option:selected").text();
		getMajorByCol2(col, "#major-d");
	});
	
	$("#major-d").bind('change',function() {
		accurateCourseQuery();
		initSemList();
		var col = $("#col-d option:selected").text();
		var major = $("#major-d option:selected").text();
		getMajorInfo2(col, major, "#semester-d");
	});
	
	$("#semester-d").bind('change',function() {
		accurateCourseQuery();
	});

	// 删除每一条教材记录
	$("#deleteidbook").live("click", function() {
		var $record = $(this).parent();
		/* 获取idbk节点 */
		var $idbk = $record.children(":first");
		/* 获取教材名称节点 */
		var $bkname = $idbk.next();
		var $author = $bkname.next();
		var $edition = $author.next();
		var $publisher = $edition.next();
		var $ISBN = $publisher.next();
		var $price = $ISBN.next();
		
		var idbk = $idbk.text();
		var bkname = $bkname.text();
		var author = $author.text();
		var edition = $edition.text();
		var publisher = $publisher.text();
		var ISBN = $ISBN.text();
		var price = $price.text();
		
		var tips = "确认删除教材<font size='5' color='red'>'" + bkname + "'</font>";
		tips += "（<font color='blue' size='5'>" + publisher + edition + "版，作者：" + author + "</font>）吗？";
		var param={'idbk':idbk};
		sys_Confirm(tips,deleteBook,param);
	});
	
	/* 读取的课本列表后的编辑按钮的点击事件响应函数 */
	$("#tempidbook").live("click", function() {
		// 获取到当前点击的教材的节点
		var $record = $(this).parent();
		/* 获取idbk节点 */
		var $idbk = $record.children(":first");
		/* 获取教材名称节点 */
		var $bkname = $idbk.next();
		var $author = $bkname.next();
		var $edition = $author.next();
		var $publisher = $edition.next();
		var $ISBN = $publisher.next();
		var $price = $ISBN.next();
		
		$("#check-c").val("");
		/* 填充修改Form中相应的值 */
		$("#idbk-b").attr("value", $idbk.text());
		$("#bkname-b").attr("value", $bkname.text());
		$("#author-b").attr("value", $author.text());
		$("#edition-b").attr("value", $edition.text());
		$("#isbn-b").attr("value", $ISBN.text());
		$("#price-b").attr("value", $price.text());
		
		var count=$("#publisher-b option").length;
		for(var i = 0; i < count; i++) {
			if($("#publisher-b").get(0).options[i].text == $publisher.text()){
				$("#publisher-b").get(0).options[i].selected = true; 
				break;  
		    }  
		}
		//$("#publisher-b option[text='"+$publisher.text()+"'").attr("selected", true);
		/* 隐藏查询模块 */
		$(".query").css("display", "none");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		$(".alter").css("left", "0%");
		$(".alter").css("width", "100%");
		/* 显示添加模块 */
		$(".alter").fadeIn(1000);

		//initPublisherInfo();
		AlterAllTag =false;
		/* 显示课程列表 */
		var idbk = $idbk.text();
		loadCourseByBook(idbk);
	});
	
	/* 读取的课本列表后的编辑按钮的点击事件响应函数 */
	$("#viewbook").live("click", function() {
		// 获取到当前点击的教材的节点
		var $record = $(this).parent();
		/* 获取idbk节点 */
		var $idbk = $record.children(":first");
		/* 获取教材名称节点 */
		var $bkname = $idbk.next();
		var $author = $bkname.next();
		var $edition = $author.next();
		var $publisher = $edition.next();
		var $ISBN = $publisher.next();
		var $price = $ISBN.next();
		
		var idbk = $idbk.text();
		var bkname = $bkname.text();
		var author = $author.text();
		var edition = $edition.text();
		var publisher = $publisher.text();
		var ISBN = $ISBN.text();
		var price = $price.text();
		
		showBookInfo(idbk, bkname, author, edition, publisher, ISBN, price);
	});
	
	
	$("#returnbtn").live("click", function() {
		$(".query").css("display", "block");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		$(".alter").fadeOut(1000);
		$("#checkresult").empty();
		accurateBookQuery();
	});
	
	$("#returnbtn-add").live("click", function() {
		$(".query").css("display", "block");
		/* 隐藏添加模块 */
		$(".alter").css("display", "none");
		$(".add").fadeOut(1000);
		$("#addresult").empty();
		accurateBookQuery();
	});
	
	$("#bkname-a").bind("change",function(){
		accurateBookQuery();
	});
	$("#publisher-a").bind("change",function(){
		accurateBookQuery();
	});
	$("#isbn-a").bind("change",function(){
		accurateBookQuery();
	});
	
	// 添加新的教材信息
	$("#add").click(function() {
		// 隐藏修改模块
		$(".query").css("display", "none");
		$(".alter").css("display", "none");
		$(".add").css("left", "0%");
		$(".add").css("width", "100%");
		$(".add").fadeIn(1000);
		AlterAllTag = false;
		//初始化所有课程,不建议开启，导致页面加载速度太慢
		//initCourseInfo();
		//初始化出版社
		initPublisherInfo();
	});
	
	// 添加教材，查询课程名称
	$("#checkbtn-add").click(function() {
		checkCourse("#check-add","#addresult");// 添加教材，查询课程
	});
	
	// 添加教材，查询课程名称
	$("#checkbtn-alter").click(function() {
		checkCourse("#check-alter","#checkresult");// 修改教材，查询课程
	});

	/**添加教材页面的checkAll事件处理**/
	$("#addresult :checkbox").live("click", function() {
		var flag = true;//用于标识是否是全选
		var id = $(this).attr("id");
		if (id == "checkall") {
			if (this.checked) {
				$("input[name='check']").each(function() { 
					// 遍历所有的name为checkhehe的checkbox
					$(this).attr("checked", true);
				});
			} else { // 反之 取消全选
				$("input[name='check']").each(function() { 
					// 遍历所有的name为checkhehe的checkbox
					$(this).attr("checked", false);
				});
			}
		} else {
			flag = true;
			idcor = "";
			$("input[name='check']").each(function() { 
				// 遍历所有的name为checkhehe的checkbox
				if (!this.checked) {
					flag = false;
				} else {
					var $record = $(this).parent().parent();
					var $idcor = $record.children(":first").next();
					idcor += $idcor.text() + ",";
				}
			});
			if (flag == true) {
				$("#checkall").attr("checked", true);
			} else {
				$("#checkall").attr("checked", false);
			}
		}
	});
	
	/**修改教材页面的checkAll事件处理**/
	$("#checkresult :checkbox").live("click", function() {
		var flag = true;//用于标识是否是全选
		var id = $(this).attr("id");
		if (id == "checkall") {
			if (this.checked) {
				AlterAllTag = true;
				$("input[name='check']").each(function() { 
					// 遍历所有的name为checkhehe的checkbox
					$(this).attr("checked", true);
				});
			} else { // 反之 取消全选
				AlterAllTag = false;
				$("input[name='check']").each(function() { 
					// 遍历所有的name为checkhehe的checkbox
					$(this).attr("checked", false);
				});
			}
		} else {
			flag = true;
			AlterAllTag = true;
			idcor = "";
			$("input[name='check']").each(function() { 
				// 遍历所有的name为checkhehe的checkbox
				if (!this.checked) {
					flag = false;
					AlterAllTag = false;
				} else {
					var $record = $(this).parent().parent();
					var $idcor = $record.children(":first").next();
					idcor += $idcor.text() + ",";
				}
			});
			if (flag == true) {
				$("#checkall").attr("checked", true);
			} else {
				$("#checkall").attr("checked", false);
			}
		}
	});

	// 注册添加按钮（addbtn）的事件
	$("#addbtn").click(function() {
		addNewBook();
	});
	
	// 注册修改按钮的事件
	$("#alterbtn").click(function() {
		var idbk = $("#idbk-b").val();
		var bkname = $("#bkname-b").val();
		var author = $("#author-b").val();
		var edition = $("#edition-b").val();
		var publisher = $("#publisher-b option:selected").text();
		var ISBN = $("#isbn-b").val();
		var price = $("#price-b").val();
		//检查书本信息的输入
		var valid = checkInputInfo(bkname,author,publisher);
		if(valid == true){
			if (AlterAllTag) {
				// 调用修改课程信息的函数
				alterAll(idbk,bkname,author,edition,publisher,ISBN,price);
			} else {
				alterBookInfo(idbk,bkname,author,edition,publisher,ISBN,price);
			}
		}
	});
});