package com.service;

import com.bean.corbook.Corbookview;
import com.dao.BookDAO;
import com.dao.CorbookviewDAO;
import com.dao.CourseDAO;
import com.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseBookViewService")
public class CourseBookViewServiceImp extends BaseServiceTemplate<Corbookview> implements CourseBookViewService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
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

    public List findCourseByIdbk(String idbk) {
        List list = corBookViewDAO.findByIdbk(idbk);
        return ConvertUtils.ToCorBookList(list);
    }

    public List findByCourse(String idcor) {
        List list = corBookViewDAO.findByIdcor(idcor);
        return ConvertUtils.ToCorBookList(list);
    }

    public List findAllCourse() {
        List list = corBookViewDAO.getAll();
        return ConvertUtils.ToCorBookList(list);
    }

    public List findCourseByCorname(String corname) {
        List<Corbookview> list = corBookViewDAO.findCourseByCorname(corname);
        return ConvertUtils.ToCorBookList(list);
    }

}
