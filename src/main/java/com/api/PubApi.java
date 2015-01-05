package com.api;

import com.service.ClassService;
import com.service.CollegeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * DATE:2014/11/25
 * TIME:1:51
 * Created by guofan on 2014/11/25
 */
@Path("/")
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
    public JSONArray getCampus() {
        return JSONArray.fromObject(classServie.getAllCampus());
    }

    /**
     * 所有年级
     *
     * @return json
     */
    @GET
    @Path("/grade")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getGrade() {
        return JSONArray.fromObject(classServie.getAllGrade());
    }

    /**
     * 所有学院
     *
     * @return json
     */
    @GET
    @Path("/col")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getCol(@PathParam("campus") String campus) {
        return JSONArray.fromObject(collegeService.getAllColName());
    }


    @GET
    @Path("/major/{col}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getMajor(@PathParam("col") String col) {
        return JSONArray.fromObject(collegeService.getMajorNameByCol("col"));
    }

}
