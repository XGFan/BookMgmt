package com.dao;

import com.bean.bookpurchase.Bookpurchase;
import com.bean.bookpurchaseview.Bookpurchaseview;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Bookpurchase entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.bookpurchase.Bookpurchase
 */

public class BookpurchaseDAO extends HibernateDaoSupport {
    public static final String ISBN = "isbn";
    private static final Log log = LogFactory.getLog(BookpurchaseDAO.class);
    // property constants
    private static final String IDCM = "idcm";
    private static final String COL = "col";
    private static final String MAJOR = "major";
    private static final String SEMNUM = "semnum";
    private static final String CAMPUS = "campus";
    private static final String IDCLS = "idcls";
    private static final String GRADE = "grade";
    private static final String CLSNO = "clsno";
    private static final String STUNUM = "stunum";
    private static final String IDCOR = "idcor";
    private static final String CORNAME = "corname";
    private static final String IDCORSEM = "idcorsem";
    private static final String SEMESTER = "semester";
    private static final String IDBK = "idbk";
    private static final String BKNAME = "bkname";
    private static final String AUTHOR = "author";
    private static final String EDITION = "edition";
    private static final String IDSP = "idsp";
    private static final String PUBLISHER = "publisher";
    private static final String SUPPLIER = "supplier";

    protected void initDao() {
        // do nothing
    }

    /*将筛选后的课程信息保存到临时表中 2014.3.18 zhangchi*/
    public void batchSave(List<Bookpurchaseview> bpvlist) {
        log.debug("batchSave Bookpurchase instances");
        try {
            // List list = new ArrayList();
            for (Bookpurchaseview bpv : bpvlist) {
                // 将Bookpurchaseview对象转换为Bookpurchase对象
                Bookpurchase bp = new Bookpurchase();
                bp.setAuthor(bpv.getId().getAuthor());
                bp.setBkname(bpv.getId().getBkname());
                bp.setCampus(bpv.getId().getCampus());
                bp.setClsno(bpv.getId().getClsno().toString());
                bp.setCol(bpv.getId().getCol());
                bp.setCorname(bpv.getId().getCorname());
                bp.setGrade(bpv.getId().getGrade());
                bp.setIdbk(bpv.getId().getIdbk());
                bp.setIdcls(bpv.getId().getIdcls());
                bp.setIdcm(bpv.getId().getIdcm());
                bp.setIdcor(bpv.getId().getIdcor());
                bp.setIdcorsem(bpv.getId().getIdcorsem());
                bp.setIdsp(bpv.getId().getIdsp());
                bp.setIsbn(bpv.getId().getIsbn());
                bp.setMajor(bpv.getId().getMajor());
                bp.setPublisher(bpv.getId().getPublisher());
                bp.setSemester(bpv.getId().getSemester());
                bp.setSemnum(bpv.getId().getSemnum().toString());
                bp.setStunum(bpv.getId().getStunum().toString());
                bp.setSupplier(bpv.getId().getSupplier());
                bp.setEdition(bpv.getId().getEdition().toString());

                // list.add(bp);
                getHibernateTemplate().save(bp);
            }

            // getHibernateTemplate().saveOrUpdateAll(list);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 清空bookpurchase表
     */
    public void bathDelete() {
        log.debug("deleting Bookpurchase instance");
        try {
            getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    String queryString = "delete Bookpurchase";
                    Transaction trans = session.beginTransaction();
                    Query query = session.createQuery(queryString);
                    query.executeUpdate();
                    trans.commit();
                    session.close();
                    return null;
                }
            });
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
     * 获取统计后的书本列表
     */
    public List<Object[]> getBookList() {
        log.debug("merging Bookpurchase instance");
        try {
            log.debug("getBookList");
            String queryString = " SELECT bk.col,bk.major,bk.grade,bk.clsno,bk.idbk,bk.bkname, ";
            queryString += " bk.author,bk.publisher,bk.edition,bk.isbn,bk.supplier,bk.campus,sum(bk.stunum) as bknum ";
            queryString += " FROM Bookpurchase AS bk ";
            queryString += " GROUP BY bk.campus,bk.idbk ";
            queryString += " ORDER BY convert_gbk(bk.supplier) asc";
            queryString += " ,convert_gbk(bk.campus) asc";
            queryString += " ,convert_gbk(bk.publisher) asc";
            queryString += " ,bk.edition asc,convert_gbk(bk.bkname) asc";
            queryString += " ,convert_gbk(bk.author) asc,bk.grade";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

}