/**
 * 专业信息表的js代码
 */
//定义假数据，以下是从后台获得
var grades="/bookmgmt/api/class/grade/grade";
var colleges="/bookmgmt/api/class/clo";
var majors="/bookmgmt/api/class/major";
var campuss="/bookmgmt/api/class/campus";
//定义表
$(function(){
	$('#tt').datagrid({
		title:'专业信息管理',
		iconCls:'icon-edit',
		url:'api/class',
		method:'get',
		rownumbers:true,
		fit:true,
		singleSelect:true,
		idField:'rownumbers',
		pagination:true,
		fitClumns:true,
		pageSize:100,
		pageList: [50,100,200], 
		columns:[[
			{field:'grade',title:'年级',width:100,align:'center',
				formatter:function(value){
					for(var i=0; i<grades.length; i++){
						if (grades[i].grade == value) return grades[i].name;
					}
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'grade',
						textField:'name',
						data:grades,//获取年级信息
						required:true
					}
				}
			},
			{field:'col',title:'学院',width:160,align:'center',
	        	formatter:function(value){
	        		for(var i=0;i<colleges.length;i++){
	        			if(colleges[i].col==value) return colleges[i].name;
	        		}
	        		return value;
	        	},
	    		editor:{
					type:'combobox',
					options:{
						valueField:'col',
						textField:'name',
						data:colleges,//获取学院信息
						required:true
					}		        	
	        	}	
			},
			
			{field:'major',title:'专业',width:160,align:'center',
	        	formatter:function(value){
	        		for(var i=0;i<majors.length;i++){
	        			if(majors[i].major==value) return majors[i].name;
	        		}
	        		return value;
	        	},
	    		editor:{
					type:'combobox',
					options:{
						valueField:'major',
						textField:'name',
						data:majors,//获取专业信息
						required:true
					}		        	
	        	}
            },
			{field:'campus',title:'校区',width:50,align:'center',
	        	formatter:function(value){
	        		for(var i=0;i<campuss.length;i++){
	        			if(campuss[i].campus==value) return campuss[i].name;
	        		}
	        		return value;
	        	},
	    		editor:{
					type:'combobox',
					options:{
						valueField:'campus',
						textField:'name',
						data:campuss,//获取校区信息
						required:true
					}		        	
	        	}
			},
			{field:'stunum',title:'人数',width:100,editor:'text'},
			{field:'action',title:'编辑',width:120,align:'center',
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
		//状态转换函数
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
	$('#tt').datagrid('beginEdit', getRowIndex(target));
}
function deleterow(target){//删除
	$.messager.confirm('确认框','你确定删除?',function(r){
		if (r){
			$('#tt').datagrid('deleteRow', getRowIndex(target));//使用的是easyui封装的删除函数，这里是做演示
		}
	});
}
function saverow(target){//保存演示用法的
	$('#tt').datagrid('endEdit', getRowIndex(target));
	$("#tt").datagrid("reload");
}
function cancelrow(target){//取消
	$('#tt').datagrid('cancelEdit', getRowIndex(target));
}