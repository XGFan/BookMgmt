package com.action;

import com.bean.supplier.Supplier;
import com.opensymphony.xwork2.ActionContext;
import com.service.SupplierService;
import com.util.*;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SupAction {
    private Pagination pagination;// 用于进行分页查询
    private SupplierService supser;
    private List<Supplier> suplist = new ArrayList();

    public List getSuplist() {
        return suplist;
    }

    public void setSuplist(List suplist) {
        this.suplist = suplist;
    }

    public SupplierService getSupser() {
        return supser;
    }

    public void setSupser(SupplierService supser) {
        this.supser = supser;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @SuppressWarnings("unchecked")
    public String searchAllSup() {
        List list = supser.getAllSupplier();
        SendData.send(list);
        return null;
    }

    public String initPagination() {
        try {
            /* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            String publisher = request.getParameter("publisher");
            String supplier = request.getParameter("supplier");

            // System.out.println("publisher" + publisher + "supplier" +
            // supplier);
            int totalRecord = 0;
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            this.suplist = supser.searchByPubSup(publisher, supplier);

            ActionContext.getContext().getSession().put("suplist", suplist);

            totalRecord = suplist.size();
            /* 设置总页面数目,总记录数量 */
            pagination.setTotalRecord(totalRecord);

            // System.out.println("totalPage:" + pagination.getTotalPage());
            // System.out.println("totalRecord:" + pagination.getTotalRecord());

            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String initPage() {
        try {
            /* 获取页面端传递的参数 */
            int totalRecord = 0;
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            this.suplist = supser.initSup();
            ActionContext.getContext().getSession().put("suplist", suplist);
            totalRecord = suplist.size();
			/* 设置总页面数目,总记录数量 */
            pagination.setTotalRecord(totalRecord);

            // System.out.println("totalPage:" + pagination.getTotalPage());
            // System.out.println("totalRecord:" + pagination.getTotalRecord());

            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String pagefuzzyQuery() {
        try {
			/* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            int currentPage = Integer.parseInt(request
                    .getParameter("currentPage"));
            suplist = (List<Supplier>) ActionContext.getContext().getSession()
                    .get("suplist");
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            pagination.setCurrentPage(currentPage);
            List<Supplier> list;
			/* 学院为和专业为全部，获取所有课程 */
            list = GetPaginationInfo.getSubList(suplist, pagination);
            list = ConvertUtils.ToSupplierList(list);// 将list转换成map格式以便于转换为json格式
            SendData.send(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findAllPub() {
        List list = supser.findAllPub();
        SendData.send(list);
        return null;
    }

    /**
     * 添加出版社
     *
     * @return null
     */
    public String addPub() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String publisher = request.getParameter("publisher");
        String supplier = request.getParameter("supplier");
        boolean tag = supser.addPub(publisher, supplier);
        Result result = new Result(tag);
        SendData.send(result);
        return null;
    }

    /**
     * 添加供应商
     *
     * @return null
     */
    public String addSup() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String supplier = request.getParameter("supplier");
        supser.addSup(supplier);
        return null;
    }

    /* 删除供应商 */
    public String delSup() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String supplier = request.getParameter("supplier");
        supser.delSup(supplier);
        return null;
    }

    /* 删除出版社（记录） */
    public String delPub() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String publisher = request.getParameter("publisher");
        String supplier = request.getParameter("supplier");
        String idsp = request.getParameter("idsp");
        Supplier sup = new Supplier();
        sup.setIdsp(idsp);
        sup.setPublisher(publisher);
        sup.setSupplier(supplier);
        supser.delPub(sup);
        return null;
    }

    /* 修改（记录） */
    public String alterSup() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String publisher = request.getParameter("publisher");
        String supplier = request.getParameter("supplier");
        String idsp = request.getParameter("idsp");
        System.out.println(publisher + "," + supplier + "," + idsp);
        Supplier sup = new Supplier();
        sup.setPublisher(publisher);
        sup.setSupplier(supplier);
        sup.setIdsp(idsp);
        supser.updateSupplier(sup);
        return null;
    }

}
