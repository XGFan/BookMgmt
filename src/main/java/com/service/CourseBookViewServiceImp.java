package com.service;

import com.bean.corbook.Corbookview;
import com.dao.BookDAO;
import com.dao.CorbookviewDAO;
import com.dao.CourseDAO;
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

    public List findCourseByIdbk(String idbk) {
        return corBookViewDAO.findByIdbk(idbk);
    }

    public List findByCourse(String idcor) {
        return corBookViewDAO.findByIdcor(idcor);
    }


}
