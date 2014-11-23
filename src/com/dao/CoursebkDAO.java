package com.dao;

import com.bean.coursebk.Coursebk;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.coursebk.Coursebk
 */

public class CoursebkDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CoursebkDAO.class);

    protected void initDao() {
    }

    /**
     * 保存新的课程
     *
     * @param transientInstance 课程实例
     */
    public boolean save(Coursebk transientInstance) {
        log.debug("saving Coursebk instance");
        boolean tag = true;
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            tag = false;
        }
        return tag;
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