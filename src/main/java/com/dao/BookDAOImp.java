package com.dao;

import com.bean.book.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookDAO")
public class BookDAOImp extends BaseDaoImp<Book> implements BookDAO {
    private static final Log log = LogFactory.getLog(BookDAOImp.class);


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
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("Find By BookPub name failed", re);
        }
        return list;
    }
}