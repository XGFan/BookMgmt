package com.bean.corbook;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Corbookview entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.corbook.Corbookview
 * @author MyEclipse Persistence Tools
 */

public class CorbookviewDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CorbookviewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Corbookview transientInstance) {
		log.debug("saving Corbookview instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Corbookview persistentInstance) {
		log.debug("deleting Corbookview instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Corbookview findById(com.bean.corbook.CorbookviewId id) {
		log.debug("getting Corbookview instance with id: " + id);
		try {
			Corbookview instance = (Corbookview) getHibernateTemplate().get(
					"com.bean.corbook.Corbookview", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Corbookview instance) {
		log.debug("finding Corbookview instance by example");
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
		log.debug("finding Corbookview instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Corbookview as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Corbookview instances");
		try {
			String queryString = "from Corbookview";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findCourseByCorname(String corname) {
		log.debug("findCourseByCorname");
		try {
			String queryString = "from Corbookview view where view.corname like '%"
					+ corname + "%'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("findCourseByCorname failed", re);
			throw re;
		}
	}

	public List findByBk(String bkname) {
		log.debug("finding all Corbookview instances");
		try {
			String queryString = "from Corbookview view where view.bkname like '%"
					+ bkname + "%'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Corbookview merge(Corbookview detachedInstance) {
		log.debug("merging Corbookview instance");
		try {
			Corbookview result = (Corbookview) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Corbookview instance) {
		log.debug("attaching dirty Corbookview instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Corbookview instance) {
		log.debug("attaching clean Corbookview instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CorbookviewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CorbookviewDAO) ctx.getBean("CorbookviewDAO");
	}
}