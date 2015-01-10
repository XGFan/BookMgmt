package com.api;

import com.bean.college.College;
import com.service.ClassService;
import com.service.CollegeService;
import com.service.CourseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:11:00
 * Created by Yuanyuan on 2015/1/6
 */
@Path("/college")
public class CollegeApi {
    @Context
    ServletContext context;
    @Autowired
    ClassService classServie;
    @Autowired
    CollegeService collegeService;
    @Autowired
    CourseService courseService;


    //学院/专业 精确查找=====================500 error
    @GET
    @Path("/{col}/{major}")
    @Produces("application/json;charset=UTF-8")
    public College getCollege(@PathParam("col") String col,
                                @PathParam("major") String major) {
        return collegeService.getCollege(col, major);
    }

    //关键字模糊查找===========================正常
    @GET
    @Path("/{keyword}")
    @Produces("application/json;charset=UTF-8")
    public List getCollege(@PathParam("keyword") String keyword) {
        return collegeService.fuzzyQuery(keyword);
    }


    //所有学院专业====================正常
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public List getAllCol() {
        return collegeService.getAll();
    }


    //删除学院专业====================200 OK FALSE
    @DELETE
    @Path("/id={idcm}")
    @Produces("application/json;charset=UTF-8")
    public Boolean deleteCollege(@PathParam("idcm") String idcm) {
        return collegeService.deleteCollegeById(idcm);
    }

    //查看学院专业========================500 error
    @GET
    @Path("/id={idcm}")
    @Produces("application/json;charset=UTF-8")
    public College getCol(@PathParam("idcm") String idcm) {
        return collegeService.findById(idcm);
    }

    //添加学院专业============================正常
    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public boolean addClass(
            @FormParam("col") String col,
            @FormParam("major") String major,
            @FormParam("semnum") int semnum,
            @FormParam("idcm") String idcm) {
        College coll = new College();
        coll.setCol(col);
        coll.setMajor(major);
        coll.setSemnum(semnum);
        coll.setIdcm(idcm);
        return collegeService.save(coll);
    }
}
