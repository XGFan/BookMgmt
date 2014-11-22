package com.bean.bkpurchase;

import java.util.List;

/**
 * 教科书购买表DAO
 */
public interface BkpurchaseDAOInf {


    /**
     * 返回所有需要购买的教科书的清单
     *
     * @return 所有需要购买的教科书清单
     */
    public List findAll();

}
