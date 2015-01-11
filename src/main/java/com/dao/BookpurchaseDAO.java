package com.dao;

import com.bean.bookpurchase.Bookpurchase;
import com.bean.bookpurchaseview.Bookpurchaseview;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("bookpurchaseDAO")
public class BookpurchaseDAO extends BaseDaoImp<Bookpurchase> {

    private static final Log log = LogFactory.getLog(BookpurchaseDAO.class);

    /*将筛选后的课程信息保存到临时表中 2014.3.18 zhangchi*/
    public void saveTemp(List<Bookpurchaseview> bpvlist) {
        log.debug("saveTemp Bookpurchase instances");
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
                save(bp);
            }
            // getHibernateTemplate().saveOrUpdateAll(list);
        } catch (RuntimeException re) {
            log.error("Find All BookPurchaseView Failed", re);
            throw re;
        }
    }

    /**
     * 清空bookpurchase表
     */
    public void deleteAll() {
        log.debug("deleting Bookpurchase instance");
        try {
            getCurrentSession().createQuery("delete Bookpurchase");
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
        List res = null;
        try {
            log.debug("getBookList");
            String queryString = " SELECT bk.col,bk.major,bk.grade,bk.clsno,bk.idbk,bk.bkname, ";
            queryString += " bk.author,bk.publisher,bk.edition,bk.isbn,bk.supplier,bk.campus,sum(bk.stunum) as bknum ";
            queryString += " FROM Bookpurchase AS bk ";
            queryString += " GROUP BY bk.campus,bk.idbk ";
            queryString += " ORDER BY bk.supplier asc";
            queryString += " ,bk.campus asc";
            queryString += " ,bk.publisher asc";
            queryString += " ,bk.edition asc,bk.bkname asc";
            queryString += " ,bk.author asc,bk.grade";
             res = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("merge failed", re);
        }
        return res;
    }
}