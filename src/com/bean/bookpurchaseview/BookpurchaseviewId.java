package com.bean.bookpurchaseview;

/**
 * BookpurchaseviewId entity. @author MyEclipse Persistence Tools
 */

public class BookpurchaseviewId implements java.io.Serializable {

	// Fields

	private String idcm;
	private String col;
	private String major;
	private Integer semnum;
	private String campus;
	private String idcls;
	private String grade;
	private Integer clsno;
	private Integer stunum;
	private String idcor;
	private String corname;
	private String idcorsem;
	private String semester;
	private String idbk;
	private String bkname;
	private String author;
	private Integer edition;
	private String isbn;
	private String idsp;
	private String publisher;
	private String supplier;

	public BookpurchaseviewId(String idcm, String col, String major,
			Integer semnum, String campus, String idcls, String grade,
			Integer clsno, Integer stunum, String idcor, String corname,
			String idcorsem, String semester, String idbk, String bkname,
			String author, Integer edition, String isbn, String idsp,
			String publisher, String supplier) {
		super();
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

	public BookpurchaseviewId() {
		super();
		// TODO Auto-generated constructor stub
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

	public Integer getSemnum() {
		return semnum;
	}

	public void setSemnum(Integer semnum) {
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

	public Integer getClsno() {
		return clsno;
	}

	public void setClsno(Integer clsno) {
		this.clsno = clsno;
	}

	public Integer getStunum() {
		return stunum;
	}

	public void setStunum(Integer stunum) {
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

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
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

	@Override
	public String toString() {
		return "BookpurchaseviewId [idcm=" + idcm + ", col=" + col + ", major="
				+ major + ", semnum=" + semnum + ", campus=" + campus
				+ ", idcls=" + idcls + ", grade=" + grade + ", clsno=" + clsno
				+ ", stunum=" + stunum + ", idcor=" + idcor + ", corname="
				+ corname + ", idcorsem=" + idcorsem + ", semester=" + semester
				+ ", idbk=" + idbk + ", bkname=" + bkname + ", author="
				+ author + ", edition=" + edition + ", isbn=" + isbn
				+ ", idsp=" + idsp + ", publisher=" + publisher + ", supplier="
				+ supplier + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bkname == null) ? 0 : bkname.hashCode());
		result = prime * result + ((campus == null) ? 0 : campus.hashCode());
		result = prime * result + ((clsno == null) ? 0 : clsno.hashCode());
		result = prime * result + ((col == null) ? 0 : col.hashCode());
		result = prime * result + ((corname == null) ? 0 : corname.hashCode());
		result = prime * result + ((edition == null) ? 0 : edition.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((idbk == null) ? 0 : idbk.hashCode());
		result = prime * result + ((idcls == null) ? 0 : idcls.hashCode());
		result = prime * result + ((idcm == null) ? 0 : idcm.hashCode());
		result = prime * result + ((idcor == null) ? 0 : idcor.hashCode());
		result = prime * result
				+ ((idcorsem == null) ? 0 : idcorsem.hashCode());
		result = prime * result + ((idsp == null) ? 0 : idsp.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result
				+ ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((semnum == null) ? 0 : semnum.hashCode());
		result = prime * result + ((stunum == null) ? 0 : stunum.hashCode());
		result = prime * result
				+ ((supplier == null) ? 0 : supplier.hashCode());
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
		BookpurchaseviewId other = (BookpurchaseviewId) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bkname == null) {
			if (other.bkname != null)
				return false;
		} else if (!bkname.equals(other.bkname))
			return false;
		if (campus == null) {
			if (other.campus != null)
				return false;
		} else if (!campus.equals(other.campus))
			return false;
		if (clsno == null) {
			if (other.clsno != null)
				return false;
		} else if (!clsno.equals(other.clsno))
			return false;
		if (col == null) {
			if (other.col != null)
				return false;
		} else if (!col.equals(other.col))
			return false;
		if (corname == null) {
			if (other.corname != null)
				return false;
		} else if (!corname.equals(other.corname))
			return false;
		if (edition == null) {
			if (other.edition != null)
				return false;
		} else if (!edition.equals(other.edition))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (idbk == null) {
			if (other.idbk != null)
				return false;
		} else if (!idbk.equals(other.idbk))
			return false;
		if (idcls == null) {
			if (other.idcls != null)
				return false;
		} else if (!idcls.equals(other.idcls))
			return false;
		if (idcm == null) {
			if (other.idcm != null)
				return false;
		} else if (!idcm.equals(other.idcm))
			return false;
		if (idcor == null) {
			if (other.idcor != null)
				return false;
		} else if (!idcor.equals(other.idcor))
			return false;
		if (idcorsem == null) {
			if (other.idcorsem != null)
				return false;
		} else if (!idcorsem.equals(other.idcorsem))
			return false;
		if (idsp == null) {
			if (other.idsp != null)
				return false;
		} else if (!idsp.equals(other.idsp))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (semester == null) {
			if (other.semester != null)
				return false;
		} else if (!semester.equals(other.semester))
			return false;
		if (semnum == null) {
			if (other.semnum != null)
				return false;
		} else if (!semnum.equals(other.semnum))
			return false;
		if (stunum == null) {
			if (other.stunum != null)
				return false;
		} else if (!stunum.equals(other.stunum))
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		return true;
	}

}