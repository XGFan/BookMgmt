package com.bean.supplier;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Supplier entity. @author MyEclipse Persistence Tools
 */
@XmlRootElement
@JsonIgnoreProperties(value={"books","hibernateLazyInitializer"})
public class Supplier implements java.io.Serializable {

    // Fields

    private String idsp;
    private String supplier;
    private String publisher;
    private Set books = new HashSet(0);

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
    public Supplier(String idsp, String supplier, String publisher ,Set books) {
        this.idsp = idsp;
        this.supplier = supplier;
        this.publisher = publisher;
        this.books = books;
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

    public Set getBooks() {
        return this.books;
    }

    public void setBooks(Set books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Supplier [idsp=" + idsp + ", supplier=" + supplier
                + ", publisher=" + publisher +  "]";
    }


}