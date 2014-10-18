package com.bean.book;

import java.util.List;

import com.bean.corbook.CorbookviewId;
import com.bean.supplier.Supplier;
import com.printInfo.book.Service.CourseBookViewService;
import com.util.Pagination;

public interface BookDAOInf {
	public void save(Book transientInstance);
	public boolean delete(Book persistentInstance);
	public Book findById(java.lang.String id);
	public List findByExample(Book instance) ;
	public List findByProperty(String propertyName, Object value);
	public List findByBkname(Object bkname);
	public List FuzzyFindByBkname(String bkname);
	public List findByAuthor(Object author);
	public List findByEdition(Object edition);
	public List findByIsbn(Object isbn);
	public List findByPrice(Object price);
	public List findByMemo(Object memo);
	public List findAll();
	public List<Book> getByBookPub(String bookname, String pub);

	public void updateBook(Book book);
	//通过教材查询课程
	public List<CorbookviewId> searchByBook(String idbk);

}
