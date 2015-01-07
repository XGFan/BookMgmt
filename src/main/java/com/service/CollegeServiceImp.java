package com.service;

import com.bean.college.College;
import com.dao.CollegeDAO;
import com.util.GetPaginationInfo;
import com.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.util.ConvertUtils.ToCollegeList;

@Service("collegeService")
public class CollegeServiceImp extends BaseServiceTemplate<College> implements CollegeService {
    @Autowired
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

    public List initCol() {
        List list = collegeDAO.getAll();
        return ToCollegeList(list);
    }

    public boolean save(College col) {
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

    public List searchByCol(String col) {
        List list = collegeDAO.findByPropertyF("col",col);
        return ToCollegeList(list);
    }

    @Override
    public College getCollege(String col, String major) {
        List list = collegeDAO.getCol(col, major);
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            return (College) (list.get(0));
        }
    }

    public List searchByCol(String col, Pagination pagination) {
        List list = collegeDAO.findByPropertyF("col", col);
        return ToCollegeList(GetPaginationInfo.getSubList(list, pagination));
    }

    public List getAllColName() {
        return collegeDAO.getAllCol();
    }

    public List getMajorNameByCol(String col) {
        return collegeDAO.getMajorByCol(col);
    }

    public List getColObj(String col, String major) {
        return collegeDAO.getCol(col, major);
    }

    public List getCol(String col, String major) {
        List list = collegeDAO.getCol(col, major);
        return ToCollegeList(list);
    }

    public List fuzzyQuery(String condition) {
        List list = collegeDAO.fuzzyQuery(condition);
        return ToCollegeList(list);
    }

    public List fuzzyQuery(String condition, Pagination pagination) {
        List list = collegeDAO.fuzzyQuery(condition);
        return ToCollegeList(GetPaginationInfo.getSubList(list, pagination));
    }
}
