package com.dao;

import com.bean.bookpurchaseview.Bookpurchaseview;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookpurchaseviewDAO")
public class BookpurchaseviewDAO extends BaseDaoImp<Bookpurchaseview>{
    private static final Log log = LogFactory.getLog(BookpurchaseviewDAO.class);

    //todo
    public List findByYearAndSem(int year, int sem) {
        log.debug("finding Bookpurchaseview instances ByYeayAndSem");
        try {
            String queryString = "from Bookpurchaseview as b where (((" + year + "- b.id.grade)*2 + " + sem + ") = b.id.semester)";
            queryString += " order by b.id.col,b.id.major,b.id.grade desc,b.id.clsno";
            return getCurrentSession().createQuery(queryString).list();
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
            queryString += " order by convert_gbk(b.id.bkname) asc";
            return getCurrentSession().createQuery(queryString).list();
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
            return getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}