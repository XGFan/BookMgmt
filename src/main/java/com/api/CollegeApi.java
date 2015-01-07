package com.api;

import com.bean.cls.ClassInfo;
import com.bean.college.College;
import com.service.ClassService;
import com.service.CollegeService;
import com.service.CourseService;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;


import static com.util.GetPaginationInfo.getSubList;

/**
* DATE:2014/12/09
* TIME:16:04
* Created by zhilin on 2014/12/09
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

//查询
   @GET
   @Path("/{col}/{major}")
   @Produces("application/json;charset=UTF-8")
   public JSONArray getCollege(@PathParam("col") String col,
                              @PathParam("major") String major){
        return JSONArray.fromObject(collegeService.getCollege(col,major));
    }
//分页
//    @GET
//    @Path("/p{page}/n{num}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getAllClass(@PathParam("page") int page,@PathParam("num") int num){
//        return  getSubList(getAllClass(),page,num);
//    }
//关键字查询
   //关键字是ok的
    @GET
    @Path("/{keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getCollege(@PathParam("keyword") String keyword){
        return JSONArray.fromObject(collegeService.fuzzyQuery(keyword));
    }
//显示所有专业名
    //“/”这个也ok
    @GET
    @Path("/")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllColName(){
        return JSONArray.fromObject(collegeService.getAllColName());
    }


//根据关键字删除
    //delete也ok
    @DELETE
    @Path("/idcm/{ids}")
    @Produces("application/json;charset=UTF-8")
    public Boolean deleteCollege(@PathParam("ids") String ids){
        return collegeService.deleteCollegeById(ids);
    }


//添加学院专业
//    @POST
//    @Path("/new")
//    @Produces("application/json;charset=UTF-8")
//    public boolean addClass(
//            @FormParam("col") College col
//    ){
//        return collegeService.save(col);
//    }
//更新
//    @PUT
//    @Path("/{idcm}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean editCollege(@PathParam("idcm") College idcm){
//        return collegeService.update(idcm);
//    }


}
