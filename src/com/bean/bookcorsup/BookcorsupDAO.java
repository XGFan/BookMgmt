package com.bean.bookcorsup;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bookcorsup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.bookcorsup.Bookcorsup
 */

public class BookcorsupDAO extends HibernateDaoSupport implements
        BookcorsupDAOInf {
    private static final Log log = LogFactory.getLog(BookcorsupDAO.class);

    // property constants

    protected void initDao() {
        // do nothing
    }

    public List findbklist(String str) {
//		log.debug("finding all Bookcorsup instances");
        try {
            String queryString = "select distinct idbk from Bookcorsup where semester in("
                    + str + ")";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    // public List findbklisteven(){
    // log.debug("finding all Bookcorsup instances");
    // try {
    // String queryString =
    // "select distinct idbk from Bookcorsup where semester in('2','4','6','8','10')";
    // return getHibernateTemplate().find(queryString);
    // } catch (RuntimeException re) {
    // log.error("find all failed", re);
    // throw re;
    // }
    // }
    public List findcorlistbyidbk(String idbk) {
        log.debug("finding all Bookcorsup instances");
        try {
            String queryString = "from Bookcorsup where idbk='" + idbk + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}