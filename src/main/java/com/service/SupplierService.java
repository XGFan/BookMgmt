package com.service;

import com.bean.supplier.Supplier;

import java.util.List;

public interface SupplierService extends BaseService<Supplier> {

    /**
     * 返回所有distinct的供应商
     *
     * @return string list
     */
    public List getAllSupplier();

    /**
     * 返回所有distinct的供应商
     *
     * @return string list
     */
    public List getAllPublisher();

    /**
     * 通过出版社或者供应商查询
     *
     * @param publisher 出版社
     * @param supplier  供应商
     * @return Supplier obj LIST
     */
    public List searchByPubSup(String publisher, String supplier);

    /**
     * 根据出版社精确查找
     *
     * @param publish 出版社
     * @return supplier obj list
     */
    public List findByPublish(String publish);

    /**
     * 根据供应商精确查找
     *
     * @param supplier 出版社
     * @return supplier obj list
     */
    public List findBySupplier(String supplier);

    /**
     * 为某一供应商添加出版社
     *
     * @param publisher 出版社
     * @param supplier  供应商
     * @return boolean
     */
    public boolean addPub(String publisher, String supplier);

    /**
     * 添加供应商 ，或为相应的供应商添加出版社
     * （若无相应的供应商则返回到添加供应商的界面。若有供应商则添加）
     *
     * @param supplier 供应商
     * @return boolean
     */
    public boolean addSup(String supplier);



}
