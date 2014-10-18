package com.bean.bookpurchaseview;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bookpurchaseview entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.bookpurchaseview.Bookpurchaseview
 * @author MyEclipse Persistence Tools
 */

public class BookpurchaseviewDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BookpurchaseviewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Bookpurchaseview transientInstance) {
		log.debug("saving Bookpurchaseview instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bookpurchaseview persistentInstance) {
		log.debug("deleting Bookpurchaseview instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bookpurchaseview findById(
			com.bean.bookpurchaseview.BookpurchaseviewId id) {
		log.debug("getting Bookpurchaseview instance with id: " + id);
		try {
			Bookpurchaseview instance = (Bookpurchaseview) getHibernateTemplate()
					.get("com.bean.bookpurchaseview.Bookpurchaseview", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bookpurchaseview instance) {
		log.debug("finding Bookpurchaseview instance by example");
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
		log.debug("finding Bookpurchaseview instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Bookpurchaseview as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Bookpurchaseview instances");
		try {
			String queryString = "from Bookpurchaseview";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Bookpurchaseview> findByYearAndSem(int year, int sem) {
		log.debug("finding Bookpurchaseview instances ByYeayAndSem");
		try {
			String queryString = "from Bookpurchaseview as b where (((" + year
					+ "- b.id.grade)*2 + " + sem + ") = b.id.semester)";
			queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Bookpurchaseview> findByYearAndSemAndCol(int year, int sem,
			String idcls) {
		log.debug("finding Bookpurchaseview instances ByYeayAndSem");
		try {
			String queryString = "from Bookpurchaseview as b where (((" + year
					+ "- b.id.grade)*2 + " + sem
					+ ") = b.id.semester and b.id.idcls= '" + idcls + "')";
			//queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
			queryString += " order by convert_gbk(b.id.bkname) asc";
			// System.out.println(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Bookpurchaseview> findByYearAndSemAndGrade(int year, int sem,
			int grade) {
		log.debug("finding Bookpurchaseview instances ByYeayAndSem");
		try {
			String queryString = "from Bookpurchaseview as b where (((" + year
					+ "- b.id.grade)*2 + " + sem
					+ ") = b.id.semester and b.id.grade= '" + grade + "')";
			queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
			// System.out.println(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bookpurchaseview merge(Bookpurchaseview detachedInstance) {
		log.debug("merging Bookpurchaseview instance");
		try {
			Bookpurchaseview result = (Bookpurchaseview) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bookpurchaseview instance) {
		log.debug("attaching dirty Bookpurchaseview instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bookpurchaseview instance) {
		log.debug("attaching clean Bookpurchaseview instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BookpurchaseviewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BookpurchaseviewDAO) ctx.getBean("BookpurchaseviewDAO");
	}
}