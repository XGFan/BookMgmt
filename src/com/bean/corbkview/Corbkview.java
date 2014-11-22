package com.bean.corbkview;

/**
 * Corbkview entity. @author MyEclipse Persistence Tools
 */
/*课程书本视图*/
public class Corbkview implements java.io.Serializable {

    private Integer id;
    /*课程id*/
    private String idcor;
    /*书本id*/
    private String idbk;
    /*学院名称*/
    private String col;
    /*专业名称*/
    private String major;
    /*所在的某一学期*/
    private String semester;
    /*课程名称*/
    private String corname;
    /*书本名称*/
    private String bkname;
    /*出版社*/
    private String publisher;
    /*ISBN号*/
    private String isbn;


    // Constructors

    public String getIdcor() {
        return idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    public String getIdbk() {
        return idbk;
    }

    public void setIdbk(String idbk) {
        this.idbk = idbk;
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

    public String getCorname() {
        return corname;
    }

    public void setCorname(String corname) {
        this.corname = corname;
    }

    public String getBkname() {
        return bkname;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    /**
     * default constructor
     */
    public Corbkview() {
    }

    /** full constructor */
//	public Corbkview(CorbkviewId id) {
//		this.id = id;
//	}
//
//	// Property accessors

//	public CorbkviewId getId() {
//		return this.id;
//	}
//
//	public void setId(CorbkviewId id) {
//		this.id = id;
//	}

}