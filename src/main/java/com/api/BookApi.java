package com.api;

import com.bean.cls.ClassInfo;
import com.service.ClassService;
import com.service.CollegeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import static com.util.GetPaginationInfo.getSubList;

/**
 * DATE:2014/11/24
 * TIME:16:10
 * Created by guofan on 2014/11/24
 */
@Path("/class")
public class BookApi {
    @Context
    ServletContext context;
    @Autowired
    ClassService classServie;
    @Autowired
    CollegeService collegeService;

    @GET
    @Path("/{campus}/{col}/{major}/{grade}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getClass(@PathParam("campus") String campus, @PathParam("col") String col,
                              @PathParam("major") String major, @PathParam("grade") String grade) {
        return JSONArray.fromObject(classServie.accurateQuery(col, major, grade, campus));
    }

    @GET
    @Path("/p{page}/n{num}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllClass(@PathParam("page") int page, @PathParam("num") int num) {
        return getSubList(getAllClass(), page, num);
    }

    @GET
    @Path("/{keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getClass(@PathParam("keyword") String keyword) {
        return JSONArray.fromObject(classServie.fuzzyFind(keyword));
    }

    @GET
    @Path("/")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllClass() {
        return JSONArray.fromObject(classServie.findAll());
    }


    @DELETE
    @Path("/idcls/{ids}")
    @Produces("application/json;charset=UTF-8")
    public Boolean deleteClasss(@PathParam("ids") String ids) {
        return classServie.deleteClass(ids);
    }


    @POST
    @Path("/new")
//    @Consumes("application/x-www-form-urlencoded;text/html;charset=utf-8")
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
        return classServie.updateClass(temp);
    }


}
