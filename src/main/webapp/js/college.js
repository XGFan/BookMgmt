var dg1;
$(function(){
	colT=$('#collegeTable').datagrid({
		title:'院系信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'../api/college/p1/n300',
	    fit:true,
	    width: $(window).width()*0.8,
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:20,
		pageList: [20,50,100],
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
				  { field: 'idcm',title: '专业编号',align:'center', width:$(this).width()*0.16},
		          { field: 'col',title: '学院',align:'center', width:$(this).width()*0.25},
		          { field: 'major',title: '专业名称',align:'center', width:$(this).width()*0.3},
		          { field:'semnum',title: '总学期',align:'center', width:$(this).width()*0.2}
		]]
	}); 
});

var url;
/********************************查询显示与隐藏******************************/
function searchCol(){
	url="../api/college/"+$("#fuzzyCol").val();
	colT.datagrid('load',url);
}
function queryToggleCol(){
  $("#query").toggle();
}
/******************************添加专业对话框********************************/
function openAddDlgCol(){
	$('#addDlgCol').dialog('open').dialog('setTitle','添加学院/专业');
	url="../api/college/new";
}
function saveCollegeData(){
	$("#fmAddCol").form("submit",{
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
				$("#fmAddCol").form("clear");
				colT.datagrid('unselectAll');
				$('#addDlgCol').dialog('close');
				colT.datagrid("reload");
			}
		}
	});
}
function closeAddDlgCol(){
	$('#addDlgCol').dialog('close');
}
/*******************************编辑对话框*********************************/
function openEditDlgCol(){
	$('#editDlgCol').dialog('open').dialog('setTitle','编辑');
}
function closeEditDlgCol(){
	$('#editDlgCol').dialog('close');
}


/********************************删除对话框********************************/
function deleteCol(){
	var selectedRows=colT.datagrid("getSelections");
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	};
	
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].idcm);
	};
	$.messager.confirm("系统提示","将会一并删除与该专业有关的班级、课程、教学计划等信息。<br />您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			for(var i=0;i<strIds.length;i++){
			var ids=strIds[i];
			$.ajax({
				url:'../api/college/id='+ids,
				type:'delete',
				datatype:'json',
				success:function(result){
					colT.datagrid("reload");
				},
				error:function(msg){
					alert(msg);
				}
			});
		   }
		}
	});
}
//function openDialog3(){
//	$('#dlg3').dialog('open').dialog('setTitle','删除');
//}
//function closeDialog3(){
//	$('#dlg3').dialog('close');
//}











