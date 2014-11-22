package com.priInfo.corplan;

import java.util.*;

import com.bean.college.College;
import com.bean.college.CollegeDAO;
import com.bean.corbook.*;
import com.bean.corplan.*;
import com.bean.course.Course;
import com.bean.course.CourseDAO;
import com.util.ConvertUtils;

public class CorplanServiceImp implements CorplanService {
    private CorplanDAO corplanDAO;
    private CourseDAO courseDAO;
    private CollegeDAO collegeDAO;
    private CorbookDAO corbookDAO;

    public CorbookDAO getCorbookDAO() {
        return corbookDAO;
    }

    public void setCorbookDAO(CorbookDAO corbookDAO) {
        this.corbookDAO = corbookDAO;
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

    public List findCorplanByColMajorSem(String col, String major, String semester) {
        List list = corplanDAO.getCorplanByColMajorSem(col, major, semester);
        return ConvertUtils.ToCorplanList(list);
    }

    public List fuzzyFind(String condition) {
        List list = corplanDAO.getCorplanFuzzy(condition);
        return ConvertUtils.ToCorplanList(list);
    }

    public boolean updateCorplan(String col, String major, String corname, String semester) {
        /* 条件判断，若有一个信息为空，则返回false */
        if (col == null || "".equals(col) || major == null || "".equals(major)) {
            return false;
        }
        if (corname == null || "".equals(corname) || semester == null
                || "".equals(semester)) {
            return false;
        }
		/* 1.查找学院 */
        List collegeList;
        collegeList = collegeDAO.getCol(col, major);
        College college = null;
        String idcm = null;
		/* 1.1未查找到相应的学院，返回false */
        if (collegeList.size() == 0)
            return false;
		/* 1.2存在相应的学院 */
        if (collegeList.size() > 0) {
            college = (College) collegeList.get(0);
            // System.out.println(college.toString());
            idcm = college.getIdcm();
        }
        // System.out.println("The College is finded!" + idcm);
		/* 2.查找Course,根据学院、专业、课程名称 */
        // String sem=semester;
        Course course = new Course(null, college, corname, semester, null, null);
        // System.out.println("The Course is builded!");
        List courseList = courseDAO.getCourseByIdcmColMajorCorname(idcm, col, major, corname);
        // List courseList = courseDAO.findByExample(course);
        // System.out.println("课程数量" + courseList.size());
		/* 2.1不存在该课程，则根据规则来生成 */
        // if (courseList.size() == 0) {
        // List tempCourseList = null;
        // tempCourseList = courseDAO.getCourseByColMajor(col, major);
        // /* 从该学院专业的所有课程中取出最后一条课程 */
        // Object[] temp = (Object[]) tempCourseList
        // .get(tempCourseList.size() - 1);
        // /* 取出该课程 */
        // Course tempcor = (Course) temp[0];
        // /* 取出它的idcor */
        // String serialNum = tempcor.getIdcor();
        // System.out.println("The Course is serialNum!"+serialNum);
        // /* 取出后面的序列号 */
        // serialNum = serialNum.substring(serialNum.length() - 4);
        // System.out.println("The Course is serialNum000!"+serialNum);
        // /* 序列号自动加1 */
        // Integer num = Integer.parseInt(serialNum) + 1;
        // System.out.println("The Course is serialNum11111!"+num);
        // /* 格式化idcor */
        // serialNum = idcm + String.format("%04d", num);
        // System.out.println("idcorqqqqqqqqqq"+serialNum );
        // /* 为course填充内容 */
        // course.setIdcor(serialNum);
        // //semester=course.getSemester();
        // course.setSemester(semester);
        // System.out.println("学期00"+course.getSemester());
        // /* 保存到数据库里面 */
        // System.out.println("3333333333333"+"addNewCourse:" +
        // course.toString());
        // //courseDAO.save(course);
        // }
		/* 2.2如果存在该课程，则更新其学期 */
        if (courseList.size() > 0) {
			/* 获取idcor */
            // course = (Course) courseList.get(0);
            Object[] tem = (Object[]) courseList.get(0);
            course = (Course) tem[0];
			/* 设置学期 */
            course.setSemester(semester);
            courseDAO.save(course);

            Corplan corplan = new Corplan();
            String idcor = course.getIdcor();
            String idcorsem;

            corplan.setCourse(course);
            corplan.setSemester(semester);

			/* 3.根据编码规则生成idcorsem */
            if (semester.length() == 1)
                semester = "0" + semester;
            idcorsem = idcor + semester;
            corplan.setIdcorsem(idcorsem);

            corplanDAO.save(corplan);
        }

        // Corplan corplan = new Corplan();
        // String idcor = course.getIdcor();
        // System.out.println("hehe!haha"+idcor);
        // String idcorsem = null;
        //
        // corplan.setCourse(course);
        // //String sem = course.getSemester();
        // //corplan.getSemester();
        // corplan.setSemester(semester);
        //
        // //corplan.getSemester();
        // //corplan.setSemester(semester);
        // /* 3.根据编码规则生成idcorsem */
        // if (semester.length() == 1)
        // semester = "0" + semester;
        // idcorsem = idcor + semester;
        // System.out.println("hehe!haha!heiehi"+idcorsem);
        // corplan.setIdcorsem(idcorsem);
        //
        // corplanDAO.save(corplan);
        return true;
    }

    public boolean deleteCorplan(String col, String major, String semester,String idcor, String corname) {
		/* query the corplan from the database */
        Corplan corplan;
        List corplanList;
        // System.out.println(col + "," + major + "," + semester + "," + idcor+
        // "," + corname);
        corplanList = corplanDAO.getCorplanByColMajorSemCornameIdcor(col,
                major, semester, corname, idcor);
        // System.out.println("corplanList.size:" + corplanList.size());
        if (corplanList.size() > 0) {
			/* delete the corplan */
            Object temp[] = (Object[]) corplanList.get(0);
            corplan = (Corplan) temp[0];

            // 删除教学计划后，删除对应的课程以及课程-课本记录
            // List li = corplanDAO.getCorplanByIdcorSemester(idcor, semester);
            // Object temp2[] = (Object[]) li.get(0);
            // Corplan cor = (Corplan) temp2[0];
            // List<Corbook> list =
            // corbookDAO.findByIdcorsem(cor.getIdcorsem());
            // for (Corbook corbook0 : list) {
            // corbookDAO.delete(corbook);
            // }

            corplanDAO.delete(corplan);

            return true;
        } else
            return false;
    }

    public void initCorplan() {
		/* initialize course list */
        List courseList;
		/* get all course info from the course talbe */
        courseList = courseDAO.findAll();
		/* initialize the corplan list */
        List corplanList = new ArrayList();
		/* get the iterator of the course list */
        /* iterator the coruse list */
        for (Object aCourseList : courseList) {
            /* get one object array from the course list */
            Object obj[] = (Object[]) aCourseList;
			/* get the course from the object array */
            Course course = (Course) obj[0];
			/* create a new corplan */
            Corplan corplan = new Corplan();

			/* get the idcor field from a course */
            String idcor = course.getIdcor();
			/* get the semester from a course */
            String semester = course.getSemester();
            String idcorsem;
			/* set the course field of the corplan object */
            corplan.setCourse(course);
			/* set the semester field of the corplan object */
            corplan.setSemester(semester);
			/* judge the length of the semester */
            if (semester.length() == 1)
                semester = "0" + semester;
			/*
			 * generate the idcorsem based on the principle of the idcorsem
			 * generator
			 */
            idcorsem = idcor + semester;
			/* set the primary key of the corplan object */
            corplan.setIdcorsem(idcorsem);
			/* add one corplan to the corplanlist */
            corplanList.add(corplan);
        }
		/* sava all the corplan in the corplan list to database */
        corplanDAO.initAllCorplan(corplanList);

    }

    public void dropAllCorplan() {
        corplanDAO.deleteAllCorplan();
    }
}
