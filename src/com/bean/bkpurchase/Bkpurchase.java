package com.bean.bkpurchase;

import com.bean.book.Book;

/**
 * Bkpurchase entity. @author MyEclipse Persistence Tools
 * 教科书购买表
 */

/**
 *
 */
public class Bkpurchase implements java.io.Serializable {

    /**
     * ID
     */
    private Integer id;
    /**
     * 购买的教科书对象
     */
    private Book book;
    /**
     * 购买数量
     */
    private Integer bknum;
    /**
     * 校区
     */
    private String campus;
    /**
     * 供应商
     */
    private String supplier;

    // Constructors

    /**
     * default constructor
     */
    public Bkpurchase() {
    }

    /**
     * full constructor
     */
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