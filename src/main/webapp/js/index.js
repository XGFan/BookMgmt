$(function(){
	/*定义树状菜单*/
	// 班级信息管理树菜单
	var treeData=[{
		text:"班级信息",
		children:[{
			text:"年级信息管理",
			attributes:{
				url:"class/classAll.jsp"
			}
		},{
			text:"班级信息管理",
			attributes:{
				url:"class/classGrade.jsp"
			}
		}]
	}];	
	$("#tree").tree({
		data:treeData,
		lines:true,
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	
	//教材管理树菜单
	var treeData1=[{
		text:"教材信息",
		children:[{
			text:"教材信息管理",
			attributes:{
				url:"book/book.jsp"
			}
		}]
	}];	
	$("#tree1").tree({
		data:treeData1,
		lines:true,
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	
	//教学计划管理树菜单
	var treeData2=[{
		text:"教学计划",
		children:[{
			text:"教学计划管理",
			attributes:{
				url:"corplan/corplan.jsp"
			}
		}]
	}];
	$("#tree2").tree({
		data:treeData2,
		lines:true,
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	
	//基础信息管理树菜单
	var treeData3=[{
		text:"课程信息",
		children:[{
			text:"课程信息管理",
			attributes:{
				url:"course/course.jsp"
			}
		},{
		    text:"课程-教材信息",
			attributes:{
				url:"course/viewCourseBk.jsp"
			}
		}]
	},{
		text:"院系信息",
		children:[{
			text:"院系信息管理",
			attributes:{
				url:"college/college.jsp"
			}
		}]
	},{
		text:"供应商信息",
		children:[{
			text:"供应商信息管理",
			attributes:{
				url:"supplier/supplier.jsp"
			}
		}]
	}];
	$("#tree3").tree({
		data:treeData3,
		lines:true,
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	
	//教材采购树菜单
	var treeData4=[{
		text:"教材采购",
		children:[{
			text:"教材采购管理",
			attributes:{
				url:"bkpurchase/bkpurchase.jsp"
			}
		}]
	}];
	$("#tree4").tree({
		data:treeData4,
		lines:true,
		onClick:function(node){
			if(node.attributes){
				openTab(node.text,node.attributes.url);
			}
		}
	});
	//教材发放
	var treeData5=[{
		text:"教材发放",
		children:[{
			text:"教材发放管理",
			attributes:{
				url:"bookDistribute/bookDis1.jsp"
			}
		}]
	}];
	
	$("#tree5").tree({
		data:treeData5,
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
});
