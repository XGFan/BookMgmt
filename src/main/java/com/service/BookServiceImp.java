package com.service;

import com.bean.book.Book;
import com.dao.BookDAO;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BookServiceImp extends BaseServiceTemplate<Book> implements BookService {
    @Autowired
    private BookDAO bookDAO;

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    public boolean deleteBook(String idbk) {
        Book temp = bookDAO.findById(idbk);
        boolean tag;
        tag = bookDAO.delete(temp);
        return tag;
    }

    public List initBook() {
        List list = bookDAO.getAll();
        return ConvertUtils.ToBookList(list);
    }

    public List initBook(Pagination pagination) {
        List list = this.initBook();
        return GetPaginationInfo.getSubList(list, pagination);

    }

    public List searchByBookPub(String bookname, String pub) {
        List list = bookDAO.findByBookPubFuzzy(bookname, pub);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public List searchByBookPub(String bookname, String pub, Pagination pagination) {
        List list = this.searchByBookPub(bookname, pub);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public List searchByISBN(String isbn) {
        List list = bookDAO.findByPropertyA("isbn", isbn);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public List searchByISBN(String isbn, Pagination pagination) {
        List list = this.searchByISBN(isbn);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public List searchByBkname(String bkname) {
        List list = bookDAO.findByPropertyF("bkname",bkname);
        return ConvertUtils.ToBookListFromBook(list);
    }
}
