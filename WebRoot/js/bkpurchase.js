// JavaScript Document
var tag = true;// 定义一个标识符，来确定是模糊查询还是精确查询，默认是模糊查询
$(function() {

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
	
	/* 显示出等待的图片 */
	function showMask() {
		$("#mask").css("display", "block");
	}
	/* 隐藏出等待的图片 */
	function hideMask() {
		$("#mask").css("display", "none");
	}

	/*******************************Ajax函数***********************************/
	// 获取所有的供应商
	function getAllSup() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_searchAllSup.action",
			data : null,
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				$("#supplier").empty();
				var optionstr = "<option>----全部----</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#supplier").append(optionstr);
			}
		});
	}
	
	/* 创建购书表数据 */
	function createBKPurData() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_generateBookList.action",
			data : "bkpubchasedate=" + encodeURI($("#bkpubchase").val()),
			beforeSend : function() {
				// $("#queryresult").empty();// 清空result中的内容
				showMask();
			},
			success : function(data) {
				loadBookPurchaseData(data);
				hideMask();
				sys_Alert("购书清单已保存至E盘！");
			}
		});
	}
	
	/* 创建购书表数据 */
	function createFreshManBKPurData() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_generateFreshManBookList.action",
			data : "bkpubchasedate=" + encodeURI($("#bkpubchase").val()),
			beforeSend : function() {
				// $("#queryresult").empty();// 清空result中的内容
				//alert("数据有点多，请耐心等待!");
				showMask();
			},
			success : function(data) {
				loadBookPurchaseData(data);
				hideMask();
				sys_Alert("购书清单已保存至E盘！");
			}
		});
	}

	/**获取购书日期**/
	function getBKPurDate() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_getBKPurDate.action",
			data : null,
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				var optionstr = null;
				$.each(data, function(index, Info) {
					optionstr = Info;
				});
				$("#bkpubchase").attr("value", optionstr);
			}
		});
	}
	
	// 修改购书日期
	function alterBKPurDate() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_alterBKPurDate.action",
			data : "alterdate="
					+ encodeURI($("#bkpubchase-u option:selected").text()),
			beforeSend : function() {
				// $("#queryresult").empty();//清空result中的内容
			},
			success : function(data) {
				// 更新的input中的日期
				getBKPurDate();
				var purchaseDate = $("#bkpubchase-u option:selected").text();
				$("#bkpubchase").attr("value", purchaseDate);
				sys_Alert("修改成功！");
			}
		});
	}

	//获取购书日期范围
	function getBKPurDateRange() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_getBKPurDateRange.action",
			data : null,
			beforeSend : function() {
				$("#bkpubchase-u").empty();// 清空result中的内容
			},
			success : function(data) {
				var optionstr = "<option>请选择</option>";
				$.each(data, function(index, Info) {
					optionstr += "<option>" + Info + "</option>";
				});
				$("#bkpubchase-u").append(optionstr);
			}
		});
	}

	// 获取某一供应商的购书单
	function getPurInfoBySupplier() {
		var supplier = $("#supplier option:selected").text();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "bkpur_getPurInfoBySupplier.action",
			data : "supplier=" + encodeURI(supplier),
			beforeSend : function() {
				$("#queryresult").empty();// 清空result中的内容
			},
			success : function(data) {
				loadBookPurchaseData(data);// 调用数据加载方法
			}
		});
	}

	// 根据供应商查询购书列表，展示数据
	function loadBookPurchaseData(data) {
		var strHTML = "<table id='resulttable'>";// 初始化内容变量
		if (data.length >= 1) {
			strHTML += "<tr align='center' style='position:relative'><th>编号</th><th>书本名</th><th>作者</th>";
			strHTML += "<th>ISBN</th><th>出版社</th><th>订购数量</th><th>校区</th><th>供应商</th></tr>";
			$.each(data, function(index, Info) {
				if (index % 2 == 0)// 实现奇数和偶数行的不同样式
					strHTML += "<tr class='trEven'><td width='5%' align='center'>"
							+ Info["no"] + "</td>";
				else
					strHTML += "<tr no='trOdd'><td width='5%' align='center'>" + Info["no"]
							+ "</td>";
				strHTML += "<td width='27%'>" + Info["bkname"] + "</td>";
				strHTML += "<td width='10%'>" + Info["author"] + "</td>";
				strHTML += "<td width='10%'>" + Info["isbn"] + "</td>";
				var pub = null;
				if(Info["edition"]=="1"){
					pub = Info["publisher"];
				}else{
					pub = Info["publisher"] + Info["edition"] + "版";
				}
				strHTML += "<td width='15%'>" + pub + "</td>";
				
				strHTML += "<td width='10%'>" + Info["bknum"] + "</td>";
				strHTML += "<td width='10%' align='center'>" + Info["campus"] + "</td>";
				strHTML += "<td width='13%'>" + Info["supplier"] + "</td>";
			});
			// $(".result #pagination").css("display", "block");// 显示分页
		} else {
			$(".result #pagination").css("display", "none");// 隐藏分页
			strHTML += "<tr><td><font size=\"5\" color=\"red\">"
					+ "生成购买清单失败，<br/>请重新生成！" + "</font></td></tr>";
		}
		strHTML += "</table>";
		$("#queryresult").css("top", "10%");
		$("#queryresult").css("height", "480px");
		$("#queryresult").css("overflow-y", "scroll");
		$("#queryresult").html(strHTML);// 显示处理后的数据
	}

	// 获取所有的供应商
	getAllSup();
	// 获取xml中的购买日期
	getBKPurDate();

	$("#querybtn").click(function() {
		alterDivFadeOut();
		getPurInfoBySupplier();// 获取某一供应商的购书单
	});

	$("#bkpurbtn").click(function() {
		// 购书Button
		alterDivFadeOut();
		createBKPurData();// 创建购书数据
	});
	
	$("#freshManBKpurBtn").click(function() {
		// 新生购书Button
		alterDivFadeOut();
		createFreshManBKPurData();// 创建购书数据
	});
	
	$("#editbtn").click(function() {
		alterDivFadeIn();
		getBKPurDateRange();
	});

	$("#alterbtn").click(function() {
		alterBKPurDate();
		alterDivFadeOut();
	});
	
	$("#cancelbtn").click(function() {
		alterDivFadeOut();
	});
	
	/** 修改界面闪出 **/
	function alterDivFadeOut(){
		$(".query").css("width", "100%");
		$(".query").css("display", "block");
		$(".alter").fadeOut(1000);
	}
	/** 修改界面闪入 **/
	function alterDivFadeIn(){
		$(".query").css("width", "80%");
		$(".alter").css("display","block");
		$(".alter").css("left","80%");
		$(".alter").css("width","20%");
		$(".alter").fadeIn(1000);
	}

	// 弹出对话框的函数
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

});// JavaScript Document
