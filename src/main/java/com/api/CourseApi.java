package com.api;

import com.bean.cls.ClassInfo;
import com.bean.course.Course;
import com.service.ClassService;
import com.service.CollegeService;
import com.service.CourseService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * DATE:2015/01/08
 * TIME:16:10
 * Created by YuanYuan on 2015/01/08
 */
@Path("/course")
public class CourseApi {
    @Context
    ServletContext context;
    @Autowired
    CourseService courseService;
    @Autowired
    CollegeService collegeService;

//    @GET
//    @Path("/{campus}/{col}/{major}/{grade}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getClass(@PathParam("campus") String campus, @PathParam("col") String col,
//                              @PathParam("major") String major, @PathParam("grade") String grade) {
//    	return JSONArray.fromObject(classServie.accurateQuery(col, major, grade, campus));
//    }

//    @GET
//    @Path("/p{page}/n{num}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getAllClass(@PathParam("page") int page, @PathParam("num") int num) {
//        return getSubList(getAllClass(), page, num);
//    }

//    @GET
//    @Path("/{keyword}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getClass(@PathParam("keyword") String keyword) {
//    	return JSONArray.fromObject(classServie.fuzzyFind(keyword));
//    }

    //==============正常
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllCourse() {
        return JSONArray.fromObject(courseService.init());
    }
    
//    //根据idcor查找课程信息
//    @GET
//    @Path("/new")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getCourse(@PathParam("idcor") String idcor){
//    	return JSONArray.fromObject(courseService.getCourseByIdcor(idcor));
//    }


//    @DELETE
//    @Path("/idcls/{ids}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean deleteClasss(@PathParam("ids") String ids) {
//        return classServie.deleteClass(ids);
//    }
    
//    @DELETE
//    @Path("/idcls[]/{ids[]}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean deleteClasss(@PathParam("ids[]") String ids[]) {
//        return classServie.deleteClasses(ids);
//    }


//    @SuppressWarnings("deprecation")
//	@POST
//    @Path("/new")
//    @Produces("application/json;charset=UTF-8")
//  //  @Consumes("application/x-www-form-urlencoded")
//
//    public boolean addCourse(
//            @FormParam("idcor") String idcor,
//            @FormParam("idcm") String idcm,
//            @FormParam("corname") String corname,
//            @FormParam("semester") String semester
//    ) {
//    	Course cor=new Course();
//    	cor.setIdcor(idcor);
//    	cor.setIdcm(idcm);
//    	cor.setCorname(corname);
//    	cor.setSemester(semester);
//        return courseService.updateCourse(cor);
//    }

    //修改班级人数
//    @PUT
//    @Path("/{idcls}/{stunum}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean editClass(@PathParam("idcls") String idcls, @PathParam("stunum") int stuNum) {
//        ClassInfo temp = classServie.findById(idcls);
//        temp.setStunum(stuNum);
//        return classServie.updateClass(temp);
//    }
    
//    //修改班级。。。
//    @PUT
//    @Path("/{idcls}/{count}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean editClass(@PathParam("idcls") String idcls, @PathParam("stunum") int stuNum) {
//        ClassInfo temp = classServie.findById(idcls);
//        temp.setStunum(stuNum);
//        return classServie.updateClass(temp);
//    }


}
