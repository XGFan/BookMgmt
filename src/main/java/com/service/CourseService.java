package com.service;

import com.bean.course.Course;

import java.util.List;

public interface CourseService extends BaseService<Course> {


    /**
     * 根据学院名称，专业，学期查找所有信息(join)
     *
     * @param col   学院名称
     * @param major 专业名称
     * @param sem   科目安排在哪个学期
     * @return 学院专业在某个学期的科目 map list
     */
    public List findByColMajorSem(String col, String major, String sem);

    /**
     * 根据学院名称，专业，学期查找所有信息(join)
     *
     * @param col   学院名称
     * @param major 专业名称
     * @return 学院专业在某个学期的科目 map list
     */
    public List findByColMajor(String col, String major);


    /**
     * 模糊查找，从学院，专业，科目名查询(join)
     *
     * @param condition 关键字
     * @return 科目信息  list
     */
    public List fuzzyQuery(String condition);

    public String getMagicNum();

    public String add(Course course);
}
