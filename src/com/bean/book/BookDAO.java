package com.bean.book;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.college.College;
import com.bean.corbook.CorbookviewId;
import com.printInfo.book.Service.CourseBookViewService;

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
    private static final Log log = LogFactory.getLog(BookDAO.class);
    // property constants
    public static final String BKNAME = "bkname";
    public static final String AUTHOR = "author";
    public static final String EDITION = "edition";
    public static final String ISBN = "isbn";
    public static final String PRICE = "price";
    public static final String MEMO = "memo";

    protected void initDao() {
        // do nothing
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

    public void saveOrUpdate(Book transientInstance) {
        log.debug("saving Book instance");
        try {
            getHibernateTemplate().saveOrUpdate(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public boolean delete(Book persistentInstance) {
        boolean result = false;
        log.debug("deleting Book instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
            result = true;
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            result = false;
            throw re;
        }
        return result;
    }

    public Book findById(java.lang.String id) {
        log.debug("getting Book instance with id: " + id);
        try {
            Book instance = (Book) getHibernateTemplate().get(
                    "com.bean.book.Book", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Book instance) {
        log.debug("finding Book instance by example");
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
        log.debug("finding Book instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Book as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public void updateBook(Book book) {
        getHibernateTemplate().saveOrUpdate(book);
    }

    public List FuzzyFindByBkname(String bkname) {
        String queryString = null;
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

    public Book merge(Book detachedInstance) {
        log.debug("merging Book instance");
        try {
            Book result = (Book) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Book instance) {
        log.debug("attaching dirty Book instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Book instance) {
        log.debug("attaching clean Book instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
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

    public static BookDAO getFromApplicationContext(ApplicationContext ctx) {
        return (BookDAO) ctx.getBean("BookDAO");
    }

    // 通过教材查询课程
    public List<CorbookviewId> searchByBook(String idbk) {
        log.debug("finding all Course instances");
        try {
            String queryString = "from corbookview ";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }


}