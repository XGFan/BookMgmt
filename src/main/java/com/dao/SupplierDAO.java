package com.dao;

import com.bean.supplier.Supplier;

import java.util.List;

public interface SupplierDAO extends BaseDao<Supplier> {

    /**
     * 根据供应商精确查找
     *
     * @param supplier 供应商
     * @return Supplier obj LIST
     */
    public List findBySupplier(String supplier);

    /**
     * 根据出版社精确查找
     *
     * @param publisher 出版社
     * @return Supplier obj LIST
     */
    public List findByPublisher(String publisher);
    


    /**
     * 返回所有，根据publisher排序
     *
     * @return Supplier obj LIST
     */
    public List findAllOrderByPublisher();

    /**
     * 返回所有供应商，根据idsp排序
     *
     * @return Supplier obj LIST
     */
    public List findAllOrderByIdsp();

    /**
     * 返回所有distinct的供应商
     *
     * @return string list
     */
    public List findAllSupplier();

    /**
     * 返回所有distinct的出版社
     *
     * @return string list
     */
    public List findAllublisher();

    /**
     * supplier 的模糊查询
     *
     * @param publisher 出版社
     * @return list
     */
    public List likeFindByPub(String publisher);

    /**
     * todo 这hql语句有问题
     *
     * @param supplier  供应商
     * @param publisher 出版社
     * @return 查找内容
     */
    public List findBySubPub(String supplier, String publisher);

    /**
     * todo 这hql语句看着不对劲
     * 按supplier 精确查询，publisher精确查询
     *
     * @param supplier  供应商
     * @param publisher 出版社
     * @return 查找内容
     */
    public List accfindBySubPub(String supplier, String publisher);


}
