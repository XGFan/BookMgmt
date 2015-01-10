package com.bean.cls;

import com.bean.college.College;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * ClassInfo entity. @author MyEclipse Persistence Tools
 */
/*班级信息*/
@XmlRootElement
public class ClassInfo implements java.io.Serializable {

    // Fields
    /*班级id*/
    private String idcls;
    /*学院*/
    @JsonUnwrapped
    private College college;
    /*校区*/
    private String campus;
    /*年级*/
    private String grade;
    /*学期*/
    private String semester;
    /*班级号*/
    private Integer clsno;
    /*学生数量*/
    private Integer stunum;

    // Constructors

    /**
     * default constructor
     */
    public ClassInfo() {
    }

    /**
     * full constructor
     */
    public ClassInfo(String idcls, College college, String campus, String grade,
                     String semester, Integer clsno, Integer stunum) {
        this.idcls = idcls;
        this.college = college;
        this.campus = campus;
        this.grade = grade;
        this.semester = semester;
        this.clsno = clsno;
        this.stunum = stunum;
    }

    // Property accessors

    public String getIdcls() {
        return this.idcls;
    }

    public void setIdcls(String idcls) {
        this.idcls = idcls;
    }

    public College getCollege() {
        return this.college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public String getCampus() {
        return this.campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getClsno() {
        return this.clsno;
    }

    public void setClsno(Integer clsno) {
        this.clsno = clsno;
    }

    public Integer getStunum() {
        return this.stunum;
    }

    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }

    @Override
    public String toString() {
        return "ClassInfo [campus=" + campus + ", clsno=" + clsno + ", college="
                + college + ", grade=" + grade + ", idcls=" + idcls
                + ", semester=" + semester + ", stunum=" + stunum + "]";
    }

}