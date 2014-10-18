package com.bean.supplier;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Supplier entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.supplier.Supplier
 * @author MyEclipse Persistence Tools
 */

public class SupplierDAO extends HibernateDaoSupport implements SupplierDAOInf {
	private static final Log log = LogFactory.getLog(SupplierDAO.class);
	// property constants
	public static final String SUPPLIER = "supplier";
	public static final String PUBLISHER = "publisher";

	protected void initDao() {
		// do nothing
	}

	/*
	 * supplier 的模糊查询
	 */
	public List<Supplier> likeFindByPub(String publisher) {
		String hql = "from Supplier where publisher like ?";
		return getHibernateTemplate().find(hql, "%" + publisher + "%");
	}

	/*
	 * 按supplier 精确查询，publisher模糊查询
	 */
	public List findBySubPub(String supplier, String publisher) {

		String hql = "from Supplier where supplier='" + supplier
				+ "' and publisher like ?";
		return getHibernateTemplate().find(hql, "%" + publisher + "%");
	}

	/*
	 * 按supplier 精确查询，publisher精确查询
	 */
	public List accfindBySubPub(String supplier, String publisher) {
		log.debug("accfindBySubPub");
		try {
			String hql = "from Supplier where supplier='" + supplier;
			hql += "' and publisher = '" + publisher + "'";
			return getHibernateTemplate().find(hql);
		} catch (RuntimeException re) {
			log.error("accfindBySubPub failed", re);
			throw re;
		}
	}

	/*
	 * 根据每个对象查出他们的子对象，如果有子对象就更新， //没子对象就不更新，所以假设查到子对象了，并更新了子对象
	 */
	public void updateSup(Supplier sup) {
		try {
			getHibernateTemplate().saveOrUpdate(sup);
			log.debug("updateSup successful");
		} catch (RuntimeException re) {
			log.error("updateSup failed", re);
			throw re;
		}
	}

	public void save(Supplier transientInstance) {
		log.debug("saving Supplier instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Supplier persistentInstance) {
		log.debug("deleting Supplier instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Supplier findById(java.lang.String id) {
		log.debug("getting Supplier instance with id: " + id);
		try {
			Supplier instance = (Supplier) getHibernateTemplate().get(
					"com.bean.supplier.Supplier", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Supplier instance) {
		log.debug("finding Supplier instance by example");
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
		log.debug("finding Supplier instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Supplier as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySupplier(Object supplier) {
		return findByProperty(SUPPLIER, supplier);
	}

	public List findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	/* 根据出版社查idsp */
	/*
	 * public Supplier findByPub(String publisher) {
	 * log.debug("finding all Supplier instances"); try { String queryString =
	 * "from Supplier where publisher='"+publisher+"'"; List
	 * list=getHibernateTemplate().find(queryString); return list; } catch
	 * (RuntimeException re) { log.error("find all failed", re); throw re; } }
	 */

	public List findAll() {
		log.debug("finding all publisher instances");
		try {
			String queryString = "from Supplier s order by convert_gbk(s.publisher) asc";
			List list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllSupplier() {
		log.debug("finding all Supplier instances");
		try {
			String queryString = "select distinct s.supplier from Supplier s order by convert_gbk(s.supplier) asc";
			List list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllOrderByPublisher() {
		log.debug("finding all Supplier instances");
		try {
			String queryString = "from Supplier s order by convert_gbk(s.publisher) asc";
			List list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllOrderByIdsp() {
		log.debug("finding all Supplier instances");
		try {
			String queryString = "from Supplier s order by s.idsp";
			List list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Supplier merge(Supplier detachedInstance) {
		log.debug("merging Supplier instance");
		try {
			Supplier result = (Supplier) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Supplier instance) {
		log.debug("attaching dirty Supplier instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Supplier instance) {
		log.debug("attaching clean Supplier instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SupplierDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SupplierDAO) ctx.getBean("SupplierDAO");
	}
}