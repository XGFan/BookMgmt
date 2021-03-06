package com.bean.bookpurchase;

/**
 * Bookpurchase entity. @author MyEclipse Persistence Tools
 */

public class Bookpurchase implements java.io.Serializable {


    private Integer id;
    /*学院专业id*/
    private String idcm;
    /*学院名称*/
    private String col;
    /*专业名称*/
    private String major;
    /*学期数量*/
    private String semnum;
    /*校区*/
    private String campus;
    /*班级编号*/
    private String idcls;
    /*年级*/
    private String grade;
    /*班号*/
    private String clsno;
    /*学生人数*/
    private String stunum;
    /*课程id*/
    private String idcor;
    /*课程名称*/
    private String corname;
    /*todo*/
    private String idcorsem;
    /*todo*/
    private String semester;
    /*书本id*/
    private String idbk;
    /*书本名称*/
    private String bkname;
    /*作者*/
    private String author;
    /*书本版本*/
    private String edition;
    /*书本的isbn编号*/
    private String isbn;
    /*供应商id*/
    private String idsp;
    /*出版社*/
    private String publisher;
    /*供应商*/
    private String supplier;

    // Constructors

    /**
     * default constructor
     */
    public Bookpurchase() {
    }

    /**
     * minimal constructor
     */
    public Bookpurchase(Integer id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public Bookpurchase(Integer id, String idcm, String col, String major,
                        String semnum, String campus, String idcls, String grade,
                        String clsno, String stunum, String idcor, String corname,
                        String idcorsem, String semester, String idbk, String bkname,
                        String author, String edition, String isbn, String idsp,
                        String publisher, String supplier) {
        super();
        this.id = id;
        this.idcm = idcm;
        this.col = col;
        this.major = major;
        this.semnum = semnum;
        this.campus = campus;
        this.idcls = idcls;
        this.grade = grade;
        this.clsno = clsno;
        this.stunum = stunum;
        this.idcor = idcor;
        this.corname = corname;
        this.idcorsem = idcorsem;
        this.semester = semester;
        this.idbk = idbk;
        this.bkname = bkname;
        this.author = author;
        this.edition = edition;
        this.isbn = isbn;
        this.idsp = idsp;
        this.publisher = publisher;
        this.supplier = supplier;
    }

    // Property accessors
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdcm() {
        return idcm;
    }

    public void setIdcm(String idcm) {
        this.idcm = idcm;
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

    public String getSemnum() {
        return semnum;
    }

    public void setSemnum(String semnum) {
        this.semnum = semnum;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getIdcls() {
        return idcls;
    }

    public void setIdcls(String idcls) {
        this.idcls = idcls;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClsno() {
        return clsno;
    }

    public void setClsno(String clsno) {
        this.clsno = clsno;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
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

    public String getIdcorsem() {
        return idcorsem;
    }

    public void setIdcorsem(String idcorsem) {
        this.idcorsem = idcorsem;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getIdbk() {
        return idbk;
    }

    public void setIdbk(String idbk) {
        this.idbk = idbk;
    }

    public String getBkname() {
        return bkname;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}