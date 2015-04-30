package com.service.imp;

import com.dao.BaseDao;
import com.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:18:39
 * Created by guofan on 2015/1/6
 */
public abstract class BaseServiceTemplate<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    BaseDao<T> getBaseDao(){
        return baseDao;
    }

    @Autowired
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseServiceTemplate() {
    }


    public boolean save(T entity) {
        return getBaseDao().save(entity);
    }


    public boolean delete(T entity) {
        return getBaseDao().delete(entity);
    }


    public boolean update(T entity) {
        return getBaseDao().update(entity);
    }

    public List<T> getAll() {
        return getBaseDao().getAll();
    }

    public T findById(Serializable id) {
        return getBaseDao().findById(id);
    }

    public List<T> findAcc(String propertyName, String value) {
        return getBaseDao().findByPropertyA(propertyName,value);
    }

    public List<T> findFuz(String propertyName, String value) {
        return getBaseDao().findByPropertyF(propertyName,value);
    }

}
