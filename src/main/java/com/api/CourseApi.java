package com.api;

import com.bean.college.College;
import com.bean.course.Course;
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

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllCourse() {
        return JSONArray.fromObject(courseService.init());
    }

    @DELETE
    @Path("/id={idcor}")
    @Produces("application/json;charset=UTF-8")
    public boolean delCourse(@PathParam("idcor") String idcor) {
        return courseService.delete(courseService.findById(idcor));
    }

    @PUT
    @Path("/{idcor}")
    @Produces("application/json;charset=UTF-8")
    public boolean updateCourse(@PathParam("idcor") String idcor, @FormParam("corname") String corname) {
        Course temp = courseService.findById("idcor");
        temp.setCorname(corname);
        return courseService.update(temp);
    }

    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray findByKeyword(@PathParam("keyword") String keyword) {
        return JSONArray.fromObject(courseService.fuzzyQuery(keyword));
    }

    @GET
    @Path("/{idcor}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getById(@PathParam("idcor") String idcor) {
        return JSONArray.fromObject(courseService.findById(idcor));
    }

    @GET
    @Path("/{col}/{major}/{sem}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray findByCMS(@PathParam("col") String col, @PathParam("major") String major, @PathParam("sem") String sem) {
        return JSONArray.fromObject(courseService.findByColMajorSem(col, major, sem));
    }

    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public String addCourse(@FormParam("col")String col,@FormParam("major")String major,
                            @FormParam("corname")String corname,@FormParam("sem")String sem){
        College college = collegeService.getCollege(col, major);
        String idcor = college.getIdcm()+courseService.getMagicNum();
        Course course = new Course(idcor,college,corname,sem);
        if(courseService.save(course)){
            return idcor;
        }else{
            return null;
        }
    }

}
