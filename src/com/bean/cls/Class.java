package com.bean.cls;

import com.bean.college.College;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class Class implements java.io.Serializable {

	// Fields

	private String idcls;
	private College college;
	private String campus;
	private String grade;
	private String semester;
	private Integer clsno;
	private Integer stunum;

	// Constructors

	/** default constructor */
	public Class() {
	}

	/** full constructor */
	public Class(String idcls, College college, String campus, String grade,
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
		return "Class [campus=" + campus + ", clsno=" + clsno + ", college="
				+ college + ", grade=" + grade + ", idcls=" + idcls
				+ ", semester=" + semester + ", stunum=" + stunum + "]";
	}

}