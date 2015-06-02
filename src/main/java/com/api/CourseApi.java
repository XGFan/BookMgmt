package com.api;

import com.bean.book.Book;
import com.bean.college.College;
import com.bean.corplan.Corplan;
import com.bean.course.Course;
import com.bean.coursebk.Coursebk;
import com.service.*;
import com.util.GetPaginationInfo;
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
 * DATE:2015/01/08
 * TIME:16:10
 * Created by YuanYuan on 2015/01/08
 */
@Path("/course")
@RestController
public class CourseApi {
    @Context
    ServletContext context;
    @Autowired
    CourseService courseService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    CourseBkService courseBkService;
    @Autowired
    CorplanService corplanService;
    @Autowired
    BookService bookService;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAllCourse(@QueryParam("page") int page, @QueryParam("rows") int row) {
        return getSubMap(courseService.getAll(), page, row);
    }

    @GET
    @Path("/p{page}/n{num}")
    @Produces("application/json;charset=UTF-8")
    public List getAllByPage(@PathParam("page") int page, @PathParam("num") int num) {
        return GetPaginationInfo.getSubList(courseService.getAll(), page, num);
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
        Course temp = courseService.findById(idcor);
        temp.setCorname(corname);
        return courseService.update(temp);
    }


    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public Map findByKeyword(@PathParam("keyword") String keyword, @QueryParam("page") int page, @QueryParam("rows")
    int row) {
        return getSubMap(courseService.fuzzyQuery(keyword), page, row);
    }

    @GET
    @Path("/{idcor}")
    @Produces("application/json;charset=UTF-8")
    public List getById(@PathParam("idcor") String idcor) {
        List ans = new ArrayList();
        ans.add(courseService.findById(idcor));
        return ans;
    }

    @GET
    @Path("/{col}/{major}/{sem}")
    @Produces("application/json;charset=UTF-8")
    public Map findByCMS(@PathParam("col") String col,
                         @PathParam("major") String major,
                         @PathParam("sem") String sem,
                         @QueryParam("page") int page,
                         @QueryParam("rows") int row) {
        if (sem.equals("all")) {
            sem = null;
        }
        return getSubMap(courseService.findByColMajorSem(col, major, sem), page, row);
    }

    @GET
    @Path("/{col}/{major}")
    @Produces("application/json;charset=UTF-8")
    public List findByCM(@PathParam("col") String col, @PathParam("major") String major, @PathParam("sem") String sem) {
        return courseService.findByColMajor(col, major);
    }

    @POST
    @Path("/new")
    @Produces("text/plain;charset=UTF-8")
    public String addCourse(@FormParam("col") String col, @FormParam("major") String major,
                            @FormParam("corname") String corname, @FormParam("sem") String sem) {
        College college = collegeService.getCollege(col, major);
        String idcor = college.getIdcm() + courseService.getMagicNum();
        Course course = new Course(idcor, college, corname, sem);
        if (courseService.save(course)) {
            return idcor;
        } else {
            return null;
        }
    }

    @POST
    @Path("/finalNew")
    @Consumes("application/json;charset=UTF-8")
    public boolean finalAddCourse(Map<String, Object> args) {
        String corname = args.get("corname").toString();
        String sem = args.get("semester").toString();
        List<Object> majorList = (List<Object>) args.get("majorList");
        List<Object> bookList = (List<Object>) args.get("bookList");
        for (Object o : majorList) {
            Map<String, Object> temp = (Map<String, Object>) o;
            Course tempCor = new Course();
            String major = (String) temp.get("major");
            String col = (String) temp.get("col");
            College college = collegeService.getCollege(col, major);
            String idcor = college.getIdcm() + courseService.getMagicNum();
            Course course = new Course(idcor, college, corname, sem);
            String idcors = courseService.add(course);
            if (idcors != null) {
                for (Object o1 : bookList) {
                    Map<String, Object> t = (Map<String, Object>) o1;
                    Book tempbook = (Book) bookService.findById(t.get("idbk").toString());
                    Coursebk cbktemp = courseBkService.add(tempbook.getIdbk(), idcors);
                }

                Corplan tempcorplan = new Corplan();
                tempcorplan.setCourse(courseService.findById(idcors));
                tempcorplan.setSemester(sem);
                String idcorplan = idcors + (Integer.parseInt(sem) > 10 ? sem : ("0" + sem));
                tempcorplan.setIdcorsem(idcorplan);
                corplanService.save(tempcorplan);

            }
        }
        return true;
    }

}
