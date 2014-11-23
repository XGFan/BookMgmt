package com.action;

import com.bean.bookpurchaseview.Bookpurchaseview;
import com.service.BKDistributeService;
import com.service.BKPurchaseService;
import com.service.ClassService;
import com.util.Pagination;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BKdistAction {
    private String col;
    private String campus;
    private String grade;
    private String major;
    private Pagination pagination;
    private BKDistributeService bkdistser;
    private BKPurchaseService bkpurser;
    private ClassService classService;

    public BKPurchaseService getBkpurser() {
        return bkpurser;
    }

    public void setBkpurser(BKPurchaseService bkpurser) {
        this.bkpurser = bkpurser;
    }

    public BKDistributeService getBkdistser() {
        return bkdistser;
    }

    public void setBkdistser(BKDistributeService bkdistser) {
        this.bkdistser = bkdistser;
    }

    public ClassService getClassService() {
        return classService;
    }

    public void setClassService(ClassService classService) {
        this.classService = classService;
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

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String printBKDistInfo() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idcls = request.getParameter("idcls");
        // System.out.println("idcls: "+idcls);
        String dateStr = bkpurser.getBKPurDate();
        String yearStr = dateStr.substring(0, dateStr.indexOf("-"));
        int year = Integer.parseInt(yearStr);
        // 获取第一个‘-’的下标
        String semStr = dateStr.substring(dateStr.length() - 1);
        int sem = Integer.parseInt(semStr);
        List<Bookpurchaseview> bkpurviews = bkdistser.BKDistInfoQuery(year,
                sem, idcls);
        // System.out.println(bkpurviews.size());
        ServletActionContext.getRequest()
                .setAttribute("bkpurviews", bkpurviews);
        // System.out.println("success");
        return "SUCCESS";
    }

}
