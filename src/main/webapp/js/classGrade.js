/**
 * 专业信息表的js代码
 */
////定义表
$(function(){
	$('#tt').datagrid({
		title:'专业信息管理',
		iconCls:'icon-edit',
		url:'../api/class/p1/n300',
		method:'get',
		rownumbers:true,
		fit:true,
		singleSelect:true,
		idField:'idcls',
		pagination:true,
		fitClumns:true,
		pageSize:50,
		toolbar:'#cBkTbn',
		pageList: [50,100,200], 
		columns:[[
			{field:'ck',title:'ck',checkbox:true},
			{field:'grade',title:'年级',width:$(this).width()*0.1,align:'center'},
			{field:'col',title:'学院',width:$(this).width()*0.15,align:'center'},
			{field:'major',title:'专业',width:$(this).width()*0.2,align:'center'},
			{field:'campus',title:'校区',width:$(this).width()*0.1,align:'center'},
            {field:'clsno',title: '班级号',width:$(this).width()*0.09,align:'center'},
			{field:'stunum',title:'人数',width:$(this).width()*0.1,align:'center',
				editor:{
					type:'text',
					required:true
				}
			},
			{field:'action',title:'编辑',width:$(this).width()*0.2,align:'center',
				formatter:function(value,row,index){
					if (row.editing){
						var s = '<a href="#" onclick="saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="deleterow(this)">删除</a>';
						return e+d;
					}
				}
			}
		]],
//		状态转换函数
		onBeforeEdit:function(index,row){
			row.editing = true;
			updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			updateActions(index);
		}
	});
	
});
/*****************数据查询*************************/
function refresh(){
	$('#tt').datagrid('reload');
}
function fuzzyGrade(){
	url="api/class/key="+ $("#searchGrade").val(),
	$('#tt').datagrid('load',url);
}
/*******************编辑函数**********************/

function updateActions(index){//更新
	$('#tt').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
function getRowIndex(target){//获取目标行
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){//编辑
	$('#tt').datagrid('beginEdit',getRowIndex(target));
}
function deleterow(target){//删除
	$.messager.confirm('确认框','你确定删除?',function(r){
		if (r){
			$('#tt').datagrid('unselectAll');
			$('#tt').datagrid('selectRow',getRowIndex(target));
			var selectedRows=$('#tt').datagrid('getSelections');
			var row=selectedRows[0];
			$.ajax({
				url:'../api/class/idcls/'+row.idcls,
				type:'delete',
				dataType:'json',
				success:function(result){
					$('#tt').datagrid("reload");
				},
			    error: function (msg) { 
					$.messager.alert("系统提示",msg); 
				} 
			});
		}
	});
}
function saverow(target){
	var num=$('#tt').datagrid('getEditor',{index:getRowIndex(target),field:'stunum'});
	var snum=num.target.val();
	$('#tt').datagrid('unselectAll');
	$('#tt').datagrid('selectRow',getRowIndex(target));
	var selectedRows=$('#tt').datagrid('getSelections');
	var row=selectedRows[0];
	$.ajax({
		type:'put',
		url:'../api/class/'+row.idcls+'/'+snum,
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				$('#tt').datagrid('endEdit', getRowIndex(target));
				$("#tt").datagrid('reload');
			}
		}
	});
}
function cancelrow(target){//取消
	$('#tt').datagrid('cancelEdit', getRowIndex(target));
	$('#tt').datagrid('unselectAll');
}
















