package com.api;

import com.bean.cls.ClassInfo;
import com.bean.cls.GradeInfo;
import com.bean.college.College;
import com.service.ClassService;
import com.service.CollegeService;
import com.util.GetPaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DATE:2014/11/24
 * TIME:16:10
 * Created by guofan on 2014/11/24
 */
@Path("/class")
@RestController
public class ClassApi {
    @Context
    ServletContext context;
    @Autowired
    ClassService classServie;
    @Autowired
    CollegeService collegeService;

    @GET
    @Path("/{campus}/{col}/{major}/{grade}")
    @Produces("application/json;charset=UTF-8")
    public List getClass(@PathParam("campus") String campus, @PathParam("col") String col,
                              @PathParam("major") String major, @PathParam("grade") String grade) {
        System.out.println("I got it");
        return classServie.accurateQuery(col, major, grade, campus);
    }

    @GET
    @Path("/p{page}/n{num}")
    @Produces("application/json;charset=UTF-8")
    public List getAllByPage(@PathParam("page")int page,@PathParam("num")int num){
        return GetPaginationInfo.getSubList(classServie.getAll(), page, num);
    }


    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public Map getClass(@PathParam("keyword") String keyword,@QueryParam("page")int page,@QueryParam("rows") int row) {
        return GetPaginationInfo.getSubMap(classServie.fuzzyFind(keyword), page, row);
    }

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAllClass(@QueryParam("page")int page,@QueryParam("rows") int row) {
        return GetPaginationInfo.getSubMap(classServie.getAll(), page, row);
    }

    @GET
    @Path("/grade")
    @Produces("application/json;charset=UTF-8")
    public List getGrade() {
        return classServie.getAllGrade();
    }

    @GET
    @Path("/allGrade")
    @Produces("application/json;charset=UTF-8")
    public Map getAllGrade(@QueryParam("page")int page,@QueryParam("rows") int row) {
        List<ClassInfo> list = classServie.getAll();
        List<GradeInfo> ansList = classToGrade(list);
        return GetPaginationInfo.getSubMap(ansList, page, row);
    }

    @GET
    @Path("/gradeKey={keyword}")
    @Produces("application/json;charset=UTF-8")
    public Map getGrade(@PathParam("keyword") String keyword,@QueryParam("page")int page,@QueryParam("rows") int row) {
        List<ClassInfo> list = classServie.fuzzyFind(keyword);
        List<GradeInfo> ansList = classToGrade(list);
        return GetPaginationInfo.getSubMap(ansList, page, row);
    }


    private List classToGrade(List<ClassInfo> classInfos){
        List<GradeInfo> ansList = new ArrayList<GradeInfo>();
        for(ClassInfo u : classInfos){
            String id = u.getIdcls().substring(0,8);
            if(isClassThere(id, ansList)){
                for(GradeInfo v : ansList){
                    if(id.equals(v.getIdgrade())){
                        ansList.remove(v);
                        v.setStunum(v.getStunum() + u.getStunum());
                        v.setClassNum(v.getClassNum() + 1);
                        ansList.add(v);
                    }
                }
            }else{
                GradeInfo temp = new GradeInfo();
                temp.setIdgrade(id);
                temp.setCampus(u.getCampus());
                temp.setCollege(u.getCollege().getCol());
                temp.setMajor(u.getCollege().getMajor());
                temp.setGrade(u.getGrade());
                temp.setClassNum(1);
                temp.setStunum(u.getStunum());
                ansList.add(temp);
            }
        }
        return ansList;
    }

    private boolean isClassThere(String id,List<GradeInfo> list){
        boolean flag = false;
        for (GradeInfo u : list) {
            if(u.getIdgrade().equals(id)){
                return true;
            }
        }
        return flag;
    }


    @POST
    @Path("/newGrade")
    @Produces("text/plain;charset=UTF-8")
    public boolean addNewGrade(@FormParam("campus") String campus,
                               @FormParam("col") String col,
                               @FormParam("major") String major,
                               @FormParam("grade") String grade,
                               @FormParam("clsnum") int clsnum) {

            return classServie.addClasses(campus, grade, clsnum, collegeService.getCollege(col, major));
    }

    @DELETE
    @Path("/grade/{ids}")
    @Produces("application/json;charset=UTF-8")
    public boolean deleteGrade(@PathParam("ids") String ids) {
//        return classServie.deleteClass(ids);
        String grade = ids.substring(0,4);
        String idcm = ids.substring(4,8);
        College col = collegeService.findById(idcm);
        List<ClassInfo> cls = classServie.accurateQuery(col.getCol(),col.getMajor(),grade,null);
        for (ClassInfo c : cls) {
            if(c.getIdcls().substring(0,8).equals(ids)){
                classServie.deleteClass(c.getIdcls());
            }
        }
        return true;
    }


    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public boolean addClass(
            @FormParam("campus") String campus,
            @FormParam("col") String col,
            @FormParam("major") String major,
            @FormParam("grade") String grade,
            @FormParam("clsnum") int clsnum
    ) {
        return classServie.addClasses(campus, grade, clsnum, collegeService.getCollege(col, major));
    }

    @PUT
    @Path("/{idcls}/{stunum}")
    @Produces("application/json;charset=UTF-8")
    public boolean editClass(@PathParam("idcls") String idcls, @PathParam("stunum") int stuNum) {
        ClassInfo temp = classServie.findById(idcls);
        temp.setStunum(stuNum);
        return classServie.update(temp);
    }

}
