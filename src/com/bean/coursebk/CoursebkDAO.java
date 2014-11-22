package com.bean.coursebk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Coursebk entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.coursebk.Coursebk
 */

public class CoursebkDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CoursebkDAO.class);

    protected void initDao() {
        // do nothing
    }

    /**
     * 保存新的课程
     *
     * @param transientInstance 课程实例
     */
    public void save(Coursebk transientInstance) {
        log.debug("saving Coursebk instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /**
     * 根据科目id和教科书id来查找课程
     *
     * @param idcor 科目
     * @param idbk  教科书
     * @return obj list
     * @see com.bean.coursebk.Coursebk
     */
    public List findByIdcorAndIdbk(String idcor, String idbk) {
        log.debug("getting Coursebk instance with idcor: " + idcor);
        try {
            String[] args = {idcor, idbk};
            String queryString = "from Coursebk as cb where cb.course.idcor =? and cb.book.idbk=?";
            return getHibernateTemplate().find(queryString, args);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

}