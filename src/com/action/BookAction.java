package com.action;

import com.bean.book.Book;
import com.bean.corbook.Corbookview;
import com.bean.course.Course;
import com.bean.coursebk.Coursebk;
import com.bean.supplier.Supplier;
import com.dao.CoursebkDAO;
import com.service.BookService;
import com.service.CourseBookViewService;
import com.service.CourseService;
import com.service.SupplierService;
import com.util.Pagination;
import com.util.Result;
import com.util.SendData;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookAction {
    private BookService bookService;
    private CourseService courseService;
    private CourseBookViewService corbkviewService;
    private SupplierService supSer;
    private String isbn;
    private String bookname;
    private String pub;
    private Pagination pagination;// 页码
    private CoursebkDAO coursebkDao;
    private List<Book> list = new ArrayList<Book>();

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public CoursebkDAO getCoursebkDao() {
        return coursebkDao;
    }

    public void setCoursebkDao(CoursebkDAO coursebkDao) {
        this.coursebkDao = coursebkDao;
    }

    public SupplierService getSupSer() {
        return supSer;
    }

    public void setSupSer(SupplierService supSer) {
        this.supSer = supSer;
    }

    public CourseBookViewService getCorbkviewService() {
        return corbkviewService;
    }

    public void setCorbkviewService(CourseBookViewService corbkviewService) {
        this.corbkviewService = corbkviewService;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 通过教材名称、出版社、ISBN 精确查询
     *
     * @return null
     */
    public String accurateBookQuery() {
        try {
            /* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            int currentPage = Integer.parseInt(request
                    .getParameter("currentPage"));
            String bkname = request.getParameter("bkname");
            String publihser = request.getParameter("publisher");
            String ISBN = request.getParameter("ISBN");

            boolean hasbkname = !(bkname == null || "".equals(bkname));
            boolean hasPublihser = !(publihser == null || "".equals(publihser));
            boolean hasISBN = !(ISBN == null || "".equals(ISBN));

            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setCurrentPage(currentPage);

            List booklist = null;
            if (!hasbkname & !hasPublihser & !hasISBN)
                booklist = bookService.initBook(pagination);
            if (hasISBN) {
                // 学院为和专业为全部，获取所有课程
                booklist = bookService.searchByISBN(ISBN, pagination);
            }
            if (hasbkname || hasPublihser) {
                // 学院为和学期为全部，获取该专业所有课程
                booklist = bookService.searchByBookPub(bkname, publihser,
                        pagination);
            }

            SendData.send(booklist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 精确查询的分页信息的获取
     */
    public String getAccPagination() {
        try {
            /* 获取页面端传递的参数 */
            HttpServletRequest request = ServletActionContext.getRequest();
            String bkname = request.getParameter("bkname");
            String publihser = request.getParameter("publisher");
            String ISBN = request.getParameter("ISBN");
            // String price= request.getParameter("price");

			/* 设置总页面数目,总记录数量 */
            boolean hasbkname = !(bkname == null || "".equals(bkname));
            boolean hasPublihser = !(publihser == null || "".equals(publihser));
            boolean hasISBN = !(ISBN == null || "".equals(ISBN));
            int totalRecord = 0;
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            if (!hasbkname & !hasPublihser & !hasISBN)
                totalRecord = bookService.initBook().size();
            if (hasISBN) {
                // 学院为和专业为全部，获取所有课程
                totalRecord = bookService.searchByISBN(ISBN).size();
            }
            if (hasbkname || hasPublihser) {
                // 学院为和学期为全部，获取该专业所有课程
                totalRecord = bookService.searchByBookPub(bkname, publihser)
                        .size();
            }
            pagination.setTotalRecord(totalRecord);
            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getfuzzyPagination() {
        try {
            /* 获取页面端传递的参数 */
//            HttpServletRequest request = ServletActionContext.getRequest();
            // String condition = request.getParameter("condition");
            int totalRecord;
            if (pagination == null)
                pagination = new Pagination(8);
            pagination.setSize(8);
            /* 获取某专业某一学期的课程 */
            totalRecord = bookService.initBook().size();

            pagination.setTotalRecord(totalRecord);
            System.out.println("totalPage:" + pagination.getTotalPage());
            System.out.println("totalRecord:" + pagination.getTotalRecord());
            SendData.send(pagination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String searchAllBook() {
        List list;
        if (pagination == null)
            pagination = new Pagination(8);
        pagination.setSize(8);
        pagination.setCurrentPage(1);
        list = bookService.initBook(pagination);

        SendData.send(list);
        return null;

    }

    /**
     *
     */
    public String addBook() {
        Book book = new Book();
        book.setBkname("1 ");
        SimpleDateFormat myFmt = new SimpleDateFormat("yyMMdd");
        int random = (int) Math.floor(Math.random() * 100000);
        book.setIdbk(myFmt.format(new Date()) + String.format("%1$05d", random)
                + "");
        bookService.addBook(book);
        return null;
    }

    /**
     * 删除教材 *
     */
    public String deleteBook() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idbk = request.getParameter("idbk");
        boolean tag;
        tag = bookService.deleteBook(idbk);
        Result result = new Result(tag);
        SendData.send(result);
        return null;
    }

    public String searchByISBNBook() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String isbn = request.getParameter("ISBN");
        this.list = bookService.searchByISBN(isbn);
        return null;
    }

    public List<CourseBookViewService> findCourseByIdbk() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idbk = request.getParameter("idbk");
        List<Corbookview> list;
        list = corbkviewService.findCourseByIdbk(idbk);
        SendData.send(list);
        return null;

    }

    public List<Book> findBookByBkname() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String bkname = request.getParameter("bkname");
        List<Book> list;
        list = bookService.searchByBkname(bkname);
        SendData.send(list);
        return null;
    }

    /**
     * 修改课本信息 2014.4.4
     *
     * @return null
     */
    public String modifyAll() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String idbk = request.getParameter("idbk");
        String bkname = request.getParameter("bkname");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        String publisher = request.getParameter("publisher");
        String ISBN = request.getParameter("ISBN");
        String price = request.getParameter("price");

        Book book = new Book();
        book.setIdbk(idbk);
        book.setBkname(bkname);
        book.setAuthor(author);
        book.setEdition(Integer.parseInt(edition));
        book.setIsbn(ISBN);
        book.setPrice(Double.parseDouble(price));

        Supplier sup = new Supplier();
        List li = supSer.findByPublish(publisher);
        for (Object o : li) {
            sup = (Supplier) o;
        }

        book.setSupplier(sup);
        Result result = new Result(false);
        result.setResult(bookService.updateBook(book));
        SendData.send(result);
        return null;
    }

    // 当部分选时，插入
    public String insert() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idcorStr = request.getParameter("idcorStr");

        // 对多个课程编号进行分割
        String[] idcorArray = idcorStr.split(",");
        // for (String idcor : idcorArray) {
        // System.out.println("idcor:" + idcor);
        // }
        // 获取参数
        String bkname = request.getParameter("bkname");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        String publisher = request.getParameter("publisher");
        String ISBN = request.getParameter("ISBN");
        String price = request.getParameter("price");
        // System.out.println(bkname + "," + author + "," + edition + ","
        // + publisher + "," + ISBN + "," + price);

        Book book = new Book();
        // 根据日期+随机数来生成idbk
        SimpleDateFormat myFmt = new SimpleDateFormat("yyMMdd");
        int random = (int) Math.floor(Math.random() * 100000);
        String newidbk = myFmt.format(new Date())
                + String.format("%1$05d", random) + "";
        // 为新建的书本注入属性
        book.setIdbk(newidbk);
        book.setBkname(bkname);
        book.setAuthor(author);
        if (edition.equals("")) {
            book.setEdition(1);
        } else {
            book.setEdition(Integer.parseInt(edition));
        }

        book.setIsbn(ISBN);

        if (price.equals("")) {
            book.setPrice(0.0);
        } else {
            book.setPrice(Double.parseDouble(price));
        }
        // 获取出版社对象
        Supplier sup = new Supplier();
        List li = supSer.findByPublish(publisher);
        sup = (Supplier) li.get(0);
        book.setSupplier(sup);

        // 为选中的课程关联教材
        if (idcorArray[0].equals("")) {
            // 若没有选中任何，则仅仅保存教材
            bookService.addBook(book);
        } else {
            // 若关联了课程，则为课程选用教材
            bookService.addBook(book);
            for (String idcor : idcorArray) {
                Coursebk coursebk = new Coursebk();
                // 获取课程
                Course course = courseService.getCourseByIdcor(idcor);

                // // 查看该课程是否选用了教材
                // List courseBkList = coursebkDao.findByIdcor(idcor);
                // // 如果该课程已经选用了教材则更新
                // if (courseBkList.size() > 0) {
                // coursebk = (Coursebk) courseBkList.get(0);
                // coursebk.setBook(book);
                // coursebkDao.update(coursebk);
                // } else {
                // coursebk.setCourse(course);
                // coursebk.setBook(book);
                // coursebkDao.save(coursebk);
                // }

                coursebk.setCourse(course);
                coursebk.setBook(book);
                coursebkDao.save(coursebk);
            }
        }
        Result result = new Result(true);
        SendData.send(result);
        return null;
    }
}
