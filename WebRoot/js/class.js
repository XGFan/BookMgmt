$(function(){
	dg1=$('#dg1').datagrid({
		title:'年级信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'class.json',
	    fit:true,
	    loadMsg:'数据加载中，请稍后。。。',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:10,
		pageList: [10, 20, 30], 
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
		          { field:'grade',title:'年级',align:'center',width:100},
		          { field: 'college',title: '学院',align:'center', width:100 },
		          { field: 'major',title: '专业',align:'center', width:100 },
		          { field: 'campus',title: '校区',align:'center', width:100 },
		          { field: 'clsnum',title: '班级数目',align:'center', width:100 },
		          { field: 'idcls',title: '班号',align:'center', width:100 },
		          { field:'stunum',title: '人数',align:'center', width:100 } 
		]]
	}); 
})
$(function(){
	dg2=$('#dg2').datagrid({
		title:'班级信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'class.json',
	    fit:true,
	    loadMsg:'数据加载中，请稍后。。。',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		onClickRow:'onClickRow',
		pageSize:10,
		pageList: [10, 20, 30], 
		toolbar:'#tb2',
		columns:[[
		          { field:'grade',title:'年级',align:'center',width:212},
		          { field: 'college',title: '学院',align:'center', width:261 },
		          { field: 'major',title: '专业',align:'center', width:286 },
		          { field: 'campus',title: '校区',align:'center', width:210 },
		          { field:'stunum',title: '人数',align:'center', width:141 }
		]]
	}); 
	$("#dg2").datagrid("reload");
})
$(function(){
	dg3=$('#dg3').datagrid({
		title:'年级信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'class.json',
	    fit:true,
	    loadMsg:'数据加载中，请稍后。。。',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:10,
		onClickRow:'onClickRow',
		pageList: [10, 20, 30], 
		toolbar:'#tb3',
		columns:[[
		          { field:'grade',title:'年级',align:'center',width:100},
		          { field: 'college',title: '学院',align:'center', width:100 },
		          { field: 'major',title: '专业',align:'center', width:100 },
		          { field: 'campus',title: '校区',align:'center', width:100 },
		          { field: 'idcls',title: '班号',align:'center', width:100 },
		          { field:'stunum',title: '人数',align:'center', width:100 }
		]]
	}); 
})
var url;
var editIndex = undefined;
var dg1,dg2,dg3;
function addData(){
	$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
	url="保存URL";	
}
function saveData(){
	$("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			if($('#grade').combobox("getValue")==""){
				$.messager.alert("系统提示","请选择年级");
				return false;
			}
			if($('#college').combobox("getValue")==""){
				$.messager.alert("系统提示","请选择学院");
				return false;
			}
			if($('#major').combobox("getValue")==""){
				$.messager.alert("系统提示","请选择专业");
				return false;
			}
			if($('#campus').combobox("getValue")==""){
				$.messager.alert("系统提示","请选择校区");
				return false;
			}
			if($('#stunum').val()==""){
				$.messager.alert("系统提示","请填入人数");
				return false;
			}
			return $(this).form("validate");
		},
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				resetValue();
				$("#dlg").dialog("close");
				$("#dg1").datagrid("reload");
			}
		}
	});
}
function resetValue(){
	$("#grade").val("");
	$("#college").val("");
	$("#major").val("");
	$("#campus").val("");
	$("#clsnum").val("");
	$("#idcls").val("");
	$("#stunum").val("");
}
function closeDialog(){
	$("#dlg").dialog("close");
	resetValue();
}
function editData(){
	var selectedRows=$("#dg1").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要编辑的数据！");
		return;
	}
	var row=selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
	$("#fm").form("load",row);
	url="保存数据?stuId="+row.grade;
}
/*为连接service准备的
function deleteData(){
	var selectedRows=$('#dg1').datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].id);
	}
	var ids=strIds.join(",");
	$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRow.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("URL",{delIds:ids},function(result){
				if(result.success){
                    $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                    $("#dg1").datagrid("reload");		
				}else{
					$.messager.alert("系统提示",result.errorMsg)
				}
			},"json");
		}
	});
}
*/
function deleteData(){
	var selectedRows=dg1.datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的数据！");
		return;
	}
	else{
		$.messager.confirm(' ','你确定要删除该条记录？',function(r){
	      if(r){
		    dg1.datagrid('cancelEdit', editIndex)
				    .datagrid('deleteRow', editIndex);
		     editIndex = undefined;
		  }
		})
	}
}

function endEditing(a){
	if (editIndex == undefined){return true}
	if ($(a).datagrid('validateRow', editIndex)){
		var ed = $(a).datagrid('getEditor', {index:editIndex,field:'productid'});
		var productname = $(ed.target).combobox('getText');
		$(a).datagrid('getRows')[editIndex]['productname'] = productname;
		$(a).datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow(index,a){
	if(editIndex!=index){
		if(endEditing()){
			$(a).datagrid('selectRow',index)
			             .datagrid('beginEdit',index);
			editIndex=index;
		}else{
			$(a).datagrid('selectRow',editIndex);
		}
	}
}

/*
function editData(a){
	if(endEditing(a)){
		$(a).datagrid('appendRow');
		editIndex=$(a).datagrid('getRows').length-1;
		$(a).datagrid('selectRow',editIndex)
		              .datagrid('beginEdit',editIndex);
	}
}
function deleteData(a){
	if (editIndex == undefined){return}
	$(a).datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
}
*/















