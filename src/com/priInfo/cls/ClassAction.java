package com.priInfo.cls;

import java.util.Iterator;
import java.util.List;
import com.bean.cls.Class;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.college.College;
import com.opensymphony.xwork2.ActionSupport;
import com.priInfo.college.ColServ;
import com.util.Pagination;
import com.util.SendData;

public class ClassAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ClassService classService;
	private ColServ collegeService;
	private String condition;
	private Pagination pagination;
	private String col;
	private String major;
	private String grade;
	private String campus;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ClassService getClassService() {
		return classService;
	}

	public void setClassService(ClassService classService) {
		this.classService = classService;
	}

	public String list() {
		List clslist = classService.findAll();
		if (pagination == null)
			pagination = new Pagination(9);
		pagination.setSize(9);
		pagination.setCurrentPage(1);
		clslist = classService.findAllByPagination(pagination);
		clslist.add(pagination);
		SendData.send(clslist);
		return null;
	}

	public String fuzzyQuery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		if (pagination == null)
			pagination = new Pagination(9);
		pagination.setSize(9);
		pagination.setCurrentPage(1);
		pagination.setCurrentPage(currentPage);
		// System.out.println(currentPage);
		List clslist = classService.fuzzyFind(condition, pagination);
		clslist.add(pagination);
		// System.out.println(clslist.size());
		SendData.send(clslist);
		return null;
	}

	public String accurateQuery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		if (pagination == null)
			pagination = new Pagination(9);
		pagination.setSize(9);
		pagination.setCurrentPage(currentPage);
		if (col == null || col.equals("请选择")) {
			col = "";
		}
		if (grade == null || grade.equals("请选择")) {
			grade = "";
		}
		if (major == null || major.equals("请选择")) {
			major = "";
		}
		if (campus == null || campus.equals("请选择")) {
			campus = "";
		}

		List clslist = classService.accurateQuery(col, major, grade, campus,
				pagination);
		clslist.add(pagination);
		SendData.send(clslist);
		return null;
	}

	public List getAllCol() {
		List list = collegeService.getAllCol();
		SendData.send(list);
		return null;
	}

	public List getAllCampus() {
		List list = classService.getAllCampus();
		SendData.send(list);
		return null;
	}

	public List getAllGrade() {
		List list = classService.getAllGrade();
		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i));
		}
		SendData.send(list);
		return null;
	}

	public String getMajorByCol() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String col = request.getParameter("col");
		List list = null;
		if ("----全部----".equals(col) || "请选择".equals(col))
			list = collegeService.getMajorByCol("");
		else
			list = collegeService.getMajorByCol(col);
		SendData.send(list);
		return null;
	}

	public ColServ getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(ColServ collegeService) {
		this.collegeService = collegeService;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String deleteClasses() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String idclses = request.getParameter("idclses");
		String[] idclsid = idclses.split(","); // 以'，'划分，存储在数组中
		boolean result = classService.deleteClasses(idclsid);
		SendData.send(result);
		return null;
	}

	public String addClasses() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String addcampus = request.getParameter("addcampus");
		String addgrade = request.getParameter("addgrade");
		int addclsnnum = Integer.parseInt(request.getParameter("addclsnnum"));
		String addcol = request.getParameter("addcol");
		String addmajor = request.getParameter("addmajor");
		College addcollege = (College) collegeService.getCols(addcol,
				addmajor).get(0);
		boolean result = classService.addClasses(addcampus, addgrade,
				addclsnnum, addcollege);
		SendData.send(result);
		return null;
	}

	public String editClass() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ccampus = request.getParameter("ccampus");
		String tempid = request.getParameter("tempid");
		String stunum = request.getParameter("stunum");
		Class cls = classService.findById(tempid);
		if (ccampus != null && !ccampus.equals(""))
			cls.setCampus(ccampus);
		if (stunum != null && !stunum.equals("")) {
			int stunums = Integer.parseInt(request.getParameter("stunum"));
			cls.setStunum(stunums);
		}
		boolean result = classService.editClasses(cls);
		SendData.send(result);
		return null;
	}
}
