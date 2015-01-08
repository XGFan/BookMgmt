package com.service;

import com.bean.cls.ClassInfo;
import com.bean.college.College;
import com.dao.ClassDAO;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classServie")
public class ClassServiceImp implements ClassService {
    @Autowired
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

    @Override
    public List fuzzyFind(String condition) {
        return ConvertUtils.class2List(classDAO.getClassFuzzy(condition));
    }

    public List findAllByPagination(Pagination pagination) {
        return GetPaginationInfo.getSubList(ConvertUtils.class2List(classDAO.findAll()), pagination);
    }

    @Override
    public List findAll() {
        return ConvertUtils.class2List(classDAO.findAll());
    }

    public List accurateQuery(String col, String major, String grade, String campus, Pagination pagination) {
        return GetPaginationInfo.getSubList(ConvertUtils.class2List(classDAO.getClassByGradeCampusColMajor(col, major, grade, campus)), pagination);
    }

    @Override
    public List accurateQuery(String col, String major, String grade, String campus) {
        return ConvertUtils.class2List(classDAO.getClassByGradeCampusColMajor(col, major, grade, campus));
    }

    public List getAllCampus() {
        return classDAO.getAllCampus();
    }


    public List getAllGrade() {
        return classDAO.getAllGrade();
    }


    public boolean deleteClasses(String[] idclses) {
        for (String idcls : idclses) {
            if (!deleteClass(idcls))
                return false;
        }
        return true;
    }

    @Override
    public boolean deleteClass(String idcls) {
        ClassInfo cls = classDAO.findById(idcls);
        return classDAO.delete(cls);
    }

    public boolean addClasses(String campus, String grade, int clsnum, College college) {
        ClassInfo cls = new ClassInfo();
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
            if (!classDAO.save(cls))
                return false;
        }
        return true;
    }

    public boolean updateClass(ClassInfo cls) {
        return classDAO.update(cls);
    }

    public ClassInfo findById(String idcls) {
    	System.out.println(idcls+"csi999999999");
        return classDAO.findById(idcls);
    }

}
