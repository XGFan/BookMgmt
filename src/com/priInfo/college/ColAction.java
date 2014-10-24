package com.priInfo.college;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.util.Pagination;
import com.util.Result;
import com.util.SendData;
import com.bean.college.College;

public class ColAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

    /*专业id*/
	private String idcm;
    /*学院名称*/
	private String col;
    /*专业名称*/
	private String major;
    /*TODO*/
	private ColServ csv;
    /*分页配置信息*/
	private Pagination pagination;

	public String getIdcm() {
		return idcm;
	}

	public void setIdcm(String idcm) {
		this.idcm = idcm;
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

	public ColServ getCsv() {
		return csv;
	}

	public void setCsv(ColServ csv) {
		this.csv = csv;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * 初始化数据，显示所有的院系专业
	 * 
	 * @return
	 */
	public String list() {
		List collist = csv.initCol();
		if (pagination == null)
			pagination = new Pagination(6);
		pagination.setSize(6);
		pagination.setCurrentPage(1);
		collist = csv.searchByCol("", pagination);
		SendData.send(collist);
		return null;
	}

	/**
	 * 精确查询主方法
	 * 
	 * @return
	 */
	public String accurateQuery() {
		try {
			List collist = csv.initCol();
			/* 获取客户端封装的数据 */
			if (pagination == null)
				pagination = new Pagination(6);
			pagination.setSize(6);
			/* 设置总页面数目,总记录数量 */
			// int totalRecord = csv.searchByCol(col).size();
			// pagination.setTotalRecord(totalRecord);

			/* 从页面获取参数，包括学院，第几页 */
			HttpServletRequest request = ServletActionContext.getRequest();
			String col = request.getParameter("col");
			int currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
			pagination.setCurrentPage(currentPage);
			/* 使用分页查询 */
			if ("----全部----".equals(col) || "--请选择--".equals(col))
				collist = csv.searchByCol("", pagination);
			else
				collist = csv.searchByCol(col, pagination);
			/* 将查询的数据返回到客户端 */
			SendData.send(collist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 精确查询的分页数据获取
	 * */
	public String accPageQuery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String col = request.getParameter("col");

		if (pagination == null)
			pagination = new Pagination(6);
		/* 设置总页面数目,总记录数量 */
		int totalRecord = 0;
		if ("----全部----".equals(col) || "--请选择--".equals(col))
			totalRecord = csv.searchByCol("").size();
		else
			totalRecord = csv.searchByCol(col).size();
		pagination.setTotalRecord(totalRecord);

		// if(pagination.getCurrentPage()<=0)
		// pagination.setCurrentPage(1);
		// if(pagination.getTotalPage()!=0&&pagination.getCurrentPage()>pagination.getTotalPage())
		// pagination.setCurrentPage(pagination.getTotalPage());

		// System.out.println("totalPage:" + pagination.getTotalPage());
		// System.out.println("totalRecord:" + pagination.getTotalRecord());
		SendData.send(pagination);

		return null;
	}

	/**
	 * 根据学院或者专业来模糊查询的主方法
	 * 
	 * @return
	 */
	public String fuzzyQuery() {
		try {
			List collist = null;
			/* 获取客户端封装的数据 */
			if (pagination == null)
				pagination = new Pagination(6);
			pagination.setSize(6);

			/* 从页面获取参数，包括学院，第几页 */
			HttpServletRequest request = ServletActionContext.getRequest();
			String condition = request.getParameter("condition");
			int currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
			pagination.setCurrentPage(currentPage);
			/* 使用分页查询 */
			collist = csv.fuzzyQuery(condition, pagination);
			/* 将查询的数据返回到客户端 */
			SendData.send(collist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 模糊查询的分页数据获取
	 * 
	 * @return
	 */
	public String fuzzyPageQuery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String condition = request.getParameter("condition");

		if (pagination == null)
			pagination = new Pagination(6);
		/* 设置总页面数目,总记录数量 */
		int totalRecord = csv.fuzzyQuery(condition).size();
		pagination.setTotalRecord(totalRecord);

		// System.out.println("totalPage:" + pagination.getTotalPage());
		// System.out.println("totalRecord:" + pagination.getTotalRecord());
		SendData.send(pagination);

		return null;
	}

	/**
	 * 获取所有的学院
	 * 
	 * @return null
	 */
	public String getAllCol() {
		List list = csv.getAllCol();
		SendData.send(list);
		return null;
	}

	/**
	 * 根据学院来获取专业
	 * 
	 * @return null
	 */
	public String getMajorByCol() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String col = request.getParameter("col");
		List list = null;
		if ("----全部----".equals(col) || "--请选择--".equals(col)) {
			list = csv.getMajorByCol("");
		} else {
			list = csv.getMajorByCol(col);
		}
		SendData.send(list);
		return null;
	}

	/**
	 * 根据学院专业精确获取
	 * 
	 * @return
	 */
	public String getColByColMajor() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String col = request.getParameter("col");
		String major = request.getParameter("major");

		List list = null;

		if ("--请选择--".equals(col) || "----全部----".equals(col))
			list = csv.getCol("", major);
		else
			list = csv.getCol(col, major);
		SendData.send(list);
		return null;
	}

	/**
	 * 根据获取过来的参数来更新专业信息
	 * 
	 * @return
	 */
	public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String idcm = request.getParameter("idcm");
		String col = request.getParameter("col");
		String major = request.getParameter("major");
		Integer semnum = Integer.parseInt(request.getParameter("semnum"));
		College college = new College(idcm, col, major, semnum);
		Boolean tag = false;// 定义一个变量用于标识是否修改成功
		tag = csv.updateCol(college);// 返回是否修改成功
		Result result = new Result(tag);
		SendData.send(result);// 将数据发送到服务器端
		return null;
	}

	public String addMajor() {
		/* 从客户端获取参数 */
		HttpServletRequest request = ServletActionContext.getRequest();
		String col = request.getParameter("col");
		String major = request.getParameter("major");
		Integer semnum = Integer.parseInt(request.getParameter("semnum"));
		College college = new College(col, major, semnum);
		boolean tag = csv.saveCol(college);
		Result result = new Result(tag);
		SendData.send(result);
		return null;
	}

	public String deleteCollege() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String idcm = request.getParameter("idcm");
		boolean tag = csv.deleteCollegeById(idcm);
		Result result = new Result(tag);
		SendData.send(result);
		return null;
	}

	public String query() {
		csv.searchByCol(col);
		csv.searchByMajor(major);
		return "query";
	}

}
