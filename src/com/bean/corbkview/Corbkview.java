package com.bean.corbkview;

/**
 * Corbkview entity. @author MyEclipse Persistence Tools
 */

public class Corbkview implements java.io.Serializable {

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

	/** default constructor */
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