package com.bean.corbook;

/**
 * CorbookviewId entity. @author MyEclipse Persistence Tools
 */

public class CorbookviewId implements java.io.Serializable {

	// Fields

	private String idcorsem;
	private String idbk;
	private String idcor;
	/*private String corname;
	private String col;
	private String major;
	private String semester;
	private String bkname;
	private String publisher;
	private String isbn;*/

	// Constructors

	/** default constructor */
	public CorbookviewId() {
	}

	/** minimal constructor */
/*	public CorbookviewId(String idcorsem, String idbk, String idcor,
			String col, String major, String semester, String publisher) {
		this.idcorsem = idcorsem;
		this.idbk = idbk;
		this.idcor = idcor;
		this.col = col;
		this.major = major;
		this.semester = semester;
		this.publisher = publisher;
	}
*/
	/** full constructor */
	/*public CorbookviewId(String idcorsem, String idbk, String idcor,
			String corname, String col, String major, String semester,
			String bkname, String publisher, String isbn) {
		this.idcorsem = idcorsem;
		this.idbk = idbk;
		this.idcor = idcor;
		this.corname = corname;
		this.col = col;
		this.major = major;
		this.semester = semester;
		this.bkname = bkname;
		this.publisher = publisher;
		this.isbn = isbn;
	}*/
	public CorbookviewId(String idcorsem, String idbk, String idcor){
		
		this.idcorsem = idcorsem;
		this.idbk = idbk;
		this.idcor = idcor;
	}
	// Property accessors

	public String getIdcorsem() {
		return this.idcorsem;
	}

	public void setIdcorsem(String idcorsem) {
		this.idcorsem = idcorsem;
	}

	public String getIdbk() {
		return this.idbk;
	}

	public void setIdbk(String idbk) {
		this.idbk = idbk;
	}

	public String getIdcor() {
		return this.idcor;
	}

	public void setIdcor(String idcor) {
		this.idcor = idcor;
	}

	/*public String getCorname() {
		return this.corname;
	}

	public void setCorname(String corname) {
		this.corname = corname;
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

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getBkname() {
		return this.bkname;
	}

	public void setBkname(String bkname) {
		this.bkname = bkname;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}*/
/*
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CorbookviewId))
			return false;
		CorbookviewId castOther = (CorbookviewId) other;

		return ((this.getIdcorsem() == castOther.getIdcorsem()) || (this
				.getIdcorsem() != null
				&& castOther.getIdcorsem() != null && this.getIdcorsem()
				.equals(castOther.getIdcorsem())))
				&& ((this.getIdbk() == castOther.getIdbk()) || (this.getIdbk() != null
						&& castOther.getIdbk() != null && this.getIdbk()
						.equals(castOther.getIdbk())))
				&& ((this.getIdcor() == castOther.getIdcor()) || (this
						.getIdcor() != null
						&& castOther.getIdcor() != null && this.getIdcor()
						.equals(castOther.getIdcor())))
				&& ((this.getCorname() == castOther.getCorname()) || (this
						.getCorname() != null
						&& castOther.getCorname() != null && this.getCorname()
						.equals(castOther.getCorname())))
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

		result = 37 * result
				+ (getIdcorsem() == null ? 0 : this.getIdcorsem().hashCode());
		result = 37 * result
				+ (getIdbk() == null ? 0 : this.getIdbk().hashCode());
		result = 37 * result
				+ (getIdcor() == null ? 0 : this.getIdcor().hashCode());
		result = 37 * result
				+ (getCorname() == null ? 0 : this.getCorname().hashCode());
		result = 37 * result
				+ (getCol() == null ? 0 : this.getCol().hashCode());
		result = 37 * result
				+ (getMajor() == null ? 0 : this.getMajor().hashCode());
		result = 37 * result
				+ (getSemester() == null ? 0 : this.getSemester().hashCode());
		result = 37 * result
				+ (getBkname() == null ? 0 : this.getBkname().hashCode());
		result = 37 * result
				+ (getPublisher() == null ? 0 : this.getPublisher().hashCode());
		result = 37 * result
				+ (getIsbn() == null ? 0 : this.getIsbn().hashCode());
		return result;
	}*/

}