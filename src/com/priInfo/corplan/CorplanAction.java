package com.priInfo.corplan;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.bean.college.*;
import com.bean.course.*;
import com.opensymphony.xwork2.ActionSupport;
import com.priInfo.college.ColServ;
import com.priInfo.course.CourseService;
import com.util.*;

public class CorplanAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	/*课程安排Service*/
	private CorplanService corplanService;
    /*课程信息Service*/
	private CourseService courseService;
    /*学院专业信息Service*/
	private ColServ collgeService;

	/* setters and getters of the fields */
	public CorplanService getCorplanService() {
		return corplanService;
	}

	public void setCorplanService(CorplanService corplanService) {
		this.corplanService = corplanService;
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
     * 获取所有的科目
     * @return
     */
	public String list() {
		List list = courseService.init();
		list = courseService.findByCol("");
		SendData.send(list);
		return null;
	}

	/**
	 * 通过学院、专业、学期获取课程信息 精确查询
	 * @return null
	 */
	public String accurateCorplanQuery() {
		try {
			/* get the Servlet API */
			HttpServletRequest request = ServletActionContext.getRequest();
			/* get the parameters sent from the browser */
			String col = request.getParameter("col");
			String major = request.getParameter("major");
			String semester = request.getParameter("semester");
			/* check null and blank of the parameters */
			boolean hasCol = !(col == null || "".equals(col)
					|| "----全部----".equals(col) || "--请选择--".equals(col));
			boolean hasMajor = !(major == null || "".equals(major)
					|| "----全部----".equals(major) || "--请选择--".equals(major));
			boolean hasSem = !(semester == null || "".equals(semester)
					|| "----全部----".equals(semester) || "--请选择--"
					.equals(semester));
			//System.out.println(col + "," + major + "," + semester);
			/* init the corplanlist */
			List corplanList = null;
			/* fetch the corplan data from the service layer */
			if (hasCol && hasMajor && hasSem) {
				corplanList = corplanService.findCorplanByColMajorSem(col,major, semester);
			}
			/* response the data to the client */
			SendData.send(corplanList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过学院、专业、学期获取科目信息 精确查询
	 * @return null
	 */
	public String accurateCourseQuery() {
		try {
			/* get the Servlet API */
			HttpServletRequest request = ServletActionContext.getRequest();
			/* get the parameters from the browser */
			String col = request.getParameter("col");
			String major = request.getParameter("major");
			String semester = request.getParameter("semester");
			// semester = "--请选择--";
			/* check null and blank */
			boolean hasCol = !(col == null || "".equals(col)
					|| "----全部----".equals(col) || "--请选择--".equals(col));
			boolean hasMajor = !(major == null || "".equals(major)
					|| "----全部----".equals(major) || "--请选择--".equals(major));
			boolean hasSemester = !(semester == null || "".equals(semester)
					|| "----全部----".equals(semester) || "--请选择--"
					.equals(semester));
			//System.out.println(col + "," + major + "," + semester);
			/* init corplan list */
			List corplanList = null;
			/*
			 * get corplan data from the db through the parameters given by
			 * client
			 */
			if (!hasCol & !hasMajor) {
				/* 学院为和专业为全部，获取所有课程 */
				corplanList = courseService.findByCol("");
			}
			if (!hasCol & hasMajor) {
				/* 学院为和学期为全部，获取该专业所有课程 */
				corplanList = courseService.findByColMajor("", major);
			}
			if (hasCol && !hasMajor) {
				/* 专业为全部，获取该学院所有课程 */
				corplanList = courseService.findByCol(col);
			}
			if (hasCol && hasMajor) {
				/* 学期为全部，获取该学院专业所有学期课程 */
				corplanList = courseService.findByColMajor(col, major);
			}

			if (hasCol && hasMajor && hasSemester) {
				corplanList = courseService.findByColMajorSem(col, major,
						semester);
			}
			/* send data to the browser */
			SendData.send(corplanList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 模糊查询教学计划
	 * @return
	 */
	public String fuzzyQuery() {
		try {
			/* 获取页面端传递的参数 */
			HttpServletRequest request = ServletActionContext.getRequest();
			String condition = request.getParameter("condition");

			List corplanList = null;
			/* 学院为和专业为全部，获取所有课程 */
			corplanList = corplanService.fuzzyFind(condition);
			SendData.send(corplanList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 添加一门新的课程
     * @return null
     */
	public String addNewCourse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		/* 从页面获取即将添加的课程的相关参数 */
		String col = request.getParameter("col");
		String major = request.getParameter("major");
		String corname = request.getParameter("corname");
		String semester = request.getParameter("semester");
		/*
		 * invoke the method of courseService ,add a new course and a corplan to
		 * the database
		 */
		boolean tag = courseService.addNewCourseReturnBoolean(null, col, major, corname,semester);
		Result result = new Result(tag);
		/* response the result to the client */
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
		String col = request.getParameter("col");
		String major = request.getParameter("major");
		String corname = request.getParameter("corname");
		String semester = request.getParameter("semester");

		//System.out.println(idcor + "," + col + "," + major + "," + corname+ "," + semester);
		boolean tag = false;
		List list = collgeService.getCols(col, major); // 获取学院信息
		if (list.size() > 0) {
			College clg = (College) list.get(0);
			Course course = new Course(idcor, clg, corname, semester, null,null);
			tag = courseService.updateCourse(course); // 更新科目信息
		}
		Result result = new Result(tag);
		SendData.send(result);

		return null;
	}

    /**
     * 将某一课程加入到某专业某一学期的教学计划中去
     * @return null
     */
	public String addNewCorplan() {
		HttpServletRequest request = ServletActionContext.getRequest();
		/* 从页面获取即将添加的课程的相关参数 */
		String col = request.getParameter("col");
		String major = request.getParameter("major");
		String corname = request.getParameter("corname");
		String semester = request.getParameter("semester");
		boolean tag = false;
		/* invoke the method of corplanService to add or update a corplan */
		tag = corplanService.updateCorplan(col, major, corname, semester); // 更新教学计划
		Result result = new Result(tag);
		/* return the result of the process to the client */
		SendData.send(result);
		return null;
	}

	/*
	 * public void delete(){ HttpServletRequest request =
	 * ServletActionContext.getRequest(); String col =
	 * request.getParameter("col"); String major =
	 * request.getParameter("major"); String semester =
	 * request.getParameter("semester"); String idcor =
	 * request.getParameter("idcor"); String corname =
	 * request.getParameter("corname"); boolean hasCol = !(col == null ||
	 * "".equals(col) || "----全部----".equals(col) || "--请选择--".equals(col));
	 * boolean hasMajor = !(major == null || "".equals(major) ||
	 * "----全部----".equals(major) || "--请选择--".equals(major)); boolean hasSem =
	 * !(semester == null || "".equals(semester) ||
	 * "----全部----".equals(semester) || "--请选择--" .equals(semester)); boolean
	 * hasCor = !(corname == null || "".equals(corname)); boolean hasIdcor =
	 * !(idcor == null || "".equals(idcor)); boolean tag = false; if (hasCol &&
	 * hasMajor && hasSem && hasCor && hasIdcor){ tag =
	 * corplanService.delete(corname); } System.out.println(tag); Result result
	 * = new Result(tag); SendData.send(result); }
	 */
	/* delete a corplan judged by col,major,semester,idcor */

	/**
	 * 删除某专业某学期的教学计划中的一项
	 * @return null
	 */
	public String deleteCorplan() {
		/* get the Servlet API */
		HttpServletRequest request = ServletActionContext.getRequest();
		/* get the parameters from the browser */
		String col = request.getParameter("col");
		String major = request.getParameter("major");
		String semester = request.getParameter("semester");
		String idcor = request.getParameter("idcor");
		String corname = request.getParameter("corname");
		//System.out.println(col + "," + major + "," + corname + "," + semester+ "," + idcor);
		/* check null and blank */
		boolean hasCol = !(col == null || "".equals(col)
				|| "----全部----".equals(col) || "--请选择--".equals(col));
		boolean hasMajor = !(major == null || "".equals(major)
				|| "----全部----".equals(major) || "--请选择--".equals(major));
		boolean hasSem = !(semester == null || "".equals(semester)
				|| "----全部----".equals(semester) || "--请选择--".equals(semester));
		boolean hasCor = !(corname == null || "".equals(corname));
		boolean hasIdcor = !(idcor == null || "".equals(idcor));
		boolean tag = false;
		if (hasCol && hasMajor && hasSem && hasCor && hasIdcor) {
			tag = corplanService.deleteCorplan(col, major, semester, idcor,
					corname);
		}
		//System.out.println(tag);
		Result result = new Result(tag);
		SendData.send(result);
		return null;
	}

    /**
     * 清空教学计划
     * @return null
     */
	public String dropAllCorplan() {
		/* drop all the corplan data */
		corplanService.dropAllCorplan();
		boolean tag = true;
		Result result = new Result(tag);
		SendData.send(result);
		return null;
	}

    /**
     * 初始化教学计划
     * @return
     */
	public String initCorplan() {
		/* initialize the corplan based on the course table */
		corplanService.initCorplan();
		boolean tag = true;
		Result result = new Result(tag);
		SendData.send(result);
		return null;
	}
}
