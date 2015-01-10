package com.dao;

import com.bean.college.College;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学院专业DAO
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.college.College
 */
@Repository("collegeDAO")
public class CollegeDAO extends BaseDaoImp<College> {
    private static final Log log = LogFactory.getLog(CollegeDAO.class);
    private static final String COL = "col";
    private static final String MAJOR = "major";
    private static final String SEMNUM = "semnum";



    /**
     * 根据学院专业ID来删除学院专业信息
     *
     * @param idcm 学院专业ID
     * @return 操作是否成功
     */
    public boolean deleteById(String idcm) {
        log.debug("deleting College instance");
        boolean result = true;
        try {
            College persistentInstance = findById(idcm);
            delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            result = false;
        }
        return result;
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
            return getCurrentSession().createQuery(queryString).list();
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
            String queryString = "from College as c where c.col= '"+ col +"' order by c.idcm asc";
            return getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 获取所有的distinct学院名称
     *
     * @return string list
     */
    public List getAllCol() {
        log.debug("finding all Cols");
        List list = null;
        try {
            String queryString = "select distinct col from College";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 通过学院名称获取所有的distinct专业名称
     *
     * @param col 学院名
     * @return distinct专业 string list
     */
    public List getMajorByCol(String col) {
        log.debug("finding all Majors by colname");
        List list = null;
        try {
            String queryString = "select distinct major from College as c where c.col='"
                    + col + "'";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 根据学院名称和专业名称查找学院
     *
     * @param col   学院名称
     * @param major 专业名称
     * @return 学院专业 obj list
     */
    public List getCol(String col, String major) {
        log.debug("finding Col by colname and major");
        List list = null;
        try {
            String queryString = "from College as c where c.col='" + col
                    + "' and c.major='" + major + "'";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 模糊查询，通过学院或者专业
     *
     * @param condition 关键字
     * @return College obj list
     */
    public List fuzzyQuery(String condition) {
        log.debug("finding Col by colname or major");
        List list = null;
        try {
            String queryString = "from College as c where c.col like '%"
                    + condition + "%' or c.major like '%" + condition + "%'";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

}