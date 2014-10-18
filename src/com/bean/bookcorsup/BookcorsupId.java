package com.bean.bookcorsup;

/**
 * BookcorsupId entity. @author MyEclipse Persistence Tools
 */

public class BookcorsupId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String idcor;
	private String idbk;
	private String bkname;
	private String semester;
	private String supplier;

	// Constructors

	/** default constructor */
	public BookcorsupId() {
	}

	/** minimal constructor */
	public BookcorsupId(Integer id, String idcor, String idbk, String supplier) {
		this.id = id;
		this.idcor = idcor;
		this.idbk = idbk;
		this.supplier = supplier;
	}

	/** full constructor */
	public BookcorsupId(Integer id, String idcor, String idbk, String bkname,
			String semester, String supplier) {
		this.id = id;
		this.idcor = idcor;
		this.idbk = idbk;
		this.bkname = bkname;
		this.semester = semester;
		this.supplier = supplier;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdcor() {
		return this.idcor;
	}

	public void setIdcor(String idcor) {
		this.idcor = idcor;
	}

	public String getIdbk() {
		return this.idbk;
	}

	public void setIdbk(String idbk) {
		this.idbk = idbk;
	}

	public String getBkname() {
		return this.bkname;
	}

	public void setBkname(String bkname) {
		this.bkname = bkname;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BookcorsupId))
			return false;
		BookcorsupId castOther = (BookcorsupId) other;

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
				&& ((this.getBkname() == castOther.getBkname()) || (this
						.getBkname() != null
						&& castOther.getBkname() != null && this.getBkname()
						.equals(castOther.getBkname())))
				&& ((this.getSemester() == castOther.getSemester()) || (this
						.getSemester() != null
						&& castOther.getSemester() != null && this
						.getSemester().equals(castOther.getSemester())))
				&& ((this.getSupplier() == castOther.getSupplier()) || (this
						.getSupplier() != null
						&& castOther.getSupplier() != null && this
						.getSupplier().equals(castOther.getSupplier())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getIdcor() == null ? 0 : this.getIdcor().hashCode());
		result = 37 * result
				+ (getIdbk() == null ? 0 : this.getIdbk().hashCode());
		result = 37 * result
				+ (getBkname() == null ? 0 : this.getBkname().hashCode());
		result = 37 * result
				+ (getSemester() == null ? 0 : this.getSemester().hashCode());
		result = 37 * result
				+ (getSupplier() == null ? 0 : this.getSupplier().hashCode());
		return result;
	}

}