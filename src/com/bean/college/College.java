package com.bean.college;

import java.util.HashSet;
import java.util.Set;

/**
 * College entity. @author MyEclipse Persistence Tools
 */
/*学院转农业信息*/
public class College implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
    /*专业ID（唯一）*/
	private String idcm;
    /*学院名称*/
	private String col;
    /*专业名称*/
	private String major;
    /*学制，某一专业的学期数*/
	private Integer semnum;
	private Set classes = new HashSet(0);
	private Set courses = new HashSet(0);

	// Constructors

	/** default constructor */
	public College() {
	}

	/** minimal constructor */
	public College(String idcm, String col, String major, Integer semnum) {
		this.idcm = idcm;
		this.col = col;
		this.major = major;
		this.semnum = semnum;
	}

	/** full constructor */
	public College(String idcm, String col, String major, Integer semnum,
			Set classes, Set courses) {
		this.idcm = idcm;
		this.col = col;
		this.major = major;
		this.semnum = semnum;
		this.classes = classes;
		this.courses = courses;
	}

	public College(String col, String major, Integer semnum) {
		super();
		this.col = col;
		this.major = major;
		this.semnum = semnum;
	}

	// Property accessors

	public String getIdcm() {
		return this.idcm;
	}

	public void setIdcm(String idcm) {
		this.idcm = idcm;
	}

	public String getCol() {
		return this.col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Integer getSemnum() {
		return this.semnum;
	}

	public void setSemnum(Integer semnum) {
		this.semnum = semnum;
	}

	public Set getClasses() {
		return this.classes;
	}

	public void setClasses(Set classes) {
		this.classes = classes;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "College [col=" + col + ", idcm=" + idcm + ", major=" + major
				+ ", semnum=" + semnum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcm == null) ? 0 : idcm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		College other = (College) obj;
		if (idcm == null) {
			if (other.idcm != null)
				return false;
		} else if (!idcm.equals(other.idcm))
			return false;
		return true;
	}

}