package com.api;

import com.bean.coursebk.Coursebk;
import com.service.CourseBkService;
import com.service.CourseBookViewService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * DATE:2015/1/8
 * TIME:15:03
 * Created by guofan on 2015/1/8
 */
@Path("/coursebk")
public class CourseBkApi {
    @Context
    ServletContext context;
    @Autowired
    CourseBookViewService courseBookViewService;
    @Autowired
    CourseBkService courseBkService;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAll() {
        return JSONArray.fromObject(courseBookViewService.getAll());
    }

    @GET
    @Path("/cor={idcor}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getByCor(@PathParam("idcor") String idcor) {
        return JSONArray.fromObject(courseBookViewService.findByCourse(idcor));
    }

    @GET
    @Path("/bk={idbk}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getByBk(@PathParam("idbk") String idbk) {
        return JSONArray.fromObject(courseBookViewService.findCourseByIdbk(idbk));
    }

    @POST
    @Path("/cor={idcor}/bk={idbk}")
    @Produces("application/json;charset=UTF-8")
    public boolean addBk2Cor(@PathParam("idcor") String idcor, @PathParam("idbk") String idbk) {
        return courseBkService.save(idbk, idcor);
    }

    @POST
    @Path("/bk={idbk}/cor={idcor}")
    @Produces("application/json;charset=UTF-8")
    public boolean addCor2Bk(@PathParam("idcor") String idcor, @PathParam("idbk") String idbk) {
        return courseBkService.save(idbk, idcor);
    }

    @DELETE
    @Path("/cor={idcor}/bk={idbk}")
    @Produces("application/json;charset=UTF-8")
    public boolean delBkFromCor(@PathParam("idcor") String idcor, @PathParam("idbk") String idbk) {
        List<Coursebk> temp = courseBkService.findByBkAndCor(idbk, idcor);
        return temp != null && courseBkService.delete(temp.get(0));
    }

    @DELETE
    @Path("/bk={idbk}/cor={idcor}")
    @Produces("application/json;charset=UTF-8")
    public boolean delCorFromBk(@PathParam("idcor") String idcor, @PathParam("idbk") String idbk) {
        List<Coursebk> temp = courseBkService.findByBkAndCor(idbk, idcor);
        return temp != null && courseBkService.delete(temp.get(0));
    }
}