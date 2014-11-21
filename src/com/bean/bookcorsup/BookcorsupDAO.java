package com.bean.bookcorsup;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
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

    // -------------------------------------------------------//
    public void save(Bookcorsup transientInstance) {
        log.debug("saving Bookcorsup instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Bookcorsup persistentInstance) {
        log.debug("deleting Bookcorsup instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Bookcorsup findById(BookcorsupId id) {
        log.debug("getting Bookcorsup instance with id: " + id);
        try {
            Bookcorsup instance = (Bookcorsup) getHibernateTemplate().get(
                    "com.bean.bookcorsup.Bookcorsup", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Bookcorsup instance) {
        log.debug("finding Bookcorsup instance by example");
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
        log.debug("finding Bookcorsup instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Bookcorsup as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Bookcorsup instances");
        try {
            String queryString = "from Bookcorsup";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Bookcorsup merge(Bookcorsup detachedInstance) {
        log.debug("merging Bookcorsup instance");
        try {
            Bookcorsup result = (Bookcorsup) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Bookcorsup instance) {
        log.debug("attaching dirty Bookcorsup instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Bookcorsup instance) {
        log.debug("attaching clean Bookcorsup instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static BookcorsupDAO getFromApplicationContext(ApplicationContext ctx) {
        return (BookcorsupDAO) ctx.getBean("BookcorsupDAO");
    }

}