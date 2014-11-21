package com.bean.book;

import java.util.HashSet;
import java.util.Set;

import com.bean.supplier.Supplier;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

    /**
     * 书本ID
     */
    private String idbk;
    /**
     * 供应商
     */
    private Supplier supplier;
    /**
     * 供应商ID
     */
    private String idsp;
    /**
     * 书本名称
     */
    private String bkname;
    /**
     * 作者
     */
    private String author;
    /**
     * 版本
     */
    private Integer edition;
    /**
     * ISBN号码
     */
    private String isbn;
    /**
     * 价格
     */
    private Double price;
    /**
     * 备注
     */
    private String memo;


    private Set bkpurchases = new HashSet(0);
    private Set coursebks = new HashSet(0);

    public Book() {
        super();
    }

    public Book(String idbk, Supplier supplier, String idsp, String bkname,
                String author, Integer edition, String isbn, Double price,
                String memo, Set bkpurchases, Set coursebks) {
        super();
        this.idbk = idbk;
        this.supplier = supplier;
        this.idsp = idsp;
        this.bkname = bkname;
        this.author = author;
        this.edition = edition;
        this.isbn = isbn;
        this.price = price;
        this.memo = memo;
        this.bkpurchases = bkpurchases;
        this.coursebks = coursebks;
    }

    public String getIdbk() {
        return idbk;
    }

    public void setIdbk(String idbk) {
        this.idbk = idbk;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Set getBkpurchases() {
        return bkpurchases;
    }

    public void setBkpurchases(Set bkpurchases) {
        this.bkpurchases = bkpurchases;
    }

    public Set getCoursebks() {
        return coursebks;
    }

    public void setCoursebks(Set coursebks) {
        this.coursebks = coursebks;
    }
}