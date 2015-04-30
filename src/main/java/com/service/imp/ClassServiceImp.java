package com.service.imp;

import com.bean.cls.ClassInfo;
import com.bean.college.College;
import com.dao.ClassDAO;
import com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classServie")
public class ClassServiceImp extends BaseServiceTemplate<ClassInfo> implements ClassService {
    @Autowired
    private ClassDAO classDAO;

    public List fuzzyFind(String condition) {
        return classDAO.getClassFuzzy(condition);
    }

    public List accurateQuery(String col, String major, String grade, String campus) {
        return classDAO.getClassByGradeCampusColMajor(col, major, grade, campus);
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

    public boolean deleteClass(String idcls) {
        ClassInfo cls = classDAO.findById(idcls);
        return classDAO.delete(cls);
    }

    public boolean addClasses(String campus, String grade, int clsnum, College college) {
        Integer j = classDAO.getClsNum(grade, college.getIdcm()) + 1;// 班级表此时班号最大值
        String idcls;
        for (int i = j; i < j + clsnum; i++) {
            if (i < 10) {
                idcls = grade + college.getIdcm() + "0" + i;
            } else {
                idcls = grade + college.getIdcm() + i;
            }
            ClassInfo cls = new ClassInfo();
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

}
