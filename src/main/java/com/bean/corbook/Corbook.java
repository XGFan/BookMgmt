package com.bean.corbook;

import com.bean.book.Book;
import com.bean.corplan.Corplan;

/**
 * Corbook entity. @author MyEclipse Persistence Tools
 */
@Deprecated
public class Corbook implements java.io.Serializable {

    // Fields

    private Integer id;
    /*书本对象*/
    private Book book;
    /*教学计划对象*/
    private Corplan corplan;

    /**
     * default constructor
     */
    public Corbook() {
    }

    /**
     * full constructor
     */
    public Corbook(Book book, Corplan corplan) {
        this.book = book;
        this.corplan = corplan;
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

    public Corplan getCorplan() {
        return this.corplan;
    }

    public void setCorplan(Corplan corplan) {
        this.corplan = corplan;
    }

}