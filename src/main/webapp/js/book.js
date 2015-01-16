var dgBook,dgCourse,dgCourse,dg4;

$(function(){
 dgBook=$('#tableBook').datagrid({
		title:'教材信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'../api/book/p1/n300',
	    fit:true,
	    width: $(window).width()*0.8,
		height: $(window).height(),
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		idField:'idbk',
		pagination:true,
		fitClumns:true,
		pageSize:20,
		pageList: [10,20,50,100],
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
				  { field:'idbk',title:'教材编号',align:'center',width:$(this).width()*0.1},
		          { field: 'bkname',title: '教材名称',align:'center', width:$(this).width()*0.15},
		          { field: 'author',title: '作者',align:'center', width:$(this).width()*0.1},
		          { field: 'edition',title: '版本',align:'center', width:$(this).width()*0.06},
		          { field:'publisher',title: '出版社',align:'center', width:$(this).width()*0.12}, 
		          { field: 'isbn',title: 'ISBN',align:'center', width:$(this).width()*0.1} ,
		          { field:'price',title: '价格',align:'center', width:$(this).width()*0.08}, 
		          { field:'supplier',title:'供应商',align:'center'},
		          { field:'memo',title:'备注',align:'center',width:$(this).width()*0.10}
		]]
	}); 
	
	 //courseBook
 
});

/**/


/*****************查询显示与隐藏****************************/
function queryToggle(){
  $("#query").toggle();
}
function searchBooks(){
	url="../api/book/key="+$("#fuzzybkns").val(),
	dgBook.datagrid("load",url);
}
/**************************新增教材对话框********************/

function addBookData(){
	$("#addBookDlg").dialog("open").dialog("setTitle","新增教材");	
	url="../api/book/new";
}
function savaAddBkData(){
	$("#addBookinfo").form("submit",{
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
				$("#addBookinfo").form("clear");
				dgBook.datagrid('unselectAll');
				$("#addBookDlg").dialog("close");
				dgBook.datagrid("reload");
			}
		}
	});
}
function closeAddBkDlg(){
	$("#addBookDlg").dialog("close");
}

/***************************编辑教材对话框**********************/

function editBookData(){
	var selectedRows=dgBook.datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要编辑的数据！");
		return;
	}
	var row=selectedRows[0];
	$("#editBookDlg").dialog("open").dialog("setTitle","编辑书籍信息");
	$("#editBookinfo").form("load",row);
}
function savaEditBkData(){
	var selectedRows=dgBook.datagrid('getSelections');
	if(selectedRows.length!=1){
		return;
	}
	var row=selectedRows[0];
	var options={
			url:'../api/book/'+row.idbk,
			type:'put',
			datatype:'json',
			//data:{},
			beforeSubmit:function(formData,jqForm,options){
				return $("#editBookinfo").form('validate');
			}
		}
	$("#editBookinfo").ajaxSubmit(options);
//	$.ajax({
//		type:'put',
//		url:'../api/book/'+row.idbk,
//		success:function(result){
//			if(result.errorMsg){
//				$.messager.alert("系统提示",result.errorMsg);
//				return;
//			}else{
//				$.messager.alert("系统提示","保存成功");
//				$("#editBookinfo").form("clear");
//				$("#editBookDlg").dialog("close");
//				dgBook.datagrid("reload");
//			}
//		}
//	});	
}
function closeEditBkDlg(){
	$("#editBookDlg").dialog("close");
}
/***********************删除数据***************************/
function deleteBookData(){
	var selectedRows=dgBook.datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].idbk);
	}
	$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
		   for(var i=0;i<strIds.length;i++){
			var ids=strIds[i];
			$.ajax({
				url:'../api/book/id='+ids,
				type:'delete',
				dataType:'json',
				success:function(result){
					dgBook.datagrid("reload");
				},
			    error: function (msg) { 
				alert(msg); 
				} 
			});
		  }
		}
	});
}











