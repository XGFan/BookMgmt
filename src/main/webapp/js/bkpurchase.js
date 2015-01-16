var dg1;

$(function(){
	dg1=$('#dg1').datagrid({
		title:'教材采购',
		iconCls:'icon-redo',
		method:'get',
		url:'../api/bkp/p1/n300',
	    fit:true,
	    width: $(window).width()*0.8,
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		pagination:true,
		fitClumns:true,
		pageSize:20,
		pageList: [20,30,40],
		toolbar:'#tb1',
		columns:[[
			{ field: 'bkname',title: '教材',align:'center', width:$(this).width()*0.11},
			      { field: 'col',title: '学院',align:'center', width:$(this).width()*0.05},
				  { field: 'major',title: '专业',align:'center', width:$(this).width()*0.21},
		          { field: 'writer',title: '作者',align:'center', width:$(this).width()*0.11},
				  { field: 'publisher',title: '出版社',align:'center', width:$(this).width()*0.13},
				  { field: 'bknum',title: '订购数量',align:'center', width:$(this).width()*0.10},
		          { field: 'campus',title: '校区',align:'center', width:$(this).width()*0.10},
		          { field: 'sup',title: '供应商',align:'center', width:$(this).width()*0.13}
		]]
	}); 
})
function queryToggleP(){
	$("#manager").toggle();
}







