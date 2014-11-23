package com.dao;

import com.bean.corplan.Corplan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.corplan.Corplan
 */

public class CorplanDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CorplanDAO.class);

    protected void initDao() {
    }

    /**
     * 保存课程计划
     *
     * @param transientInstance 课程计划实例
     */
    public boolean save(Corplan transientInstance) {
        log.debug("saving Corplan  ");
        boolean tag = true;
        try {
            getHibernateTemplate().saveOrUpdate(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            tag = false;
        }
        return tag;
    }

    /**
     * 删除
     *
     * @param persistentInstance 教学计划实例
     */
    public boolean delete(Corplan persistentInstance) {
        log.debug("deleting Corplan instance");
        boolean tag = true;
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            tag = false;
        }
        return tag;
    }

    /**
     * 所有corplan
     *
     * @return all corplan obj list
     */
    List findAll() {
        log.debug("finding all Corplan instances");
        List list = null;
        try {
            String queryString = "from Corplan";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    /**
     * 连接教学计划，课程，学院三张表，根据学院，专业，学期查询教学计划
     * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）
     *
     * @param col      学院名
     * @param major    专业名
     * @param semester 学期
     * @return list
     */
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
     * @return list
     */
    public List getCorplanByColMajorSemCornameIdcor(String col, String major,
                                                    String semester, String corname, String idcor) {
        log.debug("get Corplan instance");
        List list = null;
        try {
            String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                    + col
                    + "%' and cpcc.major ='"
                    + major
                    + "' and cp.semester ='"
                    + semester
                    + "' and cpc.idcor ='"
                    + idcor + "' and cpc.corname ='" + corname + "'";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("attach failed", re);
        }
        return list;
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

    /**
     * 删除所有教学计划
     *
     * @return boolean
     */
    public boolean deleteAllCorplan() {
        List corplanList = this.findAll();
        boolean tag = true;
        try {
            getHibernateTemplate().deleteAll(corplanList);
        } catch (RuntimeException re) {
            log.debug("delete All Corplan failed", re);
            tag = false;
        }
        return tag;
    }

    /**
     * 初始化，根据Course自动生成所有的Corplan
     * todo
     */
    public void initAllCorplan(List entities) {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }
}