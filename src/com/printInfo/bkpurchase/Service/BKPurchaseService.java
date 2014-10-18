package com.printInfo.bkpurchase.Service;

import java.util.List;
import java.util.Map;

public interface BKPurchaseService {

	public List findPage(int page, int maxpage);

	public List getbklist(String semester);

	public List getcorlistbyidbk(String idbk);

	public List getNum(String idcor, String semester);

	public List<Map> getBKPurInfo();

	/** 获取在某一供应商处购买的图书列表，2014.3.23-zhagnchi **/
	public List getPurInfoBySupplier(String supplier) ;

	public List getBKPurDateRange();

	public String getBKPurDate();

	public void alterBKPurchase(String semester);

	public List generateBookList(String purDate);
	
	public List generateFreshManBookList(String purDate);
}
