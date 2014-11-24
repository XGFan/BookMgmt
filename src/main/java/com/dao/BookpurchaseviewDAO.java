package com.dao;

import com.bean.bookpurchaseview.Bookpurchaseview;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bookpurchaseview entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.bookpurchaseview.Bookpurchaseview
 */

public class BookpurchaseviewDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(BookpurchaseviewDAO.class);

    protected void initDao() {
    }

    //todo
    public List findByYearAndSem(int year, int sem) {
        log.debug("finding Bookpurchaseview instances ByYeayAndSem");
        try {
            String queryString = "from Bookpurchaseview as b where (((" + year + "- b.id.grade)*2 + " + sem + ") = b.id.semester)";
            queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    //todo
    public List findByYearAndSemAndCol(int year, int sem,
                                                         String idcls) {
        log.debug("finding Bookpurchaseview instances ByYeayAndSem");
        try {
            String queryString = "from Bookpurchaseview as b where (((" + year
                    + "- b.id.grade)*2 + " + sem
                    + ") = b.id.semester and b.id.idcls= '" + idcls + "')";
            //queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
            queryString += " order by convert_gbk(b.id.bkname) asc";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    //todo
    public List findByYearAndSemAndGrade(int year, int sem,
                                                           int grade) {
        log.debug("finding Bookpurchaseview instances ByYeayAndSem");
        try {
            String queryString = "from Bookpurchaseview as b where (((" + year
                    + "- b.id.grade)*2 + " + sem
                    + ") = b.id.semester and b.id.grade= '" + grade + "')";
            queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}