package com.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.corbook.Corbookview
 */
@Repository("corbookviewDAO")
public class CorbookviewDAO {
    private static final Log log = LogFactory.getLog(CorbookviewDAO.class);
    @Autowired
    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 根据属性名和属性值精确查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return Corbookview obj LIST
     */
    List findByPropertyAccurate(String propertyName, String value) {
        log.debug("finding Corbookview instance with property: " + propertyName
                + ", value: " + value);
        List list = null;
        try {
            String queryString = "from Corbookview as model where model."
                    + propertyName + "= ?";
            list = getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("accurate find by " + propertyName + " failed", re);
        }
        return list;
    }

    /**
     * 根据属性名和属性值模糊查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return Corbookview obj LIST
     */
    List findByPropertyFuzzy(String propertyName, String value) {
        log.debug("finding Corbookview instance with property: " + propertyName
                + ", value: " + value);
        List list = null;
        try {
            String queryString = "from Corbookview as model where model."
                    + propertyName + " like '%" + value + "%'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("fuzzy find by " + propertyName + " failed", re);
        }
        return list;
    }

    /**
     * 通过idcor精确查找
     *
     * @param Idcor 科目id
     * @return Corbookview obj list
     */
    public List findByIdcor(String Idcor) {
        return findByPropertyAccurate("idcor", Idcor);
    }

    /**
     * 通过idbk精确查找
     *
     * @param Idbk 教材id
     * @return Corbookview obj list
     */
    public List findByIdbk(String Idbk) {
        return findByPropertyAccurate("idbk", Idbk);
    }

    /**
     * 返回所有CourseBookView
     *
     * @return CourseBookView obj list
     */
    public List findAll() {
        log.debug("finding all Corbookview instances");
        List list = null;
        try {
            String queryString = "from Corbookview";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 通过corname模糊查找
     *
     * @param corname 科目名称
     * @return CourseBookView obj LIST
     */
    public List findCourseByCorname(String corname) {
        return findByPropertyFuzzy("corname", corname);
    }

}