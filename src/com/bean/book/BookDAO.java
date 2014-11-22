package com.bean.book;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;


/**
 * A data access object (DAO) providing persistence and search support for Book
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.book.Book
 */

public class BookDAO extends HibernateDaoSupport implements BookDAOInf {
    // property constants
    public static final String BKNAME = "bkname";
    public static final String AUTHOR = "author";
    public static final String EDITION = "edition";
    public static final String ISBN = "isbn";
    public static final String PRICE = "price";
    public static final String MEMO = "memo";
    private static final Log log = LogFactory.getLog(BookDAO.class);

    protected void initDao() {
    }

    public void save(Book transientInstance) {
        log.debug("saving Book instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public boolean delete(Book persistentInstance) {
        boolean result;
        log.debug("deleting Book instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
            result = true;
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
        return result;
    }

    public Book findById(java.lang.String id) {
        log.debug("getting Book instance with id: " + id);
        try {
            return (Book) getHibernateTemplate().get(
                    "com.bean.book.Book", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Book instance with property: " + propertyName
                + ", value: " + value);
        try {
            String hql = "from Book book where book." + propertyName + " = ?";
            return getHibernateTemplate().find(hql, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public void updateBook(Book book) {
        getHibernateTemplate().saveOrUpdate(book);
    }

    public List FuzzyFindByBkname(String bkname) {
        String queryString;
        try {
            queryString = "from Book b where b.bkname like '%" + bkname + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("FuzzyFindByBkname failed", re);
            throw re;
        }
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

    public List<Book> getByBookPub(String bookname, String pub) {
        try {
            String queryString = null;
            if (!bookname.trim().equals("") && !pub.trim().equals("")) {
                queryString = "select b from Book b join b.supplier p where p.publisher like '%"
                        + pub + "%' and b.bkname like '%" + bookname + "%'";
            }
            if (!bookname.trim().equals("") && pub.trim().equals("")) {
                queryString = "from Book b where b.bkname like '%" + bookname
                        + "%'";
            }
            if (bookname.trim().equals("") && !pub.trim().equals("")) {
                queryString = "select b from Book b join b.supplier p where p.publisher like '%"
                        + pub + "%'";
            }
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by BookPub name failed", re);
            throw re;
        }
    }


}