package com.service;

import com.dao.BookDAO;
import com.dao.BookDAOImp;
import com.bean.corbook.Corbookview;
import com.dao.CorbookviewDAO;
import com.dao.CourseDAO;
import com.util.ConvertUtils;

import java.util.List;

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
        List<Corbookview> list = corBookViewDAO.findByIdbk(idbk);
        return ConvertUtils.ToCorBookList(list);
    }

    public List<Corbookview> findByCourse(String idcor) {
        List<Corbookview> list = corBookViewDAO.findByIdcor(idcor);
        return ConvertUtils.ToCorBookList(list);
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

}
