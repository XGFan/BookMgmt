package com.newDao;

import com.bean.cls.ClassInfo;
import com.dao.BaseDao;

import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:19:32
 * Created by guofan on 2015/1/6
 */
public interface ClassDao extends BaseDao<ClassInfo> {
    /**
     * 模糊查找(join 学院专业)
     *
     * @param condition 关键字
     * @return LIST
     */
    List getClassFuzzy(String condition);

    /**
     * 根据若干信息精确查找班级(join 学院专业)
     *
     * @param col    学院
     * @param major  专业
     * @param grade  年级
     * @param campus 校区
     * @return LIST
     */
    List getClassByGradeCampusColMajor(String col, String major, String grade, String campus);

    /**
     * 返回distinct校区
     *
     * @return string list
     */
    List getAllCampus();

    /**
     * 返回distinct年级
     *
     * @return string list
     */
    List getAllGrade();

    /**
     * 获得班号
     * todo
     *
     * @param grade 年级
     * @param idcm  学院专业id
     * @return int班号
     */
    int getClsNum(String grade, String idcm);
}
