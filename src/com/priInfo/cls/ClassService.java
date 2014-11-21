package com.priInfo.cls;

import java.util.List;

import com.bean.cls.Class;
import com.bean.college.College;
import com.util.Pagination;

public interface ClassService {
    /**
     * 查询所有班级
     *
     * @return
     */
    public List findAll();

    /**
     * 通过班级编号查询班级信息
     *
     * @param idcls
     * @return
     */
    public Class findById(String idcls);

    /**
     * 根据条件进行模糊查询，并根据分页信息进行返回list
     *
     * @param condition  查询条件(LIKE)
     * @param pagination 分页配置信息
     * @return list
     */
    public List fuzzyFind(String condition, Pagination pagination);

    /**
     * 根据分页信息获取所有班级
     *
     * @param pagination
     * @return
     */
    public List findAllByPagination(Pagination pagination);

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
     * 获取所有校区
     *
     * @return
     */
    public List getAllCampus();

    /**
     * 获取所有年级
     *
     * @return
     */
    public List getAllGrade();

    /**
     * 批量删除班级
     *
     * @param idclses id LIST
     * @return
     */
    public boolean deleteClasses(String[] idclses);

    /**
     * 添加班级
     *
     * @param campus  校区
     * @param grade   年级
     * @param clsnnum 班号
     * @param college 学院
     * @return bool
     */
    public boolean addClasses(String campus, String grade, int clsnnum, College college);

    /**
     * 针对校区和人数的修改
     *
     * @param cls 班级
     * @return bool
     */
    public boolean editClasses(Class cls);
}
