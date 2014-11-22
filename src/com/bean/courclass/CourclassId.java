package com.bean.courclass;

/**
 * CourclassId entity. @author MyEclipse Persistence Tools
 */

class CourclassId implements java.io.Serializable {

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
    public CourclassId() {
    }

    /**
     * full constructor
     */
    public CourclassId(String id, String idcor, String idcm, String campus,
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

    String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String getIdcor() {
        return this.idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    String getIdcm() {
        return this.idcm;
    }

    public void setIdcm(String idcm) {
        this.idcm = idcm;
    }

    String getCampus() {
        return this.campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    Integer getStunum() {
        return this.stunum;
    }

    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CourclassId))
            return false;
        CourclassId castOther = (CourclassId) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null
                && castOther.getId() != null && this.getId().equals(
                castOther.getId())))
                && ((this.getIdcor() == castOther.getIdcor()) || (this
                .getIdcor() != null
                && castOther.getIdcor() != null && this.getIdcor()
                .equals(castOther.getIdcor())))
                && ((this.getIdcm() == castOther.getIdcm()) || (this.getIdcm() != null
                && castOther.getIdcm() != null && this.getIdcm()
                .equals(castOther.getIdcm())))
                && ((this.getCampus() == castOther.getCampus()) || (this
                .getCampus() != null
                && castOther.getCampus() != null && this.getCampus()
                .equals(castOther.getCampus())))
                && ((this.getGrade() == castOther.getGrade()) || (this
                .getGrade() != null
                && castOther.getGrade() != null && this.getGrade()
                .equals(castOther.getGrade())))
                && ((this.getSemester() == castOther.getSemester()) || (this
                .getSemester() != null
                && castOther.getSemester() != null && this
                .getSemester().equals(castOther.getSemester())))
                && ((this.getStunum() == castOther.getStunum()) || (this
                .getStunum() != null
                && castOther.getStunum() != null && this.getStunum()
                .equals(castOther.getStunum())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result
                + (getIdcor() == null ? 0 : this.getIdcor().hashCode());
        result = 37 * result
                + (getIdcm() == null ? 0 : this.getIdcm().hashCode());
        result = 37 * result
                + (getCampus() == null ? 0 : this.getCampus().hashCode());
        result = 37 * result
                + (getGrade() == null ? 0 : this.getGrade().hashCode());
        result = 37 * result
                + (getSemester() == null ? 0 : this.getSemester().hashCode());
        result = 37 * result
                + (getStunum() == null ? 0 : this.getStunum().hashCode());
        return result;
    }

}