package com.bean.corplan;

import java.util.List;

import org.hibernate.LockMode;
import org.apache.commons.logging.*;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Corplan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.corplan.Corplan
 */

public class CorplanDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CorplanDAO.class);
    // property constants
    public static final String SEMESTER = "semester";
    public static final String IDCOR = "idcor";

    protected void initDao() {
        // do nothing
    }

    /**
     * 保存课程计划
     *
     * @param transientInstance 课程计划实例
     */
    public void save(Corplan transientInstance) {
        log.debug("saving Corplan  ");
        try {
            getHibernateTemplate().saveOrUpdate(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /**
     * 删除
     *
     * @param persistentInstance
     */
    public void delete(Corplan persistentInstance) {
        log.debug("deleting Corplan instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public Corplan findById(java.lang.String id) {
        log.debug("getting Corplan instance with id: " + id);
        try {
            Corplan instance = (Corplan) getHibernateTemplate().get(
                    "com.bean.corplan.Corplan", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 根据idcorsem查找对应课程计划
     *
     * @param idcorsem
     * @return
     */
    public List findByIdcorsem(String idcorsem) {
        log.debug("getting Corplan instance with idcorsem: " + idcorsem);
        try {
            String queryString = "from Corplan c  where idcorsem = '" + idcorsem + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 根据example查找
     *
     * @param instance
     * @return LIST
     */
    public List findByExample(Corplan instance) {
        log.debug("finding Corplan instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /**
     * 根据属性值和属性名查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return LIST
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Corplan instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Corplan as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findBySemester(Object semester) {
        return findByProperty(SEMESTER, semester);
    }

    /*连接教学计划，课程，学院三张表，根据课程编号和学期查询教学计划
     * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByIdcorSemester(String idcor, String semester) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpc.idcor ='"
                + idcor + "' and cp.semester='" + semester + "'";
        return getHibernateTemplate().find(queryString);
    }

    /*连接教学计划，课程，学院三张表，根据idcor查询教学计划
     * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByIdcor(String idcor) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpc.idcor ='"
                + idcor + "'";
        return getHibernateTemplate().find(queryString);
    }

    //查询所有的教学计划
    public List findAll() {
        log.debug("finding all Corplan instances");
        try {
            String queryString = "from Corplan";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Corplan merge(Corplan detachedInstance) {
        log.debug("merging Corplan instance");
        try {
            Corplan result = (Corplan) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Corplan instance) {
        log.debug("attaching dirty Corplan instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Corplan instance) {
        log.debug("attaching clean Corplan instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static CorplanDAO getFromApplicationContext(ApplicationContext ctx) {
        return (CorplanDAO) ctx.getBean("CorplanDAO");
    }

    //根据idcor进行删除
    public void deleteById(String idcor) {
        Corplan persistentInstance = findById(idcor);
        getHibernateTemplate().delete(persistentInstance);
    }

    /*连接教学计划，课程，学院三张表，根据输入条件（学院或专业）查询教学计划
        * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByColOrMajor(String condition) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                + condition + "%' or cpcc.major like '%" + condition + "%'";
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    public List getCorplanWithJoin() {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc ";
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /*连接教学计划，课程，学院三张表，根据学院查询教学计划
        * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByCol(String col) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                + col + "%'";
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /*连接教学计划，课程，学院三张表，根据学院和专业查询教学计划
        * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByColMajor(String col, String major) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                + col + "%'and cpcc.major like '%" + major + "%'";
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /*连接教学计划，课程，学院三张表，根据专业查询教学计划
        * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByMajor(String major) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where "
                + "cpcc.major like '%" + major + "%'";
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /*连接教学计划，课程，学院三张表，根据学院，专业，学期查询教学计划
        * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByColMajorSem(String col, String major,
                                        String semester) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                + col
                + "%' and cpcc.major ='"
                + major
                + "' and cp.semester = '" + semester + "'";
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /*连接教学计划，课程，学院三张表，根据学院，专业，学期和课程名称查询教学计划
        * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）*/
    public List getCorplanByColMajorSemCorname(String col, String major,
                                               String semester, String corname) {
        log.debug("get Corplan instance");
        try {
            String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                    + col
                    + "%' and cpcc.major ='"
                    + major
                    + "' and cp.semester ='"
                    + semester
                    + "' and cpc.corname ='" + corname + "'";
            //System.out.println(getHibernateTemplate());
            List list = getHibernateTemplate().find(queryString);
            return list;
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /**
     * 连接教学计划，课程，学院三张表，根据学院，专业，学期，课程名称和课程编号查询教学计划
     * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）
     *
     * @param col      学院名
     * @param major    专业名
     * @param semester 学期
     * @param corname  科目名称
     * @param idcor    科目id
     * @return 教学计划 obj list
     */
    public List getCorplanByColMajorSemCornameIdcor(String col, String major,
                                                    String semester, String corname, String idcor) {
        log.debug("get Corplan instance");
        try {
//			String queryString = "from courplan where col like '%"
//				+ col
//				+ "%' and major ='"
//				+ major
//				+ "' and semester ='"
//				+ semester
//				+ "' and idcor ='"
//				+ idcor + "' and corname ='" + corname + "'";
            String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                    + col
                    + "%' and cpcc.major ='"
                    + major
                    + "' and cp.semester ='"
                    + semester
                    + "' and cpc.idcor ='"
                    + idcor + "' and cpc.corname ='" + corname + "'";
            List list = getHibernateTemplate().find(queryString);
            return list;
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }


    /**
     * 连接教学计划，科目，学院三张表
     * 根据输入条件（学院or专业or学期）模糊查询教学计划
     *
     * @param condition 关键字
     * @return obj list
     */
    public List getCorplanFuzzy(String condition) {
//		String queryString = "from courplan where col like '%"
//			+ condition
//			+ "%' or major like '%"
//			+ condition
//			+ "%'"
//			+ " or semester like '%" + condition + "%'";
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                + condition
                + "%' or cpcc.major like '%"
                + condition
                + "%'"
                + " or cp.semester like '%" + condition + "%'";
        System.out.println(queryString);
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /* 已知学院专业，通过课程名称模糊查找*/
    public List getCorplanFuzzyByColMajorCorname(String col, String major,
                                                 String condition) {
//		String queryString = "from courplan where col ='"
//			+ col
//			+ "' and major ='"
//			+ major
//			+ "' and corname like '%" + condition + "%'";
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col ='"
                + col
                + "' and cpcc.major ='"
                + major
                + "' and cpc.corname like '%" + condition + "%'";
        //System.out.println(queryString);
        List list = getHibernateTemplate().find(queryString);
        return list;
    }

    /**
     * 删除所有教学计划
     */
    public void deleteAllCorplan() {
        List corplanList = this.findAll();
        getHibernateTemplate().deleteAll(corplanList);
    }

    /**
     * 初始化，根据Course自动生成所有的Corplan
     *
     * @param entities
     */
    public void initAllCorplan(List entities) {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }
}