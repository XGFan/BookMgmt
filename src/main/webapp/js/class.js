$(function(){
	dg1=$('#dg1').datagrid({
		title:'年级信息管理',
		iconCls:'icon-save',
		method:'get',
		url:'api/class/p1/n300',
	    fit:true,
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		idField:'idcls',
		fitClumns:true,
		rows:50,
		pageSize:50,
		pageList: [50,100,150,200], 
		toolbar:'#tb1',
		columns:[[{ field:'ck',title:'ck',checkbox:true,width:50},
		          { field:'grade',title:'年级',align:'center',width:$(this).width()*0.1},
		          { field: 'major',title: '专业',align:'center', width:$(this).width()*0.2},
		          { field: 'col',title: '学院',align:'center', width:$(this).width()*0.18},
		          { field: 'clsno',title: '班级号',align:'center', width:$(this).width()*0.1},
		          { field:'stunum',title: '人数',align:'center', width:$(this).width()*0.1}, 
		          { field: 'campus',title: '校区',align:'center', width:$(this).width()*0.1} 
		]]
	}); 
});
var url;
var dg1;
var ids;
var grades;
var cols;
var majors;
var campuss;

/************************数据查询**************************/
function commons(url,o,f,value){
	$.ajax({
		type : "get",
		dataType : "json",
		async:false,
		url : url,
		success : function(data){
			o = "";
			o += "[";
			for(var i=0;i<data.length;i++){
				o += "{\"id\":\""+i+"\",\"value\":\""+data[i]+"\"},";
			}
			o = o.substring(0,o.length-1);
			o += "]";
			o=strToJSON(o);
			f.combobox({
				valueField:'id',
				textField:'value',
				data:o
			});
		}
	  });
}
//模糊查询
function searchData(){
	url="api/class/key="+ $("#searchs").val(),
	dg1.datagrid('load',url);
}
function strToJSON(str){
	return JSON.parse(str);
}
//高级查询效果控制
$(document).ready(function(){
	  $("#btn").click(function(){
	    $("#preciseQuery").slideToggle();
	  }); 
	  commons("api/grade",grades,$('#gradea'),grade);
      commons("api/campus",campuss,$('#campusa'),campus);
	  $.ajax({
			type : "get",
			dataType : "json",
			async:false,
			url : 'api/col',
			success : function(data){
				 cols= "";
				 cols += "[";
				for(var i=0;i<data.length;i++){
					cols += "{\"id\":\""+i+"\",\"col\":\""+data[i]+"\"},";
				}
				cols = cols.substring(0,cols.length-1);
				cols += "]";
				cols=strToJSON(cols);
				$('#cola').combobox({
					valueField:'id',
					textField:'col',
					data:cols,
					onSelect:function(rs){
						$.ajax({
							type : "get",
							dataType : "json",
							async:false,
							url : 'api/major/'+rs.col,
							success : function(data){
								majors = "";
								majors += "[";
								for(var i=0;i<data.length;i++){
									majors += "{\"id\":\""+i+"\",\"major\":\""+data[i]+"\"},";
								}
								majors = majors.substring(0,majors.length-1);
								majors += "]";
								majors=strToJSON(majors);
								$('#majora').combobox({
									valueField:'id',
									textField:'major',
									data:majors
								});
							}
						  });
					}
				});
			}
	  });	
	   
});
/************************数据查询****************************/
function searchP(){
	url="api/class/"+$('#campusa').combobox('getText')+"/"+$("#cola").combobox('getText')+"/"+$("#majora").combobox('getText')+"/"+$("#gradea").combobox('getText'),
	dg1.datagrid('load',url);
}
/************************添加数据**************************/
function addData(){
	$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
	url="api/class/new";
}
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
				$.messager.alert("系统提示","保存成功");
				//resetValue();
				$("#fm").form("clear");
				dg1.datagrid('unselectAll');
				$("#dlg").dialog("close");
				dg1.datagrid("reload");
			}
		}
	});
}
function closeDialog(){
	$("#dlg").dialog("close");
	$("#fm").form("clear");
}
/************************编辑数据**************************/
function editData(){
	var selectedRows=dg1.datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择一条要编辑的数据！");
		return;
	}
	var row=selectedRows[0];
	$("#dlg1").dialog("open").dialog("setTitle","编辑年级信息");
	$("#fm1").form("load",row);
}
function saveEditData(){
	var selectedRows=dg1.datagrid('getSelections');
	if(selectedRows.length!=1){
		return;
	}
	var row=selectedRows[0];
	$.ajax({
		type:'put',
		url:'api/class/'+row.idcls+'/'+$("#clsnum1").val(),
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}else{
				$.messager.alert("系统提示","保存成功");
				$("#fm1").form("clear");
				$(" #dlg1").dialog("close");
				dg1.datagrid("reload");
			}
		}
	});	
}
function closeEditDialog(){
	$("#dlg1").dialog("close");
	$("#fm1").form("clear");
}
/************************删除数据**************************/
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














