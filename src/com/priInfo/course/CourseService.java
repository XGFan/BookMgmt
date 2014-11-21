package com.priInfo.course;

import java.util.List;

import com.bean.course.*;
import com.util.Pagination;

public interface CourseService {

    /**
     * 找到所有的课程信息
     * @return 课程信息map list
     */
	public List<Object> init();

    /**
     * 业务方法，通过学院名称获取课程信息
     * @param col 学院名称
     * @return 课程信息 map list
     */
	public List<Object> findByCol(String col);

    /**
     * 根据分页信息和学院名称，获取一页课程信息
     * @param col 学院名称
     * @param pagination 分页信息
     * @return 一页课程信息 map list
     */
	public List<Object> findByCol(String col, Pagination pagination);

	/**
	 * 通过专业来查找课程
	 */
	public List<Object> findByMajor(String major);

	public List<Object> findByMajor(String major, Pagination pagination);

	/**
	 * 通过学院、专业来获取课程
	 * @param col 学院
	 * @param major 专业
	 * @return 全部课程信息 map list
	 */
	public List<Object> findByColMajor(String col, String major);

    /**
     * 通过学院、专业来获取一页课程
     * @param col 学院
     * @param major 专业
     * @param pagination 分页信息
     * @return 一页课程信息 map list
     */
	public List<Object> findByColMajor(String col, String major,
			Pagination pagination);

	/**
	 * 通过课程名称来查找课程
	 * 
	 * @return
	 */
	public List<Object> findByCorName(String corname);

	public List<Object> findByCorName(String corname, Pagination pagination);

    /**
     * 根据学院，专业，课程进行查找，其中课程是模糊查找
     * @param col 学院名称
     * @param major 专业
     * @param corname 课程名称
     * @return 课程信息 map list
     */
	public List<Object> findFuzzyByCorName(String col, String major,String corname);

    /**
     * 根据学院名称，专业，学期查找所有信息
     * @param col 学院名称
     * @param major 专业名称
     * @param sem 课程安排在哪个学期
     * @return 学院专业在某个学期的课程 map list
     */
	public List<Object> findByColMajorSem(String col, String major, String sem);

    /**
     * 根据学院名称，专业，学期，分页信息 查找一页信息
     * @param col 学院名称
     * @param major 专业名称
     * @param sem 课程安排在哪个学期
     * @param pagination 分页信息
     * @return 学院专业在某个学期的课程 一页 map list
     */
	public List<Object> findByColMajorSem(String col, String major, String sem,
			Pagination pagination);

    /**
     * 模糊查找，从学院，专业，课程名查询
     * @param condition 关键字
     * @return 课程 obj list
     */
	public List<Object> fuzzyQuery(String condition);

    /**
     * 模糊查找，从学院，专业，课程名查询一页信息
     * @param condition 关键字
     * @param pagination 分页信息
     * @return 一页课程 map list
     */
	public List<Object> fuzzyQuery(String condition, Pagination pagination);

    /**
     * 添加一门新课程，并生成课程计划
     * @param idbkStr 教科书id
     * @param col 学院
     * @param major 专业
     * @param corname 课程名称
     * @param semester 课程所在学期
     * @return 课程id
     * @TODO 细节未注释
     */
	public String addNewCourse(String idbkStr, String col, String major,String corname, String semester);

	public boolean addNewCourseReturnBoolean(String idbkStr, String col, String major,
			String corname, String semester);

	/**
	 * 更新课程信息
	 */
	public boolean updateCourse(Course course);

    /**
     * 更新课程
     * @param course 课程
     * @param idbkStr 选用的教科书
     * @return boolean
     */
	public boolean updateCourse(Course course,String idbkStr);

	/**
	 * 删除课程信息
	 **/
	public boolean deleteCourse(String idcor, String semester);

    /**
     * 删除科目
     * @param idcor 科目id
     * @return boolean
     */
	public boolean deleteCourse(String idcor);

    /**
     * 根据idcor查找课程
     * @param idcor 课程id？
     * @return Course object
     */
	public Course getCourseByIdcor(String idcor);
}
