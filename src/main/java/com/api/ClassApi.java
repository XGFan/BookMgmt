package com.api;

import com.bean.cls.ClassInfo;
import com.service.ClassService;
import com.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * DATE:2014/11/24
 * TIME:16:10
 * Created by guofan on 2014/11/24
 */
@Path("/class")
@RestController
public class ClassApi {
    @Context
    ServletContext context;
    @Autowired
    ClassService classServie;
    @Autowired
    CollegeService collegeService;

    @GET
    @Path("/{campus}/{col}/{major}/{grade}")
    @Produces("application/json;charset=UTF-8")
    public List getClass(@PathParam("campus") String campus, @PathParam("col") String col,
                              @PathParam("major") String major, @PathParam("grade") String grade) {
        return classServie.accurateQuery(col, major, grade, campus);
    }


    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public List getClass(@PathParam("keyword") String keyword) {
        return classServie.fuzzyFind(keyword);
    }

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public List getAllClass() {
        return classServie.getAll();
    }

    @GET
    @Path("/grade")
    @Produces("application/json;charset=UTF-8")
    public List getGrade() {
        return classServie.getAllGrade();
    }


    @DELETE
    @Path("/id={ids}")
    @Produces("application/json;charset=UTF-8")
    public boolean deleteClasss(@PathParam("ids") String ids) {
        return classServie.deleteClass(ids);
    }


    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public boolean addClass(
            @FormParam("campus") String campus,
            @FormParam("col") String col,
            @FormParam("major") String major,
            @FormParam("grade") String grade,
            @FormParam("clsnum") int clsnum
    ) {
        return classServie.addClasses(campus, grade, clsnum, collegeService.getCollege(col, major));
    }

    @PUT
    @Path("/{idcls}/{stunum}")
    @Produces("application/json;charset=UTF-8")
    public boolean editClass(@PathParam("idcls") String idcls, @PathParam("stunum") int stuNum) {
        ClassInfo temp = classServie.findById(idcls);
        temp.setStunum(stuNum);
        return classServie.update(temp);
    }

}
