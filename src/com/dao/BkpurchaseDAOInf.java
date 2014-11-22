package com.dao;

import java.util.List;

public interface BkpurchaseDAOInf {


    /**
     * 返回所有需要购买的教科书的清单
     *
     * @return 所有需要购买的教科书清单
     */
    public List findAll();

}