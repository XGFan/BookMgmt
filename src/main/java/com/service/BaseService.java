package com.service;

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
    public Result add(T entity);

    /**
     * 修改
     *
     * @param entity 实例
     * @param id     实例id
     * @return result
     */
//    public Result edit(T entity, int id);

    public Result edit(T entity);

//    public Result del(int id);

    public List getAll();

    public List findAcc(String propertyName, String value);

    public List findFuz(String propertyName, String value);

}
