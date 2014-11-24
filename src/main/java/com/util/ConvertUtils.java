package com.util;

import com.bean.book.Book;
import com.bean.cls.Class;
import com.bean.college.College;
import com.bean.corbook.Corbookview;
import com.bean.corplan.Corplan;
import com.bean.course.Course;
import com.bean.supplier.Supplier;

import java.util.*;

public class ConvertUtils {
    /**
     * 将数据库处理的结果转换为表示层需要的数据
     *
     * @param list 数据库的结果LIST（由对象组成）
     * @return 表现层的list（由map组成）
     */
    public static List ToCourseList(List list) {
        List courselist = new ArrayList();
        for (Object aList : list) {
            Object[] object = (Object[]) aList;
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

    /**
     * 将obj List转换成map list类型
     *
     * @param list obj list
     * @return map list
     */
    public static List class2List(List list) {
        Iterator it = list.iterator();
        List clslist = new ArrayList();
        while (it.hasNext()) {
            Map map = new HashMap();
            com.bean.cls.Class cls = (Class) it.next();
            map.put("col", cls.getCollege().getCol());
            map.put("major", cls.getCollege().getMajor());
            map.put("idcm", cls.getCollege().getIdcm());
            map.put("semester", cls.getSemester());
            map.put("campus", cls.getCampus());
            map.put("clsno", cls.getClsno());
            map.put("grade", cls.getGrade());
            map.put("idcls", cls.getIdcls());
            map.put("clsno", cls.getClsno());
            map.put("stunum", cls.getStunum());
            clslist.add(map);
        }
        System.out.println(clslist);
        return clslist;
    }

    /**
     * 将数据库处理的结果转换为表示层学需要的数据
     *
     * @param list obj list
     * @return mpa list
     */
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

    /**
     * 将数据库处理的结果转换为表示层学需要的数据
     *
     * @param list obj list
     * @return map list
     */
    public static List ToBookList(List list) {
        List booklist = new ArrayList();
        for (Object aList : list) {
            Object[] object = (Object[]) aList;
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

    /**
     * 将数据库处理的结果转换为表示层学需要的数据
     *
     * @param list obj list
     * @return map list
     */
    public static List ToBookListFromBook(List list) {
        List booklist = new ArrayList();
        for (Object aList : list) {
            Book book = (Book) aList;
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

    /**
     * 将数据库处理的结果转换为表示层学需要的数据
     *
     * @param list obj list
     * @return map list
     */
    public static List ToCorBookList(List list) {
        List corbooklist = new ArrayList();

        for (Object aList : list) {
            Corbookview cbv = (Corbookview) aList;
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
        List publist = new ArrayList();
        for (Object aList : list) {
            Supplier sup = (Supplier) aList;
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
     * @param list obj-list
     * @return map list
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
