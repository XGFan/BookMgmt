package com.bean.corbook;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Corbook entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.corbook.Corbook
 * @author MyEclipse Persistence Tools
 */

public class CorbookDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CorbookDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Corbook transientInstance) {
		log.debug("saving Corbook instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Corbook persistentInstance) {
		log.debug("deleting Corbook instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Corbook findById(java.lang.Integer id) {
		log.debug("getting Corbook instance with id: " + id);
		try {
			Corbook instance = (Corbook) getHibernateTemplate().get(
					"com.bean.corbook.Corbook", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Corbook instance) {
		log.debug("finding Corbook instance by example");
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
		log.debug("finding Corbook instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Corbook as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Corbook instances");
		try {
			String queryString = "from Corbook";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Corbook merge(Corbook detachedInstance) {
		log.debug("merging Corbook instance");
		try {
			Corbook result = (Corbook) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Corbook instance) {
		log.debug("attaching dirty Corbook instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Corbook instance) {
		log.debug("attaching clean Corbook instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CorbookDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CorbookDAO) ctx.getBean("CorbookDAO");
	}

	public void saveOrUpdate(Corbook corbook) {

		log.debug("saving Corbook instance");
		try {
			HibernateTemplate t = getHibernateTemplate();
			t.saveOrUpdate(corbook);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	// 通过idcorsem查询
	public List findByIdcorsem(String idcorsem) {
		try {
			String queryString = "select c from Corbook c join c.corplan p where p.idcorsem = '"
					+ idcorsem + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property idcorsem failed", re);
			throw re;
		}
	}

}