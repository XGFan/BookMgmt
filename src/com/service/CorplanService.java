package com.service;

import java.util.List;

public interface CorplanService {

    /**
     * 根据学院，专业，所在学期查询教学计划
     *
     * @param col      学院名称
     * @param major    专业名称
     * @param semester 所在学期
     * @return 教学计划 map list
     */
    public List findCorplanByColMajorSem(String col, String major, String semester);

    /**
     * 连接教学计划，科目，学院三张表
     * 根据输入条件（学院or专业or学期）模糊查询教学计划
     *
     * @param condition 关键字
     * @return 教学计划 map list
     */
    public List fuzzyFind(String condition);

    /**
     * 这个从函数名来说是更新，但是内容不是更新，更像是新保存
     * todo
     * @param col      学院名
     * @param major    专业名
     * @param corname  科目名
     * @param semester 学期
     * @return boolean
     */
    public boolean updateCorplan(String col, String major, String corname, String semester);

    /**
     * 删除教学计划
     * todo
     * @param col      学院名
     * @param major    专业名
     * @param semester 学期
     * @param idcor    科目id
     * @param corname  科目名
     * @return boolean
     */
    public boolean deleteCorplan(String col, String major, String semester, String idcor, String corname);

    /**
     * 初始化教学计划表，重新生成，根据课程信息
     * todo
     */
    public void initCorplan();

    /**
     * 删除所有教学计划
     */
    public boolean dropAllCorplan();

}
