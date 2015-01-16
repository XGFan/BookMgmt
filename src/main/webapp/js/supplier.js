var dgSupplier;
$(function(){
	dgSupplier=$('#supplierTable').datagrid({
		title:'供应商信息查询',
		iconCls:'icon-save',
		method:'get',
		url:'../api/sup/all',
	    fit:true,
	    idField:'idsp',
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:10,
		pageList: [10,20,50],
		toolbar:'#supplierBtn',
		columns:[[{field:'ck',checkbox:true},
				  { field: 'idsp',title: 'IDSP',align:'center', width:$(this).width()*0.16},
		          { field: 'publisher',title: '出版社',align:'center', width:$(this).width()*0.21},
		          { field: 'supplier',title: '供应商',align:'center', width:$(this).width()*0.25}
		]]
	}); 
});
/***************************查询数据*****************************/
function queryToggleCol(){
	  $("#query").toggle();  
}

/***************************添加数据*****************************/
function openAddSupDlg(){
	$('#addSupDlg').dialog('open').dialog('setTitle','添加供应信息');
	url="../api/sup/new";
}
function saveAddData(){
	$("#fmSupplier").form("submit",{
		url:url,
		onSubmit:function(){
			return $(this).form("validate");
		},
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				$("#fmSupplier").form("clear");
				dgSupplier.datagrid('unselectAll');
				$("#addSupDlg").dialog("close");
				dgSupplier.datagrid("reload");
			}
		}
	});
}
function closeAddSupDlg(){
	$('#addSupDlg').dialog('close');
}
/*************************编辑数据**********************************/


























