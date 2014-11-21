package com.printInfo.book.Service;

import java.util.List;
import java.util.Map;

import com.bean.book.Book;
import com.bean.corbook.Corbookview;
import com.bean.corbook.CorbookviewId;
import com.bean.course.Course;

public interface CourseBookViewService {
	// 通过课本查课程
	public List<Corbookview> findCourseByIdbk(String idbk);

    /**
     * 通过科目查找课程
     * @param idcor
     * @return
     */
	public List<Corbookview> findByCourse(String idcor);

	// 重写findByCourse方法，返回list类型
	public List<Corbookview> findByCourseList(String idcor);

	public List<Corbookview> searchall();

	/* 全执行修改 */
	public boolean modify(Book book, List<Course> course);

    /**
     * 查询所有的课程
     * @return map list
     */
	public List<Corbookview> findAllCourse();

    /**
     * 查询所有的课程
     * @return map list
     */
	public List<Corbookview> findAllBk();

    /**
     * 根据科目名称查找课程
     * @param corname
     * @return
     */
	public List<Corbookview> findCourseByCorname(String corname);

	/* 添加课程，查询教材 */
	public List<Corbookview> findBookByBkname(String bkname);
}
