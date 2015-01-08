var dg1;
$(function(){
	dg1=$('#dg1').datagrid({
		title:'院系信息管理',
		iconCls:'icon-save',
		method:'get',
		//url:'',
	    fit:true,
	    width: $(window).width()*0.8,
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:20,
		pageList: [10,20,50,100],
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
				  { field: 'majno',title: '专业编号',align:'center', width:$(this).width()*0.14},
		          { field: 'col',title: '学院',align:'center', width:$(this).width()*0.21},
		          { field: 'major',title: '专业名称',align:'center', width:$(this).width()*0.25},
		          { field:'sem',title: '总学期',align:'center', width:$(this).width()*0.1}
		]]
	}); 
})

/*查询显示与隐藏*/
function queryToggle(){
  $("#query").toggle();
}


/*添加专业对话框*/
function openDialog1(){
	$('#dlg1').dialog('open').dialog('setTitle','添加专业');
}
function closeDialog1(){
	$('#dlg1').dialog('close');
}



/*编辑对话框*/
function openDialog2(){
	$('#dlg2').dialog('open').dialog('setTitle','编辑');
}
function closeDialog2(){
	$('#dlg2').dialog('close');
}


/*删除对话框*/
function openDialog3(){
	$('#dlg3').dialog('open').dialog('setTitle','删除');
}
function closeDialog3(){
	$('#dlg3').dialog('close');
}











