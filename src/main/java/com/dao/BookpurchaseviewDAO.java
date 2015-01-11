package com.dao;

import com.bean.bookpurchaseview.Bookpurchaseview;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookpurchaseviewDAO")
public class BookpurchaseviewDAO extends BaseDaoImp<Bookpurchaseview>{
    private static final Log log = LogFactory.getLog(BookpurchaseviewDAO.class);

    /**
     * 用年份和学期查找 Bookpurchaseview（购书视图）
     * 即某学期学期要购买的书的视图
     * @param year 年份
     * @param sem 学期（1或者2）
     * @return list
     */
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

    /**
     * 用年份和学期，班级编号查找 Bookpurchaseview（购书视图）
     * 即某学期某班要购买的书的视图
     * @param year 年份
     * @param sem 学期
     * @param idcls 班级编号
     * @return list
     */
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

    /**
     * 用年份和学期，年级查找 Bookpurchaseview（购书视图）
     *  即某学期某年级要购买的书的视图
     * @param year 年份
     * @param sem 学期
     * @param grade 某一年级（ex：2012级，2013级）
     * @return list
     */
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