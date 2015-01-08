package com.service;

import com.bean.book.Book;
import com.dao.BookDAO;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImp implements BookService {
    @Autowired
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
        System.out.println(temp+"1");
        boolean tag;
        tag = bookDAO.delete(temp);
        return tag;
    }

    public List initBook() {
        List list = bookDAO.findAll();
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
        List list = bookDAO.findByIsbnAccurate(isbn);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public List searchByISBN(String isbn, Pagination pagination) {
        List list = this.searchByISBN(isbn);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public List searchByBkname(String bkname) {
        List list = bookDAO.findByBknameFuzzy(bkname);
        return ConvertUtils.ToBookListFromBook(list);
    }

    public boolean updateBook(Book book) {
        return bookDAO.update(book);
    }

//	@Override
//	public List searchByBookPub(String bookname, String author, String idsp,
//			String isbn, String memo) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
