package com.printInfo.bkpurchase.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.bookcorsup.Bookcorsup;
import com.bean.courclass.Courclass;
import com.opensymphony.xwork2.ActionContext;
import com.printInfo.bkpurchase.Service.BKPurchaseService;
import com.printInfo.supplier.Service.SupplierService;
import com.util.GetPaginationInfo;
import com.util.Pagination;
import com.util.SendData;

public class BkpurAction {
    private BKPurchaseService bkpurser;
    private SupplierService supplierService;
    private Pagination pagination;// 用于进行分页查询
    private List<Map> bkpurlist = new ArrayList();

    public BKPurchaseService getBkpurser() {
        return bkpurser;
    }

    public void setBkpurser(BKPurchaseService bkpurser) {
        this.bkpurser = bkpurser;
    }

    public SupplierService getSupplierService() {
        return supplierService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /* 按供应商分页查询 */
    public String initPagination() {
        try {
            /* 获取页面端传递的参数 */
            // System.out.println("initPagination()");
            bkpurlist.clear();
            HttpServletRequest request = ServletActionContext.getRequest();
            String supplier = request.getParameter("supplier");

            System.out.println("supplier:" + supplier);

            int totalRecord;
            if (pagination == null)
                pagination = new Pagination(6);
            List<Map> list = bkpurser.getBKPurInfo();
            if (supplier.trim().equals("请选择")) {
                bkpurlist = list;
            } else {
                for (Map s : list) {
                    if (s.get("supplier").equals(supplier.trim())) {
                        bkpurlist.add(s);
                    }
                }
            }
            ActionContext.getContext().getSession().put("bkpurlist", bkpurlist);

            totalRecord = bkpurlist.size();
			/* 设置总页面数目,总记录数量 */
            pagination.setTotalRecord(totalRecord);

            System.out.println("totalPage:" + pagination.getTotalPage());
            System.out.println("totalRecord:" + pagination.getTotalRecord());

            SendData.send(pagination);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 获取购书表信息 */
    public String initPage() {
        try {
            // 获取页面端传递的参数
            System.out.println("initPage()");

            int totalRecord;
            if (pagination == null)
                pagination = new Pagination(6);
            this.bkpurlist = bkpurser.getBKPurInfo();
            ActionContext.getContext().getSession().put("bkpurlist", bkpurlist);
            totalRecord = bkpurlist.size();
            // 设置总页面数目,总记录数量
            pagination.setTotalRecord(totalRecord);

            System.out.println("totalPage:" + pagination.getTotalPage());
            System.out.println("totalRecord:" + pagination.getTotalRecord());

            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String pagefuzzyQuery() {
        try {
            // 获取页面端传递的参数
            HttpServletRequest request = ServletActionContext.getRequest();
            int currentPage = Integer.parseInt(request
                    .getParameter("currentPage"));

            bkpurlist = (List<Map>) ActionContext.getContext().getSession()
                    .get("bkpurlist");
            if (pagination == null)
                pagination = new Pagination(6);
            pagination.setCurrentPage(currentPage);
            List<Map> list;
            // 学院为和专业为全部，获取所有课程
            list = GetPaginationInfo.getSubList(this.bkpurlist, pagination);
            // for(Map s:list){
            // System.out.println("bkpurchase       list:"+s.get("BKName"));
            // }

            // list=ConvertUtils.ToSupplierList(list);//将list转换成map格式以便于转换为json格式
            SendData.send(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 生成购书信息 */
    public String generateBookList() {
        // 生成购书信息
        HttpServletRequest request = ServletActionContext.getRequest();
        String bkpur = request.getParameter("bkpubchasedate");
        // ApplicationContext ctx = new
        // ClassPathXmlApplicationContext("applicationContext.xml");
        // bkpurser = (BKPurchaseService) ctx.getBean("bkpurService");
        // System.out.println(bkpur);
        String semester = bkpur.substring(bkpur.length() - 1);// "1"或"2"
        // System.out.println(semester);
        List list = bkpurser.generateBookList(semester);
        SendData.send(list);
        return null;
    }

    /* 生成新生购书信息 */
    public String generateFreshManBookList() {
        // 生成购书信息
        HttpServletRequest request = ServletActionContext.getRequest();
        String bkpur = request.getParameter("bkpubchasedate");
        // ApplicationContext ctx = new
        // ClassPathXmlApplicationContext("applicationContext.xml");
        // bkpurser = (BKPurchaseService) ctx.getBean("bkpurService");
        // System.out.println(bkpur);
        String semester = bkpur.substring(bkpur.length() - 1);// "1"或"2"
        // System.out.println(semester);
        List list = bkpurser.generateFreshManBookList(semester);
        SendData.send(list);
        return null;
    }

    // 获取购书表信息
    public String getBKPurchaseInfo() {
        List list = bkpurser.getBKPurInfo();
        SendData.send(list);
        return null;
    }

    /* 获取购书表日期的范围信息 */
    public String getBKPurDateRange() {
        List list = bkpurser.getBKPurDateRange();
        SendData.send(list);
        return null;
    }

    /**
     *  todo
     *  wrong
     * @return null
     */
    public String getBKPurDate() {
        String purdate = bkpurser.getBKPurDate();
        List<String> list = new ArrayList<String>();
        list.add(purdate);
        SendData.send(list);
        return null;
    }

    /*
    修改购书表日期信息
    it is wrong
    todo
     */
    public String alterBKPurDate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String bkpurdate = request.getParameter("alterdate");
        // System.out.println(bkpurdate);
        bkpurser.alterBKPurchase(bkpurdate);
        return null;
    }

    public String searchAllSup() {
        // System.out.println("searchAllSup");
        List list = supplierService.getAllSupplier();
		/*
		 * Iterator it = list.iterator(); while (it.hasNext()) { String str =
		 * (String) it.next(); // System.out.println(str); }
		 * 
		 * // list=ConvertUtils.ToSuppList(list);
		 */
        SendData.send(list);
        return null;
    }

    // 根据课本查课程（那些课程用到了该课本）
    public String getcorlist(String idbk) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        bkpurser = (BKPurchaseService) ctx.getBean("bkpurService");
        List list = bkpurser.getcorlistbyidbk(idbk);// 测试
        System.out.println("idcor的长度：" + list.size());
        for (Object o : list) {
            Bookcorsup s = (Bookcorsup) o;
            System.out.println("idbk:" + s.getIdbk() + "  idcor" + s.getIdcor()
                    + "  Semester  " + s.getSemester());
        }
        return null;
    }

    /**
     * 获取在某一供应商处购买的图书列表，2014.3.23-zhagnchi *
     */
    public String getPurInfoBySupplier() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String supplier = request.getParameter("supplier");
        List list = bkpurser.getPurInfoBySupplier(supplier);
        SendData.send(list);
        return null;
    }

    public String getNum(String idcor, String semester) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        bkpurser = (BKPurchaseService) ctx.getBean("bkpurService");
        List list = bkpurser.getNum(idcor, semester);// 测试
        System.out.println("人数的长度：" + list.size());
        for (Object o : list) {
            Courclass s = (Courclass) o;
            System.out.println("num:" + s.getStunum() + "  idcor"
                    + s.getIdcor() + "  Semester  " + s.getSemester());
        }
        return null;
    }

    public String getbklist() {

        HttpServletRequest request = ServletActionContext.getRequest();
        String semester = request.getParameter("semester");
        // ApplicationContext ctx = new
        // ClassPathXmlApplicationContext("applicationContext.xml");
        // bkpurser = (BKPurchaseService) ctx.getBean("bkpurService");

        List list = bkpurser.getbklist(semester);// 测试
        System.out.println("idbK的长度：" + list.size());
        for (Object o : list) {
            String s = (String) o;
            System.out.println("idbk:" + s);
        }
        // list= bkpurser.getBKPurInfo();
        return null;
    }
    // public static void main(String as[]){
    // BkpurAction bk=new BkpurAction();
    // // bk.getbklist();
    // bk.calculateBk();
    // // bk.getcorlist("13032225322");
    // // bk.getNum("07020715","");
    //
    // }
}
