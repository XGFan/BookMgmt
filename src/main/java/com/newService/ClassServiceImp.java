package com.newService;

import com.bean.cls.ClassInfo;
import com.bean.college.College;
import com.newDao.ClassDao;
import com.service.BaseServiceTemplate;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:19:58
 * Created by guofan on 2015/1/6
 */
public class ClassServiceImp extends BaseServiceTemplate<ClassInfo> implements ClassService {

    @Autowired
    ClassDao classDao;

    @Override
    public List fuzzyFind(String condition, Pagination pagination) {
        return GetPaginationInfo.getSubList(ConvertUtils.class2List(classDao.getClassFuzzy(condition)), pagination);
    }

    @Override
    public List fuzzyFind(String keyword) {
        return null;
    }

    @Override
    public List findAllByPagination(Pagination pagination) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List accurateQuery(String col, String major, String grade, String campus, Pagination pagination) {
        return null;
    }

    @Override
    public List accurateQuery(String col, String major, String grade, String campus) {
        return null;
    }

    @Override
    public List getAllCampus() {
        return null;
    }

    @Override
    public List getAllGrade() {
        return null;
    }

    @Override
    public boolean deleteClasses(String[] idclses) {
        return false;
    }

    @Override
    public boolean deleteClass(String idcls) {
        return false;
    }

    @Override
    public boolean addClasses(String campus, String grade, int clsnnum, College college) {
        return false;
    }

    @Override
    public boolean updateClass(ClassInfo cls) {
        return false;
    }
}
