package com.service;

import com.bean.courclass.Courclass;

import java.io.File;
import java.util.List;
import java.util.Map;
public interface BookPurchaseService {

    /**
     * 根据上下学期来获得书单
     * @param semester 学期
     * @return list
     */
    public List getbklist(String semester);

    /**
     * 课程-书-学期-供应商表 根据idbk从Bookcorsup中查找
     * @param idbk idbk
     * @return list
     */
    public List getcorlistbyidbk(String idbk);


    /**
     * 获取在某一供应商处购买的图书列表，2014.3.23-zhagnchi *
     * @return list
     */
    public List getPurInfoBySupplier(String supplier);

    /**
     * 获取所有采购的图书列表
     * @return list
     */
    public List getPurInfoBySupplier();

    public List getBKPurDateRange();

    /**
     * 从配置文件里获取学年-学期
     * @return 学年-学期
     */
    public String getBKPurDate();

    /**
     * 往配置文件里写学年-学期
     * @param YearAndSem 学年-学期
     * @return boolean
     */
    public Boolean setBKPurDate(String YearAndSem);

    public String generateBookList(String year,String sem);

    /**
     * 根据学年和学期来生成所有的购书单
     * @param year 学年
     * @param sem 学期
     * @return file xls
     */
    public File generateStudentBookList(String year,String sem);

    /**
     * 根据学年和学期来生成新生的购书单
     * @param year 学年
     * @param sem 学期
     * @param grade 新生年级
     * @return file xls
     */
    public File generateNewStudentBookList(String year,String sem,String grade);
}
