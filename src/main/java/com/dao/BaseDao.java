package com.dao;

import java.io.Serializable;
import java.util.List;


/**
 * DATE:2015/1/6
 * TIME:18:30
 * Created by guofan on 2015/1/6
 */
@SuppressWarnings("unused")
public interface BaseDao<T> {
    /**
     * 添加
     * @param obj 实例对象
     * @return boolean
     */
    boolean save(T obj);

    /**
     * 删除
     * @param obj 实例对象
     * @return boolean
     */
    boolean delete(T obj);

    /**
     * 更新
     * @param obj 实例对象
     * @return boolean
     */
    boolean update(T obj);

    /**
     * 根据hql语句查找
     * @param hql hql语句
     * @return list
     */
    List findByHql(String hql);

    /**
     * 根据主键查找
     * @param id 主键id
     * @return  obj
     */
    T findById( Serializable id);

    /**
     * 返回所有
     * @return obj list
     */
    List<T> getAll();

    /**
     * 根据属性名和属性值来精确查找
     * @param propertyName
     * @param value
     * @return obj list
     */
    List<T> findByPropertyA(String propertyName, String value);

    /**
     * 根据属性名和属性值来模糊查找
     * @param propertyName
     * @param value
     * @return obj list
     */
    List<T> findByPropertyF(String propertyName, String value);
}
