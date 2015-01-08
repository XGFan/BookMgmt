var dg1,dg2,dg3,dg4;

$(function(){
	dg1=$('#dg1').datagrid({
		title:'课程信息',
		iconCls:'icon-save',
		method:'get',
		url:'',
	    fit:true,
	    width: $(window).width()*0.8,
		height: $(window).height(),
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:100,
		pageList: [10,20,50,100],
		toolbar:'#tb1',
		columns:[[//{field:'ck',checkbox:true},
		          { field:'clsno',title:'课程编号',align:'center',width:$(this).width()*0.11},
		          { field: 'clsname',title: '课程名称',align:'center', width:$(this).width()*0.21},
		          { field: 'col',title: '院系',align:'center', width:$(this).width()*0.18},
		          { field: 'major',title: '专业',align:'center', width:$(this).width()*0.21},
		          { field:'sem',title: '学期',align:'center', width:$(this).width()*0.08},
				  {field:'options',title: '操作',align:'center', width:$(this).width()*0.16,
						formatter:function(value,row,index){
							var s = '<a href="#" onclick="moreInfo()">查看</a> ';
							var c = '<a href="#" onclick="editCourse()">编辑</a>';
							var d = '<a href="#" onclick="delCourse()"> 删除</a>';
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
		columns:[[{ field: 'bkname',title: '教材名称',align:'center', width:'150px'},
		          { field: 'writer',title: '作者',align:'center', width:'80px'},
		          { field: 'version',title: '版本',align:'center', width:'40px'},
		          { field:'publisher',title: '出版社',align:'center', width:'80px'}, 
		          { field: 'isbn',title: 'ISBN',align:'center', width:'80px'}
		]]
	});
	dg3=$('#dg3').datagrid({
		title:'请选择要关联的教材',
		method:'get',
		//url:'',
	    fit:true,
		toolbar:'#tb3',
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		fitClumns:true,
		columns:[[{field:'ck',checkbox:true},
				  { field: 'bkname',title: '教材名称',align:'center', width:'180px'},
		          { field: 'writer',title: '作者',align:'center', width:'80px'},
		          { field: 'version',title: '版本',align:'center', width:'40px'},
		          { field:'publisher',title: '出版社',align:'center', width:'100px'}, 
		          { field: 'isbn',title: 'ISBN',align:'center', width:'80px'}
		]]
	});
	dg4=$('#dg4').datagrid({
		title:'请选择要修改的关联教材',
		method:'get',
		//url:'',
	    fit:true,
		toolbar:'#tb4',
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		fitClumns:true,
		columns:[[{field:'ck',checkbox:true},
				  { field: 'bkname',title: '教材名称',align:'center', width:'180px'},
		          { field: 'writer',title: '作者',align:'center', width:'80px'},
		          { field: 'version',title: '版本',align:'center', width:'40px'},
		          { field:'publisher',title: '出版社',align:'center', width:'100px'}, 
		          { field: 'isbn',title: 'ISBN',align:'center', width:'80px'}
		]]
	});
});

/*精确查询显示与隐藏*/
function queryToggle(){
  $("#query").toggle();
}


/*新增教材对话框*/
//打开新增教材对话框
function addCourse(){
	$("#dlg2").dialog("open").dialog("setTitle","新增课程");	
}
//关闭新增对话框
function closeDialog2(){
	$("#dlg2").dialog("close");
}



/*编辑教材对话框*/
//打开编辑教材对话框
function editCourse(){
	$("#dlg3").dialog("open").dialog("setTitle","修改课程信息");	
}
//关闭编辑对话框
function closeDialog3(){
	$("#dlg3").dialog("close");
}




/*查看教材对话框*/
//打开查看教材对话框
function moreInfo(){
	$("#dlg1").dialog("open").dialog("setTitle","教材信息");
}
//关闭查看对话框
function closeDialog1(){
	$("#dlg1").dialog("close");
}














