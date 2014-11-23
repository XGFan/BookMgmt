package com.dao;

import com.bean.book.Book;

import java.util.List;

/**
 * 教材DAO
 */
public interface BookDAO {
    /**
     * 保存实例
     *
     * @param transientInstance 教材实例
     * @return boolean
     */
    boolean save(Book transientInstance);

    /**
     * 删除教材
     *
     * @param persistentInstance 实例
     * @return boolean
     */
    boolean delete(Book persistentInstance);

    /**
     * 传入id，返回相应对象
     *
     * @param id 唯一id
     * @return 教材 book
     */
    Book findById(java.lang.String id);

    /**
     * 传入属性名和属性值精确查找，返回查找结果LIST
     *
     * @param propertyName 属性名<bkname,author,edition,isbn,price,memo>
     * @param value        属性值
     * @return 教材 obj LIST
     */
    List findByPropertyAccurate(String propertyName, String value);

    /**
     * 传入属性名和属性值模糊查找，返回查找结果LIST
     *
     * @param propertyName 属性名<bkname,author,edition,isbn,price,memo>
     * @param value        属性值
     * @return 教材 obj LIST
     */
    List findByPropertyFuzzy(String propertyName, String value);

    /**
     * 根据教材名，进行模糊查找
     *
     * @param bkname 教材名
     * @return 教材 obj LIST
     */
    List findByBknameFuzzy(String bkname);

    /**
     * 根据教材名，进行模糊查找
     *
     * @param idbk 教材id
     * @return 教材 obj LIST
     */
    List findByIdbkAccurate(String idbk);

    /**
     * 返回所有教材LIST(join 供应商)
     * edition,author,price,bkname,idbk,isbn,publisher
     * @return obj LIST
     */
    List findAll();

    /**
     * 根据教材名和出版社名查找(join 供应商)
     * edition,author,price,bkname,idbk,isbn,publisher
     * @param bookname 教材名
     * @param pub      出版社名
     * @return obj LIST
     */
    List findByBookPubFuzzy(String bookname, String pub);

    /**
     * 更新教材实例
     *
     * @param book 教材实例
     */
    boolean update(Book book);

}
