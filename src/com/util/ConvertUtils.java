package com.util;

import java.util.*;

import com.bean.book.Book;
import com.bean.college.College;
import com.bean.corbook.Corbookview;
import com.bean.corbook.CorbookviewId;
import com.bean.corplan.Corplan;
import com.bean.course.*;
import com.bean.supplier.Supplier;

public class ConvertUtils {
    /**
     * 将数据库处理的结果转换为表示层需要的数据
     *
     * @param list 数据库的结果LIST（由对象组成）
     * @return 表现层的list（由map组成）
     */
    public static List ToCourseList(List list) {
        Iterator it = list.iterator();
        List courselist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] object = (Object[]) list.get(i);
            Course course = (Course) object[0];
            Map map = new HashMap();
            map.put("idcm", course.getCollege().getIdcm());
            map.put("col", course.getCollege().getCol());
            map.put("major", course.getCollege().getMajor());
            map.put("idcor", course.getIdcor());
            map.put("corname", course.getCorname());
            map.put("semester", course.getSemester());
            courselist.add(map);
        }
        return courselist;
    }

    /* 将数据库处理的结果转换为表示层学需要的数据 */
    public static List ToCorplanList(List list) {
        Iterator it = list.iterator();
        List corplanlist = new ArrayList();
        while (it.hasNext()) {
            Object[] object = (Object[]) it.next();
            Corplan corplan = (Corplan) object[0];
            Map map = new HashMap();
            map.put("idcorsem", corplan.getIdcorsem());
            map.put("idcm", corplan.getCourse().getCollege().getIdcm());
            map.put("col", corplan.getCourse().getCollege().getCol());
            map.put("major", corplan.getCourse().getCollege().getMajor());
            map.put("idcor", corplan.getCourse().getIdcor());
            map.put("corname", corplan.getCourse().getCorname());
            map.put("semester", corplan.getSemester());
            corplanlist.add(map);
        }
        return corplanlist;
    }

    /* 将数据库处理的结果转换为表示层学需要的数据 */
    public static List ToBookList(List list) {
        Iterator it = list.iterator();
        List booklist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] object = (Object[]) list.get(i);
            Book book = (Book) object[0];
            Map map = new HashMap();
            map.put("idbk", book.getIdbk());
            map.put("bkname", book.getBkname());
            map.put("author", book.getAuthor());
            map.put("edition", book.getEdition());
            map.put("publisher", book.getSupplier().getPublisher());
            map.put("isbn", book.getIsbn());
            map.put("price", book.getPrice());
            booklist.add(map);
        }
        return booklist;
    }

    /* 将数据库处理的结果转换为表示层学需要的数据 */
    public static List ToBookListFromBook(List list) {
        Iterator it = list.iterator();
        List booklist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object object = (Object) list.get(i);
            Book book = (Book) object;
            Map map = new HashMap();
            map.put("idbk", book.getIdbk());
            map.put("bkname", book.getBkname());
            map.put("author", book.getAuthor());
            map.put("edition", book.getEdition());
            map.put("publisher", book.getSupplier().getPublisher());
            map.put("isbn", book.getIsbn());
            map.put("price", book.getPrice());
            booklist.add(map);
        }
        return booklist;
    }

    public static List ToCorBookList(List list) {
        Iterator it = list.iterator();
        List corbooklist = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            Object object = (Object) list.get(i);
            Corbookview cbv = (Corbookview) object;
            Map map = new HashMap();
            map.put("col", cbv.getCol());
            map.put("major", cbv.getMajor());
            map.put("idcor", cbv.getIdcor());
            map.put("corname", cbv.getCorname());
            map.put("semester", cbv.getSemester());
            map.put("idcorsem", cbv.getIdcorsem());
            map.put("idbk", cbv.getIdbk());
            map.put("bkname", cbv.getBkname());
            map.put("author", cbv.getAuthor());
            map.put("edition", cbv.getEdition());
            map.put("publisher", cbv.getPublisher());
            map.put("isbn", cbv.getIsbn());

            corbooklist.add(map);
        }
        /**
         * 在程序设计完成后，我们应该删除print等语句，因为它们会占用系统内存 少量的语句积少成多，会极大的影响系统的性能
         * 下面的这句话删除后，系统的反映速度提高15-30s
         * //System.out.println(corbooklist.toString());
         * */
        return corbooklist;
    }

    public static List ToPubList(List list) {
        Iterator it = list.iterator();
        List publist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object object = (Object) list.get(i);
            Supplier sup = (Supplier) object;
            Map map = new HashMap();
            map.put("idsp", sup.getIdsp());
            map.put("supplier", sup.getSupplier());
            map.put("publisher", sup.getPublisher());
            publist.add(map);
        }
        return publist;
    }

    public static List ToSupplierList(List list) {
        Iterator it = list.iterator();
        List supplierlist = new ArrayList();
        while (it.hasNext()) {
            Supplier supplier = (Supplier) it.next();
            Map map = new HashMap();
            map.put("idsp", supplier.getIdsp());
            map.put("publisher", supplier.getPublisher());
            map.put("supplier", supplier.getSupplier());
            supplierlist.add(map);
        }
        return supplierlist;
    }

    /**
     * 把学院，专业，班级号的list转换成map形式的list
     *
     * @param list
     * @return
     */
    public static List ToCollegeList(List list) {
        Iterator it = list.iterator();
        List collegelist = new ArrayList();
        while (it.hasNext()) {
            // Object[] obj = (Object[]) it.next();
            College college = (College) it.next();
            Map map = new HashMap();
            map.put("idcm", college.getIdcm());
            map.put("col", college.getCol());
            map.put("major", college.getMajor());
            map.put("semnum", college.getSemnum());
            collegelist.add(map);
        }
        return collegelist;
    }

}
