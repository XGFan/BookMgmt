package com.service.imp;

import com.bean.book.Book;
import com.bean.college.College;
import com.bean.corplan.Corplan;
import com.bean.course.Course;
import com.bean.coursebk.Coursebk;
import com.dao.*;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImp extends BaseServiceTemplate<Course> implements CourseService {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private CollegeDAO collegeDAO;
    @Autowired
    private CorplanDAO corplanDAO;
    @Autowired
    private CoursebkDAO coursebkDAO;
    @Autowired
    private BookDAOImp bookDAO;


    public List findByColMajorSem(String col, String major, String sem) {
        return courseDAO.getCourseByColMajorSem(col, major, sem);
    }


    public List fuzzyQuery(String condition) {
        return courseDAO.fuzzyQuery(condition);
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
        List collegeList;
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
        //System.out.println("starting save a course");
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
            String serialNum;
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
        String idcorsem;

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
        List collegeList;
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
        System.out.println("starting save a course");
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
        String idcorsem;

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

    public boolean deleteCourse(String idcor) {
        boolean tag = true;
        Course course;
        course = courseDAO.findById(idcor);
        if (course != null) {
            try {
                courseDAO.delete(course);
            } catch (Exception e) {
                e.printStackTrace();
                tag = false;
            }
        }
        return tag;
    }

    public String getMagicNum(){
        String hql = "select MAX( idcor % 10000) from Course";
        int num =  (Integer) courseDAO.findByHql(hql).get(0);
        return String.format("%04d",num+1);
    }

}
