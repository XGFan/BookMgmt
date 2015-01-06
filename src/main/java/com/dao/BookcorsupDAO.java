package com.dao;

import com.bean.bookcorsup.Bookcorsup;

import java.util.List;

/**
 * TODO
 */
public interface BookcorsupDAO extends BaseDao<Bookcorsup>{

    /**
     * TODO 未知的方法
     */
    public List findbklist(String str);

    /**
     * TODO
     */
    public List findcorlistbyidbk(String idbk);
}
