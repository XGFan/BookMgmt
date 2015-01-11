package com.service;

import com.bean.cls.ClassInfo;
import com.bean.college.College;

import java.util.List;

public interface ClassService extends BaseService<ClassInfo>{

    /**
     * 根据条件进行模糊查询
     *
     * @param condition 查询条件
     * @return map list
     */
    public List fuzzyFind(String condition);




    /**
     * 根据学院，专业，年级，校区精确查询班级信息
     *
     * @param col    学院
     * @param major  专业
     * @param grade  年纪
     * @param campus 校区
     * @return 查询结果LIST
     */
    public List accurateQuery(String col, String major, String grade,
                              String campus);

    /**
     * 获取所有校区
     *
     * @return string list
     */
    public List getAllCampus();

    /**
     * 获取所有年级
     *
     * @return string list
     */
    public List getAllGrade();

    /**
     * 批量删除班级
     *
     * @param idclses id []
     * @return boolean
     */
    public boolean deleteClasses(String[] idclses);

    public boolean deleteClass(String idcls);

    /**
     * 添加班级
     *
     * @param campus  校区
     * @param grade   年级
     * @param clsnnum 需要添加班级数量
     * @param college 学院
     * @return bool
     */
    public boolean addClasses(String campus, String grade, int clsnnum, College college);

}
