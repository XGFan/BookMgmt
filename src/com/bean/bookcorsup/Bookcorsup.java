package com.bean.bookcorsup;

/**
 * Bookcorsup entity. @author MyEclipse Persistence Tools
 */

public class Bookcorsup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String idcor;
	private String idbk;
	private String bkname;
	private String semester;
	private String supplier;

	// Constructors
	/** minimal constructor */
	public Bookcorsup(Integer id, String idcor, String idbk, String supplier) {
		this.id = id;
		this.idcor = idcor;
		this.idbk = idbk;
		this.supplier = supplier;
	}

	/** full constructor */
	public Bookcorsup(Integer id, String idcor, String idbk, String bkname,
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

	// Constructors

	/** default constructor */
	public Bookcorsup() {
	}



}