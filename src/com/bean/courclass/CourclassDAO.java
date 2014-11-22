package com.bean.courclass;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

public class CourclassDAO extends HibernateDaoSupport implements
        CourclassDAOInf {
    private static final Log log = LogFactory.getLog(CourclassDAO.class);

    // property constants

    protected void initDao() {
        // do nothing
    }

    public List findByCorSem(String idcor, String semester) {
        log.debug("finding all Courclass instances");
        try {
            String queryString = "from Courclass where idcor='" + idcor
                    + "' and semester='" +semester + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}