package com.service;

import com.bean.course.Course;
import com.util.Pagination;

import java.util.List;

public interface CourseService extends BaseService<Course> {

    /**
     * 找到所有的科目信息(join)
     *
     * @return 科目信息 map list
     */
    public List init();

    /**
     * 通过学院名获取科目信息(join)
     *
     * @param col 学院名
     * @return 科目信息 map list
     */
    public List findByCol(String col);

    /**
     * 通过学院名获取科目信息(join)
     * 获取一页信息
     *
     * @param col        学院名称
     * @param pagination 分页信息
     * @return 一页科目信息 map list
     */
    public List findByCol(String col, Pagination pagination);

    /**
     * 通过学院和专业查找科目，专业是模糊查找(join)
     *
     * @param col   学院
     * @param major 专业
     * @return 全部科目信息 map list
     */
    public List findByColMajor(String col, String major);

    /**
     * 通过学院和专业查找科目，专业是模糊查找(join)
     * 获取一页信息
     *
     * @param col        学院
     * @param major      专业
     * @param pagination 分页信息
     * @return 一页信息 map list
     */
    public List findByColMajor(String col, String major, Pagination pagination);

    /**
     * 根据学院，专业，科目进行查找，其中科目名称是模糊查找(join)
     *
     * @param col     学院名称
     * @param major   专业
     * @param corname 科目名称
     * @return 科目信息 map list
     */
    public List findFuzzyByCorName(String col, String major, String corname);

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
     * 查找一页信息
     *
     * @param col        学院名称
     * @param major      专业名称
     * @param sem        科目安排在哪个学期
     * @param pagination 分页信息
     * @return 学院专业在某个学期的科目 一页 map list
     */
    public List findByColMajorSem(String col, String major, String sem,
                                  Pagination pagination);

    /**
     * 模糊查找，从学院，专业，科目名查询(join)
     *
     * @param condition 关键字
     * @return 科目信息  list
     */
    public List fuzzyQuery(String condition);

    /**
     * 模糊查找，从学院，专业，科目名查询(join)
     * 查询一页信息
     *
     * @param condition  关键字
     * @param pagination 分页信息
     * @return 一页科目信息 map list
     */
    public List fuzzyQuery(String condition, Pagination pagination);

    /**
     * 添加一门新科目，并生成课程计划
     *
     * @param idbkStr  教材id
     * @param col      学院名
     * @param major    专业名
     * @param corname  科目名称
     * @param semester 所在学期
     * @return 课程id
     * TODO 细节未注释
     */
    public String addNewCourse(String idbkStr, String col, String major, String corname, String semester);

    /**
     * 添加一门新科目，并生成课程计划
     * todo
     *
     * @param idbkStr  教材id
     * @param col      学院名
     * @param major    专业名
     * @param corname  科目名称
     * @param semester 所在学期
     * @return boolean
     */
    public boolean addNewCourseReturnBoolean(String idbkStr, String col, String major, String corname, String semester);

    /**
     * 更新课程信息
     * <p/>
     * TODO 这个不像是更新科目
     *
     * @deprecated
     */
    public boolean updateCourse(Course course);

    /**
     * 更新课程
     * todo
     *
     * @param course  课程
     * @param idbkStr 选用的教科书
     * @return boolean
     */
    public boolean updateCourse(Course course, String idbkStr);

    /**
     * 删除科目
     *
     * @param idcor 科目id
     * @return boolean
     */
    public boolean deleteCourse(String idcor);

    /**
     * 根据idcor查找科目
     *
     * @param idcor 科目id
     * @return Course object
     */
    public Course getCourseByIdcor(String idcor);

    public String getMagicNum();
}
