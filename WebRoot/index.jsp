<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <head>
    <title></title>
    <link href="easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <script src="easyui/jquery.min.js" type="text/javascript"></script>
    <script src="easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="js/index.js" type="text/javascript"></script>
  </head>
  
<body class="easyui-layout" style="overflow-y: hidden;margin: 5px;">
    <div data-options="region:'north',title:'湖北中医药大学教材采购管理系统',split:false" style="height:160px;">
      <div align="left" style="width:100%; height:100%; z-index:-1">
      <img src="images/top1.jpg" height="100%" width="100%">
      </div>
    </div>  
    <div region="south" style="height: 25px; background: #D2E0F2;" align="center">版权所有<a href="#">ZSS</a></div>
 
    <div region="west" split="true" collapsible="false" title="导航菜单" style="width: 180px;overflow:hidden;" iconCls="icon-redo">
        <div id="menu" class="easyui-accordion" fit="true" border="false">
            <div title="班级信息" style="overflow:auto; padding: 10px;" iconCls="icon-edit">
                <div title="班级信息管理">
                    <ul>
                        <li>
                            <div>
                                <a target="mainFrame" href="multiple/class.jsp">班级信息管理</a></div>
                        </li>
                    </ul>
                </div>
            </div>
            <div title="教材信息" style="padding: 10px;" iconCls="icon-edit">
                <div title="教材信息管理">
                    <ul>
                        <li>
                            <div>
                                <a  href="#">教材信息管理</a></div>
                        </li>
                    </ul>
                </div>
            </div>
            <div title="关于" iconCls="icon-help">
              <div align="center" style="padding-top:10px; "><font color="red" size="4">ZSS.郑帅帅</font></div>
            </div>
        </div>
    </div>
    <div region="center" id="mainPanle" style="background: #eee;overflow:hidden;">
        <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="主页" style="padding: 40px;" id="home">
             <div align="center" style="padding-top: 150px;"><font color="red" size="7">欢迎使用湖北中医药大学教材采购管理系统</font>
             </div>
        </div>
    </div>
</body>
 </body>
</html>
