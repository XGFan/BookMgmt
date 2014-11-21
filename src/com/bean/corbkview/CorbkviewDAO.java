package com.bean.corbkview;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Corbkview entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.corbkview.Corbkview
 */

public class CorbkviewDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CorbkviewDAO.class);

    protected void initDao() {
        // do nothing
    }

    /**
     * 保存
     *
     * @param transientInstance
     */
    public void save(Corbkview transientInstance) {
        log.debug("saving Corbkview instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /**
     * 删除
     *
     * @param persistentInstance
     */
    public void delete(Corbkview persistentInstance) {
        log.debug("deleting Corbkview instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public Corbkview findById(com.bean.corbkview.CorbkviewId id) {
        log.debug("getting Corbkview instance with id: " + id);
        try {
            Corbkview instance = (Corbkview) getHibernateTemplate().get(
                    "com.bean.corbkview.Corbkview", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 传入实例查找
     *
     * @param instance
     * @return LIST
     */
    public List findByExample(Corbkview instance) {
        log.debug("finding Corbkview instance by example");
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

    /**
     * 根据属性名和属性值来查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return LIST
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Corbkview instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Corbkview as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /**
     * 返回所有
     *
     * @return
     */
    public List findAll() {
        log.debug("finding all Corbkview instances");
        try {
            String queryString = "from Corbkview";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Corbkview merge(Corbkview detachedInstance) {
        log.debug("merging Corbkview instance");
        try {
            Corbkview result = (Corbkview) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Corbkview instance) {
        log.debug("attaching dirty Corbkview instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Corbkview instance) {
        log.debug("attaching clean Corbkview instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static CorbkviewDAO getFromApplicationContext(ApplicationContext ctx) {
        return (CorbkviewDAO) ctx.getBean("CorbkviewDAO");
    }
}