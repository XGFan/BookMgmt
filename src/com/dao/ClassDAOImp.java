package com.dao;

import com.bean.cls.Class;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Class
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.cls.Class
 */

public class ClassDAOImp extends HibernateDaoSupport implements ClassDAO {
    private static final Log log = LogFactory.getLog(ClassDAOImp.class);

    protected void initDao() {
    }

    @Override
    public boolean save(com.bean.cls.Class transientInstance) {
        boolean tag = true;
        log.debug("saving Class instance");
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
    public boolean delete(Class persistentInstance) {
        boolean tag = true;
        log.debug("deleting Class instance");
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
    public Class findById(java.lang.String id) {
        Class cls = null;
        log.debug("getting Class instance with id: " + id);
        try {
            cls = (Class) getHibernateTemplate().get("com.bean.cls.Class", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
        }
        return cls;
    }

    @Override
    public List findAll() {
        List list = null;
        log.debug("finding all Class instances");
        try {
            String queryString = "from Class";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    @Override
    public List getClassFuzzy(String condition) {
        List list = null;
        String queryString = "from com.bean.cls.Class c join fetch c.college cc"
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
        try{
            list = getHibernateTemplate().find(queryString);
        }catch (RuntimeException re){
            log.error("find class failed", re);
        }
        return list;
    }

    @Override
    public List getClassByGradeCampusColMajor(String col, String major,
                                              String grade, String campus) {
        String queryString = "from com.bean.cls.Class c join fetch c.college cc where"
                + " cc.col like '%"
                + col
                + "%' and cc.major like '%"
                + major
                + "%'"
                + " and c.campus like '%"
                + campus
                + "%' and c.grade like '%"
                + grade
                + "%'"
                + " order by cc.col,cc.major,c.grade,c.clsno";
        List list = null;
        try{
            list =  getHibernateTemplate().find(queryString);
        }catch (RuntimeException re){
            log.error("find class ByGradeCampusColMajor failed", re);
        }
        return list;
    }

    @Override
    public List getAllCampus() {
        log.debug("finding all Cols");
        List list = null;
        try {
            String queryString = "select distinct campus from Class";
            list =  getHibernateTemplate().find(queryString);
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
            String queryString = "select distinct grade from Class";
             list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    @Override
    public boolean saveOrUpdate(Class persistentInstance) {
        boolean tag = true;
        log.debug("saveOrUpdating Class instance");
        try {
            getHibernateTemplate().saveOrUpdate(persistentInstance);
            log.debug("saveOrUpdate successful");
        } catch (RuntimeException re) {
            log.error("saveOrUpdate failed", re);
            tag = false;
        }
        return tag;
    }

    @Override
    public int getClsNum(String grade, String idcm) {
        String queryString = "from com.bean.cls.Class c join c.college cc where c.clsno=any(select max(c1.clsno) from Class c1 join c1.college cc1 where c1.grade='"
                + grade
                + "' and cc1.idcm ='"
                + idcm
                + "') and c.grade='"
                + grade + "' and cc.idcm ='" + idcm + "'";
        // List<Object[]>
        // list=this.getSession().createQuery(queryString).list();
        /** 在将上面这句话转换成下面这句话之前，每次只能添加2次班级，系统就不能访问数据库，但是Tomcat正常开启。张驰 20140506**/
        List<Object[]> list = getHibernateTemplate().find(queryString);
        Integer clsNum = 0;
        for (Object[] objs : list) {
            Class cls = (Class) objs[0];
            // System.out.println(cls.getClsno());
            clsNum = cls.getClsno();
        }
        return clsNum;
    }
}