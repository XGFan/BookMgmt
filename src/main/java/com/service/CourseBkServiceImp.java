package com.service;

import com.bean.book.Book;
import com.bean.course.Course;
import com.bean.coursebk.Coursebk;
import com.dao.BookDAO;
import com.dao.CourseDAO;
import com.dao.CoursebkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DATE:2015/1/8
 * TIME:15:38
 * Created by guofan on 2015/1/8
 */
@Service("courseBkService")
public class CourseBkServiceImp extends BaseServiceTemplate<Coursebk> implements CourseBkService {
    @Autowired
    private CoursebkDAO coursebkDAO;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CourseDAO courseDAO;

    public List<Coursebk> findByIdCor(String idcor){
        return coursebkDAO.findByPropertyA("idcor",idcor);
    }

    public List<Coursebk> findByIdBk(String idbk){
        return coursebkDAO.findByPropertyA("idbk",idbk);
    }

    public List<Coursebk> findByBkAndCor(String idbk,String idcor){
        return coursebkDAO.findByIdcorAndIdbk(idcor,idbk);
    }

    public boolean save(String idbk, String idcor){
        Book book = bookDAO.findById(idbk);
        Course course = courseDAO.findById(idcor);
        if(book != null && course != null){
            return save(new Coursebk(book,course));
        }else{
            return false;
        }
    }
}
