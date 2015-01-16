/**
 * 专业信息表的js代码
 */
var dg1,dg2;

//定义表
$(function(){
	dg1=$('#dg1').datagrid({
		title:'教材发放管理',
		iconCls:'icon-save',
		method:'get',
		//url:'',
	    fit:true,
	    width: function () { return document.body.clientWidth * 0.9 },
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:20,
		pageList: [20,50,100,200],
		toolbar:'#tb1',
		columns:[[{field:'ck',checkbox:true},
		          { field:'grade',title:'年级',align:'center',width:$(this).width() * 0.1},
		          { field: 'col',title: '学院',align:'center', width:$(this).width() * 0.18},
		          { field: 'major',title: '专业',align:'center',width:$(this).width() * 0.18},
		          { field: 'campus',title: '校区',align:'center', width:$(this).width() * 0.18},
		          { field: 'clsnum',title: '班号',align:'center', width:$(this).width() * 0.1},
		          { field: 'clsnum',title: '学期',align:'center', width:$(this).width() * 0.1}
		]]
	}); 

//	dg2=$('#tt').datagrid({
//		title:'教材发放单',
//		iconCls:'icon-edit',
//		url:'',
//		rownumbers:true,
//		fit:true,
//		singleSelect:true,
//		idField:'rownumbers',
//		pagination:true,
//		fitClumns:true,
//		pageSize:20,
//		pageList: [20,50,100], 
//		columns:[[
//            {field:'grade',title:'年级',width:$(this).width() * 0.1,align:'center'},
//            {field:'col',title:'学院',width:$(this).width() * 0.2,align:'center'},
//            {field:'major',title:'专业',width:$(this).width() * 0.25,align:'center'},
//            {field:'book',title:'教材名称',width:$(this).width() * 0.25,align:'center'},
//			  {field:'stunum',title:'数量',width:$(this).width() * 0.2,align:'center'}
//		]]
//	});
});

/*精确查询显示与隐藏*/
$(document).ready(function(){
	  $("#btn").click(function(){
	    $("#preciseQuery").slideToggle();
	  });
});














