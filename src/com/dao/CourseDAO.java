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
        boolean tag = true;
        try {
            getHibernateTemplate().saveOrUpdate(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            tag = false;
        }
        return tag;
    }

    /**
     * 删除科目
     *
     * @param persistentInstance 科目实例
     */
    public boolean delete(Course persistentInstance) {
        log.debug("deleting Course instance");
        boolean tag = true;
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            tag = false;
        }
        return tag;
    }

    /**
     * 根据科目id查找科目
     *
     * @param id 科目id
     * @return 科目 obj list
     */
    public Course findById(java.lang.String id) {
        log.debug("getting Course instance with id: " + id);
        Course course = null;
        try {
            course = (Course) getHibernateTemplate().get(
                    "com.bean.course.Course", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
        }
        return course;
    }

    /**
     * 找到所有的科目信息(join)
     *
     * @return 科目 list
     */
    public List findAll() {
        log.debug("finding all Course instances");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 根据学院名称查找科目(join)
     *
     * @param col 学院名称
     * @return 科目信息  list
     */
    public List getCourseByCol(String col) {
        log.debug("finding all Courses by colname");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where cc.col like '%"
                    + col + "%'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 精确查找（学院专业id，学院名，专业名，科目名）(join)
     *
     * @param idcm    学院专业id
     * @param col     学院名
     * @param major   专业名
     * @param corname 科目名
     * @return 科目 list
     */
    public List getCourseByIdcmColMajorCorname(String idcm, String col, String major, String corname) {
        log.debug("finding all Courses by idcm col major corname");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where cc.idcm = '"
                    + idcm + "' and c.corname = '" + corname + "' and cc.col = '" + col + "' and cc.major = '" + major + "'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 精确查找（学院专业id，科目名称）(join)
     *
     * @param idcm     学院专业id
     * @param corname  科目名称
     * @param semester 所在学期
     * @return 科目 list
     */
    public List getCourseByIdcmCornameSem(String idcm, String corname, String semester) {
        log.debug("finding all Courses by idcmcornamesem");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where cc.idcm = '"
                    + idcm + "' and c.corname = '" + corname + "' and c.semester = '" + semester + "'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 通过学院和专业查找科目，专业是模糊查找(join)
     *
     * @param col   学院
     * @param major 专业
     * @return 科目  list
     */
    public List getCourseByColMajor(String col, String major) {
        log.debug("finding all Courses by colname nad major");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where cc.col like '%"
                    + col + "%' and cc.major ='" + major + "'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 根据学院，专业，科目进行查找，其中科目名称是模糊查找(join)
     *
     * @param col     学院名称
     * @param major   专业
     * @param corname 科目名称
     * @return 科目信息  list
     */
    public List getCourseFuzzyByCorname(String col, String major, String corname) {
        log.debug("finding all Courses by corname");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where cc.col ='"
                    + col
                    + "' and cc.major ='"
                    + major
                    + "' and c.corname like '%" + corname + "%'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 根据学院，专业，学期查找(join)
     *
     * @param col   学院名称
     * @param major 专业名称
     * @param sem   课程安排在哪个学期
     * @return 学院专业在某个学期的课程  list
     */
    public List getCourseByColMajorSem(String col, String major, String sem) {
        log.debug("finding all Courses by col,major,sem");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where ";
            queryString += "cc.col like '%" + col + "%' and cc.major='" + major
                    + "' and c.semester='" + sem + "'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 模糊查找，从学院，专业，科目名查询(join)
     *
     * @param condition 关键字
     * @return list
     */
    public List fuzzyQuery(String condition) {
        log.debug("finding all Courses by col or major or corname");
        List list = null;
        try {
            String queryString = "from Course c join c.college cc where ";
            queryString += "cc.col like '%" + condition
                    + "%' or cc.major like '%" + condition
                    + "%' or c.corname like '%" + condition + "%'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

}