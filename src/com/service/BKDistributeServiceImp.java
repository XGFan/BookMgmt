package com.service;

import com.dao.BookpurchaseviewDAO;

import java.util.List;

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
