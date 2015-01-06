package com.newService;

import com.bean.book.Book;
import com.service.BaseService;

import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:19:18
 * Created by guofan on 2015/1/6
 */
public interface BookService extends BaseService<Book> {
    public List searchByISBN(String isbn);

}
