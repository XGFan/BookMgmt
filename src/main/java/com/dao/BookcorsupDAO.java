package com.dao;

import com.bean.bookcorsup.Bookcorsup;

import java.util.List;

/**
 * 课程-书-学期-供应商表
 */
public interface BookcorsupDAO extends BaseDao<Bookcorsup>{

    /**
     * 课程-书-学期-供应商表
     * 在学期范围内，Bookcorsup中所用到的idbk（dict）
     */
    public List findbklist(String str);

    /**
     * 课程-书-学期-供应商表
     * 根据idbk从Bookcorsup中查找
     */
    public List findcorlistbyidbk(String idbk);
}
