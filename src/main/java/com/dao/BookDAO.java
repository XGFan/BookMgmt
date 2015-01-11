package com.dao;

import com.bean.book.Book;

import java.util.List;

/**
 * 教材DAO
 */
public interface BookDAO extends BaseDao<Book> {
    /**
     * 根据教材名和出版社名查找(join 供应商)
     * edition,author,price,bkname,idbk,isbn,publisher
     *
     * @param bookname 教材名
     * @param pub      出版社名
     * @return obj LIST
     */
    List findByBookPubFuzzy(String bookname, String pub);


}
