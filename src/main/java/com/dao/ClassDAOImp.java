package com.dao;

import com.bean.cls.ClassInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.cls.ClassInfo
 */
@Repository("classDAO")
public class ClassDAOImp implements ClassDAO {
    private static final Log log = LogFactory.getLog(ClassDAOImp.class);
    @Autowired
    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public boolean save(ClassInfo transientInstance) {
        boolean tag = true;
        log.debug("saving ClassInfo instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            tag = false;
        }
        return tag;
    }

    @Override
    public boolean delete(ClassInfo persistentInstance) {
        boolean tag = true;
        log.debug("deleting ClassInfo instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            tag = false;
        }
        return tag;
    }

    @Override
    public ClassInfo findById(java.lang.String id) {
        ClassInfo cls = null;
        log.debug("getting ClassInfo instance with id: " + id);
        try {
            cls = (ClassInfo) getHibernateTemplate().get("com.bean.cls.ClassInfo", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
        }
        return cls;
    }

    @Override
    public List findAll() {
        List list = null;
        log.debug("finding all ClassInfo instances");
        try {
            String queryString = "from ClassInfo";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

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
            list = getHibernateTemplate().find(queryString);
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
        if(col!="") {
            flag = false;
            queryString += "where ";
            queryString +="cc.col = '"+ col+"'";
        }
        if(major!="") {
            if(flag) {
                queryString += "where ";
            }else{
                queryString += " and ";
            }
            flag = false;
            queryString += "cc.major = '" + major + "'";
        }
        if(grade!="") {
            if(flag) {
                queryString += "where ";
            }else{
                queryString += " and ";
            }
            flag = false;
            queryString += "c.grade = " + grade;
        }
        if(campus!="") {
            if(flag) {
                queryString += "where ";
            }else{
                queryString += " and ";
            }
            flag = false;
            queryString += "c.campus = '" + campus +"'";
        }
        queryString += " order by cc.col,cc.major,c.grade,c.clsno";
        List list = null;
        System.out.println(queryString);
        try {
            list = getHibernateTemplate().find(queryString);
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
            list = getHibernateTemplate().find(queryString);
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
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    @Override
    public boolean update(ClassInfo persistentInstance) {
        boolean tag = true;
        log.debug("saveOrUpdating ClassInfo instance");
        try {
            getHibernateTemplate().update(persistentInstance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            tag = false;
        }
        return tag;
    }

    @Override
    public int getClsNum(String grade, String idcm) {
        String queryString = "from com.bean.cls.ClassInfo c join c.college cc where c.clsno=any(select max(c1.clsno) from ClassInfo c1 join c1.college cc1 where c1.grade='"
                + grade
                + "' and cc1.idcm ='"
                + idcm
                + "') and c.grade='"
                + grade + "' and cc.idcm ='" + idcm + "'";
        // List<Object[]>
        // list=this.getSession().createQuery(queryString).list();
        /** 在将上面这句话转换成下面这句话之前，每次只能添加2次班级，系统就不能访问数据库，但是Tomcat正常开启。张驰 20140506**/
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(queryString);
        Integer clsNum = 0;
        for (Object[] objs : list) {
            ClassInfo cls = (ClassInfo) objs[0];
            // System.out.println(cls.getClsno());
            clsNum = cls.getClsno();
        }
        return clsNum;
    }
}