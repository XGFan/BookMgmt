package com.api;

import com.bean.college.College;
import com.bean.corplan.Corplan;
import com.bean.course.Course;
import com.service.CollegeService;
import com.service.CorplanService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/1/8
 * TIME:16:34
 * Created by guofan on 2015/1/8
 */
@Path("/corplan")
@RestController
public class CorplanApi {
    @Autowired
    CorplanService corplanService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    CourseService courseService;

    @GET
    @Path("/{col}/{major}/{sem}")
    @Produces("application/json;charset=UTF-8")
    public List getCMAll(@PathParam("col") String col, @PathParam("major") String major, @PathParam("sem") String sem) {
        College temp = collegeService.getCollege(col, major);
        if (temp != null) {
            return corplanService.findCorplanByColMajorSem(col, major, sem);
        } else
            return null;
    }

    @GET
    @Path("/{col}/{major}")
    @Produces("application/json;charset=UTF-8")
    public List getCMAll(@PathParam("col") String col, @PathParam("major") String major) {
        College temp = collegeService.getCollege(col, major);
        if (temp != null) {
            return corplanService.findCorplanByColMajorSem(col, major);
        } else
            return null;
    }

    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public Boolean addCorplan(@FormParam("idcor") String idcor, @FormParam("sem") String sem) {
        Course temp = courseService.findById(idcor);
        if (temp != null) {
//            位数不足补全
            String idcorplan = idcor + (Integer.parseInt(sem) < 10 ? sem : ("0" + sem));
            return corplanService.save(new Corplan(idcorplan, temp, sem));
        } else {
            return false;
        }
    }

    @DELETE
    @Path("/del")
    @Produces("application/json;charset=UTF-8")
    public boolean delCorplan(@FormParam("idcorsem") String idcorsem) {
        Corplan temp = corplanService.findById(idcorsem);
        return corplanService.delete(temp);
    }
}
