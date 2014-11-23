package com.service;

import com.bean.book.Book;
import com.dao.BookDAO;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;

import java.util.List;

public class BookServiceImp implements BookService {
    private BookDAO bookDAO;

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public boolean addBook(Book book) {
        return bookDAO.save(book);
    }

    public boolean deleteBook(String idbk) {
        Book temp = bookDAO.findById(idbk);
        boolean tag;
        tag = bookDAO.delete(temp);
        return tag;
    }

    public List<Object> initBook() {
        List list = bookDAO.findAll();
        return ConvertUtils.ToBookList(list);
    }

    public List<Object> initBook(Pagination pagination) {
        List list = this.initBook();
        return GetPaginationInfo.getSubList(list, pagination);

    }

    public List<Book> searchByBookPub(String bookname, String pub) {
        List list = bookDAO.findByBookPubFuzzy(bookname, pub);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public List<Book> searchByBookPub(String bookname, String pub, Pagination pagination) {
        List list = this.searchByBookPub(bookname, pub);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public List<Book> searchByISBN(String isbn) {
        List list = bookDAO.findByIdbkAccurate(isbn);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public List<Book> searchByISBN(String isbn, Pagination pagination) {
        List list = this.searchByISBN(isbn);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public List<Book> searchByBkname(String bkname) {
        List<Book> list = bookDAO.findByBknameFuzzy(bkname);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public boolean updateBook(Book book) {
        bookDAO.update(book);
        return true;
    }

}
