package com.printInfo.bkdistribute.Service;

import java.util.List;

import com.bean.bookpurchaseview.Bookpurchaseview;
import com.bean.college.College;
import com.util.Pagination;

public interface BKDistributeService {
    //	public List<Bookpurchaseview> BKDistInfoQuery(int year, int sem, String col);
    public List<Bookpurchaseview> BKDistInfoQuery(int year, int sem, String idcls);

}
