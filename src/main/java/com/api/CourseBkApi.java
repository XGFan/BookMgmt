package com.api;

import com.bean.book.Book;
import com.bean.course.Course;
import com.bean.coursebk.Coursebk;
import com.service.CourseBkService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * DATE:2015/1/8
 * TIME:15:03
 * Created by guofan on 2015/1/8
 */
@Path("/coursebk")
@RestController
public class CourseBkApi {
    @Context
    ServletContext context;
    @Autowired
    CourseBkService courseBkService;
    @Autowired
    CourseService courseService;




    @GET
    @Path("/cor={idcor}")
    @Produces("application/json;charset=UTF-8")
    public List getByCor(@PathParam("idcor") String idcor) {
        List<Coursebk> corbk = courseBkService.findByIdCor(idcor);
        List<Book> ans = new ArrayList();
        for (Coursebk coursebk : corbk) {
            ans.add(coursebk.getBook());
        }
        return ans;
    }

    @GET
    @Path("/bk={idbk}")
    @Produces("application/json;charset=UTF-8")
    public List getByBk(@PathParam("idbk") String idbk) {
        List<Coursebk> corbk = courseBkService.findByIdBk(idbk);
        List<Course> ans = new ArrayList();
        for (Coursebk coursebk : corbk) {
            ans.add(coursebk.getCourse());
        }
        return ans;
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
