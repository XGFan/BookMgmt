package com.printInfo.book.Service;

import java.util.List;

import com.bean.corbook.Corbookview;

public interface CourseBookViewService {
    // 通过课本查课程
    public List<Corbookview> findCourseByIdbk(String idbk);

    /**
     * 通过科目查找课程
     *
     * @param idcor 科目id
     * @return 科目教材 obj list
     */
    public List<Corbookview> findByCourse(String idcor);

    /**
     * 查询所有的课程
     *
     * @return map list
     */
    public List<Corbookview> findAllCourse();

    /**
     * 查询所有的课程
     *
     * @return map list
     */
    public List<Corbookview> findAllBk();

    /**
     * 根据科目名称查找课程
     *
     * @param corname 科目名
     * @return 科目教材视图 obj list
     */
    public List<Corbookview> findCourseByCorname(String corname);
}
