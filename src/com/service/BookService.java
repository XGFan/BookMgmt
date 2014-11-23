package com.service;

import com.bean.book.Book;
import com.util.Pagination;

import java.util.List;

public interface BookService {
    public List<Object> initBook();

    public List<Object> initBook(Pagination pagination);

    public List<Book> searchByISBN(String isbn);

    /*通过页码和ISBN进行查询教材信息*/
    public List<Book> searchByISBN(String isbn, Pagination pagination);

    public List<Book> searchByBookPub(String bookname, String pub);

    public List<Book> searchByBkname(String bkname);

    /*通过页码和教材名称和出版社进行查询教材信息*/
    public List<Book> searchByBookPub(String bookname, String pub, Pagination pagination);

    public boolean deleteBook(String idbk);

    public boolean addBook(Book book);

    public boolean updateBook(Book book);
}
