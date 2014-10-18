package com.bean.bkpurchase;

import com.bean.book.Book;

/**
 * Bkpurchase entity. @author MyEclipse Persistence Tools
 */

public class Bkpurchase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Book book;
	private Integer bknum;
	private String campus;
	private String supplier;

	// Constructors

	/** default constructor */
	public Bkpurchase() {
	}

	/** full constructor */
	public Bkpurchase(Book book, Integer bknum, String campus, String supplier) {
		this.book = book;
		this.bknum = bknum;
		this.campus = campus;
		this.supplier = supplier;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getBknum() {
		return this.bknum;
	}

	public void setBknum(Integer bknum) {
		this.bknum = bknum;
	}

	public String getCampus() {
		return this.campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}