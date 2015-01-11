package com.bean.corplan;

import com.bean.course.Course;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Corplan entity. @author MyEclipse Persistence Tools
 */
/*教学计划*/
@XmlRootElement
public class Corplan implements java.io.Serializable {

    /*教学计划的主键，由idcor(课程主键)+semester（学期）*/
    private String idcorsem;
    /*课程*/
    @JsonUnwrapped
    private Course course;
    /*学期*/
    private String semester;

    // Constructors

    /**
     * default constructor
     */
    public Corplan() {
    }

    /**
     * minimal constructor
     */
    public Corplan(String idcorsem, Course course, String semester) {
        this.idcorsem = idcorsem;
        this.course = course;
        this.semester = semester;
    }

    // Property accessors

    public String getIdcorsem() {
        return this.idcorsem;
    }

    public void setIdcorsem(String idcorsem) {
        this.idcorsem = idcorsem;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Corplan [idcorsem=" + idcorsem + ", course=" + course
                + ", semester=" + semester + "]";
    }

}