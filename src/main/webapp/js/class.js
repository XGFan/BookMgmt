$(function(){
	dg1=$('#dg1').datagrid({
		title:'年级信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'api/class',
	    fit:true,
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		idField:'idcls',
		fitClumns:true,
		pageSize:50,
		pageList: [50,100,150,200], 
		toolbar:'#tb1',
		columns:[[{ field:'ck',title:'ck',checkbox:true,width:50},
		          { field:'grade',title:'年级',align:'center',width:100},
		          { field: 'major',title: '专业',align:'center', width:100 },
		          { field: 'col',title: '学院',align:'center', width:100 },
		          { field: 'clsno',title: '班级号',align:'center', width:100 },
		          { field:'stunum',title: '人数',align:'center', width:100 }, 
		          { field: 'campus',title: '校区',align:'center', width:100 } 
		]]
	}); 
});
var url;
var dg1;
var ids;
//模糊查询
function searchData(){
	url="api/class/"+ $("#searchs").val(),
	dg1.datagrid('load',url);
}
//高级查询效果控制
$(document).ready(function(){
	  $("#btn").click(function(){
	    $("#preciseQuery").slideToggle();
	  });
});
//高级查询
function searchP(){
	url="api/class/"+$("#campusa").val()+"/"+$("#cola").val()+"/"+$("#majora").val()+"/"+$('#gradea').val(),
	dg1.datagrid('load',url);
}
//添加
function addData(){
	$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
	url="api/class/new";
}
//保存
function saveData(){
	$("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			return $(this).form("validate");
		},
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				//$.messager.alert("系统提示","保存成功");
				//resetValue();
				$("#fm").form("clear");
				dg1.datagrid('unselectAll');
				$("#dlg").dialog("close");
				dg1.datagrid("reload");
			}
		}
	});
}
function resetValue(){
	$("#grade1").val("");
	$("#col1").val("");
	$("#major1").val("");
	$("#campus1").val("");
	$("#clsnum1").val("");
}
function closeDialog(){
	$("#dlg").dialog("close");
	$("#fm").form("clear");
}
//编辑数据
function editData(){
	var selectedRows=dg1.datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要编辑的数据！");
		return;
	}
	var row=selectedRows[0];
	$("#editDlg").dialog("open").dialog("setTitle","编辑班级信息");
	$("#fm").form("load",row);
	ids=row.idcls;
	$("#grade1").val('row.grade');
	$("#col1").value=row.col;
	$("#major1").value=row.major;
	alert(row.major);
	$("#campus1").value=row.campus;
	
//	$.ajax({
//		type:'get',
//		url:"api/class/"+row.idcls+"/"+$("#clsnum").val(),
//		success:function(data){
//		}
//	});
}
function saveEdit(){
	alert($("#clsnum").val());
	$.ajax({
		url:"api/class/"+ids+"/"+$("#clsnum1").val(),
		type:'get',
		success:function(data){
			resetValue();
			$("#editDlg").dialog("close");
			dg1.datagrid("reload");
		}
	});
}
function closeEdit(){
	$("#editDlg").dialog("close");
	resetValue();	
}
//var a="api/class/"+row.idcls+"/"+$("#clsnum1").val();
//,{
//	type:"get",
//	url:"api/class/"+row.idcls+"/"+$("#clsnum").val(),
//	success: function(){
//	}
//});
//url="api/class/new?idcls"+row.idcls;
//$.ajax({
//	type:'get',
//	data:'',
//	url:"api/class/"+row.idcls+"/"+$("#clsnum").val(),
//	success:function(data){
//		alert(row.idcls+"****"+$("#clsnum").val());
//	}
//});
//删除
function deleteData(){
	var selectedRows=dg1.datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].idcls);
	}
	$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
		   for(var i=0;i<strIds.length;i++){
			var ids=strIds[i];
			$.ajax({
				url:'api/class/idcls/'+ids,
				type:'delete',
				dataType:'json',
				success:function(result){
					dg1.datagrid("reload");
				},
			    error: function (msg) { 
				alert(msg); 
				} 
			});
		  }
		}
	});
}














