package com.dao;

import com.bean.course.Course;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 对科目信息进行读写
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.course.Course
 */

public class CourseDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CourseDAO.class);
    // property constants
    private static final String CORNAME = "corname";
    private static final String SEMESTER = "semester";

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
            return (Course) getHibernateTemplate().get(
                    "com.bean.course.Course", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
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

}