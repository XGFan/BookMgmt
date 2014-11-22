package com.bean.cls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Class
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.cls.Class
 */

public class ClassDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(ClassDAO.class);
    // property constants
    private static final String CAMPUS = "campus";
    private static final String GRADE = "grade";
    private static final String SEMESTER = "semester";
    private static final String CLSNO = "clsno";
    private static final String STUNUM = "stunum";

    protected void initDao() {
        // do nothing
    }

    /**
     * 保存班级实例
     *
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
     *
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
     *
     * @param id 班级id
     * @return 班级实例
     */
    public Class findById(java.lang.String id) {
        log.debug("getting Class instance with id: " + id);
        try {
            return (Class) getHibernateTemplate().get(
                    "com.bean.cls.Class", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 返回所有的班级信息
     *
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

    /**
     * 模糊查找
     *
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
        return getHibernateTemplate().find(queryString);
    }

    /**
     * 根据若干信息精确查找班级
     *
     * @param col    学院
     * @param major  专业
     * @param grade  年级
     * @param campus 校区
     * @return 班级LIST
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
        return getHibernateTemplate().find(queryString);
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