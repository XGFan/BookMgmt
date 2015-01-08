var dg1,dg2,dg3,dg4;

$(function(){
	dg1=$('#dg1').datagrid({
		title:'教材信息管理',
		iconCls:'icon-save',
		method:'get',
		//url:'',
	    fit:true,
	    width: $(window).width()*0.8,
		height: $(window).height(),
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:20,
		pageList: [10,20,50,100],
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
				  { field:'bkno',title:'教材编号',align:'center',width:$(this).width()*0.12},
		          { field: 'bkname',title: '教材名称',align:'center', width:$(this).width()*0.20},
		          { field: 'writer',title: '作者',align:'center', width:$(this).width()*0.1},
		          { field: 'version',title: '版本',align:'center', width:$(this).width()*0.07},
		          { field:'publisher',title: '出版社',align:'center', width:$(this).width()*0.13}, 
		          { field: 'isbn',title: 'ISBN',align:'center', width:$(this).width()*0.1} ,
		          { field:'price',title: '价格',align:'center', width:$(this).width()*0.08}, 
		          { field:'options',title: '操作',align:'center', width:$(this).width()*0.16,
						formatter:function(value,row,index){
							var s = '<a href="#" onclick="moreInfo()">查看</a> ';
							var c = '<a href="#" onclick="editBook()">编辑</a>';
							var d = '<a href="#" onclick="delBook()">删除</a>';
							return s+c+d;
						}
				  }
		]]
	}); 
	dg2=$('#dg2').datagrid({
		method:'get',
		//url:'',
	    fit:true,
	    loadMsg:'数据加载中，请稍后...',
		fitClumns:true,
		columns:[[{ field:'clsno',title:'课程编号',align:'center',width:'90px'},
		          { field: 'clsname',title: '课程名称',align:'center', width:'150px'},
		          { field: 'col',title: '院系',align:'center', width:'100px'},
		          { field: 'major',title: '专业',align:'center', width:'150px'},
		          { field:'sem',title: '学期',align:'center', width:'50px'}
		]]
	}); 
	dg3=$('#dg3').datagrid({
		title:'请选择要关联的课程',
		method:'get',
		//url:'',
	    fit:true,
		toolbar:'#tb3',
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		fitClumns:true,
		columns:[[{field:'ck',checkbox:true},
				  { field:'clsno',title:'课程编号',align:'center',width:'90px'},
		          { field: 'clsname',title: '课程名称',align:'center', width:'120px'},
		          { field: 'col',title: '院系',align:'center', width:'90px'},
		          { field: 'major',title: '专业',align:'center', width:'120px'},
		          { field:'sem',title: '学期',align:'center', width:'50px'}
		]]
	}); 
	dg4=$('#dg4').datagrid({
		title:'请选择要修改的关联课程',
		method:'get',
		//url:'',
	    fit:true,
		toolbar:'#tb4',
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		fitClumns:true,
		columns:[[{field:'ck',checkbox:true},
				  { field:'clsno',title:'课程编号',align:'center',width:'90px'},
		          { field: 'clsname',title: '课程名称',align:'center', width:'120px'},
		          { field: 'col',title: '院系',align:'center', width:'90px'},
		          { field: 'major',title: '专业',align:'center', width:'120px'},
		          { field:'sem',title: '学期',align:'center', width:'50px'}
		]]
	}); 
});

/*
*
*
*
*
*
*
*/


/*查询显示与隐藏*/
function queryToggle(){
  $("#query").toggle();
}
function queryToggle1(){
  $("#query1").toggle();
}
function queryToggle2(){
  $("#query2").toggle();
}


/*新增教材对话框*/
//打开新增教材对话框
function addBook(){
	$("#dlg2").dialog("open").dialog("setTitle","新增教材");	
}
//关闭新增对话框
function closeDialog2(){
	$("#dlg2").dialog("close");
}



/*编辑教材对话框*/
//打开编辑教材对话框
function editBook(){
	$("#dlg3").dialog("open").dialog("setTitle","修改教材信息");	
}
//关闭编辑对话框
function closeDialog3(){
	$("#dlg3").dialog("close");
}




/*查看教材对话框*/
//打开查看教材对话框
function moreInfo(){
	$("#dlg1").dialog("open").dialog("setTitle","课程信息");
}
//关闭查看对话框
function closeDialog1(){
	$("#dlg1").dialog("close");
}
//删除被选中教材
function delBook(){}

//查询教材
function searchBook(){}













