package com.bean.bkpurchase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bkpurchase entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.bkpurchase.Bkpurchase
 */

public class BkpurchaseDAO extends HibernateDaoSupport implements
        BkpurchaseDAOInf {
    // property constants
    public static final String BKNUM = "bknum";
    public static final String CAMPUS = "campus";
    public static final String SUPPLIER = "supplier";
    private static final Log log = LogFactory.getLog(BkpurchaseDAO.class);

    protected void initDao() {
        // do nothing
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

//	public List findByBknum(Object bknum) {
//		return findByProperty(BKNUM, bknum);
//	}
//
//	public List findByCampus(Object campus) {
//		return findByProperty(CAMPUS, campus);
//	}
//
//	public List findBySupplier(Object supplier) {
//		return findByProperty(SUPPLIER, supplier);
//	}

}