package com.test;

import java.util.List;
import java.util.Map;

import com.util.SendData;
import com.bean.corplan.*;
import com.priInfo.corplan.CorplanService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCorplan {
	public static void main(String[] args) {
		  ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		  CorplanService corplanService = (CorplanService)ctx.getBean("corplanService");
//**测试查找所有的记录：
		  //corplanService.dropAllCorplan();
		  //corplanService.initCorplan();
//		  List collist = corplanService.findAll();
//		  SendData.send(collist);
//		  for(int i = 0; i < collist.size(); i++){
//			  Map temp = (Map) collist.get(i);
//			  System.out.println(temp.get("idcorsem")+","+temp.get("col"));
//		  }
//		  System.out.println(collist.size());
		  corplanService.updateCorplan("信息工程学院","医学信息工程","JQuery","8");
		  
//**测试按照学院查找：
//		  List collist = corplanService.findCorplanByCol("信息工程");
//		  SendData.send(collist);
//		  System.out.println(collist.size());
		  
//**测试按照学院和专业查找：
//		  List collist = corplanService.findCorplanByColMajor("信息工程","信息工程");
//		  SendData.send(collist);
//		  System.out.println(collist.size());
		  
//**测试按照学院或专业查找：
//		  List collist = corplanService.findCorplanByColOrMajor("信息工程");
//		  SendData.send(collist);
//		  System.out.println(collist.size());
		  
//**测试按照学院，专业,学期模糊查找：
//		  List collist = corplanService.fuzzyFind("1");
//		  SendData.send(collist);
//		  System.out.println(collist.size());
		  
//**测试按照学院，专业，和课程名查找：
//		  List collist = corplanService.findCorplanByColMajorSem("信息工程","信息工程","");
//		  SendData.send(collist);
//		  System.out.println(collist.size());
		  
//??测试添加一条Corplan记录：
		  
//??测试修改一条Corplan记录：
		  
	}

}
