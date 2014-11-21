package com.printInfo.bkdistribute.Service;

import java.util.List;

import com.bean.bookpurchaseview.BookpurchaseviewDAO;
import com.bean.cls.ClassDAO;
import com.bean.college.College;
import com.printInfo.bkdistribute.Service.BKDistributeService;
import com.util.Pagination;

public class BKDistributeServiceImp implements BKDistributeService {
    private BookpurchaseviewDAO bookpurchaseviewDAO;

    public BookpurchaseviewDAO getBookpurchaseviewDAO() {
        return bookpurchaseviewDAO;
    }

    public void setBookpurchaseviewDAO(BookpurchaseviewDAO bookpurchaseviewDAO) {
        this.bookpurchaseviewDAO = bookpurchaseviewDAO;
    }

    public List findAll() {
        return bookpurchaseviewDAO.findAll();
    }

    public List BKDistInfoQuery(int year, int sem, String idcls) {
        return bookpurchaseviewDAO.findByYearAndSemAndCol(year, sem, idcls);

    }
    // public List BKDistInfoQuery(int year, int sem, String col) {
    // return bookpurchaseviewDAO.findByYeayAndSemAndIdcls(year, sem, col);
    //
    // }

}
