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
public class BookServiceImp extends BaseServiceTemplate<Book> implements BookService {
    @Autowired
    private BookDAO bookDAO;

    public boolean deleteBook(String idbk) {
        Book temp = bookDAO.findById(idbk);
        boolean tag;
        tag = bookDAO.delete(temp);
        return tag;
    }

    public List searchByISBN(String isbn) {
        return bookDAO.findByPropertyA("isbn", isbn);
    }

    public List searchByBkname(String bkname) {
        return bookDAO.findByPropertyF("bkname",bkname);
    }

}
