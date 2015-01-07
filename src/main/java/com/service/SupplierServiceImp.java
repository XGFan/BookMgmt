package com.service;

import com.bean.supplier.Supplier;
import com.dao.SupplierDAO;
import com.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("supplierService")
public class SupplierServiceImp implements SupplierService {
    @Autowired
    private SupplierDAO supplierDAO;

    public SupplierDAO getSupplierDAO() {
        return supplierDAO;
    }

    public void setSupplierDAO(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public List findAllPub() {
        List list = supplierDAO.findAllOrderByPublisher();
        return ConvertUtils.ToPubList(list);
    }

    public List getAllSupplier() {
        return supplierDAO.findAllSupplier();
    }

    public List findByPublish(String publish) {
        return supplierDAO.findByPublisher(publish);
    }

    public boolean delPub(Supplier sup) {
        String supplier = sup.getSupplier();
        if (supplier.equals("其他")) {
            supplierDAO.delete(sup);
        } else {
            if (supplierDAO.findBySupplier(supplier).size() > 1) {
                sup = supplierDAO.findById(sup.getIdsp());
                supplierDAO.delete(sup);
            }
            if (supplierDAO.findBySupplier(supplier).size() == 1) {
                sup.setPublisher(null);
                supplierDAO.update(sup);
            }
        }
        return true;
    }

    public boolean delSup(String sup) {
        List<Supplier> list = supplierDAO.findBySupplier(sup);
        for (Supplier s : list) {
            String publisher = s.getPublisher();
            if (publisher == null) {
                supplierDAO.delete(s);
            } else {
                if (supplierDAO.findByPublisher(publisher).size() > 1) {
                    supplierDAO.delete(s);
                } else {
                    s.setSupplier("其他");
                    supplierDAO.update(s);
                }
            }
        }
        return true;
    }

    public List initSup() {
        return supplierDAO.getAll();
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

    public boolean addSup(String supplier) {
        List list = supplierDAO.findAllOrderByIdsp();
        Supplier sup = (Supplier) list.get(list.size() - 1);// 获得最后一个元素对象
        String idsp = String.valueOf(Integer.parseInt(sup.getIdsp()) + 1);//
        List supList = supplierDAO.findBySupplier(supplier);
        if (!supList.isEmpty()) {
            return false;
        } else {
            Supplier temp = new Supplier();
            temp.setIdsp(idsp);
            temp.setSupplier(supplier);
            temp.setPublisher(null);
            supplierDAO.save(temp);
            return true;
        }
    }

    public boolean addPub(String publisher, String supplier) {
        List list = supplierDAO.findAllOrderByIdsp();
        Supplier sup = (Supplier) list.get(list.size() - 1);// 获得最后一个元素对象
        /*生成新的idsp*/
        String idsp = String.valueOf(Integer.parseInt(sup.getIdsp()) + 1);
        List supList = supplierDAO.accfindBySubPub(supplier, publisher);
        if (supList.isEmpty()) { // 判断list为空用 isempty 或list.size()==0
            Supplier temp = new Supplier();
            temp.setIdsp(idsp);
            temp.setSupplier(supplier);
            temp.setPublisher(publisher);
            supplierDAO.save(temp);
            return true;
        } else
            return false;
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierDAO.update(supplier);
    }
}
