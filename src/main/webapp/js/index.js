$(function(){
	var treeData=[{
		text:"根目录",
		children:[{
			text:"班级信息管理",
			attributes:{
				url:"class/classAll.jsp"
			}
		},{
			text:"专业信息管理",
			attributes:{
				url:"class/classGrade.jsp"
			}
		}]
	}];	
	// 实例化树菜单
	$("#tree").tree({
		data:treeData,
		lines:true,
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	// 新增Tab
	function openTab(text,url){
		if($("#tabs").tabs('exists',text)){
			$("#tabs").tabs('select',text);
		}else{
			var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
			$("#tabs").tabs('add',{
				title:text,
				closable:true,
				content:content
			});
		}
	}
})