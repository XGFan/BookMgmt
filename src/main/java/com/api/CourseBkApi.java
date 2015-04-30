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
import java.util.Map;

import static com.util.GetPaginationInfo.getSubMap;

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
    public Map getByCor(@PathParam("idcor") String idcor,@QueryParam("page")int page,@QueryParam("rows") int row) {
        List<Coursebk> corbk = courseBkService.findByIdCor(idcor);
        List<Book> ans = new ArrayList();
        for (Coursebk coursebk : corbk) {
            ans.add(coursebk.getBook());
        }
        return getSubMap(ans,page,row);
    }

    @GET
    @Path("/bk={idbk}")
    @Produces("application/json;charset=UTF-8")
    public Map getByBk(@PathParam("idbk") String idbk,@QueryParam("page")int page,@QueryParam("rows") int row) {
        List<Coursebk> corbk = courseBkService.findByIdBk(idbk);
        List<Course> ans = new ArrayList();
        for (Coursebk coursebk : corbk) {
            ans.add(coursebk.getCourse());
        }
        return getSubMap(ans,page,row);
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
