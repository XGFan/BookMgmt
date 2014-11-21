package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bean.college.College;
import com.bean.college.CollegeDAO;
import com.priInfo.college.ColAction;

public class TestCollege {

    public static void main(String args[]) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ColAction clt = (ColAction) ctx.getBean("collegeAction");
        CollegeDAO clgDao = (CollegeDAO) ctx.getBean("CollegeDAO");
        //clt.list();//College(String idcm, String col, String major, Integer semnum)
        //College clg = new College("liub","liub","liub",9);
        //clt.add(clg);
        //clt.del("liub");
        //clg.setCol("liuei");
        //clt.update(clg);
        //clt.query("信息工程学院");
        clt.accurateQuery();
        List collist = clgDao.findByMajor("医学信息工程");
        System.out.println(collist.size());
        //clt.
        //System.out.println();
    }
}
