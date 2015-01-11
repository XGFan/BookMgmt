package com.dao;

import com.bean.coursebk.Coursebk;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.coursebk.Coursebk
 */
@Repository("coursebkDAO")
public class CoursebkDAO extends BaseDaoImp<Coursebk> {
    private static final Log log = LogFactory.getLog(CoursebkDAO.class);

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
        List res = null;
        try {
            String queryString = "from Coursebk as cb where cb.course.idcor ='"+idcor+"' and cb.book.idbk='"+idbk+"'";
            res = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("get Coursebk instance with idcor failed", re);
        }
        return res;
    }

}