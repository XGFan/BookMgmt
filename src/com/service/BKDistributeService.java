package com.service;

import com.bean.bookpurchaseview.Bookpurchaseview;

import java.util.List;

public interface BKDistributeService {
    public List<Bookpurchaseview> BKDistInfoQuery(int year, int sem, String idcls);
}
