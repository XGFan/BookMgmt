package com.bean.college;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * College entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.college.College
 * @author MyEclipse Persistence Tools
 */

public class CollegeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CollegeDAO.class);
	// property constants
	public static final String COL = "col";
	public static final String MAJOR = "major";
	public static final String SEMNUM = "semnum";

	protected void initDao() {
		// do nothing
	}

    /**
     * 保存学院专业实例
     * @param transientInstance 学院专业信息实例
     * @return 操作是否成功
     */
	public boolean save(College transientInstance) {
		log.debug("saving College instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    /**
     * 更新学院专业实例
     * @param transientInstance 学院专业实例信息
     * @return 操作是否成功
     */
	public boolean update(College transientInstance) {
		log.debug("update College instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("update successful");
			return true;
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

    /**
     * 删除学院专业实例
     * @param persistentInstance 学院专业信息实例
     */
	public void delete(College persistentInstance) {
		log.debug("deleting College instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    /**
     * 根据学院ID来删除学院专业信息
     * @param idcm 学院专业ID
     * @return 操作是否成功
     */
	public boolean deleteById(String idcm) {
		log.debug("deleting College instance");
		boolean result = false;
		try {
			College persistentInstance = findById(idcm);
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
			result = true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			result = false;
			throw re;
		} finally {
			return result;
		}
	}

    /**
     * 根据学院专业id来查找学院专业信息
     * @param id 学院专业id
     * @return 找到的该专业的信息
     */
	public College findById(java.lang.String id) {
		log.debug("getting College instance with id: " + id);
		try {
			College instance = (College) getHibernateTemplate().get(
					"com.bean.college.College", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * public List<College> findById(java.lang.String id) {
	 * log.debug("getting College instance with id: " + id); try { College
	 * instance = (College) getHibernateTemplate().get(
	 * "com.bean.college.College", id); List<College> list=new
	 * ArrayList<College>(); list.add(instance); return list; } catch
	 * (RuntimeException re) { log.error("get failed", re); throw re; } }
	 */

    /**
     *根据实例example来查找学院专业信息
     * @param instance 学院专业example
     * @return LIST
     */
	public List findByExample(College instance) {
		log.debug("finding College instance by example");
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
     * 根据属性名和属性名来查找
     * @param propertyName 属性名
     * @param value 属性值
     * @return 查找的结果LIST
     */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding College instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from College as model where model."
					+ propertyName + " like '%" + (String) value + "%'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

    /**
     * 根据学院查找
     * @param col 学院
     * @return 学院专业班级信息
     */
	public List findByCol(Object col) {
		return findByProperty(COL, col);
	}

	public List findByMajor(Object major) {
		return findByProperty(MAJOR, major);
	}

	public List findBySemnum(Object semnum) {
		return findByProperty(SEMNUM, semnum);
	}

    /**
     * 返回所有的学院专业信息
     * @return LIST
     */
	public List findAll() {
		log.debug("finding all College instances");
		try {
			String queryString = "from College";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 获取所有的专业，根据IDCM排序
	 * @return 按照idcm排序的list
	 */
	public List findAllOrderByIdcm() {
		log.debug("finding all College instances");
		try {
			String queryString = "from College as c order by c.idcm asc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 获取某学院的所有专业，根据IDCM排序
	 * @return 某学院的所有专业，根据IDCM排序
	 */
	public List findByColOrderByIdcm(String col) {
		log.debug("finding all College instances");
		try {
			String queryString = "from College as c where c.col=? order by c.idcm asc";
			return getHibernateTemplate().find(queryString, col);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllDistinct() {
		log.debug("finding all College instances");
		try {
			String queryString = "select distinct cl.idcm,cl.col from College as cl";
			System.out.println(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public College merge(College detachedInstance) {
		log.debug("merging College instance");
		try {
			College result = (College) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(College instance) {
		log.debug("attaching dirty College instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(College instance) {
		log.debug("attaching clean College instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 获取所有的学院名称
	 * */
	public List getAllCol() {
		log.debug("finding all Cols");
		try {
			String queryString = "select distinct col from College";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 通过学院名称获取所有的专业名称
	 * */
	public List getMajorByCol(String col) {
		log.debug("finding all Majors by colname");
		try {
			String queryString = "select distinct major from College as c where c.col='"
					+ col + "'";
			List list = getHibernateTemplate().find(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 根据学院名称和专业名称获取记录
	 * */
	public List<College> getCol(String col, String major) {
		log.debug("finding Col by colname and major");
		try {
			String queryString = "from College as c where c.col='" + col
					+ "' and c.major='" + major + "'";
			// System.out.println(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 模糊查询，通过学院或者专业
	 * */
	public List fuzzyQuery(String condition) {
		log.debug("finding Col by colname or major");
		try {
			String queryString = "from College as c where c.col like '%"
					+ condition + "%' or c.major like '%" + condition + "%'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static CollegeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CollegeDAO) ctx.getBean("CollegeDAO");
	}

}