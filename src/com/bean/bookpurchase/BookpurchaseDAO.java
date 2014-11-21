package com.bean.bookpurchase;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.bookpurchaseview.Bookpurchaseview;

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
    private static final Log log = LogFactory.getLog(BookpurchaseDAO.class);
    // property constants
    public static final String IDCM = "idcm";
    public static final String COL = "col";
    public static final String MAJOR = "major";
    public static final String SEMNUM = "semnum";
    public static final String CAMPUS = "campus";
    public static final String IDCLS = "idcls";
    public static final String GRADE = "grade";
    public static final String CLSNO = "clsno";
    public static final String STUNUM = "stunum";
    public static final String IDCOR = "idcor";
    public static final String CORNAME = "corname";
    public static final String IDCORSEM = "idcorsem";
    public static final String SEMESTER = "semester";
    public static final String IDBK = "idbk";
    public static final String BKNAME = "bkname";
    public static final String AUTHOR = "author";
    public static final String EDITION = "edition";
    public static final String ISBN = "isbn";
    public static final String IDSP = "idsp";
    public static final String PUBLISHER = "publisher";
    public static final String SUPPLIER = "supplier";

    protected void initDao() {
        // do nothing
    }

    /*将筛选后的课程信息保存到临时表中 2014.3.18 zhangchi*/
    public void batchSave(List<Bookpurchaseview> bpvlist) {
        log.debug("batchSave Bookpurchase instances");
        try {
            Iterator<Bookpurchaseview> it = bpvlist.iterator();
            // List list = new ArrayList();
            while (it.hasNext()) {
                Bookpurchaseview bpv = (Bookpurchaseview) it.next();
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
     *
     * @date 2014.3.18 zhangchi
     * *
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
     *
     * @date 2014.3.18 zhangchi
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

    public void save(Bookpurchase transientInstance) {
        log.debug("saving Bookpurchase instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Bookpurchase persistentInstance) {
        log.debug("deleting Bookpurchase instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Bookpurchase findById(java.lang.Integer id) {
        log.debug("getting Bookpurchase instance with id: " + id);
        try {
            Bookpurchase instance = (Bookpurchase) getHibernateTemplate().get(
                    "com.bean.bookpurchase.Bookpurchase", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Bookpurchase instance) {
        log.debug("finding Bookpurchase instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Bookpurchase instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Bookpurchase as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByIdcm(Object idcm) {
        return findByProperty(IDCM, idcm);
    }

    public List findByCol(Object col) {
        return findByProperty(COL, col);
    }

    public List findByMajor(Object major) {
        return findByProperty(MAJOR, major);
    }

    public List findBySemnum(Object semnum) {
        return findByProperty(SEMNUM, semnum);
    }

    public List findByCampus(Object campus) {
        return findByProperty(CAMPUS, campus);
    }

    public List findByIdcls(Object idcls) {
        return findByProperty(IDCLS, idcls);
    }

    public List findByGrade(Object grade) {
        return findByProperty(GRADE, grade);
    }

    public List findByClsno(Object clsno) {
        return findByProperty(CLSNO, clsno);
    }

    public List findByStunum(Object stunum) {
        return findByProperty(STUNUM, stunum);
    }

    public List findByIdcor(Object idcor) {
        return findByProperty(IDCOR, idcor);
    }

    public List findByCorname(Object corname) {
        return findByProperty(CORNAME, corname);
    }

    public List findByIdcorsem(Object idcorsem) {
        return findByProperty(IDCORSEM, idcorsem);
    }

    public List findBySemester(Object semester) {
        return findByProperty(SEMESTER, semester);
    }

    public List findByIdbk(Object idbk) {
        return findByProperty(IDBK, idbk);
    }

    public List findByBkname(Object bkname) {
        return findByProperty(BKNAME, bkname);
    }

    public List findByAuthor(Object author) {
        return findByProperty(AUTHOR, author);
    }

    public List findByEdition(Object edition) {
        return findByProperty(EDITION, edition);
    }

    public List findByIdsp(Object idsp) {
        return findByProperty(IDSP, idsp);
    }

    public List findByPublisher(Object publisher) {
        return findByProperty(PUBLISHER, publisher);
    }

    public List findBySupplier(Object supplier) {
        return findByProperty(SUPPLIER, supplier);
    }

    public List findAll() {
        log.debug("finding all Bookpurchase instances");
        try {
            String queryString = "from Bookpurchase";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Bookpurchase merge(Bookpurchase detachedInstance) {
        log.debug("merging Bookpurchase instance");
        try {
            Bookpurchase result = (Bookpurchase) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Bookpurchase instance) {
        log.debug("attaching dirty Bookpurchase instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Bookpurchase instance) {
        log.debug("attaching clean Bookpurchase instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static BookpurchaseDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (BookpurchaseDAO) ctx.getBean("BookpurchaseDAO");
    }
}