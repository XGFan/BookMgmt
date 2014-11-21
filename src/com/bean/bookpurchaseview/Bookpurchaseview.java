package com.bean.bookpurchaseview;

/**
 * Bookpurchaseview entity. @author MyEclipse Persistence Tools
 */

public class Bookpurchaseview implements java.io.Serializable {

    // Fields

    private BookpurchaseviewId id;

    // Constructors

    /**
     * default constructor
     */
    public Bookpurchaseview() {
    }

    /**
     * full constructor
     */
    public Bookpurchaseview(BookpurchaseviewId id) {
        this.id = id;
    }

    // Property accessors

    public BookpurchaseviewId getId() {
        return this.id;
    }

    public void setId(BookpurchaseviewId id) {
        this.id = id;
    }

}