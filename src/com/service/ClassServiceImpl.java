package com.service;

import com.bean.cls.Class;
import com.dao.ClassDAO;
import com.bean.college.College;
import com.util.GetPaginationInfo;
import com.util.Pagination;

import java.util.*;

public class ClassServiceImpl implements ClassService {

    private ClassDAO classDAO;

    /**
     * 将List转换成map类型
     *
     * @param l list
     * @return map类型的list
     */
    private static List class2List(List l) {
        Iterator it = l.iterator();
        List clslist = new ArrayList();
        while (it.hasNext()) {
            Map map = new HashMap();
            Class cls = (Class) it.next();
            map.put("col", cls.getCollege().getCol());
            map.put("major", cls.getCollege().getMajor());
            map.put("idcm", cls.getCollege().getIdcm());
            map.put("semester", cls.getSemester());
            map.put("campus", cls.getCampus());
            map.put("clsno", cls.getClsno());
            map.put("grade", cls.getGrade());
            map.put("idcls", cls.getIdcls());
            map.put("clsno", cls.getClsno());
            map.put("stunum", cls.getStunum());
            clslist.add(map);
        }
        System.out.println(clslist);
        return clslist;
    }

    public ClassDAO getClassDAO() {
        return classDAO;
    }

    public void setClassDAO(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    public List fuzzyFind(String condition, Pagination pagination) {
        return GetPaginationInfo.getSubList(class2List(classDAO.getClassFuzzy(condition)), pagination);
    }

    public List findAllByPagination(Pagination pagination) {
        return GetPaginationInfo.getSubList(class2List(classDAO.findAll()), pagination);
    }

    public List accurateQuery(String col, String major, String grade, String campus, Pagination pagination) {
        return GetPaginationInfo.getSubList(class2List(classDAO.getClassByGradeCampusColMajor(col, major, grade, campus)), pagination);
    }

    public List getAllCampus() {
        return classDAO.getAllCampus();
    }


    public List getAllGrade() {
        return classDAO.getAllGrade();
    }


    public boolean deleteClasses(String[] idclses) {
        Class cls = new Class();
        for (String idcls : idclses) {
            cls = classDAO.findById(idcls);
            classDAO.delete(cls);
        }
        return true;
    }


    public boolean addClasses(String campus, String grade, int clsnum, College college) {
        Class cls = new Class();
        Integer j = classDAO.getClsNum(grade, college.getIdcm()) + 1;// 班级表此时班号最大值
        String idcls;
        for (int i = classDAO.getClsNum(grade, college.getIdcm()) + 1; i < j
                + clsnum; i++) {
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
            //System.out.println(cls);
            classDAO.save(cls);
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
