package com.bean.coursebk;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Coursebk entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.coursebk.Coursebk
 * @author MyEclipse Persistence Tools
 */

public class CoursebkDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CoursebkDAO.class);

	protected void initDao() {
		// do nothing
	}

    /**
     * 保存新的课程
     * @param transientInstance 课程实例
     */
	public void save(Coursebk transientInstance) {
		log.debug("saving Coursebk instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Coursebk transientInstance) {
		log.debug("updating Coursebk instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(Coursebk persistentInstance) {
		log.debug("deleting Coursebk instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Coursebk findById(java.lang.Integer id) {
		log.debug("getting Coursebk instance with id: " + id);
		try {
			Coursebk instance = (Coursebk) getHibernateTemplate().get(
					"com.bean.coursebk.Coursebk", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByIdcor(String idcor) {
		log.debug("getting Coursebk instance with idcor: " + idcor);
		try {
			String queryString = "from Coursebk as cb where cb.course.idcor ='"+idcor+"'";
			return  getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    /**
     * 根据科目id和教科书id来查找课程
     * @param idcor 科目
     * @param idbk 教科书
     * @return obj list
     * @see com.bean.coursebk.Coursebk
     */
	public List findByIdcorAndIdbk(String idcor,String idbk) {
		log.debug("getting Coursebk instance with idcor: " + idcor);
		try {
			String[] args = {idcor,idbk};
			String queryString = "from Coursebk as cb where cb.course.idcor =? and cb.book.idbk=?";
			return  getHibernateTemplate().find(queryString, args);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Coursebk instance) {
		log.debug("finding Coursebk instance by example");
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
		log.debug("finding Coursebk instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Coursebk as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Coursebk instances");
		try {
			String queryString = "from Coursebk";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Coursebk merge(Coursebk detachedInstance) {
		log.debug("merging Coursebk instance");
		try {
			Coursebk result = (Coursebk) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Coursebk instance) {
		log.debug("attaching dirty Coursebk instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Coursebk instance) {
		log.debug("attaching clean Coursebk instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CoursebkDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CoursebkDAO) ctx.getBean("CoursebkDAO");
	}
}