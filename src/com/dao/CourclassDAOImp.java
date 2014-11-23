package com.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Courclass entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.courclass.Courclass
 */

public class CourclassDAOImp extends HibernateDaoSupport implements
        CourclassDAO {
    private static final Log log = LogFactory.getLog(CourclassDAOImp.class);

    protected void initDao() {
    }

    public List findByCorSem(String idcor, String semester) {
        log.debug("finding all Courclass instances");
        try {
            String queryString = "from Courclass where idcor='" + idcor
                    + "' and semester='" + semester + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}