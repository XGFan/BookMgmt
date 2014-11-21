package com.bean.courclass;

/**
 * Courclass entity. @author MyEclipse Persistence Tools
 */

public class Courclass implements java.io.Serializable {

    // Fields

    private String id;
    private String idcor;
    private String idcm;
    private String campus;
    private String grade;
    private String semester;
    private Integer stunum;

    // Constructors

    /**
     * default constructor
     */
    public Courclass() {
    }

    /**
     * full constructor
     */
    public Courclass(String id, String idcor, String idcm, String campus,
                     String grade, String semester, Integer stunum) {
        this.id = id;
        this.idcor = idcor;
        this.idcm = idcm;
        this.campus = campus;
        this.grade = grade;
        this.semester = semester;
        this.stunum = stunum;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcor() {
        return this.idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    public String getIdcm() {
        return this.idcm;
    }

    public void setIdcm(String idcm) {
        this.idcm = idcm;
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

    public Integer getStunum() {
        return this.stunum;
    }

    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }

//	private CourclassId id;
//
//	// Constructors
//
//	/** default constructor */
//	public Courclass() {
//	}
//
//	/** full constructor */
//	public Courclass(CourclassId id) {
//		this.id = id;
//	}
//
//	// Property accessors
//
//	public CourclassId getId() {
//		return this.id;
//	}
//
//	public void setId(CourclassId id) {
//		this.id = id;
//	}

}