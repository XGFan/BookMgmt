package com.bean.supplier;

/**
 * Supplier entity. @author MyEclipse Persistence Tools
 */

public class Supplier implements java.io.Serializable {

    // Fields

    private String idsp;
    private String supplier;
    private String publisher;
  //  private Set books = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Supplier() {
    }

    /**
     * minimal constructor
     */
    public Supplier(String idsp, String supplier) {
        this.idsp = idsp;
        this.supplier = supplier;
    }

    /**
     * full constructor
     */
//    public Supplier(String idsp, String supplier, String publisher, Set books) {
//        this.idsp = idsp;
//        this.supplier = supplier;
//        this.publisher = publisher;
//        //this.books = books;
//    }

    // Property accessors

    public Supplier(String idsp, String supplier, String publisher) {
        this.idsp = idsp;
        this.supplier = supplier;
        this.publisher = publisher;
        // TODO Auto-generated constructor stub
    }

    public String getIdsp() {
        return this.idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

//    public Set getBooks() {
//        return this.books;
//    }
//
//    public void setBooks(Set books) {
//        this.books = books;
//    }

//    @Override
//    public String toString() {
//        return "Supplier [idsp=" + idsp + ", supplier=" + supplier
//                + ", publisher=" + publisher + ", books=" + books + "]";
//    }
    
    @Override
    public String toString() {
        return "Supplier [idsp=" + idsp + ", supplier=" + supplier
                + ", publisher=" + publisher +"]";
    }


}