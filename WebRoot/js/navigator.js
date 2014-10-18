// JavaScript Document
$(function() {
	var curY;// 获取所选项的Top值
	var curX;// 获取所选项的Left值
	var curH;// 获取所选项的Height值
	var curW;// 获取所选项的width值
	var srtY;// 设置提示箭头的Top值
	var srtX;// 设置提示箭头的left值
	var objL;// 获取当前对象

	/** 设置当前位置数值，参数obj为当前对象名称* */
	function setInitValue(obj) {
		curY = obj.offset().top;
		curX = obj.offset().left;
		curH = obj.height();
		curW = obj.width();
		// srtY=curY+(curH/2)+"px";//提示箭头的Top值
		srtY = curY + curH - 120 + "px";// 提示箭头的Top值
		// srtY=curH+"px";//提示箭头的Top值
		// 这里为什么要减去120我也不知道，自己调试的。
		// srtX=curW-5+"px";//设置提示箭头的left值
		srtX = curX - 150 + "px";// 设置提示箭头的left值
		// 这里为什么要减去70我也不是很清楚，自己调试的
		// alert(srtY+','+srtX);
	}

	// 设置当前所选项的鼠标滑过事件
	$(".navigator .optn").mouseover(function() {
		// alert("hehe");
		objL = $(this);// 获取当前对象
		setInitValue(objL);// 设置当前位置
		var allY = curY - curH + "px";// 设置提示框的Top值
		objL.addClass("optnFocus");// 增加获取焦点时的样式

		objL.next("ul").show()// 显示并设置提示框的坐标
		.css({
			"top" : srtY,
			"left" : srtX
		});
		// $("#sort").show()//显示并设置提示小箭头的位置
		// .css({"top":srtY,"left":srtX});
		// alert(srtY+','+srtX);
	}).mouseout(function() {
		// 删除获取焦点时的样式
		$(this).removeClass("optnFocus");
		$(this).next("ul").hide();// 隐藏提示框
		// $("#sort").hide();//隐藏提示箭头
	});
	
	$(".navigator .tip").mousemove(function() {
		$(this).show();// 显示提示框
		objL = $(this).prev("li");// 获取当前的上级li对象
		setInitValue(objL);// 设置当前位置
		// 增加上级li对象获取焦点时的样式
		objL.addClass("optnFocus");
		// 显示并设置提示箭头的坐标
		// $("#sort").show().css({"top":srtY,"left":srtX});
	}).mouseout(function() {
		$(this).hide();// 隐藏提示框
		// 删除获取焦点时的样式
		$(this).prev("li").removeClass("optnFocus");
		// $("#sort").hide();//隐藏提示箭头
	});

	function setMinWidth(width) {
		var bWidth = ($(window).width() <= width) ? width : '100%';
		$('body').width(bWidth);

		// 窗口放大缩小，设置页面宽度
		$(window).resize(function() {
			var bWidth = ($(window).width() <= width) ? width : '100%';
			$('body').width(bWidth);
		});
	}

	setMinWidth(1024);
});
