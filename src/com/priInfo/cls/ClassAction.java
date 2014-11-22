package com.priInfo.cls;

import java.util.List;

import com.bean.cls.Class;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.college.College;
import com.opensymphony.xwork2.ActionSupport;
import com.priInfo.college.ColServ;
import com.util.Pagination;
import com.util.SendData;

public class ClassAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private ClassService classService;
    private ColServ collegeService;
    private String condition;
    /*用来分页的对象*/
    private Pagination pagination;
    /*学院名称*/
    private String col;
    /*专业名称*/
    private String major;
    /*年级*/
    private String grade;
    /*校区*/
    private String campus;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ClassService getClassService() {
        return classService;
    }

    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    public ColServ getCollegeService() {
        return collegeService;
    }

    public void setCollegeService(ColServ collegeService) {
        this.collegeService = collegeService;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * 返回一页班级信息
     *
     * @return null
     */
    public String list() {
        /*设置好分页配置信息*/
        if (pagination == null)
            pagination = new Pagination(9);
        pagination.setSize(9);
        pagination.setCurrentPage(1);//设置当前页码
        /*获取一页信息*/
        List clslist = classService.findAllByPagination(pagination);
        /*附上分页配置对象*/
        clslist.add(pagination);
        /*应该是以response的方式转为json发送出去*/
        SendData.send(clslist);
        return null;
    }

    /**
     * 模糊查找，根据分页信息返回一页信息
     *
     * @return null
     */
    public String fuzzyQuery() {
        /*获取当前页码*/
        HttpServletRequest request = ServletActionContext.getRequest();
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        if (pagination == null)
            pagination = new Pagination(9);
        pagination.setSize(9);
        pagination.setCurrentPage(1);
        /*设置当前页码*/
        pagination.setCurrentPage(currentPage);
        List clslist = classService.fuzzyFind(condition, pagination);
        /*把分页信息放入*/
        clslist.add(pagination);
        // System.out.println(clslist.size());
        SendData.send(clslist);
        return null;
    }

    /**
     * 精确查找，根据分页信息返回
     *
     * @return null
     */
    public String accurateQuery() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        if (pagination == null)
            pagination = new Pagination(9);
        pagination.setSize(9);
        pagination.setCurrentPage(currentPage);
        if (col == null || col.equals("请选择")) {
            col = "";
        }
        if (grade == null || grade.equals("请选择")) {
            grade = "";
        }
        if (major == null || major.equals("请选择")) {
            major = "";
        }
        if (campus == null || campus.equals("请选择")) {
            campus = "";
        }

        List clslist = classService.accurateQuery(col, major, grade, campus,
                pagination);
        clslist.add(pagination);
        SendData.send(clslist);
        return null;
    }

    /**
     * 返回所有学院
     *
     * @return 学院LIST
     */
    public List getAllCol() {
        List list = collegeService.getAllColName();
        SendData.send(list);
        return null;
    }

    /**
     * 返回所有校区
     *
     * @return 校区LIST
     */
    public List getAllCampus() {
        List list = classService.getAllCampus();
        SendData.send(list);
        return null;
    }

    /**
     * 返回所有年级
     *
     * @return 年级LIST
     */
    public List getAllGrade() {
        List list = classService.getAllGrade();
        SendData.send(list);
        return null;
    }

    /**
     * 根据学院名称获取专业
     *
     * @return 专业LIST
     */
    public String getMajorByCol() {
        HttpServletRequest request = ServletActionContext.getRequest();
        /*获取学院名称*/
        String col = request.getParameter("col");
        List list;
        if ("----全部----".equals(col) || "请选择".equals(col))
            list = collegeService.getMajorNameByCol("");
        else
            list = collegeService.getMajorNameByCol(col);
        SendData.send(list);
        return null;
    }


    /**
     * 删除班级list
     *
     * @return null
     */
    public String deleteClasses() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idclses = request.getParameter("idclses");
        /* 以'，'划分，存储在数组中*/
        String[] idclsid = idclses.split(",");
        boolean result = classService.deleteClasses(idclsid);
        SendData.send(result);
        return null;
    }

    /**
     * 添加班级
     *
     * @return null
     */
    public String addClasses() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String addcampus = request.getParameter("addcampus");
        String addgrade = request.getParameter("addgrade");
        int addclsnnum = Integer.parseInt(request.getParameter("addclsnnum"));
        String addcol = request.getParameter("addcol");
        String addmajor = request.getParameter("addmajor");
        College addcollege = collegeService.getCols(addcol,addmajor).get(0);
        boolean result = classService.addClasses(addcampus, addgrade,
                addclsnnum, addcollege);
        SendData.send(result);
        return null;
    }

    /**
     * 修改班级
     *
     * @return null
     */
    public String editClass() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String ccampus = request.getParameter("ccampus");
        String tempid = request.getParameter("tempid");
        String stunum = request.getParameter("stunum");
        /*根据传入的临时id来查找修改对象*/
        Class cls = classService.findById(tempid);
        if (ccampus != null && !ccampus.equals(""))
            cls.setCampus(ccampus);
        if (stunum != null && !stunum.equals("")) {
            int stunums = Integer.parseInt(request.getParameter("stunum"));
            cls.setStunum(stunums);
        }
        boolean result = classService.editClasses(cls);
        SendData.send(result);
        return null;
    }
}
