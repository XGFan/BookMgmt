package com.bean.cls;

import com.bean.college.College;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * DATE:2015/4/19
 * TIME:12:50
 * Created by guofan on 2015/4/19
 */
public class GradeInfo {
    private String idgrade;
    /*ѧԺ*/
    private String college;
    /*רҵ*/
    private String major;
    /*У��*/
    private String campus;
    /*�꼶*/
    private String grade;
    /*�༶��*/
    private Integer classNum;
    /*ѧ������*/
    private Integer Stunum;

    public GradeInfo() {
    }

    public String getIdgrade() {
        return idgrade;
    }

    public void setIdgrade(String idgrade) {
        this.idgrade = idgrade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getStunum() {
        return Stunum;
    }

    public void setStunum(Integer stunum) {
        Stunum = stunum;
    }
}
