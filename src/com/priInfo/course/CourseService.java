package com.priInfo.course;

import java.util.List;

import com.bean.course.*;
import com.util.Pagination;

public interface CourseService {
	/**
	 * 初始化所有课程
	 * 
	 * @return
	 */
	public List<Object> init();

	/**
	 * 通过学院来查找课程
	 */
	public List<Object> findByCol(String col);

	public List<Object> findByCol(String col, Pagination pagination);

	/**
	 * 通过专业来查找课程
	 */
	public List<Object> findByMajor(String major);

	public List<Object> findByMajor(String major, Pagination pagination);

	/**
	 * 通过学院、专业来获取课程
	 * 
	 * @param col
	 * @param major
	 * @return
	 */
	public List<Object> findByColMajor(String col, String major);

	public List<Object> findByColMajor(String col, String major,
			Pagination pagination);

	/**
	 * 通过课程名称来查找课程
	 * 
	 * @return
	 */
	public List<Object> findByCorName(String corname);

	public List<Object> findByCorName(String corname, Pagination pagination);

	public List<Object> findFuzzyByCorName(String col, String major,
			String corname);

	/**
	 * 通过学院、专业、学期来查找课程
	 * 
	 * @return
	 */
	public List<Object> findByColMajorSem(String col, String major, String sem);

	public List<Object> findByColMajorSem(String col, String major, String sem,
			Pagination pagination);

	/**
	 * 模糊查询
	 * 
	 * @return
	 */
	public List<Object> fuzzyQuery(String condition);

	public List<Object> fuzzyQuery(String condition, Pagination pagination);

	/**
	 * 添加一门课程
	 */
	public String addNewCourse(String idbkStr, String col, String major,
			String corname, String semester);
	public boolean addNewCourseReturnBoolean(String idbkStr, String col, String major,
			String corname, String semester);

	/**
	 * 更新课程信息
	 */
	public boolean updateCourse(Course course);
	public boolean updateCourse(Course course,String idbkStr);

	/**
	 * 删除课程信息
	 **/
	public boolean deleteCourse(String idcor, String semester);
	public boolean deleteCourse(String idcor);

	public Course getCourseByIdcor(String idcor);
}
