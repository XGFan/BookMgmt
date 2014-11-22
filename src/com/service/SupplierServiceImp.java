package com.service;

import com.bean.supplier.Supplier;
import com.dao.SupplierDAOInf;
import com.util.ConvertUtils;

import java.util.List;

public class SupplierServiceImp implements SupplierService {
    private SupplierDAOInf supplierDAO;

    public SupplierDAOInf getSupplierDAO() {
        return supplierDAO;
    }

    public void setSupplierDAO(SupplierDAOInf supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    /*
     * 初始化供应商信息。
     */
    public List<Object> findAllPub() {
        List list = supplierDAO.findAllOrderByPublisher();
        return ConvertUtils.ToPubList(list);
    }

    public List getAllSupplier() {
        return supplierDAO.findAllSupplier();
    }

    /* 根据出版社查询出版社的idsp */
    public List<Supplier> findByPublish(String publish) {
        return supplierDAO.findByProperty("publisher", publish);
    }

    /*
     * 根据idsp删除出版社信息。若供应商为“其他”则删除该条记录否则 按供应商名进行查询 若记录条数为1，则将出版社置为null.否则删除该条记录
     */
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
                supplierDAO.updateSup(sup);
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.printInfo.supplier.SupplierService#delSup(java.lang.String)
     */
    public boolean delSup(String sup) {
        List<Supplier> list = supplierDAO.findByProperty("supplier", sup);
        for (Supplier s : list) {
            String publisher = s.getPublisher();
            if (publisher == null) {
                supplierDAO.delete(s);
            } else {
                if (supplierDAO.findByPublisher(publisher).size() > 1) {
                    supplierDAO.delete(s);
                } else {
                    s.setSupplier("其他");
                    supplierDAO.updateSup(s);
                }
            }
        }
        return true;
    }

    /*
     * @see com.printInfo.supplier.SupplierService#initSup()
     */
    public List<Supplier> initSup() {
        return supplierDAO.findAll();
    }

    /*
     * @see
     * com.printInfo.supplier.SupplierService#searchByPubSup(java.lang.String,
     * java.lang.String)
     */
    public List<Supplier> searchByPubSup(String publisher, String supplier) {
        if (supplier.trim().equals("--请选择--")) {
            if (publisher.equals("")) {// 查询整张表
                return supplierDAO.findAll();
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

    /*
     * 添加供应商 ，或为相应的供应商添加出版社（若无相应的供应商则返回到添加供应商的界面。若有供应商则添加）
     */
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

    // 为某一供应商添加出版社
    public boolean addPub(String publisher, String supplier) {
        List list = supplierDAO.findAllOrderByIdsp();
        Supplier sup = (Supplier) list.get(list.size() - 1);// 获得最后一个元素对象
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
        System.out.println("service :" + supplier.getSupplier() + "  "
                + supplier.getPublisher());
        supplierDAO.updateSup(supplier);
        return true;
    }
}
