package com.service;

import com.bean.supplier.Supplier;
import com.dao.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("supplierService")
public class SupplierServiceImp extends BaseServiceTemplate<Supplier> implements SupplierService {
    @Autowired
    private SupplierDAO supplierDAO;


    public List getAllSupplier() {
        return supplierDAO.findAllSupplier();
    }

    @Override
    public List getAllPublisher() {
        return supplierDAO.findAllublisher();
    }

    public List findByPublish(String publish) {
        return supplierDAO.findByPublisher(publish);
    }

    @Override
    public List findBySupplier(String supplier) {
        return supplierDAO.findBySupplier(supplier);
    }




    public List searchByPubSup(String publisher, String supplier) {
        if (supplier.trim().equals("--请选择--")) {
            if (publisher.equals("")) {// 查询整张表
                return supplierDAO.getAll();
            } else {
                // System.out.println("按出版社模糊查询");
                return supplierDAO.likeFindByPub(publisher);
            }
        } else {
            if (publisher.trim().equals("")) {
                // System.out.println("按供应商查询");
                return supplierDAO.findBySupplier(supplier);
            } else {
                // System.out.println("按供应商精确查询 且按出版社模糊查询");
                return supplierDAO.findBySubPub(supplier, publisher);
            }
        }
    }

}
