package com.printInfo.book.Service;

import java.util.List;

import com.bean.book.Book;
import com.util.Pagination;

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

    /*
     * delete the information of the publisher<注释说明>
     */
    public boolean deleteBook(Book book);

    public boolean deleteBook(String idbk);

    public String addBook(Book book);

    public boolean updateBook(Book book);
}
