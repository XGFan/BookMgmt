package com.service;

import com.dao.BookpurchaseviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("bKDistributeService")
public class BKDistributeServiceImp implements BKDistributeService {
    @Autowired
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
