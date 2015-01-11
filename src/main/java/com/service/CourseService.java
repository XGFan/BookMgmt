package com.service;

import com.bean.course.Course;
import com.util.Pagination;

import java.util.List;

public interface CourseService extends BaseService<Course> {

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 通过学院名获取科目信息(join)
//     *
//     * @param col 学院名
//     * @return 科目信息 map list
//     */
//    public List findByCol(String col);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 通过学院名获取科目信息(join)
//     * 获取一页信息
//     *
//     * @param col        学院名称
//     * @param pagination 分页信息
//     * @return 一页科目信息 map list
//     */
//    public List findByCol(String col, Pagination pagination);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 通过学院和专业查找科目，专业是模糊查找(join)
//     *
//     * @param col   学院
//     * @param major 专业
//     * @return 全部科目信息 map list
//     */
//    public List findByColMajor(String col, String major);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 通过学院和专业查找科目，专业是模糊查找(join)
//     * 获取一页信息
//     *
//     * @param col        学院
//     * @param major      专业
//     * @param pagination 分页信息
//     * @return 一页信息 map list
//     */
//    public List findByColMajor(String col, String major, Pagination pagination);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 根据学院，专业，科目进行查找，其中科目名称是模糊查找(join)
//     *
//     * @param col     学院名称
//     * @param major   专业
//     * @param corname 科目名称
//     * @return 科目信息 map list
//     */
//    public List findFuzzyByCorName(String col, String major, String corname);
// --Commented out by Inspection STOP (2015/1/11 3:11)

    /**
     * 根据学院名称，专业，学期查找所有信息(join)
     *
     * @param col   学院名称
     * @param major 专业名称
     * @param sem   科目安排在哪个学期
     * @return 学院专业在某个学期的科目 map list
     */
    public List findByColMajorSem(String col, String major, String sem);

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 根据学院名称，专业，学期查找所有信息(join)
//     * 查找一页信息
//     *
//     * @param col        学院名称
//     * @param major      专业名称
//     * @param sem        科目安排在哪个学期
//     * @param pagination 分页信息
//     * @return 学院专业在某个学期的科目 一页 map list
//     */
//    public List findByColMajorSem(String col, String major, String sem,
//                                  Pagination pagination);
// --Commented out by Inspection STOP (2015/1/11 3:11)

    /**
     * 模糊查找，从学院，专业，科目名查询(join)
     *
     * @param condition 关键字
     * @return 科目信息  list
     */
    public List fuzzyQuery(String condition);

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 模糊查找，从学院，专业，科目名查询(join)
//     * 查询一页信息
//     *
//     * @param condition  关键字
//     * @param pagination 分页信息
//     * @return 一页科目信息 map list
//     */
//    public List fuzzyQuery(String condition, Pagination pagination);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 添加一门新科目，并生成课程计划
//     *
//     * @param idbkStr  教材id
//     * @param col      学院名
//     * @param major    专业名
//     * @param corname  科目名称
//     * @param semester 所在学期
//     * @return 课程id
//     * TODO 细节未注释
//     */
//    public String addNewCourse(String idbkStr, String col, String major, String corname, String semester);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 添加一门新科目，并生成课程计划
//     * todo
//     *
//     * @param idbkStr  教材id
//     * @param col      学院名
//     * @param major    专业名
//     * @param corname  科目名称
//     * @param semester 所在学期
//     * @return boolean
//     */
//    public boolean addNewCourseReturnBoolean(String idbkStr, String col, String major, String corname, String semester);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 更新课程信息
//     * <p/>
//     * TODO 这个不像是更新科目
//     *
//     * @deprecated
//     */
//    public boolean updateCourse(Course course);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 更新课程
//     * todo
//     *
//     * @param course  课程
//     * @param idbkStr 选用的教科书
//     * @return boolean
//     */
//    public boolean updateCourse(Course course, String idbkStr);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 删除科目
//     *
//     * @param idcor 科目id
//     * @return boolean
//     */
//    public boolean deleteCourse(String idcor);
// --Commented out by Inspection STOP (2015/1/11 3:11)

// --Commented out by Inspection START (2015/1/11 3:11):
//    /**
//     * 根据idcor查找科目
//     *
//     * @param idcor 科目id
//     * @return Course object
//     */
//    public Course getCourseByIdcor(String idcor);
// --Commented out by Inspection STOP (2015/1/11 3:11)

    public String getMagicNum();
}
