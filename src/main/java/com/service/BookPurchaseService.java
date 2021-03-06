package com.service;

import java.io.File;
import java.util.List;

public interface BookPurchaseService {


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

    /**
     * 生成<学年-学期>选单
     * @return list
     */
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

    public void deleteAll();
}
