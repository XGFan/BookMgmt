package com.service;

import com.bean.book.Book;
import com.util.Pagination;

import java.util.List;

public interface BookService {
    /**
     * 返回所有的教材
     *
     * @return book map list
     */
    public List initBook();

    /**
     * 返回所有教材，并通过分页信息返回map list
     *
     * @param pagination 分页信息
     * @return 一页教材 map list
     */
    public List initBook(Pagination pagination);

    /**
     * 根据ISBN号查精确查找，返回map list？
     * todo isbn理论应该有很少有重复的
     *
     * @param isbn ISBN号
     * @return map list
     */
    public List searchByISBN(String isbn);

    /**
     * 根据ISBN号查精确查找，并通过分页信息返回map list？
     * todo isbn理论应该有很少有重复的
     *
     * @param isbn ISBN号
     * @return map list
     */
    public List searchByISBN(String isbn, Pagination pagination);

    /**
     * 根据教材名和出版社名查找(join 供应商)
     * edition,author,price,bkname,idbk,isbn,publisher
     *
     * @param bookname 教材名
     * @param pub      出版社名
     * @return map list
     */
    public List searchByBookPub(String bookname, String pub);

    /**
     * 根据教材名，进行模糊查找
     *
     * @param bkname 教材名
     * @return map list
     */
    public List searchByBkname(String bkname);

    /**
     * 根据教材名和出版社名查找(join 供应商) edition,author,price,bkname,idbk,isbn,publisher
     * 并且分页
     *
     * @param bookname   教材名
     * @param pub        出版社名
     * @param pagination 分页信息
     * @return 一页 map list
     */
    public List searchByBookPub(String bookname, String pub, Pagination pagination);
    
//    /**
//     * 根据 bkname,author,idsp,isbn,memo查找
//     * 并且分页
//     *
//     * @param bookname   教材名
//     * @param author     作者
//     * @param idsp		   供应商ID
//     * @param isbn		 ISBN号码
//     * @param memo		    备注
//     * @return 一页 map list
//     */
//    public List searchByBookPub(String bookname, String author, String idsp, String isbn, String memo);

    /**
     * 根据idbk进行删除
     *
     * @param idbk 教材id
     * @return boolean
     */
    public boolean deleteBook(String idbk);

    /**
     * 添加实例
     *
     * @param book book object
     * @return boolean
     */
    public boolean addBook(Book book);

    /**
     * 更新实例
     *
     * @param book book obj
     * @return boolean
     */
    public boolean updateBook(Book book);
}
