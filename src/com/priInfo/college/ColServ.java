package com.priInfo.college;

import java.util.List;
import com.bean.college.College;
import com.util.Pagination;

public interface ColServ {
	/**
	 * query all rows of the college table
	 */
	public List<Object> initCol();

	/**
	 * query the information of the college by the major
	 */
	public List<Object> searchByMajor(String major);

	/**
	 * query the information of the college by the colname
	 */
	public List<Object> searchByCol(String col);

	public List<Object> searchByCol(String col, Pagination pagination);

	/**
	 * delete the college by the idcm;
	 */
	public boolean deleteCollegeById(String idcm);

	/**
	 * update the information of the college
	 */
	public boolean editCol(College col);

	/**
	 * add a row of the college information
	 */
	public boolean saveCol(College col);

	public boolean updateCol(College col);

	public College searchById(String id);

	public List getAllCol();

	public List getMajorByCol(String col);

	public List<College> getCols(String col, String major);
	
	public List<College> getCol(String col, String major);

	public List fuzzyQuery(String condition);

	public List fuzzyQuery(String condition, Pagination pagination);

}
