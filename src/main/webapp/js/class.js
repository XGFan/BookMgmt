$(function(){
	dg1=$('#dg1').datagrid({
		title:'年级信息管理',
		iconCls:'icon-add',
		method:'get',
		url:'/bookmgmt/api/class',
	    fit:true,
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		idField:'idcls',
		fitClumns:true,
		pageSize:50,
		pageList: [50,100,150,200], 
		toolbar:'#tb1',
		columns:[[{ field:'id',title:'id',checkbox:true,width:50},
		          { field:'idcls',title:'班级编号',align:'center',width:100},
		          { field:'grade',title:'年级',align:'center',width:100},
		          { field: 'major',title: '专业',align:'center', width:100 },
		          { field: 'col',title: '学院',align:'center', width:100 },
		          { field: 'clsno',title: '班级数',align:'center', width:100 },
		          { field:'stunum',title: '人数',align:'center', width:100 }, 
		          { field: 'campus',title: '校区',align:'center', width:100 } 
		]]
	}); 
});
var url;
var dg1;
//模糊查询
function searchData(){
	url="/bookmgmt/api/class/"+ $("#searchs").val(),
	dg1.datagrid('load',url);
}

//添加
function addData(){
	$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
	
	var new1=$("#fm").serialize();
	//url="/bookmgmt/api/new";
	$.post(
		"/bookmgmt/api/class/new",
		 new1,function(js){
		 dg1.datagrid("reload");
		}
	);
}
//保存
function saveData(){
	$("#fm").form("submit",{
		url:url,
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				//resetValue();
				$("#fm").form('clear');
				dg1.datagrid('unselectAll');
				$("#dlg").dialog("close");
				dg1.datagrid("reload");
			}
		}
	});
}

//function resetValue(){
//	$('#grade').combobox("setValue","");
//	$('#col').combobox("setValue","");
//	$('#major').combobox("setValue","");
//	$('#campus').combobox("setValue","");
//	$('#clsno').val("");
//}
function closeDialog(){
	$("#dlg").dialog("close");
	$("#fm").form('clear');
}
function editData(){
	var selectedRows=dg1.datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要编辑的数据！");
		return;
	}
	var row=selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
	$("#fm").form("load",row);
	$.ajax({
		url:"/bookmgmt/api/class/"+row.idcls,
		type:'get',
		success:function(result){
			//$.messager.alert("系统提示","修改成功！");
			dg1.datagrid("reload");
		}
	   // error: function (msg) { 
		//alert(msg); 
		//} 
	});
	
}
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
	var ids=strIds.join(",");
	$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.ajax({
				url:'/bookmgmt/api/class/idcls/'+ids,
				type:'del',
				dataType:'json',
				success:function(result){
					$.messager.alert("系统提示","删除成功！");
					dg1.datagrid("reload");
				},
			    error: function (msg) { 
				alert(msg); 
				} 
			});
		}
	});
}














