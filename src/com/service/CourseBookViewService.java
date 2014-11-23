package com.service;

import java.util.List;

public interface CourseBookViewService {
    /**
     * 通过教材id查找CourseBookView
     *
     * @param idbk
     * @return CourseBookView map list
     */
    public List findCourseByIdbk(String idbk);

    /**
     * 通过科目id查找CourseBookView
     *
     * @param idcor 科目id
     * @return CourseBookView map list
     */
    public List findByCourse(String idcor);

    /**
     * 查询所有的CourseBookView
     *
     * @return CourseBookView map list
     */
    public List findAllCourse();

    /**
     * 根据科目名称查找课程
     *
     * @param corname 科目名
     * @return CourseBookView map list
     */
    public List findCourseByCorname(String corname);
}
