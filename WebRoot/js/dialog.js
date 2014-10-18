// JavaScript Document
$(function() {
	function sys_Alert(content) {// 弹出提示信息窗口
		$("#dialog-modal").dialog({
			height : 200,
			width : 150,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				Cancel : function() {
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
			height : 200,
			width : 150,
			modal : true,
			title : "系统提示",
			hide : "slide",
			buttons : {
				"确定" : function() {
					$(this).dialog("close");
				},
			},
			open : function() {
				$(this).html("");
				$(this).append("<p>" + content + "</p>");
			}
		});
	}
});