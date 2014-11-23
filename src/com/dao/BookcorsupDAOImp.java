package com.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BookcorsupDAOImp extends HibernateDaoSupport implements
        BookcorsupDAO {
    private static final Log log = LogFactory.getLog(BookcorsupDAOImp.class);

    protected void initDao() {
    }

    public List findbklist(String str) {
        log.debug("finding all Bookcorsup instances");
        try {
            String queryString = "select distinct idbk from Bookcorsup where semester in( " + str + " )";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

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