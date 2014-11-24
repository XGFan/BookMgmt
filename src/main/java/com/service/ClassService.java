package com.service;

import com.bean.cls.ClassInfo;
import com.bean.college.College;
import com.util.Pagination;

import java.util.List;

public interface ClassService {

    /**
     * 根据班级id查找班级
     *
     * @param idcls 班级id
     * @return class obj
     */
    public ClassInfo findById(String idcls);

    /**
     * 根据条件进行模糊查询，并根据分页信息进行返回list
     *
     * @param condition  查询条件
     * @param pagination 分页配置信息
     * @return map list
     */
    public List fuzzyFind(String condition, Pagination pagination);

    /**
     * 根据条件进行模糊查询
     *
     * @param condition  查询条件
     * @return map list
     */
    public List fuzzyFind(String condition);


    /**
     * 获取所有班级,根据分页信息返回一页
     *
     * @param pagination 分页信息
     * @return 一页班级
     */
    public List findAllByPagination(Pagination pagination);

    /**
     * 获取所有班级,根据分页信息返回一页
     *
     * @return 所有班级
     */
    public List findAll();

    /**
     * 根据学院，专业，年级，校区精确查询班级信息,返回一页信息
     *
     * @param col        学院
     * @param major      专业
     * @param grade      年纪
     * @param campus     校区
     * @param pagination 分页信息
     * @return 查询结果LIST
     */
    public List accurateQuery(String col, String major, String grade,
                              String campus, Pagination pagination);

    /**
     * 根据学院，专业，年级，校区精确查询班级信息
     *
     * @param col        学院
     * @param major      专业
     * @param grade      年纪
     * @param campus     校区
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

    /**
     * 修改班级
     *
     * @param cls 班级
     * @return bool
     */
    public boolean updateClass(ClassInfo cls);
}
