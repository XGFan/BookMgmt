package com.printInfo.bkdistribute.Service;

import java.util.List;

import com.bean.bookpurchaseview.Bookpurchaseview;

public interface BKDistributeService {
    public List<Bookpurchaseview> BKDistInfoQuery(int year, int sem, String idcls);
}
