package com.bean.cls;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Class
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.cls.Class
 * @author MyEclipse Persistence Tools
 */

public class ClassDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ClassDAO.class);
	// property constants
	public static final String CAMPUS = "campus";
	public static final String GRADE = "grade";
	public static final String SEMESTER = "semester";
	public static final String CLSNO = "clsno";
	public static final String STUNUM = "stunum";

	protected void initDao() {
		// do nothing
	}

    /**
     * 保存班级实例
     * @param transientInstance 班级信息实例
     */
	public void save(Class transientInstance) {
		log.debug("saving Class instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    /**
     * 删除班级实例
     * @param persistentInstance 班级信息实例
     */
	public void delete(Class persistentInstance) {
		log.debug("deleting Class instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    /**
     * 根据班级id查找班级
     * @param id 班级id
     * @return 班级实例
     */
	public Class findById(java.lang.String id) {
		log.debug("getting Class instance with id: " + id);
		try {
			Class instance = (Class) getHibernateTemplate().get(
					"com.bean.cls.Class", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    /**
     * 根据传入的example进行模糊查找
     * @param instance 班级实例example
     * @return 查找到的班级信息LIST
     */
	public List findByExample(Class instance) {
		log.debug("finding Class instance by example");
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
     * 根据传入的属性名和属性值来进行查找
     * @param propertyName 属性名
     * @param value 属性值
     * @return 查找到的班级信息LIST
     */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Class instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Class as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCampus(Object campus) {
		return findByProperty(CAMPUS, campus);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findBySemester(Object semester) {
		return findByProperty(SEMESTER, semester);
	}

	public List findByClsno(Object clsno) {
		return findByProperty(CLSNO, clsno);
	}

	public List findByStunum(Object stunum) {
		return findByProperty(STUNUM, stunum);
	}

    /**
     * 返回所有的班级信息
     * @return LIST
     */
	public List findAll() {
		log.debug("finding all Class instances");
		try {
			String queryString = "from Class";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List getClassWithJoin() {
		log.debug("finding all Class instances");
		try {
			String queryString = "from Class c join c.college cc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Class merge(Class detachedInstance) {
		log.debug("merging Class instance");
		try {
			Class result = (Class) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Class instance) {
		log.debug("attaching dirty Class instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Class instance) {
		log.debug("attaching clean Class instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClassDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ClassDAO) ctx.getBean("ClassDAO");
	}

    /**
     * 模糊查找
     * @param condition 条件
     * @return LIST
     */
	public List getClassFuzzy(String condition) {
		String queryString = "from com.bean.cls.Class c join fetch c.college cc"
				+ " where cc.col like '%"
				+ condition
				+ "%' or cc.major like '%"
				+ condition
				+ "%'"
				+ " or c.semester like '%"
				+ condition
				+ "%' or c.grade like '%"
				+ condition
				+ "%' or c.campus like '%" + condition + "%'";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

    /**
     * 根据若干信息精确查找班级
     * @param col 学院
     * @param major 专业
     * @param grade 年级
     * @param campus 校区
     * @return  班级LIST
     */
	public List getClassByGradeCampusColMajor(String col, String major,
			String grade, String campus) {
		String queryString = "from com.bean.cls.Class c join fetch c.college cc where"
				+ " cc.col like '%"
				+ col
				+ "%' and cc.major like '%"
				+ major
				+ "%'"
				+ " and c.campus like '%"
				+ campus
				+ "%' and c.grade like '%"
				+ grade
				+ "%'"
				+ " order by cc.col,cc.major,c.grade,c.clsno";
		List list = getHibernateTemplate().find(queryString);
		return list;
	}

	public List getAllCampus() {
		log.debug("finding all Cols");
		try {
			String queryString = "select distinct campus from Class";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List getAllGrade() {
		log.debug("finding all Cols");
		try {
			String queryString = "select distinct grade from Class";
			// System.out.println(queryString);
			// System.out.println(getHibernateTemplate().find(queryString).size());
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Class persistentInstance) {
		log.debug("saveOrUpdating Class instance");
		try {
			getHibernateTemplate().saveOrUpdate(persistentInstance);
			log.debug("saveOrUpdate successful");
		} catch (RuntimeException re) {
			log.error("saveOrUpdate failed", re);
			throw re;
		}
	}

	public int getClsNum(String grade, String idcm) {
		String queryString = "from com.bean.cls.Class c join c.college cc where c.clsno=any(select max(c1.clsno) from Class c1 join c1.college cc1 where c1.grade='"
				+ grade
				+ "' and cc1.idcm ='"
				+ idcm
				+ "') and c.grade='"
				+ grade + "' and cc.idcm ='" + idcm + "'";
		// List<Object[]>
		// list=this.getSession().createQuery(queryString).list();
		/** 在将上面这句话转换成下面这句话之前，每次只能添加2次班级，系统就不能访问数据库，但是Tomcat正常开启。张驰 20140506**/
		List<Object[]> list = getHibernateTemplate().find(queryString);
		Integer clsNum = 0;
		for (Object[] objs : list) {
			Class cls = (Class) objs[0];
			// System.out.println(cls.getClsno());
			clsNum = cls.getClsno();
		}
		return clsNum;
	}
}