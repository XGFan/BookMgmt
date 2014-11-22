package com.dao;

import com.bean.book.Book;

import java.util.List;

public interface BookDAOInf {
    /**
     * 保存实例
     *
     * @param transientInstance 教材实例
     */
    public void save(Book transientInstance);

    /**
     * 传入实例在数据库中删除
     *
     * @param persistentInstance 实例
     * @return boolean
     */
    public boolean delete(Book persistentInstance);

    /**
     * 传入id，返回相应对象
     *
     * @param id 唯一id
     * @return 书本对象
     */
    public Book findById(java.lang.String id);

    /**
     * 传入属性名和属性值精确查找，返回查找结果LIST
     *
     * @param propertyName 属性名<bkname,author,edition,isbn,price,memo>
     * @param value        属性值
     * @return 结果LIST
     */
    public List findByProperty(String propertyName, Object value);

    /**
     * 根据书本名称，进行模糊查找，返回LIST
     *
     * @param bkname 书本名称
     * @return 结果LIST
     */
    public List FuzzyFindByBkname(String bkname);

    /**
     * 返回所有书本LIST
     *
     * @return 返回所有书本LIST
     */
    public List findAll();

    /**
     * @param bookname 书本名称
     * @param pub      出版社
     * @return 结果LIST
     */
    public List<Book> getByBookPub(String bookname, String pub);

    /**
     * 修改实例
     *
     * @param book book实例
     */
    public void updateBook(Book book);

}