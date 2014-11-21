package com.printInfo.book.Service;

import java.util.List;
import java.util.Map;

import com.bean.book.Book;
import com.bean.book.BookDAO;
import com.bean.corbook.Corbookview;
import com.bean.corbook.CorbookviewDAO;
import com.bean.corbook.CorbookviewId;
import com.bean.course.Course;
import com.bean.course.CourseDAO;
import com.util.ConvertUtils;

public class CourseBookViewServiceImpl implements CourseBookViewService {
	private BookDAO bookDAO;
	private CourseDAO courseDAO;
	private CorbookviewDAO corBookViewDAO;

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public CorbookviewDAO getCorBookViewDAO() {
		return corBookViewDAO;
	}

	public void setCorBookViewDAO(CorbookviewDAO corBookViewDAO) {
		this.corBookViewDAO = corBookViewDAO;
	}

	public List<Corbookview> findCourseByIdbk(String idbk) {
		List<Corbookview> list = corBookViewDAO.findByProperty("idbk", idbk);
		return ConvertUtils.ToCorBookList(list);
	}

	public List<Corbookview> findByCourse(String idcor) {
		List<Corbookview> list = corBookViewDAO.findByProperty("idcor", idcor);
		return ConvertUtils.ToCorBookList(list);
	}

	// 重写findByCourse方法，返回list类型
	public List<Corbookview> findByCourseList(String idcor) {
		List<Corbookview> list = corBookViewDAO.findByProperty("idcor", idcor);
		return list;
	}

	public List<Corbookview> searchall() {
		List list = corBookViewDAO.findAll();
		return list;
	}

	/* 执行全修改 */
	public boolean modify(Book book, List<Course> course) {
		bookDAO.updateBook(book);
		return true;
	}

	public List<Corbookview> findAllCourse() {
		List<Corbookview> list = corBookViewDAO.findAll();
		return ConvertUtils.ToCorBookList(list);
	}


	public List<Corbookview> findAllBk() {
		List<Corbookview> list = corBookViewDAO.findAll();
		return ConvertUtils.ToCorBookList(list);
	}

	public List<Corbookview> findCourseByCorname(String corname) {
		List<Corbookview> list = corBookViewDAO.findCourseByCorname(corname);
		return ConvertUtils.ToCorBookList(list);
	}

	/* 添加课程，查询教材 */
	public List<Corbookview> findBookByBkname(String bkname) {
		List<Corbookview> list = corBookViewDAO.findByBk(bkname);
		return ConvertUtils.ToCorBookList(list);
	}
}
