package com.dao;

import com.bean.corplan.Corplan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.corplan.Corplan
 */
@Repository("corplanDAO")
public class CorplanDAO extends BaseDaoImp<Corplan> {
    private static final Log log = LogFactory.getLog(CorplanDAO.class);


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
        return getCurrentSession().createQuery(queryString).list();
    }

    /**
     * 连接教学计划，课程，学院三张表，根据学院，专业查询教学计划
     * 三张表连接后字段（idcorsem,idcor,semeter,idcm,corname,col,major,semnum）
     *
     * @param col      学院名
     * @param major    专业名
     * @return list
     */
    public List getCorplanByColMajorSem(String col, String major) {
        String queryString = "from Corplan cp join cp.course cpc join cpc.college cpcc where cpcc.col like '%"
                + col
                + "%' and cpcc.major ='"
                + major
                + "'" ;
        return getCurrentSession().createQuery(queryString).list();
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
            list = getCurrentSession().createQuery(queryString).list();
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
        return getCurrentSession().createQuery(queryString).list();
    }

    /**
     * 删除所有教学计划
     *
     * @return boolean
     */
    public boolean deleteAllCorplan() {
        List<Corplan> corplanList = this.getAll();
        boolean tag = true;
        try {
            for(Corplan u:corplanList) {
                delete(u);
            }
        } catch (RuntimeException re) {
            log.debug("delete All Corplan failed", re);
            tag = false;
        }
        return tag;
    }

    /**
     * 初始化，根据Course自动生成所有的Corplan
     * @param entities 所有 course 列表
     */
    public void initAllCorplan(List entities) {
        getCurrentSession().saveOrUpdate(entities);
    }


    public List getByIdcor(String idcor) {
        String queryString = "from Corplan where idcor = '" + idcor + "'";
        return getCurrentSession().createQuery(queryString).list();
    }
}