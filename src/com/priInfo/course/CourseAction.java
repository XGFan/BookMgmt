package com.priInfo.course;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.corbook.Corbookview;
import com.bean.course.*;
import com.opensymphony.xwork2.ActionSupport;
import com.priInfo.college.ColServ;
import com.printInfo.book.Service.CourseBookViewService;
import com.util.*;

public class CourseAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    /* fields of CourseAction */
    private CourseService courseService;
    private ColServ collgeService;
    private Pagination pagination;
    private CourseBookViewService courseBookViewService;

    public CourseBookViewService getCourseBookViewService() {
        return courseBookViewService;
    }

    public void setCourseBookViewService(CourseBookViewService courseBookViewService) {
        this.courseBookViewService = courseBookViewService;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public ColServ getCollgeService() {
        return collgeService;
    }

    public void setCollgeService(ColServ collgeService) {
        this.collgeService = collgeService;
    }

    /**
     * 返回第一页的课程信息
     *
     * @return null
     */
    public String list() {
        List list = courseService.init();
        if (pagination == null)
            pagination = new Pagination(8);
        pagination.setSize(8);
        pagination.setCurrentPage(1);
        list = courseService.findByCol("", pagination);
        SendData.send(list);
        return null;
    }

    /**
     * 通过学院、专业、学期获取课程信息 精确查询
     *
     * @return null
     */
    public String accurateQuery() {
        try {
            /* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            int currentPage = Integer.parseInt(request
                    .getParameter("currentPage"));
            String col = request.getParameter("col");
            String major = request.getParameter("major");
            String semester = request.getParameter("semester");
            boolean hasCol = !(col == null || "".equals(col)
                    || "----全部----".equals(col) || "----请选择----".equals(col));
            boolean hasMajor = !(major == null || "".equals(major)
                    || "----全部----".equals(major) || "--请选择--".equals(major));
            boolean hasSem = !(semester == null || "".equals(semester)
                    || "----全部----".equals(semester) || "--请选择--"
                    .equals(semester));
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            pagination.setCurrentPage(currentPage);

            List corlist = null;
            // System.out.println("学院:"+hasCol);
            // System.out.println("专业:"+hasMajor);
            // System.out.println("学期:"+hasSem);
            if (!hasCol & !hasMajor) {
				/* 学院和专业为全部，获取所有课程 */
                corlist = courseService.findByCol("", pagination);
            }
            if (!hasCol & hasMajor & !hasSem) {
				/* 学院为和学期为全部，获取该专业所有课程 */
                corlist = courseService.findByColMajor("", major, pagination);
            }
            if (!hasCol & hasMajor & hasSem) {
				/* 学院为为全部，获取该专业某学期所有课程 */
                corlist = courseService.findByColMajorSem("", major, semester,
                        pagination);
            }
            if (hasCol && !hasMajor) {
				/* 专业为全部，获取该学院所有课程 */
                corlist = courseService.findByCol(col, pagination);
            }
            if (hasCol && hasMajor && !hasSem) {
				/* 学期为全部，获取该学院专业所有学期课程 */
                corlist = courseService.findByColMajor(col, major, pagination);
            }
            if (hasCol && hasMajor && hasSem) {
				/* 获取某专业某一学期的课程 */
                corlist = courseService.findByColMajorSem(col, major, semester,
                        pagination);
            }
            SendData.send(corlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 精确查询的获取分页信息
     *
     * @return null
     */
    public String getAccPagination() {
        try {
			/* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            String col = request.getParameter("col");
            String major = request.getParameter("major");
            String semester = request.getParameter("semester");
			/* 设置总页面数目,总记录数量 */
            boolean hasCol = !(col == null || "".equals(col)
                    || "----全部----".equals(col) || "----请选择----".equals(col));
            boolean hasMajor = !(major == null || "".equals(major)
                    || "----全部----".equals(major) || "--请选择--".equals(major));
            boolean hasSem = !(semester == null || "".equals(semester)
                    || "----全部----".equals(semester) || "--请选择--"
                    .equals(semester));
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            int totalRecord = 0;
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            if (!hasCol & !hasMajor) {
				/* 学院为和专业为全部，获取所有课程 */
                totalRecord = courseService.findByCol("").size();
            }
            if (!hasCol & hasMajor & !hasSem) {
				/* 学院为和学期为全部，获取该专业所有课程 */
                totalRecord = courseService.findByColMajor("", major).size();
            }
            if (!hasCol & hasMajor & hasSem) {
				/* 学院为为全部，获取该专业某学期所有课程 */
                totalRecord = courseService.findByColMajorSem("", major,
                        semester).size();
            }
            if (hasCol && !hasMajor) {
				/* 专业为全部，获取该学院所有课程 */
                totalRecord = courseService.findByCol(col).size();
            }
            if (hasCol && hasMajor && !hasSem) {
				/* 学期为全部，获取该学院专业所有学期课程 */
                totalRecord = courseService.findByColMajor(col, major).size();
            }
            if (hasCol && hasMajor && hasSem) {
				/* 获取某专业某一学期的课程 */
                totalRecord = courseService.findByColMajorSem(col, major,
                        semester).size();
            }
            pagination.setTotalRecord(totalRecord);
            // System.out.println("totalPage:" + pagination.getTotalPage());
            // System.out.println("totalRecord:" + pagination.getTotalRecord());
            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 模糊查询
     *
     * @return null
     */
    public String fuzzyQuery() {
        try {
			/* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            String condition = request.getParameter("condition");
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setCurrentPage(currentPage);

            List corlist;
			/* 学院为和专业为全部，获取所有课程 */
            corlist = courseService.fuzzyQuery(condition, pagination);
            SendData.send(corlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取模糊查询的分页信息
     */
    public String getfuzzyPagination() {
        try {
			/* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            String condition = request.getParameter("condition");
            int totalRecord = 0;
            if (pagination == null)
                pagination = new Pagination(6);
			/* 获取某专业某一学期的课程 */
            totalRecord = courseService.fuzzyQuery(condition).size();
            pagination.setTotalRecord(totalRecord);
            // System.out.println("totalPage:" + pagination.getTotalPage());
            // System.out.println("totalRecord:" + pagination.getTotalRecord());
            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据学院，专业，课程进行查找，其中课程是模糊查找
     * 返回的不是一页数据，而是全部数据
     *
     * @return null
     */
    public String fuzzyQueryByCorname() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String col = request.getParameter("col");
        String major = request.getParameter("major");
        String corname = request.getParameter("corname");

        boolean hasCol = !(col == null || "".equals(col)
                || "----全部----".equals(col) || "--请选择--".equals(col));
        boolean hasMajor = !(major == null || "".equals(major)
                || "----全部----".equals(major) || "--请选择--".equals(major));
        if (!hasCol || !hasMajor) {
            SendData.send(null);
            return null;
        }
        List list;
        list = courseService.findFuzzyByCorName(col, major, corname);
        SendData.send(list);
        return null;
    }

    /**
     * 添加新的课程，可能会用到多本书
     */
    public String addNewCourse() {
        HttpServletRequest request = ServletActionContext.getRequest();
		/* 从页面获取即将添加的课程的相关参数 */
        String idbkStr = request.getParameter("idbkStr");
        String col = request.getParameter("col");
        String major = request.getParameter("major");
        String corname = request.getParameter("corname");
        String semester = request.getParameter("semester");
        String idcor;
        idcor = courseService.addNewCourse(idbkStr, col, major, corname, semester);
        Map map = new HashMap();
        map.put("idcor", idcor);
        List result = new ArrayList();
        result.add(map);
        SendData.send(result);
        return null;
    }

    /**
     * 更新课程信息
     */
    public String updateCourse() {
        HttpServletRequest request = ServletActionContext.getRequest();
		/* 从页面获取即将添加的课程的相关参数 */
        String idcor = request.getParameter("idcor");
        String idbkStr = request.getParameter("idbkStr");
        String corname = request.getParameter("corname");
        String semester = request.getParameter("semester");

        // System.out.println(idcor + "," + col + "," + major + "," + corname
        // + "," + semester);
        boolean tag = false;
        boolean hasIdcor = !(idcor == null || "".equals(idcor));
        boolean hasCorname = !(corname == null || "".equals(corname));
        boolean hasSemester = !(semester == null || "".equals(semester));
        // 如果这三个参数任何一个为空，提示更新失败！
        if (!hasIdcor || !hasCorname || !hasSemester) {
            tag = false;
        } else {
            // 从数据库中取出course
            Course course = courseService.getCourseByIdcor(idcor);
            if (course != null) {
                // 重新设置课程名称和学期
                course.setCorname(corname);
                course.setSemester(semester);
                tag = courseService.updateCourse(course, idbkStr);
            }
        }
        Result result = new Result(tag);
        SendData.send(result);
        return null;
    }

    /**
     * 删除科目
     *
     * @return null
     */
    public String deleteCourse() {
        HttpServletRequest request = ServletActionContext.getRequest();
		/* 从页面获取即将添加的课程的相关参数 */
        String idcor = request.getParameter("idcor");
        boolean hasIdcor = !(idcor == null || "".equals(idcor));
        boolean tag = false;
        if (hasIdcor) {
            tag = courseService.deleteCourse(idcor);
        }
        Result result = new Result(tag);
        SendData.send(result);
        return null;
    }

    /**
     * 所有课程
     *
     * @return null
     */
    public List<Corbookview> findAll() {
        List<Corbookview> list;
        list = courseBookViewService.findAllCourse();
        SendData.send(list);
        return null;
    }

    /**
     * 所有课程
     *
     * @return null
     * @deprecated
     */
    public List<Corbookview> findAllBook() {
        List<Corbookview> list;
        list = courseBookViewService.findAllBk();
        SendData.send(list);
        return null;
    }

    /**
     * 通过科目id查找课程
     *
     * @return null
     */
    public List<CourseBookViewService> findByCourse() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idcor = request.getParameter("idcor");
        List<Corbookview> list;
        list = courseBookViewService.findByCourse(idcor);
        SendData.send(list);
        return null;
    }

    /**
     * 根据科目名查找课程
     *
     * @return null
     */
    public List<Corbookview> findByCorname() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String corname = request.getParameter("corname");
        List<Corbookview> list;
        list = courseBookViewService.findCourseByCorname(corname);
        SendData.send(list);
        return null;
    }
}
