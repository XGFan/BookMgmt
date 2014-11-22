package com.bean.corplan;

import com.bean.course.Course;

/**
 * Courplan entity. @author MyEclipse Persistence Tools
 */

public class Courplan implements java.io.Serializable {

    // Fields

    private CourplanId id;
    private String idcorsem;
    private String idcor;
    private String corname;
    private String col;
    private String major;
    private String semester;
    private Course course;


    // Constructors

    /**
     * default constructor
     */
    public Courplan() {
    }

    /**
     * full constructor
     */
    public Courplan(CourplanId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Property accessors

    public CourplanId getId() {
        return this.id;
    }

    public void setId(CourplanId id) {
        this.id = id;
    }

    public String getIdcorsem() {
        return idcorsem;
    }

    public void setIdcorsem(String idcorsem) {
        this.idcorsem = idcorsem;
    }

    public String getIdcor() {
        return idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    public String getCorname() {
        return corname;
    }

    public void setCorname(String corname) {
        this.corname = corname;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


}