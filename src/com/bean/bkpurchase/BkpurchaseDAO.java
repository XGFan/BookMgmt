package com.bean.bkpurchase;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bkpurchase entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.bkpurchase.Bkpurchase
 * @author MyEclipse Persistence Tools
 */

public class BkpurchaseDAO extends HibernateDaoSupport implements
		BkpurchaseDAOInf {
	private static final Log log = LogFactory.getLog(BkpurchaseDAO.class);
	// property constants
	public static final String BKNUM = "bknum";
	public static final String CAMPUS = "campus";
	public static final String SUPPLIER = "supplier";

	protected void initDao() {
		// do nothing
	}

	public void Bkpursave(List<Bkpurchase> list) {
		log.debug("saving Bkpurchase instance");
		try {
			// System.out.println("Hibernate dao");
            // 找出所有需要购买的教科书清单
			List li = this.findAll();
            // 如果不为空，则全部清空
			if (!li.isEmpty()) {
				getHibernateTemplate().deleteAll(li);
			}
            //依次保存
			for (Bkpurchase bkpur : list) {
				// System.out.println("id  "+bkpur.getId()+"  ");
				getHibernateTemplate().save(bkpur);
			}
            //写入日志
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void save(Bkpurchase transientInstance) {
		log.debug("saving Bkpurchase instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bkpurchase persistentInstance) {
		log.debug("deleting Bkpurchase instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bkpurchase findById(java.lang.Integer id) {
		log.debug("getting Bkpurchase instance with id: " + id);
		try {
            //根据ID来实例化对象
			Bkpurchase instance = (Bkpurchase) getHibernateTemplate().get(
					"com.bean.bkpurchase.Bkpurchase", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bkpurchase instance) {
		log.debug("finding Bkpurchase instance by example");
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
		log.debug("finding Bkpurchase instance with property: " + propertyName
				+ ", value: " + value);
		try {
            //生成模糊查找语句
			String queryString = "from Bkpurchase as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

    public List findAll() {
        log.debug("finding all Bkpurchase instances");
        try {
            String queryString = "from Bkpurchase";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

//	public List findByBknum(Object bknum) {
//		return findByProperty(BKNUM, bknum);
//	}
//
//	public List findByCampus(Object campus) {
//		return findByProperty(CAMPUS, campus);
//	}
//
//	public List findBySupplier(Object supplier) {
//		return findByProperty(SUPPLIER, supplier);
//	}

	public Bkpurchase merge(Bkpurchase detachedInstance) {
		log.debug("merging Bkpurchase instance");
		try {
			Bkpurchase result = (Bkpurchase) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bkpurchase instance) {
		log.debug("attaching dirty Bkpurchase instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bkpurchase instance) {
		log.debug("attaching clean Bkpurchase instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BkpurchaseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BkpurchaseDAO) ctx.getBean("BkpurchaseDAO");
	}
}