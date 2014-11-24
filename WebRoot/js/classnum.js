/**
 * Created by Administrator on 14-11-13.
 */
var url;

function searchGrade(){
    $("#dg").datagrid("load",{
        gradeName:$("#s_gradeName").val()
    });
}

function clearSearchGrade(){
    $("#tb").find("input").val("");
}

function openGradeModifyDialog(){
    var selectedRows=$("#dg").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一行数据");
        return;
    }
    var row=selectedRows[0];
    row.field("num")
}

function deleteGrade(){
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length==0){
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
    }
    var strIds=[];
    for(var i=0;i<selectedRows.length;i++){
        strIds.push(selectedRows[i].id);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
        if(r){
            $.post("gradeDelete",{delIds:ids},function(result){
                if(result.success){
                    $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].gradeName+'</font>'+result.errorMsg);
                }
            },"json");
        }
    });
}

function openGradeModifyDialog(){
    var selectedRows=$("#dg").datagrid('getSelections');
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row=selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","修改班级人数");
    $("#fm").form("load",row);
    url="gradeSave?id="+row.id;
}


function saveClassNum(){
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
                $.messager.alert("系统提示","修改成功");
                resetValue();
                $("#dlg").dialog("close");
                $("#dlg").datagrid("reload");
            }
        }
    });
}


function closeGradeDialog(){
    $("#dlg").dialog("close");
    resetValue();
}


function resetValue(){
    $("#gradeName").val("");
    $("#gradeDesc").val("");
}
