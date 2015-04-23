package com.service;

import com.bean.college.College;

import java.util.List;

public interface CollegeService extends BaseService<College> {

    /**
     * 根据学院名和专业名来查找
     * @param col 学院名
     * @param major 专业名
     * @return College obj
     */
    public College getCollege(String col, String major);


    /**
     * 根据学院专业ID来删除学院专业信息
     *
     * @param idcm 学院专业id
     * @return boolean
     */
    public boolean deleteCollegeById(String idcm);


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
    public List getCol(String col, String major);


    /**
     * 通过学院或者专业来模糊查找
     *
     * @param condition 关键字
     * @return map list
     */
    public List fuzzyQuery(String condition);

    public  String getNewID(String col);

}
