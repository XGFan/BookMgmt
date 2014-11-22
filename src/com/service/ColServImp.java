package com.service;

import com.bean.college.College;
import com.dao.CollegeDAO;
import com.util.ConvertUtils;
import com.util.Pagination;

import java.util.List;

public class ColServImp implements ColServ {
    private CollegeDAO collegeDAO;

    public CollegeDAO getCollegeDAO() {
        return collegeDAO;
    }

    public void setCollegeDAO(CollegeDAO collegeDAO) {
        this.collegeDAO = collegeDAO;
    }

    public boolean deleteCollegeById(String idcm) {
        return collegeDAO.deleteById(idcm);
    }

    public List<Object> initCol() {
        List list = collegeDAO.findAll();
        return ConvertUtils.ToCollegeList(list);
    }

    public boolean saveCol(College col) {
        List list = collegeDAO.getCol(col.getCol(), col.getMajor());
        boolean tag = false;
        if (list.size() > 0) {
            // 如果已经存在专业，不添加
            tag = false;
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
                if (collegeDAO.save(col))
                    tag = true;
            } else {
                // 不存在学院，获取所有学院，生成学院代码
                List colList = collegeDAO.findAllOrderByIdcm();
                if (colList.size() > 0) {
                    College lastCol = (College) colList.get(colList.size() - 1);
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
                    if (collegeDAO.save(col))
                        tag = true;
                } else {
                    //数据库中不存在任何专业信息
                    col.setIdcm("0101");
                    if (collegeDAO.save(col))
                        tag = true;
                }
            }
        }
        return tag;
    }

    public boolean updateCol(College col) {
        return collegeDAO.update(col);
    }

    public List<Object> searchByCol(String col) {
        List list = collegeDAO.findByCol(col);
        return ConvertUtils.ToCollegeList(list);
    }

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

    public List getAllColName() {
        return collegeDAO.getAllCol();
    }

    public List getMajorNameByCol(String col) {
        return collegeDAO.getMajorByCol(col);
    }

    public List<College> getCols(String col, String major) {
        return collegeDAO.getCol(col, major);
    }

    public List getCol(String col, String major) {
        List<College> list = collegeDAO.getCol(col, major);
        return ConvertUtils.ToCollegeList(list);
    }

    public List fuzzyQuery(String condition) {
        List list = collegeDAO.fuzzyQuery(condition);
        return ConvertUtils.ToCollegeList(list);
    }

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
