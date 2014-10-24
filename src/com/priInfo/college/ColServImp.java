package com.priInfo.college;

import java.util.List;

import com.util.ConvertUtils;
import com.util.Pagination;
import com.bean.college.College;
import com.bean.college.CollegeDAO;

public class ColServImp implements ColServ {
	private CollegeDAO collegeDAO;

	public CollegeDAO getCollegeDAO() {
		return collegeDAO;
	}

	public void setCollegeDAO(CollegeDAO collegeDAO) {
		this.collegeDAO = collegeDAO;
	}

	/**
	 * delete the college by the idcm;
	 */
	public boolean deleteCollegeById(String idcm) {
		return collegeDAO.deleteById(idcm);
	}

	/**
	 * update the information of the college
	 */
	public boolean editCol(College col) {
		collegeDAO.save(col);
		return false;
	}

	/**
	 * query all rows of the college table
	 */
	public List<Object> initCol() {
		List list = collegeDAO.findAll();
		// return list.subList(0, 9);
		return ConvertUtils.ToCollegeList(list);
	}

	/**
	 * add a row of the college information
	 */
	public boolean saveCol(College col) {
		List list = collegeDAO.getCol(col.getCol(), col.getMajor());
		if (list.size() > 0) {
			// 如果已经存在专业，不添加
			return false;
		} else {
			// 先查找学院是否存在
			List colMajorList = collegeDAO.findByColOrderByIdcm(col.getCol());
			if (colMajorList.size() > 0) {
				// 如果学院存在,获取最后一个专业，生成IDCM
				College lastCol = (College) colMajorList.get(colMajorList
						.size() - 1);
				// 学院代码
				String colCode = lastCol.getIdcm().substring(0, 2);
				// 专业代码
				String majorCode = lastCol.getIdcm().substring(2);
				// 专业代码+1
				majorCode = Integer.parseInt(majorCode) + 1 + "";
				// 如果代码只有1位，前面添加"0"
				if (majorCode.length() == 1) {
					majorCode = "0" + majorCode;
				}
				col.setIdcm(colCode + majorCode);
				collegeDAO.save(col);
				return true;
			} else {
				// 不存在学院，获取所有学院，生成学院代码
				List colList = collegeDAO.findAllOrderByIdcm();
				if (colList.size() > 0) {
					College lastCol = (College) colList.get(colList.size() - 1);
					// 获取学院代码
					// 学院代码
					String colCode = lastCol.getIdcm().substring(0, 2);
					colCode = Integer.parseInt(colCode) + 1 + "";
					// 专业代码
					String majorCode = "01";
					// 如果学院代码1位，前面加“0”
					if (colCode.length() == 1) {
						colCode = "0" + colCode;
					}
					// 设置IDCM
					col.setIdcm(colCode + majorCode);
					collegeDAO.save(col);
					return true;
				}else{
					//数据库中不存在任何专业信息
					col.setIdcm("0101");
					collegeDAO.save(col);
					return true;
				}
			}
		}
	}
	
	public boolean updateCol(College col) {
		return collegeDAO.update(col);
	}

	/**
	 * 精确查询
	 */
	public List<Object> searchByCol(String col) {
		List list = collegeDAO.findByCol(col);
		return ConvertUtils.ToCollegeList(list);
	}

	/**
	 * 精确分页查询
	 */
	public List<Object> searchByCol(String col, Pagination pagination) {
		List list = collegeDAO.findByCol(col);
		// 设置总记录的条数
		pagination.setTotalRecord(list.size());
		if (pagination.getSize() < list.size()) {
			int range = pagination.getStart() + pagination.getSize();
			if (range < list.size()) {
				list = list.subList(pagination.getStart(),
						pagination.getStart() + pagination.getSize());
			} else {
				list = list.subList(pagination.getStart(), list.size());
			}
		}
		return ConvertUtils.ToCollegeList(list);
	}

	public List getAllCol() {
		return collegeDAO.getAllCol();
	}

	public List getMajorByCol(String col) {
		return collegeDAO.getMajorByCol(col);
	}

	/**
	 * query the information of the college by the major
	 */
	public List<Object> searchByMajor(String major) {
		List list = collegeDAO.findByMajor(major);
		return ConvertUtils.ToCollegeList(list);
	}

	/**
	 * 根据ID来获取专业记录
	 */
	public College searchById(String id) {
		return collegeDAO.findById(id);
	}

	public List<College> getCols(String col, String major) {
		List<College> list = collegeDAO.getCol(col, major);
		return list;
	}
	
	public List<College> getCol(String col, String major) {
		List<College> list = collegeDAO.getCol(col, major);
		return ConvertUtils.ToCollegeList(list);
	}

	/**
	 * 模糊查询
	 */
	public List fuzzyQuery(String condition) {
		List list = collegeDAO.fuzzyQuery(condition);
		return ConvertUtils.ToCollegeList(list);
	}

	/**
	 * 模糊分页查询
	 */
	public List fuzzyQuery(String condition, Pagination pagination) {

		List list = collegeDAO.fuzzyQuery(condition);
		// 设置总记录的条数
		pagination.setTotalRecord(list.size());
		if (pagination.getSize() < list.size()) {
			int range = pagination.getStart() + pagination.getSize();
			if (range < list.size()) {
				list = list.subList(pagination.getStart(),
						pagination.getStart() + pagination.getSize());
			} else {
				list = list.subList(pagination.getStart(), list.size());
			}
		}
		return ConvertUtils.ToCollegeList(list);
	}
}
