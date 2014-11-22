package com.printInfo.bkdistribute.Service;

import java.util.List;

import com.bean.bookpurchaseview.BookpurchaseviewDAO;

public class BKDistributeServiceImp implements BKDistributeService {
    private BookpurchaseviewDAO bookpurchaseviewDAO;

    public BookpurchaseviewDAO getBookpurchaseviewDAO() {
        return bookpurchaseviewDAO;
    }

    public void setBookpurchaseviewDAO(BookpurchaseviewDAO bookpurchaseviewDAO) {
        this.bookpurchaseviewDAO = bookpurchaseviewDAO;
    }

    public List BKDistInfoQuery(int year, int sem, String idcls) {
        return bookpurchaseviewDAO.findByYearAndSemAndCol(year, sem, idcls);
    }
}
