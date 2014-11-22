package com.bean.corplan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

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
    public static final String IDCOR = "idcor";
    private static final Log log = LogFactory.getLog(CorplanDAO.class);
    // property constants
    private static final String SEMESTER = "semester";

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
     * @param persistentInstance 教学计划实例
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

    //查询所有的教学计划
    List findAll() {
        log.debug("finding all Corplan instances");
        try {
            String queryString = "from Corplan";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List getCorplanWithJoin() {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc ";
        return getHibernateTemplate().find(queryString);
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
        return getHibernateTemplate().find(queryString);
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
            return getHibernateTemplate().find(queryString);
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
        return getHibernateTemplate().find(queryString);
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
        return getHibernateTemplate().find(queryString);
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
     * todo
     */
    public void initAllCorplan(List entities) {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }
}