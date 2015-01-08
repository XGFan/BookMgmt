package com.service;

import com.bean.book.Book;
import com.bean.course.Course;
import com.bean.coursebk.Coursebk;

import java.util.List;

/**
 * DATE:2015/1/8
 * TIME:16:19
 * Created by guofan on 2015/1/8
 */
public interface CourseBkService extends BaseService<Coursebk> {
    public List<Coursebk> findByIdCor(String idcor);

    public List<Coursebk> findByIdBk(String idbk);

    public List<Coursebk> findByBkAndCor(String idbk,String idcor);

    public boolean save(String idbk, String idcor);
}
