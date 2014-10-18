package com.bean.corplan;

import java.util.HashSet;
import java.util.Set;

import com.bean.course.Course;

/**
 * Corplan entity. @author MyEclipse Persistence Tools
 */

public class Corplan implements java.io.Serializable {

	// Fields

	private String idcorsem;  //教学计划的主键，由idcor(课程主键)+semester（学期）  
	private Course course;     //课程
	private String semester;  //学期

	// Constructors

	/** default constructor */
	public Corplan() {
	}

	/** minimal constructor */
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