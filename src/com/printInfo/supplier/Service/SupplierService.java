package com.printInfo.supplier.Service;

import java.util.List;

import com.bean.supplier.Supplier;

public interface SupplierService {
    /**
     * query all rows of the supplier table
     */
    public List<Supplier> initSup();

    public List getAllSupplier();

    public List<Supplier> searchByPubSup(String publisher, String supplier);

    /* 根据出版社查询出版社的idsp */
    public List<Supplier> findByPublish(String publish);

    /*
     * delete the information of the publisher<注释说明>
     */
    public boolean delPub(Supplier sup);

    /*
     * delete the information of the supplier<注释说明>
     */
    public boolean delSup(String sup);

    public boolean addPub(String publisher, String supplier);

    public boolean addSup(String supplier);

    public boolean updateSupplier(Supplier supplier);

    public List<Object> findAllPub();

}
