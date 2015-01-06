package com.service;

import com.dao.BaseDao;
import com.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:18:39
 * Created by guofan on 2015/1/6
 */
public class BaseServiceTemplate<T> implements BaseService<T> {

    BaseDao<T> baseDao;
    Result result;

    public BaseDao<T> getBaseDao(){
        return baseDao;
    }

    @Autowired
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseServiceTemplate() {
        result = new Result();
    }

    /**
     * 保存
     *
     * @param entity 实例
     * @return result
     */
    public Result add(T entity) {
        if (getBaseDao().add(entity)) {
            result.setStatus(true);
        } else {
            result.setMsg("添加失败");
        }
        return result;
    }

    public Result edit(T entity) {
        if (getBaseDao().update(entity)) {
            result.setStatus(true);
        } else {
            result.setMsg("修改失败");
        }
        return result;
    }

    public List getAll() {
        return getBaseDao().findAll();
    }

    public List findAcc(String propertyName, String value) {
        return getBaseDao().findByPropertyA(propertyName,value);
    }

    public List findFuz(String propertyName, String value) {
        return getBaseDao().findByPropertyF(propertyName,value);
    }

}
