package com.service;

import com.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:18:39
 * Created by guofan on 2015/1/6
 */
public class BaseServiceTemplate<T> implements BaseService<T> {

    BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao(){
        return baseDao;
    }

    @Autowired
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseServiceTemplate() {
    }


    public boolean add(T entity) {
        return getBaseDao().add(entity);
    }


    public boolean del(T entity) {
        return getBaseDao().del(entity);
    }


    public boolean update(T entity) {
        return getBaseDao().update(entity);
    }

    public List getAll() {
        return getBaseDao().findAll();
    }

    @Override
    public T findById(Serializable id) {
        return getBaseDao().findById(id);
    }

    public List findAcc(String propertyName, String value) {
        return getBaseDao().findByPropertyA(propertyName,value);
    }

    public List findFuz(String propertyName, String value) {
        return getBaseDao().findByPropertyF(propertyName,value);
    }

}
