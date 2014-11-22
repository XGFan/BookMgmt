package com.bean.bkpurchase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 教科书购买表DAO
 */
public class BkpurchaseDAO extends HibernateDaoSupport implements
        BkpurchaseDAOInf {
    public static final String BKNUM = "bknum";
    public static final String CAMPUS = "campus";
    public static final String SUPPLIER = "supplier";
    private static final Log log = LogFactory.getLog(BkpurchaseDAO.class);

    protected void initDao() {
    }

    public List findAll() {
        log.debug("finding all Bkpurchase instances");
        try {
            String queryString = "from Bkpurchase";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}