package com.bean.book;

import com.dao.BookDAOInf;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created by guofan on 2014/11/22.
 * Time:2014/11/22
 */
public class BookDaoNew implements BookDAOInf {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(Book transientInstance) {
        hibernateTemplate.save(transientInstance);
    }

    @Override
    public boolean delete(Book persistentInstance) {
        hibernateTemplate.delete(persistentInstance);
        return true;
    }

    @Override
    public Book findById(String id) {
        return (Book) hibernateTemplate.get("com.bean.book.Book", id);
    }

    @Override
    public List findByProperty(String propertyName, Object value) {
        String hql = "from Book book where book." + propertyName + " = ?";
        return hibernateTemplate.find(hql, value);
    }

    @Override
    public List FuzzyFindByBkname(String bkname) {
        String queryString;
        queryString = "from Book b where b.bkname like '%" + bkname + "%'";
        return hibernateTemplate.find(queryString);
    }

    @Override
    public List findAll() {
        String queryString = "from Book b join b.supplier ss";
        return hibernateTemplate.find(queryString);
    }

    @Override
    public List<Book> getByBookPub(String bookname, String pub) {
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
        return hibernateTemplate.find(queryString);
    }

    @Override
    public void updateBook(Book book) {
        hibernateTemplate.saveOrUpdate(book);
    }
}
