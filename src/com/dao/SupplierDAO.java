package com.dao;

import com.bean.supplier.Supplier;

import java.util.List;

public interface SupplierDAO {

    /**
     * 保存
     * @param transientInstance 实例
     * @return boolean
     */
    public boolean save(Supplier transientInstance);

    /**
     * 删除
     * @param persistentInstance 实例
     * @return boolean
     */
    public boolean delete(Supplier persistentInstance);

    /**
     * 根据id查找Supplier
     * @param id id
     * @return Supplier obj
     */
    public Supplier findById(java.lang.String id);

    /**
     * 传入属性名和属性值精确查找，返回查找结果LIST
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return Supplier obj LIST
     */
    List findByPropertyAccurate(String propertyName, String value);

    /**
     * 传入属性名和属性值模糊查找，返回查找结果LIST
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return Supplier obj LIST
     */
    List findByPropertyFuzzy(String propertyName, String value);

    /**
     * 根据供应商精确查找
     * @param supplier 供应商
     * @return Supplier obj LIST
     */
    public List findBySupplier(String supplier);

    /**
     * 根据出版社精确查找
     * @param publisher 出版社
     * @return Supplier obj LIST
     */
    public List findByPublisher(String publisher);

    /**
     * 返回所有 Supplier obj LIST
     * @return Supplier obj LIST
     */
    public List findAll();

    /**
     * 返回所有，根据publisher排序
     * @return Supplier obj LIST
     */
    public List findAllOrderByPublisher();

    /**
     * 返回所有，根据idsp排序
     * @return Supplier obj LIST
     */
    public List findAllOrderByIdsp();

    /**
     * 返回所有distinct的供应商
     * @return string list
     */
    public List findAllSupplier();

    /**
     * supplier 的模糊查询
     * @param publisher 出版社
     * @return list
     */
    public List likeFindByPub(String publisher);

    /**
     * todo 这hql语句有问题
     * @param supplier 供应商
     * @param publisher 出版社
     * @return 查找内容
     */
    public List findBySubPub(String supplier, String publisher);

    /**
     * todo 这hql语句看着不对劲
     * 按supplier 精确查询，publisher精确查询
     * @param supplier 供应商
     * @param publisher 出版社
     * @return 查找内容
     */
    public List accfindBySubPub(String supplier, String publisher);

    /**
     * 更新
     * @param sup supplier Object
     * @return boolean
     */
    public boolean updateSup(Supplier sup);

}
