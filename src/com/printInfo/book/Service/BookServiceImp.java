package com.printInfo.book.Service;

import java.util.List;

import com.bean.book.Book;
import com.bean.book.BookDAOInf;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;

public class BookServiceImp implements BookService {
	private BookDAOInf bookDAO;

	public BookDAOInf getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAOInf bookDAO) {
		this.bookDAO = bookDAO;
	}

	public String addBook(Book book) {
		bookDAO.save(book);
		return null;
	}

	public boolean deleteBook(Book book) {
		Book temp = bookDAO.findById(book.getIdbk());
		bookDAO.delete(temp);
		return false;
	}

	public boolean deleteBook(String idbk) {
		Book temp = bookDAO.findById(idbk);
		boolean tag = false;
		tag = bookDAO.delete(temp);
		return tag;
	}

	public List<Object> initBook() {
		List list = bookDAO.findAll();
		return ConvertUtils.ToBookList(list);
	}

	public List<Object> initBook(Pagination pagination) {
		List list = this.initBook();
		return GetPaginationInfo.getSubList(list, pagination);

	}

	public List<Book> searchByBookPub(String bookname, String pub) {
		List list = bookDAO.getByBookPub(bookname, pub);
		return ConvertUtils.ToBookListFromBook(list);
	}

	/** 通过页码和教材名称和出版社进行查询教材信息 **/
	public List<Book> searchByBookPub(String bookname, String pub,
			Pagination pagination) {
		List list = this.searchByBookPub(bookname, pub);
		return GetPaginationInfo.getSubList(list, pagination);
	}

	public List<Book> searchByISBN(String isbn) {
		List list = bookDAO.findByIsbn(isbn);
		return ConvertUtils.ToBookListFromBook(list);
	}

	/** 通过页码和ISBN进行查询教材信息 **/
	public List<Book> searchByISBN(String isbn, Pagination pagination) {
		List list = this.searchByISBN(isbn);
		return GetPaginationInfo.getSubList(list, pagination);
	}

	public List<Book> searchByBkname(String bkname) {
		List<Book> list = bookDAO.FuzzyFindByBkname(bkname);
		return ConvertUtils.ToBookListFromBook(list);
	}

	public boolean updateBook(Book book) {
		bookDAO.updateBook(book);
		return false;
	}

}
