package com.service;

import java.io.Serializable;
import java.util.List;
import com.util.Result;

/**
 * DATE:2015/1/6
 * TIME:18:37
 * Created by guofan on 2015/1/6
 */
public interface BaseService<T> {
    /**
     * 保存
     *
     * @param entity 实例
     * @return result
     */
    public boolean save(T entity);

    public boolean delete(T entity);

    public boolean update(T entity);

    public List getAll();

    public List findAcc(String propertyName, String value);

    public List findFuz(String propertyName, String value);

    public T findById(Serializable id);

}
