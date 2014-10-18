package com.bean.corplan;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Courplan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.corplan.Courplan
 * @author MyEclipse Persistence Tools
 */

public class CourplanDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CourplanDAO.class);
	public static final String SEMESTER = "semester";
	public static final String IDCOR = "idcor";

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Courplan transientInstance) {
		log.debug("saving Courplan instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Courplan persistentInstance) {
		log.debug("deleting Courplan instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Courplan findById(java.lang.String id) {
		log.debug("getting Courplan instance with id: " + id);
		try {
			Courplan instance = (Courplan) getHibernateTemplate().get(
					"com.bean.corplan.Courplan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Courplan instance) {
		log.debug("finding Courplan instance by example");
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
		log.debug("finding Courplan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Courplan as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findBySemester(Object semester) {
		return findByProperty(SEMESTER, semester);
	}public List getCorplanByIdcorSemester(String idcor, String semester) {
		String queryString="from courplan where idcor ='"
			+ idcor + "' and semester='" + semester + "'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpc.idcor ='"
//				+ idcor + "' and cp.semester='" + semester + "'";
		return getHibernateTemplate().find(queryString);
	}
	public List getCorplanByIdcor(String idcor) {
		String queryString="from courplan where idcor ='"
			+ idcor + "'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpc.idcor ='"
//				+ idcor + "'";
		return getHibernateTemplate().find(queryString);
	}
	public List findAll() {
		log.debug("finding all Courplan instances");
		try {
			String queryString = "from Courplan";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Courplan merge(Courplan detachedInstance) {
		log.debug("merging Courplan instance");
		try {
			Courplan result = (Courplan) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Courplan instance) {
		log.debug("attaching dirty Courplan instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Courplan instance) {
		log.debug("attaching clean Courplan instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CourplanDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CourplanDAO) ctx.getBean("CourplanDAO");
	}
	public void deleteById(String idcor) {
		Courplan persistentInstance = findById(idcor);
		getHibernateTemplate().delete(persistentInstance);
	}

	public List getCorplanByColOrMajor(String condition) {
		String queryString="from courplan where col like '%"
				+ condition + "%' or major like '%" + condition + "%'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//				+ condition + "%' or cpcc.major like '%" + condition + "%'";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getCorplanWithJoin() {
		String queryString = "from courplan";
		//String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc ";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getCorplanByCol(String col) {
		String queryString="from courplan  where col like '%"+ col + "%'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//				+ col + "%'";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getCorplanByColMajor(String col, String major) {
		String queryString = "from courplan where col like '%"
			+ col + "%'and major like '%" + major + "%'";	
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//				+ col + "%'and cpcc.major like '%" + major + "%'";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getCorplanByMajor(String major) {
		String queryString = "from courplan where "+ "major like '%" + major + "%'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where "
//				+ "cpcc.major like '%" + major + "%'";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getCorplanByColMajorSem(String col, String major,
			String semester) {
		String queryString="from courplan where col like '%"+ col
		+ "%' and major ='"
		+ major
		+ "' and semester = '" + semester + "'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//				+ col
//				+ "%' and cpcc.major ='"
//				+ major
//				+ "' and cp.semester = '" + semester + "'";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getCorplanByColMajorSemCorname(String col, String major,
			String semester, String corname) {
		log.debug("get Corplan instance");
		try {
			String queryString = "from courplan where col like '%"
				+ col
				+ "%' and major ='"
				+ major
				+ "' and semester ='"
				+ semester
				+"' and corname ='" + corname + "'";
//			String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//					+ col
//					+ "%' and cpcc.major ='"
//					+ major
//					+ "' and cp.semester ='"
//					+ semester
//					+"' and cpc.corname ='" + corname + "'";
			//System.out.println(getHibernateTemplate());
			List list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getCorplanByColMajorSemCornameIdcor(String col, String major,
			String semester, String corname, String idcor) {
		log.debug("get Corplan instance");
		try {
			String queryString = "from courplan where col like '%"
				+ col
				+ "%' and major ='"
				+ major
				+ "' and semester ='"
				+ semester
				+ "' and idcor ='"
				+ idcor + "' and corname ='" + corname + "'";
//			String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//					+ col
//					+ "%' and cpcc.major ='"
//					+ major
//					+ "' and cp.semester ='"
//					+ semester
//					+ "' and cpc.idcor ='"
//					+ idcor + "' and cpc.corname ='" + corname + "'";
			List list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getCorplanFuzzy(String condition) {
		String queryString = "from courplan where col like '%"
			+ condition
			+ "%' or major like '%"
			+ condition
			+ "%'"
			+ " or semester like '%" + condition + "%'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
//				+ condition
//				+ "%' or cpcc.major like '%"
//				+ condition
//				+ "%'"
//				+ " or cp.semester like '%" + condition + "%'";
		System.out.println(queryString);
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	/* 已知学院专业，通过课程名称模糊查找*/
	public List getCorplanFuzzyByColMajorCorname(String col, String major,
			String condition) {
		String queryString = "from courplan where col ='"
			+ col
			+ "' and major ='"
			+ major
			+ "' and corname like '%" + condition + "%'";
//		String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col ='"
//				+ col
//				+ "' and cpcc.major ='"
//				+ major
//				+ "' and cpc.corname like '%" + condition + "%'";
		//System.out.println(queryString);
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	/* 清空教学计划表 */
	public void deleteAllCorplan() {
		List corplanList = this.findAll();
		getHibernateTemplate().deleteAll(corplanList);
	}

	/* 初始化，根据Course自动生成所有的Corplan */
	public void initAllCorplan(List entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

}