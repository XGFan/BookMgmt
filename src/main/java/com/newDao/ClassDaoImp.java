package com.newDao;

import com.bean.cls.ClassInfo;
import com.dao.BaseDaoImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:19:35
 * Created by guofan on 2015/1/6
 */
public class ClassDaoImp extends BaseDaoImp<ClassInfo> implements ClassDao {
    private static final Log log = LogFactory.getLog(ClassDaoImp.class);

    @Override
    public List getClassFuzzy(String condition) {
        List list = null;
        String queryString = "from com.bean.cls.ClassInfo c join fetch c.college cc"
                + " where cc.col like '%"
                + condition
                + "%' or cc.major like '%"
                + condition
                + "%'"
                + " or c.semester like '%"
                + condition
                + "%' or c.grade like '%"
                + condition
                + "%' or c.campus like '%" + condition + "%'";
        try {
            list = findByHql(queryString);
        } catch (RuntimeException re) {
            log.error("find class failed", re);
        }
        return list;
    }

    @Override
    public List getClassByGradeCampusColMajor(String col, String major, String grade, String campus) {
        boolean flag = true;
        String queryString = "from com.bean.cls.ClassInfo c join fetch c.college cc ";
        if (col != "") {
            flag = false;
            queryString += "where ";
            queryString += "cc.col = '" + col + "'";
        }
        if (major != "") {
            if (flag) {
                queryString += "where ";
            } else {
                queryString += " and ";
            }
            flag = false;
            queryString += "cc.major = '" + major + "'";
        }
        if (grade != "") {
            if (flag) {
                queryString += "where ";
            } else {
                queryString += " and ";
            }
            flag = false;
            queryString += "c.grade = " + grade;
        }
        if (campus != "") {
            if (flag) {
                queryString += "where ";
            } else {
                queryString += " and ";
            }
            flag = false;
            queryString += "c.campus = '" + campus + "'";
        }
        queryString += " order by cc.col,cc.major,c.grade,c.clsno";
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
        log.debug("finding All Campus");
        List list = null;
        try {
            String queryString = "select distinct campus from ClassInfo";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find All Campus failed", re);
        }
        return list;
    }

    @Override
    public List getAllGrade() {
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
    public int getClsNum(String grade, String idcm) {
        String queryString = "from com.bean.cls.ClassInfo c join c.college cc where c.clsno=any(select max(c1.clsno) from ClassInfo c1 join c1.college cc1 where c1.grade='"
                + grade
                + "' and cc1.idcm ='"
                + idcm
                + "') and c.grade='"
                + grade + "' and cc.idcm ='" + idcm + "'";
        /** 在将上面这句话转换成下面这句话之前，每次只能添加2次班级，系统就不能访问数据库，但是Tomcat正常开启。张驰 20140506**/
        List<Object[]> list = (List<Object[]>)getCurrentSession().createQuery(queryString).list();
        Integer clsNum = 0;
        for (Object[] obj : list) {
            ClassInfo cls = (ClassInfo) obj[0];
            clsNum = cls.getClsno();
        }
        return clsNum;
    }
}
