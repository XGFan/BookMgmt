// JavaScript Document
var tag = true;// 定义一个标识符，来确定是模糊查询还是精确查询，默认是模糊查询
var currentPage = 1; // 页索引
var totalPage = 1;// 总页数
var totalRecord = 1;// 总记录条数
$(function() {
	/**********************************************1.与分页有关的事件及函数***********************************************/
	// 第一页按钮click事件
	$("#first").click(function() {
		currentPage = 1;
		hideAddAlter();
		$("#lblCurentPage").text(1);
		pagefuzzyQuery();

	});
	// 上一页按钮click事件
	$("#previous").click(function() {
		hideAddAlter();
		if (currentPage >= 2) {
			currentPage--;
			$("#lblCurentPage").text(currentPage);
		}
		pagefuzzyQuery();
		if (currentPage == 1)
			sys_Alert('到顶啦！');
	});
	// 下一页按钮click事件
	$("#next").click(function() {
		hideAddAlter();
		if (currentPage < totalPage) {
			currentPage++;
			$("#lblCurentPage").text(currentPage);
		}
		pagefuzzyQuery();
		if (currentPage == totalPage)
			sys_Alert('到底啦！');
	});
	// 最后一页按钮click事件
	$("#last").click(function() {
		hideAddAlter();
		$("#lblCurentPage").text(totalPage);
		currentPage = totalPage;
		pagefuzzyQuery();
	});
	
	// AJAX方法获取后台分页对象的数据
	function getPagination() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_initPagination.action",
			data : "supplier="
					+ encodeURI($("#supplier option:selected").text())
					+ "&publisher=" + encodeURI($("#publisher").val()),
			success : function(data) {
				$.each(data, function(index, Info) {
					totalPage = Info["totalPage"];
					totalRecord = Info["totalRecord"];
				});
				$("#lblTotalPage").html(totalPage);
				$("#lblToatlRecord").html(totalRecord);
				hideAddAlter();
				pagefuzzyQuery(); // 存在进程间的问题
				bindPage();
			}
		});
	}
	
	// //AJAX方法获取后台分页对象的数据 (页面初始化时)
	function initPubInfo() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_initPage.action",
			data : null,
			success : function(data) {
				$.each(data, function(index, Info) {
					totalPage = Info["totalPage"];
					totalRecord = Info["totalRecord"];
				});
				$("#lblTotalPage").html(totalPage);
				$("#lblToatlRecord").html(totalRecord);
				hideAddAlter();
				pagefuzzyQuery(); // 存在进程间的问题
				bindPage();
			}
		});
	}
	
	// 初始化页码信息
	function initPage() {
		hideAddAlter();
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

	/**********************************************2.与业务逻辑有关的Ajax函数***********************************************/
	function pagefuzzyQuery() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_pagefuzzyQuery.action",
			data : "currentPage=" + currentPage,
			beforeSend : function() {

			},
			success : function(data) {
				loadSupplierData(data);
				bindPage();
			}
		});
	}
	
	
	function loadSupplierListForAlter(str) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_searchAllSup.action",
			data : null,
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				$("#supplier-u").empty();
				var optionstr = "<option>--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#supplier-u").append(optionstr);
				count = $("#supplier-u option").length;
				for ( var i = 0; i < count; i++) {
					if ($("#supplier-u").get(0).options[i].text == str) {
						$("#supplier-u").get(0).options[i].selected = true;
						break;
					}
				}

			}
		});
	}
	
	function getAllSup(target) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_searchAllSup.action",
			data : null,
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				$(target).empty();
				var optionstr = "<option>--请选择--</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$(target).append(optionstr);
			}
		});
	}
	function loadSupplierData(data) {
		var strHTML = "<table id='resulttable'>";// 初始化内容变量
		if (data.length >= 1) {
			strHTML += "<tr align='center'><th>idsp</th><th>publisher</th><th>供应商</th><th>操作</th></tr>";
			$.each(data,function(index, Info) {
								if (index % 2 == 0)// 实现奇数和偶数行的不同样式
									strHTML += "<tr class='trEven'><td width='10%' align='center'>"
											+ Info["idsp"] + "</td>";
								else
									strHTML += "<tr class='trOdd'><td width='10%' align='center'>"
											+ Info["idsp"] + "</td>";
								strHTML += "<td width='30%' align='center'>"
										+ Info["publisher"] + "</td>";
								strHTML += "<td width='30%' align='center'>"
										+ Info["supplier"] + "</td>";
								strHTML += "<td width='30%' align='center'><input id='editidsp' class='btn' type='button' value='编辑'/>";
								strHTML += "<input id='deletePubBtn' class='btn' type='button' value='删除'/></td></tr>";
							});
			$(".result #pagination").css("display", "block");// 显示分页
		} else {
			$(".result #pagination").css("display", "none");// 隐藏分页
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "未找到相应的出版社信息，<br/>请修改查询条件后重新查询！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$("#queryresult").html(strHTML);// 显示处理后的数据
	}
	
	// 添加供应商
	function addsup() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_addSup.action",
			data : "supplier=" + encodeURI($("#addsupplier-a").val()),
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				getAllSup("#supplier");
				getAllSup("#supplier-d");
				initPubInfo();
				sys_Alert("添加成功！");
			}
		});
	}
	// 添加出版社
	function addpub() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_addPub.action",
			data : "supplier="
					+ encodeURI($("#supplier-a option:selected").text())
					+ "&publisher=" + encodeURI($("#publisher-a").val()),
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				// 更新的供应商列表
				getAllSup("#supplier");
				getAllSup("#supplier-d");
				initPubInfo();
				sys_Alert("添加成功！");
			}
		});
	}
	// 修改出版社(记录)
	function alterpub() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_alterSup.action",
			data : "supplier="
					+ encodeURI($("#supplier-u option:selected").text())
					+ "&publisher=" + encodeURI($("#publisher-u").val())
					+ "&idsp=" + encodeURI($("#idsp-u").val()),
			beforeSend : function() {
			},
			success : function(data) {
				// 更新的供应商列表
				getAllSup("#supplier");
				getAllSup("#supplier-d");
				initPubInfo();
				hideAddAlter();
				sys_Alert("修改成功！");
			}
		});
	}
	// 删除出版社(记录)
	function delpub(idsp,publisher,supplier) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_delPub.action",
			data :{
				"idsp":idsp,
				"publisher":publisher,
			    "supplier":supplier
			}, 
			beforeSend : function() {
				
			},
			success : function(data) {
				// 更新的供应商列表
				getAllSup("#supplier");
				getAllSup("#supplier-d");
				initPubInfo();
				sys_Alert("出版社已删除！");
			}
		});
	}
	// 删除供应商
	function delsup() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "supplier_delSup.action",
			data : "supplier="
					+ encodeURI($("#supplier-d option:selected").text()),
			beforeSend : function() {
			},
			success : function(data) {
				// 更新的供应商列表
				getAllSup("#supplier");
				getAllSup("#supplier-d");
				initPubInfo();
				sys_Alert("供应商已删除！");
			}
		});
	}
	
	/* 隐藏添加和修改的对话框 */
	function hideAddAlter() {
		$(".query").css("width", "100%");
		$(".alter").fadeOut(1000);
		$(".addpub").fadeOut(1000);
		$(".alter").css("display", "none");
		$(".addpub").css("display", "none");

	}
	// 弹出添加供应商的对话框
	function sys_Add_Confirm(content) {// 弹出信息询问窗口
		$("#dialog-add").dialog({
			width : 450,
			height : 300,
			modal : true,
			title : "添加新的供应商",
			hide : "slide",
			buttons : {
				"添加" : function() {
					addsup();
					$(this).dialog("close");
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
	
	function delSupplierConfirm(content, sureCall) {// 弹出信息询问窗口
		$("#dialog-delete").dialog({
			width : 450,
			height : 300,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				'删除' : function() {
					$(this).dialog("close");
					sureCall();
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
	
	function delPublisherConfirm(content, sureCall ,params) {// 弹出信息询问窗口
		$("#dialog-delete").dialog({
			width : 350,
			height : 250,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				'删除' : function() {
					$(this).dialog("close");
					sureCall(params.idsp, params.publisher, params.supplier);
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

	
	/**********************************************3.页面相关组件的事件注册***********************************************/
	// 获取所有的供应商
	getAllSup("#supplier");
	getAllSup("#supplier-d");
	// //初始化页面信息
	initPubInfo();
	
	$("#querybtn").click(function() {
		currentPage = 1; // 页索引
		getPagination();
		// pagefuzzyQuery(); //存在进程间的问题
	});

	$("#addbtnpub").click(function() {
		hideAddAlter();
		$(".query").css("width", "70%");
		$(".addpub").css("width", "30%");
		$(".addpub").css("left", "70%");
		$(".addpub").fadeIn(1000);
		getAllSup("#supplier-a");
	});
	
	$("#addpub").click(function() {
		addpub();
	});

	$("#addbtnsup").click(function() {
		hideAddAlter();
		var context = "<form id='alterform' name='alterform' method='post' action=''>供应商<input type='text' id='addsupplier-a' /></form>";
		sys_Add_Confirm(context);
	});
	
	$("#editidsp").live("click", function() {
		// 获取到当前点击的课程的节点
		var $record = $(this).parent().parent();
		/* 获取idcor节点 */
		var $idsp = $record.children(":first");
		/* 获取学院节点 */
		var $publisher = $idsp.next();
		var $supplier = $publisher.next();

		$(".query").css("width", "70%");
		/* 隐藏添加模块 */
		$(".add").css("display", "none");
		$(".alter").css("width", "30%");
		$(".alter").css("left", "70%");
		$(".alter").fadeIn(1000);
		/* 填充相应的值 */
		loadSupplierListForAlter($supplier.text());
		/* 赋值 */
		$("#idsp-u").attr("value", $idsp.text());
		$("#publisher-u").attr("value", $publisher.text());
		$("#idsp-u").attr("disabled", "true");
	});
	
	$("#deletePubBtn").live("click", function() {
		// 获取到当前点击的出版社的节点
		var $record = $(this).parent().parent();
		/* 获取idsp节点 */
		var $idsp = $record.children(":first");
		var $publisher = $idsp.next();
		var $supplier = $publisher.next();
		
		var idsp = $idsp.text();
		var publisher = $publisher.text();
		var supplier = $supplier.text();
		
		var params = {'idsp':idsp,
				'publisher':publisher,
				'supplier':supplier
				};
		var tips = "确认删除该出版社吗？";
		delPublisherConfirm(tips,delpub,params);
		//delpub($idsp.text(),$publisher.text(),$supplier.text());

	});

	/* 删除修改记录 */
	$("#alterbtn").click(function() {
		alterpub();
	});
	
	$("#cancelbtn-add").click(function() {
		hideAddAlter();
	});
	$("#cancelbtn-alter").click(function() {
		hideAddAlter();
	});
	
	/* 删除供应商 */
	$("#delbtnsup").click(function() {
		var tips = "删除供应商会删除该供应商对应的出版社、出版社的书籍等所有的数据，确认删除吗？";
		delSupplierConfirm(tips,delsup);
		//delsup();
	});

});// JavaScript Document
