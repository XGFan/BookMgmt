package com.test;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.course.CourseDAO;
import com.priInfo.course.CourseService;

public class TestCourse {
    public static void main(String args[]) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        CourseService courseService = (CourseService) ctx
                .getBean("courseService");

        CourseDAO courseDAO = (CourseDAO) ctx.getBean("CourseDAO");

        System.out.println(courseService.addNewCourse("", "信息工程学院", "医学信息工程", "JAVA2程序设计", "5"));

        // List list = courseDAO.findByCorname("军事训练");
        // System.out.println(list.size());
        // for (int i = 0;i<list.size();i++){
        // System.out.println(list.get(i).toString());
        // }
        List list = null;
        list = courseService.init();
        list = courseService.findByColMajor("", "医学信息工程");
        list = courseDAO.fuzzyQuery("管理");
        //list = courseDAO.getCourseByColMajor("", "医学信息工程");
        //list = courseDAO.getCourseByCorname("信息");
        // list = courseDAO.getCourseByColMajor("人文学院", "应用心理学");
        JSONArray json = null;
        json = JSONArray.fromObject(list);
        System.out.println(json.toString());

    }
}
