package com.dao;

import com.bean.supplier.Supplier;

import java.util.List;

public interface SupplierDAOInf {

    public void save(Supplier transientInstance);

    public void delete(Supplier persistentInstance);

    public Supplier findById(java.lang.String id);

    public List findByProperty(String propertyName, Object value);

    public List findBySupplier(Object supplier);

	/* 根据出版社查idsp */
    // public Supplier findByPub(String publisher);

    public List findByPublisher(Object publisher);

    public List findAll();

    public List findAllOrderByPublisher();

    public List findAllOrderByIdsp();

    public List findAllSupplier();

    public List<Supplier> likeFindByPub(String publisher);

    public List findBySubPub(String supplier, String publisher);

    public List accfindBySubPub(String supplier, String publisher);

    public void updateSup(Supplier sup);

}
