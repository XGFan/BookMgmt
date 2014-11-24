package com.api;

import com.service.ClassService;
import com.service.CollegeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;


import static com.util.GetPaginationInfo.getSubList;

/**
* DATE:2014/11/24
* TIME:16:10
* Created by guofan on 2014/11/24
*/
@Component
@Path("/api")
public class Class {
    @Context
    ServletContext context;
    @Autowired
    ClassService clss;
    @Autowired
    CollegeService coss;


    @GET
    @Path("/class/p{page}/n{num}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllClass(@PathParam("page") int page,@PathParam("num") int num){
        return  getSubList(clss.findAll(), page, num);
    }

    @GET
    @Path("/class")
    @Produces("application/json;charset=UTF-8")
    public String getAllClass(){
        return clss.findAll().toString();
    }

    /**
     * 所有校区
     * @return json
     */
    @GET
    @Path("/campus")
    @Produces("application/json;charset=UTF-8")
    public String getCampus(){
        return clss.getAllCampus().toString();
    }

    /**
     * 所有年级
     * @return json
     */
    @GET
    @Path("/grade")
    @Produces("application/json;charset=UTF-8")
    public String getGrade(){
        return clss.getAllGrade().toString();
    }

    /**
     * 所有学院
     * @return json
     */
    @GET
    @Path("/col")
    @Produces("application/json;charset=UTF-8")
    public String getCol(@PathParam("campus")String campus){
        return coss.getAllColName().toString();
    }


}
