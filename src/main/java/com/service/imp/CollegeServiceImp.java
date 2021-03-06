package com.service.imp;

import com.bean.college.College;
import com.dao.CollegeDAO;
import com.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public College getCollege(String col, String major) {
        List list = collegeDAO.getCol(col, major);
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            return (College) (list.get(0));
        }
    }


    public List getAllColName() {
        return collegeDAO.getAllCol();
    }

    public List getMajorNameByCol(String col) {
        return collegeDAO.getMajorByCol(col);
    }

    public List getCol(String col, String major) {
        return collegeDAO.getCol(col, major);
    }

    public List fuzzyQuery(String condition) {
        return collegeDAO.fuzzyQuery(condition);
    }

    public String getNewID(String col){
        String hql = "select idcm from College where col = '" + col + "'";
        List idStr =  collegeDAO.findByHql(hql);
        int num = 0;
        for (Object s : idStr) {
            int temp = Integer.valueOf((String)s);
            num = num<temp?temp:num;
        }
        return String.format("%04d", num + 1);
    }

}
