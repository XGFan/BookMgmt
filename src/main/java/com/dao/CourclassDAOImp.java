package com.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.courclass.Courclass
 */
@Repository("courclassDAO")
public class CourclassDAOImp implements
        CourclassDAO {
    private static final Log log = LogFactory.getLog(CourclassDAOImp.class);
    @Autowired
    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
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