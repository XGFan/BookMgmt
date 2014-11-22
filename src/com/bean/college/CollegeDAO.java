package com.bean.college;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 学院专业DAO
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.college.College
 */

public class CollegeDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CollegeDAO.class);
    private static final String COL = "col";
    private static final String MAJOR = "major";
    private static final String SEMNUM = "semnum";

    protected void initDao() {
    }

    /**
     * 保存学院专业实例
     *
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
     *
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
     * 根据学院专业ID来删除学院专业信息
     *
     * @param idcm 学院专业ID
     * @return 操作是否成功
     */
    public boolean deleteById(String idcm) {
        log.debug("deleting College instance");
        boolean result;
        try {
            College persistentInstance = findById(idcm);
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
            result = true;
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
        return result;
    }

    /**
     * 根据学院专业id来查找学院专业信息
     *
     * @param id 学院专业id
     * @return 找到的该专业的信息
     */
    College findById(java.lang.String id) {
        log.debug("getting College instance with id: " + id);
        try {
            return (College) getHibernateTemplate().get(
                    "com.bean.college.College", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 根据属性名和属性名来查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return 查找的结果LIST
     */
    List findByProperty(String propertyName, Object value) {
        log.debug("finding College instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from College as model where model."
                    + propertyName + " like '%" + value + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /**
     * 根据学院查找
     *
     * @param col 学院
     * @return 学院专业班级信息
     */
    public List findByCol(Object col) {
        return findByProperty(COL, col);
    }

    /**
     * 返回所有的学院专业信息
     *
     * @return college obj LIST
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
     *
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
     *
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

    /**
     * 获取所有的distinct学院名称
     */
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
     * 通过学院名称获取所有的distinct专业名称
     */
    public List getMajorByCol(String col) {
        log.debug("finding all Majors by colname");
        try {
            String queryString = "select distinct major from College as c where c.col='"
                    + col + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据学院名称和专业名称查找学院
     *
     * @param col   学院名称
     * @param major 专业名称
     * @return 学院专业 obj list
     */
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
     */
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

}