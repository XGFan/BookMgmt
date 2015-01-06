package com.newService;

import com.bean.book.Book;
import com.newDao.BookDao;
import com.service.BaseServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:19:30
 * Created by guofan on 2015/1/6
 */
public class BookServiceImp extends BaseServiceTemplate<Book> implements BookService {
    @Autowired
    BookDao bookDao;

    public BookServiceImp() {
    }

    @Override
    public List searchByISBN(String isbn) {
        return null;
    }
}
