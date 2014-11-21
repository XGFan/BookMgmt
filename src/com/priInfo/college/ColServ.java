package com.priInfo.college;

import java.util.List;

import com.bean.college.College;
import com.util.Pagination;

public interface ColServ {
    /**
     * 找到所有学院专业信息，以map形式的list返回
     *
     * @return
     */
    public List<Object> initCol();

    /**
     * query the information of the college by the major
     */
    public List<Object> searchByMajor(String major);

    /**
     * 通过学院精确查找专业班级信息
     *
     * @param col 学院
     * @return map形式的list列表，包含所有
     */
    public List<Object> searchByCol(String col);

    /**
     * 通过学院精确查找专业班级信息，根据分页信息返回一页信息
     *
     * @param col        学院
     * @param pagination 分页信息
     * @return 以map形式的list返回一页信息
     */
    public List<Object> searchByCol(String col, Pagination pagination);

    /**
     * delete the college by the idcm;
     */
    public boolean deleteCollegeById(String idcm);

    /**
     * update the information of the college
     */
    public boolean editCol(College col);

    /**
     * 添加学院专业信息
     *
     * @param col 学院专业obj
     * @return boolean
     */
    public boolean saveCol(College col);

    /**
     * 更新col实例
     *
     * @param col
     * @return boolean
     */
    public boolean updateCol(College col);

    public College searchById(String id);

    /**
     * 获取所有的学院
     *
     * @return LIST
     */
    public List getAllCol();

    /**
     * 根据学院获取所有专业
     *
     * @param col 学院名
     * @return 专业 obj LIST
     */
    public List getMajorByCol(String col);

    /**
     * 根据学院,专业来获取学院专业记录
     *
     * @param col   学院名
     * @param major 专业名
     * @return 学院专业信息 obj list
     */
    public List<College> getCols(String col, String major);

    /**
     * 根据学院名称和专业名称获取记录
     *
     * @param col   学院名
     * @param major 专业名
     * @return 学院专业map list
     */
    public List<College> getCol(String col, String major);

    /**
     * 通过学院或者专业来模糊查找
     *
     * @param condition 关键字
     * @return 以map形式保存的list
     */
    public List fuzzyQuery(String condition);

    /**
     * 根据关键字进行查找，并通过分页信息返回一页map形式的list
     *
     * @param condition  关键词
     * @param pagination 分页信息
     * @return
     */
    public List fuzzyQuery(String condition, Pagination pagination);

}
