var dgCourse,dgBook,url;

$(function(){
	dgCourse=$('#courseTable').datagrid({
		title:'课程信息',
		iconCls:'icon-save',
		method:'get',
		url:'../api/course/p1/n300',
	    fit:true,
	    width: $(window).width()*0.8,
		height: $(window).height(),
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		idField:'idcor',
		fitClumns:true,
		pageSize:50,
		pageList: [50,100,200],
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
		          { field:'idcor',title:'课程编号',align:'center',width:$(this).width()*0.11},
		          { field: 'corname',title: '课程名称',align:'center', width:$(this).width()*0.21},
		          { field: 'col',title: '院系',align:'center', width:$(this).width()*0.18},
		          { field: 'major',title: '专业',align:'center', width:$(this).width()*0.21},         
		          { field:'semnum',title: '专业总学期',align:'center', width:$(this).width()*0.08},
		          { field:'semester',title:'学期',align:'center'}
		]]
	}); 
	dgBook=$('#bookTable').datagrid({
	    title:'请选择要关联的教材',
		method:'get',
		url:'../api/book/all',
	    fit:true,
	    loadMsg:'数据加载中，请稍后...',
		fitClumns:true,
		pagination:true,
		idField:'idbk',
		toolbar:'#bookBtn',
		pageSize:10,
		pageList: [10,20],
		columns:[[{field:'ck',checkbox:true},
		          { field: 'bkname',title: '教材名称',align:'center',width:$(this).width()*0.2},
		          { field: 'author',title: '作者',align:'center'},
		          { field: 'edition',title: '版本',align:'center'},
		          { field:'publisher',title: '出版社',align:'center',width:$(this).width()*0.12}
		]]
	});
});
//	dg3=$('#dg3').datagrid({
//		
//		method:'get',
//		//url:'',
//	        fit:true,
//		toolbar:'#tb3',
//	        loadMsg:'数据加载中，请稍后...',
//		rownumbers:true,
//		fitClumns:true,
//		columns:[[{field:'ck',checkbox:true},
//		          { field: 'bkname',title: '教材名称',align:'center', width:'180px'},
//		          { field: 'writer',title: '作者',align:'center', width:'80px'},
//		          { field: 'version',title: '版本',align:'center', width:'40px'},
//		          { field:'publisher',title: '出版社',align:'center', width:'100px'}, 
//		          { field: 'isbn',title: 'ISBN',align:'center', width:'80px'}
//		]]
//	});
//	dg4=$('#dg4').datagrid({
//		title:'请选择要修改的关联教材',
//		method:'get',
//		//url:'',
//	    fit:true,
//		toolbar:'#tb4',
//	    loadMsg:'数据加载中，请稍后...',
//		rownumbers:true,
//		fitClumns:true,
//		columns:[[{field:'ck',checkbox:true},
//				  { field: 'bkname',title: '教材名称',align:'center', width:'180px'},
//		          { field: 'writer',title: '作者',align:'center', width:'80px'},
//		          { field: 'version',title: '版本',align:'center', width:'40px'},
//		          { field:'publisher',title: '出版社',align:'center', width:'100px'}, 
//		          { field: 'isbn',title: 'ISBN',align:'center', width:'80px'}
//		]]
//	});
//});

/*********************精确查询显示与隐藏******************************/
function queryToggle(){
  $("#query").toggle();
}
function searchCourseData(){
	url="../aip/course/key="+$('#courseData').val(),
	dgCourse.datagrid('load',url);
}

/**********************新增课程对话框*********************************/
//打开新增课程对话框
function openAddCouDlg(){
    $("#addCourseDlg").dialog("open").dialog("setTitle","新增课程");
    url="../api/course/new";
}
function saveAddCourse(){
	$("#infoCourse").form("submit",{
		url:url,
		onSubmit:function(){
			return $(this).form("validate");
		},
		success:function(data){
			var selectedRows=dgBook.datagrid('getSelections');
			if(selectedRows.length==0){
				$.messager.alert("系统提示","您没有关联课程！");
				$("#infoCourse").form("clear");
				dgCourse.datagrid('unselectAll');
				$("#addCourseDlg").dialog("close");
				dgCourse.datagrid("reload");
				return;
			}
			var strIds=[];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].idbk);
			}
			$.messager.confirm("系统提示","您确认关联<font color=red>"+selectedRows.length+"</font>本书籍？",function(r){
				if(r){
				   for(var i=0;i<strIds.length;i++){
					var ids=strIds[i];
					$.ajax({
						url:'../api/coursebk/cor='+data+'/bk='+ids,
						type:'post',
						dataType:'json',
						success:function(result){
							window.location.href='viewCourseBk.jsp';
							$('#courseBook').datagrid('load',"../api/coursebk/cor="+data);
						},
					    error: function (msg) { 
						alert(msg); 
						} 
					});
				  }
				}
			});
		}
	});
}
function closeAddCouDlg(){
	$("#addCourseDlg").dialog("close");
}








/*新增教材对话框*/
//打开新增教材对话框
function addBook(){
	$("#dlg4").dialog("open").dialog("setTitle","新增教材");	
}
//关闭新增对话框
function closeDialog4(){
	$("#dlg4").dialog("close");
}

function deleteCourse(){
	
}

/*编辑课程对话框*/
//打开编辑课程对话框
function editCourse(){
	$("#dlg3").dialog("open").dialog("setTitle","修改课程信息");	
}
//关闭编辑对话框
function closeDialog3(){
	$("#dlg3").dialog("close");
}




/*查看课程对话框*/
//打开查看课程对话框
function moreInfo(){
	$("#dlg1").dialog("open").dialog("setTitle","教材信息");
}
//关闭查看对话框
function closeDialog1(){
	$("#dlg1").dialog("close");
}














