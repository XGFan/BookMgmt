package com.bean.courclass;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
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
                    + "' and semester='" + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    // ------------------------------------------------------------//
    public void save(Courclass transientInstance) {
        log.debug("saving Courclass instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Courclass persistentInstance) {
        log.debug("deleting Courclass instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Courclass findById(com.bean.courclass.CourclassId id) {
        log.debug("getting Courclass instance with id: " + id);
        try {
            Courclass instance = (Courclass) getHibernateTemplate().get(
                    "com.bean.courclass.Courclass", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Courclass instance) {
        log.debug("finding Courclass instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Courclass instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Courclass as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Courclass instances");
        try {
            String queryString = "from Courclass";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Courclass merge(Courclass detachedInstance) {
        log.debug("merging Courclass instance");
        try {
            Courclass result = (Courclass) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Courclass instance) {
        log.debug("attaching dirty Courclass instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Courclass instance) {
        log.debug("attaching clean Courclass instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static CourclassDAO getFromApplicationContext(ApplicationContext ctx) {
        return (CourclassDAO) ctx.getBean("CourclassDAO");
    }
}