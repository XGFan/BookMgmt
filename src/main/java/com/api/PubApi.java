package com.api;

import com.service.ClassService;
import com.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * DATE:2014/11/25
 * TIME:1:51
 * Created by guofan on 2014/11/25
 */
@Path("/")
@RestController
public class PubApi {
    @Autowired
    ClassService classServie;
    @Autowired
    CollegeService collegeService;

    /**
     * 所有校区
     *
     * @return json
     */
    @GET
    @Path("/campus")
    @Produces("application/json;charset=UTF-8")
    public List getCampus() {
        return classServie.getAllCampus();
    }

    /**
     * 所有年级
     *
     * @return json
     */
    @GET
    @Path("/grade")
    @Produces("application/json;charset=UTF-8")
    public List getGrade() {
        return classServie.getAllGrade();
    }

    /**
     * 所有学院
     *
     * @return json
     */
    @GET
    @Path("/col")
    @Produces("application/json;charset=UTF-8")
    public List getCol(@PathParam("campus") String campus) {
        return collegeService.getAllColName();
    }


    @GET
    @Path("/major/{col}")
    @Produces("application/json;charset=UTF-8")
    public List getMajor(@PathParam("col") String col) {
        return collegeService.getMajorNameByCol(col);
    }

}
