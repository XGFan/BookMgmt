package com.priInfo.course;

import com.bean.course.Course;
import com.util.Pagination;

import java.util.List;

public interface CourseService {

    /**
     * 找到所有的科目信息
     *
     * @return 科目信息 map list
     */
    public List<Object> init();

    /**
     * 业务方法，通过学院名称获取科目信息
     *
     * @param col 学院名称
     * @return 科目信息 map list
     */
    public List<Object> findByCol(String col);

    /**
     * 根据分页信息和学院名称，获取一页科目信息
     *
     * @param col        学院名称
     * @param pagination 分页信息
     * @return 一页科目信息 map list
     */
    public List<Object> findByCol(String col, Pagination pagination);

    /**
     * 通过学院、专业来获取科目
     *
     * @param col   学院
     * @param major 专业
     * @return 全部科目信息 map list
     */
    public List<Object> findByColMajor(String col, String major);

    /**
     * 通过学院、专业来获取一页科目
     *
     * @param col        学院
     * @param major      专业
     * @param pagination 分页信息
     * @return 一页科目信息 map list
     */
    public List<Object> findByColMajor(String col, String major,
                                       Pagination pagination);

    /**
     * 根据学院，专业，科目名称进行查找，其中科目名称是模糊查找
     *
     * @param col     学院名称
     * @param major   专业
     * @param corname 科目名称
     * @return 科目信息 map list
     */
    public List<Object> findFuzzyByCorName(String col, String major, String corname);

    /**
     * 根据学院名称，专业，学期查找所有信息
     *
     * @param col   学院名称
     * @param major 专业名称
     * @param sem   科目安排在哪个学期
     * @return 学院专业在某个学期的科目 map list
     */
    public List<Object> findByColMajorSem(String col, String major, String sem);

    /**
     * 根据学院名称，专业，学期，分页信息 查找一页信息
     *
     * @param col        学院名称
     * @param major      专业名称
     * @param sem        科目安排在哪个学期
     * @param pagination 分页信息
     * @return 学院专业在某个学期的科目 一页 map list
     */
    public List<Object> findByColMajorSem(String col, String major, String sem,
                                          Pagination pagination);

    /**
     * 模糊查找，从学院，专业，科目名查询
     *
     * @param condition 关键字
     * @return 科目信息 obj list
     */
    public List<Object> fuzzyQuery(String condition);

    /**
     * 模糊查找，从学院，专业，科目名查询一页信息
     *
     * @param condition  关键字
     * @param pagination 分页信息
     * @return 一页科目信息 map list
     */
    public List<Object> fuzzyQuery(String condition, Pagination pagination);

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
     * 根据idcor查找课程
     *
     * @param idcor 课程id？
     * @return Course object
     */
    public Course getCourseByIdcor(String idcor);
}
