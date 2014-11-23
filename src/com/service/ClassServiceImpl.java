package com.service;

import com.bean.cls.Class;
import com.dao.ClassDAO;
import com.bean.college.College;
import com.util.GetPaginationInfo;
import com.util.Pagination;
import com.util.ConvertUtils;

import java.util.*;

public class ClassServiceImpl implements ClassService {

    private ClassDAO classDAO;

    public ClassDAO getClassDAO() {
        return classDAO;
    }

    public void setClassDAO(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    public List fuzzyFind(String condition, Pagination pagination) {
        return GetPaginationInfo.getSubList(ConvertUtils.class2List(classDAO.getClassFuzzy(condition)), pagination);
    }

    public List findAllByPagination(Pagination pagination) {
        return GetPaginationInfo.getSubList(ConvertUtils.class2List(classDAO.findAll()), pagination);
    }

    public List accurateQuery(String col, String major, String grade, String campus, Pagination pagination) {
        return GetPaginationInfo.getSubList(ConvertUtils.class2List(classDAO.getClassByGradeCampusColMajor(col, major, grade, campus)), pagination);
    }

    public List getAllCampus() {
        return classDAO.getAllCampus();
    }


    public List getAllGrade() {
        return classDAO.getAllGrade();
    }


    public boolean deleteClasses(String[] idclses) {
        Class cls;
        for (String idcls : idclses) {
            cls = classDAO.findById(idcls);
            if(cls != null){
                if (!classDAO.delete(cls))
                    return false;
            }
        }
        return true;
    }


    public boolean addClasses(String campus, String grade, int clsnum, College college) {
        Class cls = new Class();
        Integer j = classDAO.getClsNum(grade, college.getIdcm()) + 1;// 班级表此时班号最大值
        String idcls;
        for (int i = j; i < j + clsnum; i++) {
            if (i < 10) {
                idcls = grade + college.getIdcm() + "0" + i;
            } else {
                idcls = grade + college.getIdcm() + i;
            }
            cls.setIdcls(idcls);
            cls.setCollege(college);
            cls.setCampus(campus);
            cls.setGrade(grade);
            cls.setSemester("1");
            cls.setClsno(i);
            cls.setStunum(50);
            if(!classDAO.save(cls))
                return false;
        }
        return true;
    }

    public boolean editClasses(Class cls) {
        classDAO.saveOrUpdate(cls);
        return true;
    }

    public Class findById(String idcls) {
        return classDAO.findById(idcls);
    }

}
