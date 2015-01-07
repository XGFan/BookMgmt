package com.dao;

import com.bean.bookcorsup.Bookcorsup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookcorsupDAO")
public class BookcorsupDAOImp extends BaseDaoImp<Bookcorsup> implements BookcorsupDAO  {
    private static final Log log = LogFactory.getLog(BookcorsupDAOImp.class);

    public List findbklist(String str) {
        log.debug("finding all Bookcorsup instances");
        try {
            String queryString = "select distinct idbk from Bookcorsup where semester in( " + str + " )";
            return getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findcorlistbyidbk(String idbk) {
        log.debug("finding all Bookcorsup instances");
        try {
            String queryString = "from Bookcorsup where idbk='" + idbk + "'";
            return getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}