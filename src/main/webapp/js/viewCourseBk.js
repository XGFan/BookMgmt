/**
 * 2015/1/11 0:00
 * by zhengshuaishuai
 */
$(function(){
	$('#courseBook').datagrid({
		title:'课程-教科书一览表',
		iconCls:'icon-save',
		method:'get',
		url:'../api/coursebk/p1/n300',
	    fit:true,
		toolbar:'#viewBtn',
		idField:'id',
	    loadMsg:'数据加载中，请稍后...',
		rownumbers:true,
		fitClumns:true,
		row:50,
		pagination:true,
		pageSize:50,
		pageList: [50,100,150,200],
		columns:[[{field:'ck',checkbox:true},
				  { field:'idcor',title:'课程编号',align:'center',width:$(this).width()*0.08},
		          { field:'corname',title: '课程名称',align:'center',width:$(this).width()*0.15},
		          { field:'col',title: '院系',align:'center',width:$(this).width()*0.15},
		          { field:'major',title: '专业',align:'center',width:$(this).width()*0.15},
		          { field:'bkname',title:'所用书籍',align:'center',width:$(this).width()*0.2},
		          { field:'edition',title:'书籍版本',align:'center',width:$(this).width()*0.08},
		          { field:'semester',title: '学期',align:'center',width:$(this).width()*0.08}
		]]
	});
});
var url;
function searchCourseData(){
	url="../api/coursebk/cor="+$("#searchCourseBks").val(),
	$('#courseBook').datagrid('load',url);
}