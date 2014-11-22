package com.bean.corbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Corbookview entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.corbook.Corbookview
 */

public class CorbookviewDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CorbookviewDAO.class);

    // property constants

    protected void initDao() {
        // do nothing
    }

    /**
     * 根据属性名和属性值查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return LIST
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Corbookview instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Corbookview as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /**
     * 返回所有信息
     *
     * @return 科目书本视图 obj list
     */
    public List findAll() {
        log.debug("finding all Corbookview instances");
        try {
            String queryString = "from Corbookview";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 根据科目名称查找课程
     *
     * @param corname 科目名称
     * @return LIST
     */
    public List findCourseByCorname(String corname) {
        log.debug("findCourseByCorname");
        try {
            String queryString = "from Corbookview view where view.corname like '%" + corname + "%'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("findCourseByCorname failed", re);
            throw re;
        }
    }

}