package com.priInfo.course;

import java.util.*;

import com.bean.book.Book;
import com.bean.book.BookDAO;
import com.bean.college.College;
import com.bean.college.CollegeDAO;
import com.bean.course.Course;
import com.bean.course.CourseDAO;
import com.bean.coursebk.Coursebk;
import com.bean.coursebk.CoursebkDAO;
import com.bean.corplan.Corplan;
import com.bean.corplan.CorplanDAO;
import com.bean.corbook.Corbook;
import com.bean.corbook.CorbookDAO;
import com.util.ConvertUtils;
import com.util.GetPaginationInfo;
import com.util.Pagination;

public class CourseServiceImp implements CourseService {
    private CourseDAO courseDAO;
    private CollegeDAO collegeDAO;
    private CorplanDAO corplanDAO;
    private CorbookDAO corbookDAO;
    private CoursebkDAO coursebkDAO;
    private BookDAO bookDAO;

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public CoursebkDAO getCoursebkDAO() {
        return coursebkDAO;
    }

    public void setCoursebkDAO(CoursebkDAO coursebkDAO) {
        this.coursebkDAO = coursebkDAO;
    }

    public CollegeDAO getCollegeDAO() {
        return collegeDAO;
    }

    public void setCollegeDAO(CollegeDAO collegeDAO) {
        this.collegeDAO = collegeDAO;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public CorplanDAO getCorplanDAO() {
        return corplanDAO;
    }

    public void setCorplanDAO(CorplanDAO corplanDAO) {
        this.corplanDAO = corplanDAO;
    }

    public CorbookDAO getCorbookDAO() {
        return corbookDAO;
    }

    public void setCorbookDAO(CorbookDAO corbookDAO) {
        this.corbookDAO = corbookDAO;
    }


    public List<Object> init() {
        List list = courseDAO.findAll();
        return ConvertUtils.ToCourseList(list);
    }

    public List<Object> init(Pagination pagination) {
        List list = this.init();
        return GetPaginationInfo.getSubList(list, pagination);
    }


    public List<Object> findByCol(String col) {
        List list = ConvertUtils.ToCourseList(courseDAO.getCourseByCol(col));
        return list;
    }

    public List<Object> findByCol(String col, Pagination pagination) {
        List list = this.findByCol(col);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    /**
     * 业务方法，通过专业来查找课程
     */
    public List<Object> findByMajor(String major) {
        List list = ConvertUtils
                .ToCourseList(courseDAO.getCourseByMajor(major));
        return list;
    }

    public List<Object> findByMajor(String major, Pagination pagination) {
        return ConvertUtils.ToCourseList(courseDAO.getCourseByMajor(major));
    }


    public List<Object> findByColMajor(String col, String major) {
        List list = ConvertUtils.ToCourseList(courseDAO.getCourseByColMajor(col, major));
        return list;
    }

    public List<Object> findByColMajor(String col, String major, Pagination pagination) {
        List list = this.findByColMajor(col, major);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    /**
     * 业务方法，通过课程名称获取课程信息
     */
    public List<Object> findByCorName(String corname) {
        List list = ConvertUtils.ToCourseList(courseDAO
                .getCourseByCorname(corname));
        return list;
    }

    public List<Object> findFuzzyByCorName(String col, String major, String corname) {
        List list = courseDAO.getCourseFuzzyByCorname(col, major, corname);
        return ConvertUtils.ToCourseList(list);
    }

    public List<Object> findByCorName(String corname, Pagination pagination) {
        List list = this.findByCorName(corname);
        return GetPaginationInfo.getSubList(list, pagination);
    }


    public List<Object> findByColMajorSem(String col, String major, String sem) {
        List list = ConvertUtils.ToCourseList(courseDAO.getCourseByColMajorSem(col, major, sem));
        return list;
    }

    public List<Object> findByColMajorSem(String col, String major, String sem, Pagination pagination) {
        List list = this.findByColMajorSem(col, major, sem);
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public List<Object> fuzzyQuery(String condition) {
        List list = ConvertUtils.ToCourseList(courseDAO.fuzzyQuery(condition));
        return list;
    }

    public List<Object> fuzzyQuery(String condition, Pagination pagination) {
        List list = ConvertUtils.ToCourseList(courseDAO.fuzzyQuery(condition));
        return GetPaginationInfo.getSubList(list, pagination);
    }

    public String addNewCourse(String idbkStr, String col, String major, String corname, String semester) {
//		System.out.println("*****"+idbkStr+","+col + "," + major + "," + corname + "," + semester);
        /* 条件判断，若有一个信息为空，则返回false */
        if ("".equals(col) || col == null || "".equals(major) || major == null) {
            return "";
        }
        if ("".equals(corname) || corname == null || "".equals(semester)
                || semester == null) {
            return "";
        }
        String[] idbkArray = idbkStr.split(","); // 对多个课程编号进行分割
        for (String idbk : idbkArray) {
            System.out.println("idbk:" + idbk);
        }
		/* 查找学院 */
        List collegeList = new ArrayList();
        collegeList = collegeDAO.getCol(col, major);
        College college = null;
        String idcm = null;
		/* 未查找到相应的学院，返回false */
        if (collegeList.size() == 0)
            return "";
        //**********************************************
        //System.out.println(collegeList.size());
        //**********************************************
		/* 存在相应的学院 */
        if (collegeList.size() > 0) {
            college = (College) collegeList.get(0);
            // collegeList只有一个元素所以使用get(0)取得collegeList中的元素
            idcm = college.getIdcm();
        }
        //System.out.println("starting add a course");
		/* 查找Course,根据学院、专业、课程名称 */
        Course course = new Course(null, college, corname, null, null, null);
        //System.out.println("check if the course is existed");
        // List courseList = courseDAO.findByExample(course);
        List courseList = courseDAO.getCourseByIdcmColMajorCorname(idcm, col,
                major, corname);
		
		/* 不存在该课程，则根据规则来生成 */
        if (courseList.size() == 0) {
            List allCourseList = courseDAO.getCourseByColMajor(col, major);
			/* 从该学院专业的所有课程中取出最后一条课程 */
            //	System.out.println("get the last course of the college");
            //	System.out.println("$$$$$$$"+idcm);
            String serialNum = "";
            if (allCourseList.size() != 0) {
                Object[] temp = (Object[]) allCourseList
                        .get(allCourseList.size() - 1);
				/* 取出该课程 */
                Course tempcor = (Course) temp[0];
				/* 取出它的idcor */
                serialNum = tempcor.getIdcor();
                //		System.out.println(serialNum);
				/* 取出后面的序列号 */
                serialNum = serialNum.substring(serialNum.length() - 4);
                //		System.out.println(serialNum);
				/* 序列号自动加1 */
                Integer num = Integer.parseInt(serialNum) + 1;
                //		System.out.println(num);
				/* 格式化idcor */
                serialNum = idcm + String.format("%04d", num);
                //		System.out.println("serialNum:" + serialNum);
            } else {
                serialNum = idcm + "0001";
            }
		
			/* 为course填充内容 */
            //	System.out.println("generate the idcor of the new course");
            course.setIdcor(serialNum);
            course.setSemester(semester);
			/* 保存到数据库里面 */
			/* 如果idbkArray为空 */
            if (idbkArray[0].equals("")) {
                courseDAO.save(course);
            } else {
                courseDAO.save(course);
                for (String idbk : idbkArray) {
                    Coursebk coursebk = new Coursebk();
                    coursebk.setCourse(course);
                    Book book = new Book();
                    book.setIdbk(idbk);
                    coursebk.setBook(book);
                    coursebkDAO.save(coursebk);
                }
            }
        } else {/* 如果存在该课程，则更新其学期 */
            //		System.out.println("course exist in DB,update the semester!");
			/* 获取idcor */
            Object[] tem = (Object[]) courseList.get(0);
            course = (Course) tem[0];
			/* 设置学期 */
            course.setSemester(semester);
            courseDAO.save(course);
        }

		/* 生成教学计划 */
        Corplan corplan = new Corplan();
        String idcor = course.getIdcor();
        String idcorsem = null;

        corplan.setCourse(course);
        String sem = course.getSemester();
        corplan.setSemester(sem);
		/* 3.根据编码规则生成idcorsem */
        if (semester.length() == 1)
            semester = "0" + semester;
        idcorsem = idcor + semester;
//		System.out.println(idcorsem);
        corplan.setIdcorsem(idcorsem);
        corplanDAO.save(corplan);
//		System.out.println("finished!");
        return course.getIdcor();
    }

    public boolean addNewCourseReturnBoolean(String idbkStr, String col, String major, String corname, String semester) {
        System.out.println(col + "," + major + "," + corname + "," + semester);
		/* 条件判断，若有一个信息为空，则返回false */
        if ("".equals(col) || col == null || "".equals(major) || major == null) {
            return false;
        }
        if ("".equals(corname) || corname == null || "".equals(semester)
                || semester == null) {
            return false;
        }
        String[] idbkArray = idbkStr.split(","); // 对多个课程编号进行分割
        for (String idbk : idbkArray) {
            System.out.println("idbk:" + idbk);
        }
		/* 查找学院 */
        List collegeList = new ArrayList();
        collegeList = collegeDAO.getCol(col, major);
        College college = null;
        String idcm = null;
		/* 未查找到相应的学院，返回false */
        if (collegeList.size() == 0)
            return false;
		/* 存在相应的学院 */
        if (collegeList.size() > 0) {
            college = (College) collegeList.get(0);
            // collegeList只有一个元素所以使用get(0)取得collegeList中的元素
            idcm = college.getIdcm();
        }
        System.out.println("starting add a course");
		/* 查找Course,根据学院、专业、课程名称 */
        Course course = new Course(null, college, corname, null, null, null);
        System.out.println("check if the course is existed");
        // List courseList = courseDAO.findByExample(course);
        List courseList = courseDAO.getCourseByIdcmColMajorCorname(idcm, col,
                major, corname);
		/* 不存在该课程，则根据规则来生成 */
        if (courseList.size() == 0) {
            List allCourseList = courseDAO.getCourseByColMajor(col, major);
			/* 从该学院专业的所有课程中取出最后一条课程 */
            System.out.println("get the last course of the college");
            Object[] temp = (Object[]) allCourseList
                    .get(allCourseList.size() - 1);
			/* 取出该课程 */
            Course tempcor = (Course) temp[0];
			/* 取出它的idcor */
            String serialNum = tempcor.getIdcor();
			/* 取出后面的序列号 */
            serialNum = serialNum.substring(serialNum.length() - 4);
			/* 序列号自动加1 */
            Integer num = Integer.parseInt(serialNum) + 1;
			/* 格式化idcor */
            serialNum = idcm + String.format("%04d", num);
            System.out.println("serialNum:" + serialNum);
			/* 为course填充内容 */
            System.out.println("generate the idcor of the new course");
            course.setIdcor(serialNum);
            course.setSemester(semester);
			/* 保存到数据库里面 */
			/* 如果idbkArray为空 */
            if (idbkArray[0].equals("")) {
                courseDAO.save(course);
            } else {
                courseDAO.save(course);
                for (String idbk : idbkArray) {
                    Coursebk coursebk = new Coursebk();
                    coursebk.setCourse(course);
                    Book book = new Book();
                    book.setIdbk(idbk);
                    coursebk.setBook(book);
                    coursebkDAO.save(coursebk);
                }
            }
        } else {/* 如果存在该课程，则更新其学期 */
            System.out.println("course exist in DB,update the semester!");
			/* 获取idcor */
            Object[] tem = (Object[]) courseList.get(0);
            course = (Course) tem[0];
			/* 设置学期 */
            course.setSemester(semester);
            courseDAO.save(course);
        }

		/* 生成教学计划 */
        Corplan corplan = new Corplan();
        String idcor = course.getIdcor();
        String idcorsem = null;

        corplan.setCourse(course);
        String sem = course.getSemester();
        corplan.setSemester(sem);
		/* 3.根据编码规则生成idcorsem */
        if (semester.length() == 1)
            semester = "0" + semester;
        idcorsem = idcor + semester;
        System.out.println(idcorsem);
        corplan.setIdcorsem(idcorsem);
        corplanDAO.save(corplan);
        System.out.println("finished!");
        return true;
    }

    public boolean updateCourse(Course course) {
        boolean tag = false;
        String idcor = course.getIdcor();
        String idcm = idcor.substring(0, 4); // 取出所编辑课程所对应的idcm进行查重
        String corname = course.getCorname(); // 得到对应课程名
        String semester = course.getSemester(); // 得到学期
        List courseList = courseDAO.getCourseByIdcmCornameSem(idcm, corname, semester);
        if (courseList.size() == 0) {
            tag = courseDAO.save(course);
        }
        return tag;
    }


    public boolean updateCourse(Course course, String idbkStr) {
        boolean tag = false;
        // 若没有选择书本，则返回FALSE
        if (idbkStr == null || course == null || course.getIdcor() == null) {
            tag = false;
        } else {
            String[] idbkArray = idbkStr.split(",");
            if ("".equals(idbkArray[0])) {
                // 如果没有书本信息，仅仅保存课程信息
                courseDAO.save(course);
                tag = true;
            } else {
                courseDAO.save(course);
                for (String idbk : idbkArray) {
                    // 判断这本书是否已经选用教材
                    List courseBkList = coursebkDAO.findByIdcorAndIdbk(course.getIdcor(), idbk);
                    // 已经选用了教材，则不重复保存，若不存在则保存教材选用记录
                    if (courseBkList.size() == 0) {
                        Book book = bookDAO.findById(idbk);
                        Coursebk coursebk = new Coursebk();
                        coursebk.setBook(book);
                        coursebk.setCourse(course);
                        coursebkDAO.save(coursebk);
                    }
                    tag = true;
                }
            }

        }
        return tag;
    }

    /**
     * 删除课程信息
     */
    public boolean deleteCourse(String idcor, String semester) {
        boolean tag = false;
        if (semester.length() == 1)
            semester = "0" + semester;
        String idcorsem = idcor + semester;
        /** 删除corbook数据 **/
        List<Corbook> listcorbook = corbookDAO.findByIdcorsem(idcorsem);
        if (listcorbook.size() > 0) {
            for (Corbook corbook : listcorbook) {
                corbookDAO.delete(corbook);
            }
        }
        /** 删除教学计划数据 **/
        List<Corplan> listcorplan = corplanDAO.findByIdcorsem(idcorsem);
        if (listcorplan.size() > 0) {
            for (Corplan corplan : listcorplan) {
                corplanDAO.delete(corplan);
            }
        }

        Course course = null;
        course = courseDAO.findById(idcor);
        try {
            courseDAO.delete(course);
            tag = true;
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }


    public boolean deleteCourse(String idcor) {
        boolean tag = false;
        Course course = null;
        course = courseDAO.findById(idcor);
        if (course != null) {
            try {
                courseDAO.delete(course);
                tag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tag;
    }

    public Course getCourseByIdcor(String idcor) {
        return courseDAO.findById(idcor);
    }

}
