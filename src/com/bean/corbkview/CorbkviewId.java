package com.bean.corbkview;

/**
 * CorbkviewId entity. @author MyEclipse Persistence Tools
 */

class CorbkviewId implements java.io.Serializable {

    // Fields

    private Integer id;
    private String idcor;
    private String idbk;
    private String col;
    private String major;
    private String semester;
    private String corname;
    private String bkname;
    private String publisher;
    private String isbn;

    // Constructors

    /**
     * default constructor
     */
    public CorbkviewId() {
    }

    /**
     * minimal constructor
     */
    public CorbkviewId(Integer id, String col, String major, String publisher) {
        this.id = id;
        this.col = col;
        this.major = major;
        this.publisher = publisher;
    }

    /**
     * full constructor
     */
    public CorbkviewId(Integer id, String idcor, String idbk, String col,
                       String major, String semester, String corname, String bkname,
                       String publisher, String isbn) {
        this.id = id;
        this.idcor = idcor;
        this.idbk = idbk;
        this.col = col;
        this.major = major;
        this.semester = semester;
        this.corname = corname;
        this.bkname = bkname;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    // Property accessors

    Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    String getIdcor() {
        return this.idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    String getIdbk() {
        return this.idbk;
    }

    public void setIdbk(String idbk) {
        this.idbk = idbk;
    }

    String getCol() {
        return this.col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    String getCorname() {
        return this.corname;
    }

    public void setCorname(String corname) {
        this.corname = corname;
    }

    String getBkname() {
        return this.bkname;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname;
    }

    String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CorbkviewId))
            return false;
        CorbkviewId castOther = (CorbkviewId) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null
                && castOther.getId() != null && this.getId().equals(
                castOther.getId())))
                && ((this.getIdcor() == castOther.getIdcor()) || (this
                .getIdcor() != null
                && castOther.getIdcor() != null && this.getIdcor()
                .equals(castOther.getIdcor())))
                && ((this.getIdbk() == castOther.getIdbk()) || (this.getIdbk() != null
                && castOther.getIdbk() != null && this.getIdbk()
                .equals(castOther.getIdbk())))
                && ((this.getCol() == castOther.getCol()) || (this.getCol() != null
                && castOther.getCol() != null && this.getCol().equals(
                castOther.getCol())))
                && ((this.getMajor() == castOther.getMajor()) || (this
                .getMajor() != null
                && castOther.getMajor() != null && this.getMajor()
                .equals(castOther.getMajor())))
                && ((this.getSemester() == castOther.getSemester()) || (this
                .getSemester() != null
                && castOther.getSemester() != null && this
                .getSemester().equals(castOther.getSemester())))
                && ((this.getCorname() == castOther.getCorname()) || (this
                .getCorname() != null
                && castOther.getCorname() != null && this.getCorname()
                .equals(castOther.getCorname())))
                && ((this.getBkname() == castOther.getBkname()) || (this
                .getBkname() != null
                && castOther.getBkname() != null && this.getBkname()
                .equals(castOther.getBkname())))
                && ((this.getPublisher() == castOther.getPublisher()) || (this
                .getPublisher() != null
                && castOther.getPublisher() != null && this
                .getPublisher().equals(castOther.getPublisher())))
                && ((this.getIsbn() == castOther.getIsbn()) || (this.getIsbn() != null
                && castOther.getIsbn() != null && this.getIsbn()
                .equals(castOther.getIsbn())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result
                + (getIdcor() == null ? 0 : this.getIdcor().hashCode());
        result = 37 * result
                + (getIdbk() == null ? 0 : this.getIdbk().hashCode());
        result = 37 * result
                + (getCol() == null ? 0 : this.getCol().hashCode());
        result = 37 * result
                + (getMajor() == null ? 0 : this.getMajor().hashCode());
        result = 37 * result
                + (getSemester() == null ? 0 : this.getSemester().hashCode());
        result = 37 * result
                + (getCorname() == null ? 0 : this.getCorname().hashCode());
        result = 37 * result
                + (getBkname() == null ? 0 : this.getBkname().hashCode());
        result = 37 * result
                + (getPublisher() == null ? 0 : this.getPublisher().hashCode());
        result = 37 * result
                + (getIsbn() == null ? 0 : this.getIsbn().hashCode());
        return result;
    }

}