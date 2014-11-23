package com.service;

import com.bean.college.College;
import com.util.Pagination;

import java.util.List;

public interface ColServ {
    /**
     * 找到所有学院专业信息
     *
     * @return 学院专业信息 map list
     */
    public List initCol();

    /**
     * 通过学院名精确查找专业班级信息
     *
     * @param col 学院名
     * @return 学院专业信息 map list
     */
    public List searchByCol(String col);

    /**
     * 通过学院精确查找专业班级信息，根据分页信息返回一页信息
     *
     * @param col        学院名
     * @param pagination 分页信息
     * @return 以map形式的list返回一页信息
     */
    public List searchByCol(String col, Pagination pagination);

    /**
     * 根据学院专业ID来删除学院专业信息
     *
     * @param idcm 学院专业id
     * @return boolean
     */
    public boolean deleteCollegeById(String idcm);

    /**
     * 添加学院专业信息
     *
     * @param col 学院专业obj
     * @return boolean
     */
    public boolean saveCol(College col);

    /**
     * 更新学院专业实例
     *
     * @param col 学院专业实例
     * @return boolean
     */
    public boolean updateCol(College col);

    /**
     * 获取所有的学院名
     *
     * @return 学院名 string  LIST
     */
    public List getAllColName();

    /**
     * 根据学院名获取其专业名
     *
     * @param col 学院名
     * @return 专业名 string LIST
     */
    public List getMajorNameByCol(String col);

    /**
     * 根据学院名,专业名来获取学院专业记录
     *
     * @param col   学院名
     * @param major 专业名
     * @return 学院专业信息 obj list
     */
    public List getCols(String col, String major);

    /**
     * 根据学院名称和专业名称获取记录
     *
     * @param col   学院名
     * @param major 专业名
     * @return 学院专业信息  map list
     */
    public List getCol(String col, String major);

    /**
     * 通过学院或者专业来模糊查找
     *
     * @param condition 关键字
     * @return map list
     */
    public List fuzzyQuery(String condition);

    /**
     * 通过学院或者专业来模糊查找，根据分页信息分页
     *
     * @param condition  关键词
     * @param pagination 分页信息
     * @return 一页 map list
     */
    public List fuzzyQuery(String condition, Pagination pagination);

}
