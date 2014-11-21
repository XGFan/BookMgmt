package com.bean.course;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.book.Book;

/**
 * 对科目信息进行读写
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.course.Course
 */

public class CourseDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CourseDAO.class);
    // property constants
    public static final String CORNAME = "corname";
    public static final String SEMESTER = "semester";

    protected void initDao() {
    }

    /**
     * 保存科目
     *
     * @param transientInstance 科目实例
     * @return boolean
     * @see com.bean.course.Course
     */
    public boolean save(Course transientInstance) {
        log.debug("saving Course instance");
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
     * 删除科目
     *
     * @param persistentInstance 科目实例
     */
    public void delete(Course persistentInstance) {
        log.debug("deleting Course instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
     * 根据科目id查找科目
     *
     * @param id 科目id
     * @return 科目 obj list
     */
    public Course findById(java.lang.String id) {
        log.debug("getting Course instance with id: " + id);
        try {
            Course instance = (Course) getHibernateTemplate().get(
                    "com.bean.course.Course", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 根据科目example查找
     *
     * @param instance 科目example
     * @return 科目 obj list
     */
    public List findByExample(Course instance) {
        log.debug("finding Course instance by example");
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
     * 根据属性名和属性值来查找科目
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return 科目 obj list
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Course instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Course as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /**
     * @param corname
     * @return
     * @deprecated
     */
    public List findByCorname(Object corname) {
        return findByProperty(CORNAME, corname);
    }

    /**
     * @param semester
     * @return
     * @deprecated
     */
    public List findBySemester(Object semester) {
        return findByProperty(SEMESTER, semester);
    }

    /**
     * 找到所有的科目信息
     *
     * @return 科目 obj list
     */
    public List findAll() {
        log.debug("finding all Course instances");
        try {
            String queryString = "from Course c join c.college cc";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据学院名称查找科目
     *
     * @param col 学院名称
     * @return 科目信息 obj list
     */
    public List getCourseByCol(String col) {
        log.debug("finding all Courses by colname");
        try {
            String queryString = "from Course c join c.college cc where cc.col like '%"
                    + col + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 精确查找（学院专业id，学院名，专业名，科目名）
     *
     * @param idcm    学院专业id
     * @param col     学院名
     * @param major   专业名
     * @param corname 科目名
     * @return 科目 obj list
     */
    public List getCourseByIdcmColMajorCorname(String idcm, String col, String major, String corname) {
        log.debug("finding all Courses by idcm col major corname");
        try {
            String queryString = "from Course c join c.college cc where cc.idcm = '"
                    + idcm + "' and c.corname = '" + corname + "' and cc.col = '" + col + "' and cc.major = '" + major + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 精确查找（学院专业id，科目名称）
     *
     * @param idcm     学院专业id
     * @param corname  科目名称
     * @param semester 所在学期
     * @return 科目 obj list
     */
    public List getCourseByIdcmCornameSem(String idcm, String corname, String semester) {
        log.debug("finding all Courses by idcmcornamesem");
        try {
            String queryString = "from Course c join c.college cc where cc.idcm = '"
                    + idcm + "' and c.corname = '" + corname + "' and c.semester = '" + semester + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据专业名称模糊查找科目
     *
     * @param major 专业名称
     * @return 科目 obj list
     */
    public List getCourseByMajor(String major) {
        log.debug("finding all Courses by major");
        try {
            String queryString = "from Course c join c.college cc where cc.major like'%"
                    + major + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

	/*
     * public List getCourseByMajor(String major){
	 * log.debug("finding all Courses by major"); try { SessionFactory
	 * ssf=getHibernateTemplate().getSessionFactory(); String queryString =
	 * "select b.col,b.major,b.idcm,a.idcor,a.corname,a.semester "; queryString
	 * = queryString +"from course as a,college as b where a.idcm=b.idcm ";
	 * queryString = queryString +" and b.major='" + major + "'"; Session
	 * session = ssf.openSession(); Query query =
	 * session.createSQLQuery(queryString); List list = query.list(); return
	 * list; } catch (RuntimeException re) { log.error("find all failed", re);
	 * throw re; } }
	 */
    /**
     * 閫氳繃瀛﹂櫌涓撲笟鏉ヨ幏鍙栬绋嬩俊鎭?	 *
     * @param col
     * @param major
     * @return
     */
    // public List getCourseByColMajor(String col,String major){
    // log.debug("finding all Courses by colname nad major");
    // try {
    // SessionFactory ssf=getHibernateTemplate().getSessionFactory();
    // String queryString =
    // "select a.idcor,b.col,b.major,b.idcm,a.corname,a.semester ";
    // queryString = queryString
    // +"from course as a,college as b where a.idcm=b.idcm ";
    // queryString = queryString +"and b.col ='" + col +"' and b.major ='" +
    // major + "'";
    // Session session = ssf.openSession();
    // //Query query =
    // session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(CourseTemp.class));
    // Query query = session.createSQLQuery(queryString).addScalar("idcor",
    // Hibernate.STRING).addScalar("col", Hibernate.STRING).addScalar("major",
    // Hibernate.STRING).addScalar("idcm",
    // Hibernate.STRING).addScalar("corname",
    // Hibernate.STRING).addScalar("semester", Hibernate.STRING);
    // List list = query.list();
    // List<CourseTemp> result = new ArrayList<CourseTemp>();
    // for(int i=0;i<list.size();i++){
    // CourseTemp temp = new CourseTemp();
    // Object[] object = (Object[])list .get(i);
    // temp.setIdcor(object[0].toString());
    // temp.setCol(object[1].toString());
    // temp.setMajor(object[2].toString());
    // temp.setIdcm(object[3].toString());
    // temp.setCorname(object[4].toString());
    // temp.setSemester(object[5].toString());
    // result.add(temp);
    // //System.out.println(temp.toString());
    // }
    // return result;
    // } catch (RuntimeException re) {
    // log.error("find all failed", re);
    // throw re;
    // }
    // }

    /**
     * 通过学院和专业查找科目，专业是模糊查找
     *
     * @param col   学院
     * @param major 专业
     * @return 科目 obj list
     */
    public List getCourseByColMajor(String col, String major) {
        log.debug("finding all Courses by colname nad major");
        try {
            String queryString = "from Course c join c.college cc where cc.col like '%"
                    + col + "%' and cc.major ='" + major + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据科目名称查找科目
     *
     * @param corname 科目名称
     * @return 科目 obj list
     */
    public List getCourseByCorname(String corname) {
        log.debug("finding all Courses by corname");
        try {
            String queryString = "from Course c join c.college cc where c.corname ='"
                    + corname + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据学院，专业，科目进行查找，其中科目名称是模糊查找
     *
     * @param col     学院名称
     * @param major   专业
     * @param corname 科目名称
     * @return 科目信息 obj list
     */
    public List getCourseFuzzyByCorname(String col, String major, String corname) {
        log.debug("finding all Courses by corname");
        try {
            String queryString = "from Course c join c.college cc where cc.col ='"
                    + col
                    + "' and cc.major ='"
                    + major
                    + "' and c.corname like '%" + corname + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据学院，专业，学期查找
     *
     * @param col   学院名称
     * @param major 专业名称
     * @param sem   课程安排在哪个学期
     * @return 学院专业在某个学期的课程 obj list
     */
    public List getCourseByColMajorSem(String col, String major, String sem) {
        log.debug("finding all Courses by col,major,sem");
        try {
            String queryString = "from Course c join c.college cc where ";
            queryString += "cc.col like '%" + col + "%' and cc.major='" + major
                    + "' and c.semester='" + sem + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 模糊查找，从学院，专业，科目名查询
     *
     * @param condition 关键字
     * @return 科目 obj list
     */
    public List fuzzyQuery(String condition) {
        log.debug("finding all Courses by col or major or corname");
        try {
            String queryString = "from Course c join c.college cc where ";
            queryString += "cc.col like '%" + condition
                    + "%' or cc.major like '%" + condition
                    + "%' or c.corname like '%" + condition + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Course merge(Course detachedInstance) {
        log.debug("merging Course instance");
        try {
            Course result = (Course) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Course instance) {
        log.debug("attaching dirty Course instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Course instance) {
        log.debug("attaching clean Course instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static CourseDAO getFromApplicationContext(ApplicationContext ctx) {
        return (CourseDAO) ctx.getBean("CourseDAO");
    }

    public void updateCourse(Course cor) {
        getHibernateTemplate().saveOrUpdate(cor);
    }
}