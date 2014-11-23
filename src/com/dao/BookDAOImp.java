package com.dao;

import com.bean.book.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BookDAOImp extends HibernateDaoSupport implements BookDAO {
    private static final Log log = LogFactory.getLog(BookDAOImp.class);


    protected void initDao() {
    }

    public boolean save(Book transientInstance) {
        boolean tag = true;
        log.debug("saving Book instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            tag = false;
        }
        return tag;
    }

    public boolean delete(Book persistentInstance) {
        boolean result = true;
        log.debug("deleting Book instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            result = false;
        }
        return result;
    }

    public Book findById(java.lang.String id) {
        log.debug("getting Book instance with id: " + id);
        Book book = null;
        try {
            book = (Book) getHibernateTemplate().get("com.bean.book.Book", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
        }
        return book;
    }

    public List findByPropertyAccurate(String propertyName, String value) {
        log.debug("accurate finding Book instance with property: " + propertyName
                + ", value: " + value);
        List list = null;
        try {
            String hql = "from Book book where book." + propertyName + " = ?";
            list = getHibernateTemplate().find(hql, value);
        } catch (RuntimeException re) {
            log.error("accurate find by " + propertyName + " failed", re);
        }
        return list;
    }

    @Override
    public List findByPropertyFuzzy(String propertyName, String value) {
        log.debug("fuzzy finding Book instance with property: " + propertyName
                + ", value: " + value);
        List list = null;
        try {
            String hql = "from Book b where b." + propertyName + " like '%" + value + "%'";
            list = getHibernateTemplate().find(hql);
        } catch (RuntimeException re) {
            log.error("fuzzy find by " + propertyName + " failed", re);
        }
        return list;
    }

    @Override
    public List findByIdbkAccurate(String idbk) {
        return findByPropertyAccurate("idbk", idbk);
    }

    public boolean update(Book book) {
        getHibernateTemplate().saveOrUpdate(book);
        return true;
    }

    public List findByBknameFuzzy(String bkname) {
        return findByPropertyFuzzy("bkname", bkname);
    }

    public List findAll() {
        log.debug("finding all Book instances");
        try {
            String queryString = "from Book b join b.supplier ss";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findByBookPubFuzzy(String bookname, String pub) {
        List list = null;
        String queryString;
        try {
            if (!bookname.trim().equals("")) {
                if (!pub.trim().equals("")) {
                    queryString = "select b from Book b join b.supplier p where p.publisher like '%"
                            + pub + "%' and b.bkname like '%" + bookname + "%'";
                } else {
                    queryString = "from Book b where b.bkname like '%" + bookname + "%'";
                }
            } else {
                if (!pub.trim().equals("")) {
                    queryString = "select b from Book b join b.supplier p where p.publisher like '%" + pub + "%'";
                } else {
                    queryString = "select b from Book b join b.supplier";
                }
            }
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by BookPub name failed", re);
        }
        return list;
    }
}