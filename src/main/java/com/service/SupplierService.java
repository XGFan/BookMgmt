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


}
