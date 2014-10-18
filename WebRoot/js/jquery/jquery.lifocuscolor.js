// JavaScript Document
;(function($){
	  $.fn.extend({
		    "focusColor":function(li_col){
				  var def_col = "#ccc";//默认获取焦点的色值
				  var lst_col = "#fff";//默认失去焦点的色值
				  
				  //如果设置的颜色不为空，则使用设置的颜色，否则是使用默认色
				  li_col = (li_col == undefined) ? def_col : li_col;
				  //便利表项<li>中的全部元素
				  alert("a");
				  $(this).find("li").each(function(){
					    $(this).mouseover(function(){//获取焦点事件
							 alert("b");
							   $(this).css("background-color",li_col);//使用设置的颜色
							}).mouseout(function(){//鼠标焦点移除事件
							     alert("c");
								  $(this).css("background-color","#fff");
								})					  
					  });
				  return $(this);//返回JQuery对象，保持链式操作
				}
		  });
	})(JQuery);