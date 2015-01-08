package com.api;

import com.bean.college.College;
import com.service.ClassService;
import com.service.CollegeService;
import com.service.CourseService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

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

   //关键字模糊查找===========================正常
    @GET
    @Path("/{keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getCollege(@PathParam("keyword") String keyword){
        return JSONArray.fromObject(collegeService.fuzzyQuery(keyword));
    }
////显示所有专业名
//    @GET
//    @Path("/")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getAllColName(){
//        return JSONArray.fromObject(collegeService.getAllColName());
//    }


    //所有学院专业====================正常
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllCol(){
        return JSONArray.fromObject(collegeService.initCol());
    }
    
    //模糊查询
//    @GET
//    @Path("/{keyword}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getClass(@PathParam("keyword") String keyword) {
//    	return JSONArray.fromObject(classServie.fuzzyFind(keyword));
//    }
    
    //删除学院专业====================200 OK FALSE
    @DELETE
    @Path("/id={idcm}")
    @Produces("application/json;charset=UTF-8")
    public Boolean deleteCollege(@PathParam("idcm") String idcm){
        return collegeService.deleteCollegeById(idcm);
    }

    //查看学院专业========================500 error
    @GET
    @Path("/id={idcm}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getCol(@PathParam("idcm") String idcm){
    	College col=new College();
    	if(col.getIdcm().equals(idcm)){
    		return JSONArray.fromObject(collegeService.getColObj(col.getCol(), col.getMajor()));
        }else
        	return JSONArray.fromObject(collegeService.getColObj(null, null));
    }

<<<<<<< HEAD
//添加学院专业
//    @POST
//    @Path("/new")
//    @Produces("application/json;charset=UTF-8")
//    public boolean addClass(
//            @FormParam("col") College col
//    ){
//        return collegeService.save(col);
//    }
=======
    //添加学院专业============================正常
    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    //col, major, semnum, idcm
    public boolean addClass( 
    		@FormParam("col") String col,
    		@FormParam("major") String major,
    		@FormParam("semnum") int semnum,
    		@FormParam("idcm") String idcm){
    	College coll=new College();
    	coll.setCol(col);
    	coll.setMajor(major);
    	coll.setSemnum(semnum);;
    	coll.setIdcm(idcm);
        return collegeService.saveCol(coll);
    }
>>>>>>> maven-01-Yuan
//更新
//    @PUT
//    @Path("/{idcm}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean editCollege(@PathParam("idcm") College idcm){
//        return collegeService.update(idcm);
//    }


}
