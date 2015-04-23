package com.dao;

import com.bean.cls.ClassInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.cls.ClassInfo
 */
@Repository("classDAO")
public class ClassDAOImp extends BaseDaoImp<ClassInfo> implements ClassDAO {
    private static final Log log = LogFactory.getLog(ClassDAOImp.class);

    @Override
    public List getClassFuzzy(String condition) {
        List list = null;
        String queryString = "from com.bean.cls.ClassInfo c"
                + " where c.college.col like '%"
                + condition
                + "%' or c.college.major like '%"
                + condition
                + "%'"
                + " or c.semester like '%"
                + condition
                + "%' or c.grade like '%"
                + condition
                + "%' or c.campus like '%" + condition + "%'";
        try {
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find class failed", re);
        }
        return list;
    }

    @Override
    public List getClassByGradeCampusColMajor(String col, String major,
                                              String grade, String campus) {
        boolean flag = true;
        String queryString = "from com.bean.cls.ClassInfo c join fetch c.college cc ";
        if (!col.trim().equals("")&&col!=null) {
            flag = false;
            queryString += "where ";
            queryString += "cc.col = '" + col + "'";
        }
        if (!major.trim().equals("")&&major!=null) {
            if (flag) {
                queryString += "where ";
            } else {
                queryString += " and ";
            }
            flag = false;
            queryString += "cc.major = '" + major + "'";
        }
        if (!grade.trim().equals("")&&grade!=null) {
            if (flag) {
                queryString += "where ";
            } else {
                queryString += " and ";
            }
            flag = false;
            queryString += "c.grade = " + grade;
        }
        if (campus!=null&&!campus.trim().equals("")) {
            if (flag) {
                queryString += "where ";
            } else {
                queryString += " and ";
            }
            flag = false;
            queryString += "c.campus = '" + campus + "'";
        }
        queryString += " order by cc.col,cc.major,c.grade,c.clsno";
        System.out.println(queryString);
        List list = null;
        try {
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find class ByGradeCampusColMajor failed", re);
        }
        return list;
    }

    @Override
    public List getAllCampus() {
        log.debug("finding all Cols");
        List list = null;
        try {
            String queryString = "select distinct campus from ClassInfo";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    @Override
    public List getAllGrade() {
        log.debug("finding all Cols");
        List list = null;
        try {
            String queryString = "select distinct grade from ClassInfo";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    @Override
    public int getClsNum(String grade, String idcm) {
        String queryString = "from com.bean.cls.ClassInfo c join c.college cc where c.clsno=any(select max(c1.clsno) from ClassInfo c1 join c1.college cc1 where c1.grade='"
                + grade
                + "' and cc1.idcm ='"
                + idcm
                + "') and c.grade='"
                + grade + "' and cc.idcm ='" + idcm + "'";
        List<Object[]> list = (List<Object[]>) getCurrentSession().createQuery(queryString).list();
        Integer clsNum = 0;
        for (Object[] objs : list) {
            ClassInfo cls = (ClassInfo) objs[0];
            clsNum = cls.getClsno();
        }
        return clsNum;
    }
}