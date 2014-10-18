//点击编辑之后将详细信息输出到右侧修改栏目中
var tag = false;// 定义一个标识符，来确定是全选还是子选
var idcorStr;
$(function() {
	// sys_Alert("corList.js----------");
	$("#returnbtn").live("click", function() {
		$(".query").css("display", "block");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		// $(".alter").css("left","15%");
		// $(".alter").css("width","80%");
		$(".result").css("top", "40%");
		$(".result").css("height", "50%");
		$(".alter").fadeOut(1000);
	})
	
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
		var $idsp = $edition.next();
		var $ISBN = $idsp.next();
		var $price = $ISBN.next();

		/* 填充修改Form中相应的值 */
		$("#idbk-b").attr("value", $idbk.text());
		$("#bkname-b").attr("value", $bkname.text());
		$("#author-b").attr("value", $author.text());
		$("#edition-b").attr("value", $edition.text());
		$("#idsp-b").attr("value", $idsp.text());
		$("#ISBN-b").attr("value", $ISBN.text());
		$("#price-b").attr("value", $price.text());

		/* 隐藏查询模块 */
		$(".query").css("display", "none");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		$(".alter").css("left", "15%");
		$(".alter").css("width", "80%");
		$(".result").css("top", "40%");
		$(".result").css("height", "50%");
		/* 显示添加模块 */
		$(".alter").fadeIn(1000);

		/* 显示课程列表 */
		var value = $idbk.text();
		loadcorList(value);
	});

	// 添加教材，查询课程名称
	$("#checkbtn").click(function() {
		checkCourse();// 添加教材，查询课程
	});

	function initInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_findAllPub.action",
			data : null,
			beforeSend : function() {
				// $("#queryresult").empty();;
			},
			success : function(data) {
				$("#idsp-c").empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info["publisher"] + "</option>";
				});
				$("#idsp-c").append(optionstr);
			}
		});

	}

	function checkCourse() {
		var corname = $("#check-c").val();
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
				loadcourseListData(data);// 调用组织数据的方法
			}
		});
	}

	function loadcourseListData(data) {
		var strHTML = "<table id='resulttable'>";
		if (data.length >= 1) {
			strHTML += "<tr width='1%' align='center'><th>全选<input type='checkbox' id='checkall' name='checkall'/></th> <th>课程编号</th> <th>课程名称</th>  <th>专业</th> <th>学期</th></tr>";
			$.each(data,function(index, Info) {
				if (index % 2 == 0)
					strHTML += "<tr class='trEven'><td align='center'><input type='checkbox' id='check' name='check' value='"+Info["idcor"]+"'/></td>";
				else
					strHTML += "<tr class='trOdd'><td align='center'><input type='checkbox' id='check' name='check' value='"+Info["idcor"]+"'/></td>";
				strHTML += "<td width='15%' align='center'>"
						+ Info["idcor"] + "</td>";
				strHTML += "<td width='40%' align='center'>"
						+ Info["corname"] + "</td>";
				strHTML += "<td width='25%' align='center'>"
						+ Info["col"] + "</td>";
				strHTML += "<td width='25%' align='center'>"
						+ Info["major"] + "</td>";
				strHTML += "<td width='5%' align='center'>"
						+ Info["semester"] + "</td></tr>";
			});
			// $(".result #pagination").css("display","block");//显示分页
		} else {
			// $(".result #pagination").css("display","none");
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的课程信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$("#addresult").html(strHTML);
	}
	
	//初始化教材信息列表
	initInfo();
	
	var idcor = "";
	$(":checkbox").live("click", function() {
		var name = $(this).attr("id");
		if (name == "checkall") {
			if (this.checked) {
				tag = true;
				$("input[name='check']").each(function() { // 遍历所有的name为checkhehe的
					// checkbox
					$(this).attr("checked", true);
				});
			} else { // 反之 取消全选
				tag = false;
				$("input[name='check']").each(function() { // 遍历所有的name为checkhehe的
					// checkbox
					$(this).attr("checked", false);
				});
			}
		} else {
			flag = true;
			idcor = "";
			$("input[name='check']").each(function() { // 遍历所有的name为checkhehe的
				// checkbox
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

	$("#resulttable").live("click", function() {
		$("#resulttable tr:nth-child(even)").addClass("trEven");
		$("#resulttable tr:nth-child(odd)").addClass("trOdd");
	});

	// 注册添加按钮（addbtn）的事件
	$("#addbtn").click(function() {
		idcorStr = "";
		$("input[name='check']").each(function() { // 遍历所有的name为checkhehe的
			// checkbox
			if (this.checked) {
				idcorStr += $(this).val() + ",";
			}
		});
		idcorStr = idcorStr.substring(0,idcorStr.length-1);
		var bkname = $("#bkname-c").val();
		var author = $("#author-c").val();
		var edition = $("#edition-c").val();
		var publish = $("#idsp-c option:selected").text();
		var ISBN = $("#isbn-c").val();
		var price = $("#price-c").val();	
		
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_insertqq.action",
			data : {
				"idcor" : idcorStr,
				"bkname" : bkname,
				"author" : author,
				"edition" : edition,
				"publish" : publish,
				"ISBN" : ISBN,
				"price" : price
			},
			beforeSend : function() {
				sys_Alert(idcorStr+"'"+bkname+"'"+author+"'"+edition+"'"+publish+"'"+ISBN+"'"+price);
			},
			success : function(data) {
				sys_Alert("教材添加成功！");
				// loadcourseListData(data);//调用组织数据的方法
				// getAccPagination();//获取精确查询的分页数据
			}
		});

});

	// 注册修改按钮的事件
	$("#alterbtn").click(function() {
		if (tag) {
			alterAll();// 调用修改课程信息的函数
		} else {
			var idbk = $("#idbk-b").val();
			var bkname = $("#bkname-b").val();
			var author = $("#author-b").val();
			var edition = $("#edition-b").val();
			var publish = $("#idsp-b").val();
			var ISBN = $("#isbn-b").val();
			var price = $("#price-b").val();

		    idcorStr = "";
			$("input[name='check']").each(function() { // 遍历所有的name为checkhehe的
				// checkbox
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
					"publish" : publish,
					"ISBN" : ISBN,
					"price" : price
				},
				beforeSend : function() {
					// $("#queryresult").empty();;//清空result中的内容
				},
				success : function(data) {
					// loadcourseListData(data);//调用组织数据的方法
					// getAccPagination();//获取精确查询的分页数据
				}
			});

		}
	});
	
	
	// 选中所有课程，则更改所有选用这本教材的课程
	function alterAll() {
		var idbk = $("#idbk-b").val();
		var bkname = $("#bkname-b").val();
		var author = $("#author-b").val();
		var edition = $("#edition-b").val();
		var publish = $("#idsp-b").val();
		var ISBN = $("#isbn-b").val();
		var price = $("#price-b").val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_modifyAll.action",
			data : {
				"idbk" : idbk,
				"bkname" : bkname,
				"author" : author,
				"edition" : edition,
				"publish" : publish,
				"ISBN" : ISBN,
				"price" : price
			},
			success : function(data) {
				var tag = false;
				$.each(data, function(index, Info) {
					tag = Info["result"];
				});
				if (tag == true) {
					sys_Alert("修改成功！");
				}
			}
		});

	}

	/* 根据idbk获取选用该教材的课程信息 */
	function loadcorList(value) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "book_findByBook.action",
			data : "idbk=" + value,
			beforeSend : function() {
				$("#checkresult").empty();
			},
			success : function(data) {
				loadcorListData(data);
				// getAccPagination(); //获取精确查询的分页数据
				// $(".result #pagination").css("display","none");
			}
		});

	}
	
	function loadcorListData(data) {
		var strHTML = "<table id='resulttable'>";
		if (data.length >= 1) {
			strHTML += "<tr width='1%' align='center'><th>全选<input type='checkbox' id='checkall' name='checkall'/></th><th>课程编号</th><th>课程名称</th><th>院系</th><th>专业</th><th>学期</th></tr>";
			$.each(data,function(index, Info) {
								if (index % 2 == 0)
									strHTML += "<tr class='trEven'><td align='center'><input type='checkbox' id='check' name='check' value='"+Info["idcor"]+"'/></td>";
								else
									strHTML += "<tr class='trOdd'><td align='center'><input type='checkbox' id='check' name='check' value='"+Info["idcor"]+"'/></td>";
								strHTML += "<td width='15%' align='center'>"
										+ Info["idcor"] + "</td>";
								strHTML += "<td width='40%' align='center'>"
										+ Info["corname"] + "</td>";
								strHTML += "<td width='25%' align='center'>"
										+ Info["col"] + "</td>";
								strHTML += "<td width='25%' align='center'>"
										+ Info["major"] + "</td>";
								strHTML += "<td width='5%' align='center'>"
										+ Info["semester"] + "</td></tr>";
							});
			// $(".result #pagination").css("display","block");//显示分页
		} else {
			// $(".result #pagination").css("display","none");
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的课程信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$("#checkresult").html(strHTML);
	}
	
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

});
