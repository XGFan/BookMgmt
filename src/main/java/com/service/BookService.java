package com.service;

import com.bean.book.Book;
import com.util.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService extends BaseService<Book>{


    /**
     * 根据ISBN号查精确查找，返回map list？
     * todo isbn理论应该有很少有重复的
     *
     * @param isbn ISBN号
     * @return obj list
     */
    public List<Book> searchByISBN(String isbn);


    /**
     * 根据教材名，进行模糊查找
     *
     * @param bkname 教材名
     * @return obj list
     */
    public List searchByBkname(String bkname);


    /**
     * 根据idbk进行删除
     *
     * @param idbk 教材id
     * @return boolean
     */
    public boolean deleteBook(String idbk);


}
