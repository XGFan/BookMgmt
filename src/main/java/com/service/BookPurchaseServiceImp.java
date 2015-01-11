package com.service;

import com.bean.bookpurchaseview.Bookpurchaseview;
import com.bean.courclass.Courclass;
import com.dao.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
@Service("bookPurchaseService")
public class BookPurchaseServiceImp implements BookPurchaseService {
    public static final String COL = "学院";
    public static final String MAJOR = "专业";
    public static final String SEMNUM = "学制";
    public static final String EDITION = "版本";
    public static final String GRADE = "年级";
    public static final String CORNAME = "课程名称";
    public static final String SEMESTER = "学期";
    private static final String NO = "序号";
    private static final String CAMPUS = "校区";
    private static final String BKNAME = "书名";
    private static final String AUTHOR = "作者";
    private static final String ISBN = "ISBN";
    private static final String BKNUM = "订购数量";
    private static final String PUBLISHER = "出版社";
    private static final String SUPPLIER = "供应商";

    @Autowired
    private BookcorsupDAO bookcorsupDAO;
    @Autowired
    private CourclassDAO courclassDAO;
    @Autowired
    private BookDAO bookdao;
    @Autowired
    private BookpurchaseviewDAO bookpurchaseviewDAO;
    @Autowired
    private BookpurchaseDAO bookpurchaseDAO;


    // 生成教材采购清单的Excel文件，2014.3.19，zhangchi
//    void generateXlsFile() {
//        String purchaseDateStr = this.getBKPurDate();
//        // 学期格式2012-2013-1
//        // 获取学年
//        String fileName = purchaseDateStr.substring(0,
//                purchaseDateStr.lastIndexOf("-"));
//        String yearStr = purchaseDateStr.substring(0,
//                purchaseDateStr.indexOf("-"));
//        int year = Integer.parseInt(yearStr);
//        fileName = "湖北中医药大学" + fileName + "学年第";
//        // 获取第一个‘-’的下标
//        String semStr = purchaseDateStr.substring(purchaseDateStr.length() - 1);
//        int sem = Integer.parseInt(semStr);
//        fileName = fileName + sem + "学期新生购书清单";
//        HSSFWorkbook wb = new HSSFWorkbook();
//
//        HSSFSheet sheet = wb.createSheet("购书清单总表");
//
//        HSSFRow firstrow = sheet.createRow(0);
//
//        List<Object[]> bookList = bookpurchaseDAO.getBookList();
//
//        // public static final String COL = "学院";
//        // public static final String MAJOR = "专业";
//        // public static final String SEMNUM = "学制";
//        // public static final String CAMPUS = "校区";
//        // public static final String GRADE = "年级";
//        // public static final String CORNAME = "课程名称";
//        // public static final String SEMESTER = "学期";
//        // public static final String BKNAME = "书名";
//        // public static final String AUTHOR = "作者";
//        // public static final String ISBN = "ISBN";
//        // public static final String BKNUM = "订购数量";
//        // public static final String PUBLISHER = "出版社";
//        // public static final String SUPPLIER = "供应商";
//
//        HSSFCell[] firstcell = new HSSFCell[8];
//        String[] names = new String[8];
//
//        names[0] = NO;
//        names[1] = BKNAME;
//        names[2] = AUTHOR;
//        names[3] = ISBN;
//        names[4] = PUBLISHER;
//        // names[4] = EDITION;
//        names[5] = BKNUM;
//        names[6] = CAMPUS;
//        names[7] = SUPPLIER;
//
//        for (int j = 0; j < 8; j++) {
//            HSSFCell cell = firstrow.createCell((short) j);
//            cell.setCellValue(new HSSFRichTextString(names[j]));
//        }
//
//        // 遍历书本列表，写入到Excel文件
//        Iterator it = bookList.iterator();
//        int i = 1;
//        while (it.hasNext()) {
//            Object[] obj = (Object[]) it.next();
//            // 创建一行
//            HSSFRow row = sheet.createRow(i);
//            // 创建单元格
//            for (int j = 0; j < 8; j++) {
//                HSSFCell cell = row.createCell((short) j);
//                switch (j) {
//                    case 0:// No序号
//                        cell.setCellValue(new HSSFRichTextString(i + ""));
//                        break;
//                    case 1:// BKNAME书名
//                        cell.setCellValue(new HSSFRichTextString((String) obj[5]));
//                        break;
//                    case 2:// AUTHOR作者
//                        cell.setCellValue(new HSSFRichTextString((String) obj[6]));
//                        break;
//                    case 3:// ISBN
//                        cell.setCellValue(new HSSFRichTextString((String) obj[9]));
//                        break;
//                    case 4:// PUBLISHER出版社
//                        String pubEdition = null;
//                        if (obj[8].equals("1")) {
//                            pubEdition = (String) obj[7];
//                        } else {
//                            pubEdition = obj[7].toString() + obj[8].toString() + "版";
//                        }
//                        cell.setCellValue(new HSSFRichTextString(pubEdition));
//                        break;
//                    case 5:// BKNUM书本数量
//                        cell.setCellValue(new HSSFRichTextString((String) obj[12]));
//                        break;
//                    case 6:// CAMPUS校区
//                        cell.setCellValue(new HSSFRichTextString((String) obj[11]));
//                        break;
//                    case 7:// SUPPLIER供应商
//                        cell.setCellValue(new HSSFRichTextString((String) obj[10]));
//                        break;
//                }
//            }
//            i++;
//        }
//        OutputStream out;
//        try {
//
//            /** Mac OS **/
//            Properties props = System.getProperties(); // 获得系统属性集
//            String osName = props.getProperty("os.name"); // 操作系统名称
//            if (osName.equals("Mac OS X")) {
//                out = new FileOutputStream("/Users/user/Desktop/" + fileName
//                        + ".xls");
//            } else {
//                /** Windows **/
//                out = new FileOutputStream("E://" + fileName + ".xls");
//            }
//
//            wb.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    // 生成教材采购清单的Excel文件，2014.3.19，zhangchi
//    void generateFreshManXlsFile() {
//        String purchaseDateStr = this.getBKPurDate();
//        // 学期格式2012-2013-1
//        // 获取学年
//        String fileName = purchaseDateStr.substring(0,
//                purchaseDateStr.lastIndexOf("-"));
//        String yearStr = purchaseDateStr.substring(0,
//                purchaseDateStr.indexOf("-"));
//        int year = Integer.parseInt(yearStr);
//        fileName = "湖北中医药大学" + fileName + "学年第";
//        // 获取第一个‘-’的下标
//        String semStr = purchaseDateStr.substring(purchaseDateStr.length() - 1);
//        int sem = Integer.parseInt(semStr);
//        fileName = fileName + sem + "学期新生购书清单";
//        HSSFWorkbook wb = new HSSFWorkbook();
//
//        HSSFSheet sheet = wb.createSheet("购书清单总表");
//
//        HSSFRow firstrow = sheet.createRow(0);
//
//        List<Object[]> bookList = bookpurchaseDAO.getBookList();
//
//        // public static final String COL = "学院";
//        // public static final String MAJOR = "专业";
//        // public static final String SEMNUM = "学制";
//        // public static final String CAMPUS = "校区";
//        // public static final String GRADE = "年级";
//        // public static final String CORNAME = "课程名称";
//        // public static final String SEMESTER = "学期";
//        // public static final String BKNAME = "书名";
//        // public static final String AUTHOR = "作者";
//        // public static final String ISBN = "ISBN";
//        // public static final String BKNUM = "订购数量";
//        // public static final String PUBLISHER = "出版社";
//        // public static final String SUPPLIER = "供应商";
//
//        HSSFCell[] firstcell = new HSSFCell[8];
//        String[] names = new String[8];
//
//        names[0] = NO;
//        names[1] = BKNAME;
//        names[2] = AUTHOR;
//        names[3] = ISBN;
//        names[4] = PUBLISHER;
//        // names[4] = EDITION;
//        names[5] = BKNUM;
//        names[6] = CAMPUS;
//        names[7] = SUPPLIER;
//
//        for (int j = 0; j < 8; j++) {
//            HSSFCell cell = firstrow.createCell((short) j);
//            cell.setCellValue(new HSSFRichTextString(names[j]));
//        }
//
//        // 遍历书本列表，写入到Excel文件
//        Iterator it = bookList.iterator();
//        int i = 1;
//        while (it.hasNext()) {
//            Object[] obj = (Object[]) it.next();
//            // 创建一行
//            HSSFRow row = sheet.createRow(i);
//            // 创建单元格
//            for (int j = 0; j < 8; j++) {
//                HSSFCell cell = row.createCell((short) j);
//                switch (j) {
//                    case 0:// No序号
//                        cell.setCellValue(new HSSFRichTextString(i + ""));
//                        break;
//                    case 1:// BKNAME书名
//                        cell.setCellValue(new HSSFRichTextString((String) obj[5]));
//                        break;
//                    case 2:// AUTHOR作者
//                        cell.setCellValue(new HSSFRichTextString((String) obj[6]));
//                        break;
//                    case 3:// ISBN
//                        cell.setCellValue(new HSSFRichTextString((String) obj[9]));
//                        break;
//                    case 4:// PUBLISHER出版社
//                        String pubEdition = null;
//                        if (obj[8].equals("1")) {
//                            pubEdition = (String) obj[7];
//                        } else {
//                            pubEdition = obj[7].toString() + obj[8].toString() + "版";
//                        }
//                        cell.setCellValue(new HSSFRichTextString(pubEdition));
//                        break;
//                    case 5:// BKNUM书本数量
//                        cell.setCellValue(new HSSFRichTextString((String) obj[12]));
//                        break;
//                    case 6:// CAMPUS校区
//                        cell.setCellValue(new HSSFRichTextString((String) obj[11]));
//                        break;
//                    case 7:// SUPPLIER供应商
//                        cell.setCellValue(new HSSFRichTextString((String) obj[10]));
//                        break;
//                }
//            }
//            i++;
//        }
//        OutputStream out;
//        try {
//            /** Mac OS **/
//            Properties props = System.getProperties(); // 获得系统属性集
//            String osName = props.getProperty("os.name"); // 操作系统名称
//            if (osName.equals("Mac OS X")) {
//                out = new FileOutputStream("/Users/user/Desktop/" + fileName
//                        + ".xls");
//            } else {
//                /** Windows **/
//                out = new FileOutputStream("E://" + fileName + ".xls");
//            }
//            wb.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    public List getbklist(String semester) {
        // 判断上学年，下学年
        List list = null;
        String param = "";
        if (semester.trim().equals("1")) {
            for (int i = 0; i < 11; i++) {//循环11次
                param += "'" + (2 * i + 1) + "',";//奇数（1，3，5，7，9，11，13，15，17，19，21）
            }
            param += "'" + 23 + "'";
            //'1','3','5','7','9','11','13','15','17','19','21','23'
            //这TM要干嘛
            list = bookcorsupDAO.findbklist(param);
//            System.out.println("1.param" + param);
        } else {
            for (int i = 0; i < 11; i++) {//循环11次
                param += "'" + (2 * i + 2) + "',";//偶数（2，4，6，8，10，12，14，16，18，20，22）
            }
            param += "'" + 24 + "'";
//            System.out.println("2.param" + param);
            list = bookcorsupDAO.findbklist(param);
        }
        return list;
    }

    public List getcorlistbyidbk(String idbk) {
        return bookcorsupDAO.findcorlistbyidbk(idbk);
    }

//    public List<Map> getBKPurInfo() {
//        Set<Map> purinfolist = new HashSet<Map>();
//        List<Map> list = new ArrayList<Map>();
//        List<Bkpurchase> bkpurlist = bkpurchaseDAO.getAll();
//        for (Bkpurchase bk : bkpurlist) {
//            Map map = new HashMap();
//            Integer total = 0;
//            String idbk = bk.getBook().getIdbk();
//            List<Book> booklist = bookdao.findByPropertyA("idbk",idbk);
//            for (Book b : booklist) {
//                map.put("idbk", idbk);
//                map.put("BKName", b.getBkname());
//                map.put("Author", b.getAuthor());
//                map.put("supplier", b.getSupplier().getSupplier());
//            }
//            for (Bkpurchase bkp : bkpurlist) {
//                if (idbk.equals(bkp.getBook().getIdbk())) {
//
//                    if (bkp.getCampus().equals("黄") && bkp.getBknum() != null) {
//                        map.put("HJHcampus", bkp.getCampus());
//                        map.put("HJHBKnum", bkp.getBknum());
//                        total += bkp.getBknum();
//                    }
//                    if (bkp.getCampus().equals("昙") && bkp.getBknum() != null) {
//                        map.put("THLcampus", bkp.getCampus());
//                        map.put("THLBKnum", bkp.getBknum());
//                        total += bkp.getBknum();
//                    }
//                }
//            }
//            map.put("total", total);
//            purinfolist.add(map);
//        }
//        for (Map bk : purinfolist) {
//            System.out.println("idbk:" + bk.get("idbk") + "-bkname:"
//                    + bk.get("BKName") + "-Author:" + bk.get("Author")
//                    + "-HJHcampus:" + bk.get("HJHBKnum") + "-THLcampus:"
//                    + bk.get("THLBKnum") + "-total:" + bk.get("total")
//                    + "-supplier:" + bk.get("supplier"));
//            list.add(bk);
//        }
//        System.out.println(purinfolist.size() + "," + bkpurlist.size());
//        return list;
//    }

    public List getPurInfoBySupplier(String supplier) {
        // 返回采购清单到页面
        List result = new ArrayList();
        List<Object[]> bookList = bookpurchaseDAO.getBookList();
        Iterator it = bookList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object[] obj = (Object[]) it.next();
            Map temp = new HashMap();
            if (obj[10].equals(supplier) || "".equals(supplier)) {
                i++;
                temp.put("no", i);
                temp.put("col", obj[0]);
                temp.put("major", obj[1]);
                temp.put("grade", obj[2]);
                temp.put("clsno", obj[3]);
                temp.put("idbk", obj[4]);
                temp.put("bkname", obj[5]);
                temp.put("author", obj[6]);
                temp.put("publisher", obj[7]);
                temp.put("edition", obj[8]);
                temp.put("isbn", obj[9]);
                temp.put("supplier", obj[10]);
                temp.put("campus", obj[11]);
                temp.put("bknum", obj[12]);
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List getPurInfoBySupplier() {
        return getPurInfoBySupplier("");
    }


    @Override
    public String getBKPurDate() {
        Properties prop = new Properties();
        String path = BookPurchaseServiceImp.class.getClassLoader().getResource("date.properties").getPath();
        System.out.println(path);
        try {
            InputStream in = new FileInputStream(path);
            prop.load(in);
            in.close();
            return prop.getProperty("Date").trim();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean setBKPurDate(String yearAndSem){
        boolean re = false;
        Properties prop = new Properties();
        String path = BookPurchaseServiceImp.class.getClassLoader().getResource("date.properties").getPath();
        try{
            InputStream in = new FileInputStream(path);
            prop.load(in);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        prop.setProperty("Date",yearAndSem);
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
            prop.store(out,null);
            out.close();
            re = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }




    public List getBKPurDateRange() {
        List<String> daterange = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        String datestring = null;
        for (int i = -1; i <= 2; i++) {
            datestring = (year + i - 1) + "-" + (year + i) + "-1";
            daterange.add(datestring);
            datestring = (year + i - 1) + "-" + (year + i) + "-2";
            daterange.add(datestring);
        }
        return daterange;
    }
    @Deprecated
    public String generateBookList(String year,String sem) {
        int yearNum = Integer.parseInt(year);
        int semNum = Integer.parseInt(sem);
        List<Bookpurchaseview> list = bookpurchaseviewDAO.findByYearAndSem(yearNum, semNum);
        // 清空bookpurchase表
        bookpurchaseDAO.deleteAll();
        // 将筛选后的课程信息保存到临时表bookpurchase
        bookpurchaseDAO.saveTemp(list);
        // 返回采购清单到页面
        List result = new ArrayList();
        List<Object[]> bookList = bookpurchaseDAO.getBookList();
        Iterator it = bookList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object[] obj = (Object[]) it.next();
            Map temp = new HashMap();
            i++;
            temp.put("no", i);
            temp.put("col", obj[0]);
            temp.put("major", obj[1]);
            temp.put("grade", obj[2]);
            temp.put("clsno", obj[3]);
            temp.put("idbk", obj[4]);
            temp.put("bkname", obj[5]);
            temp.put("author", obj[6]);
            temp.put("publisher", obj[7]);
            temp.put("edition", obj[8]);
            temp.put("isbn", obj[9]);
            temp.put("supplier", obj[10]);
            temp.put("campus", obj[11]);
            temp.put("bknum", obj[12]);

            result.add(temp);
        }
        return null;
    }


    public File generateStudentBookList(String year,String sem){
        String fileName = "湖北中医药大学" + year + "学年第"+ sem + "学期购书清单";
        int yearNum = Integer.parseInt(year);
        int semNum = Integer.parseInt(sem);
        List<Bookpurchaseview> list = bookpurchaseviewDAO.findByYearAndSem(yearNum,semNum);
        bookpurchaseDAO.deleteAll();
        bookpurchaseDAO.saveTemp(list);
        return generateXlsFile(fileName);
    }

    public File generateNewStudentBookList(String year,String sem,String grade){
        String fileName = "湖北中医药大学" + year + "学年第"+ sem + "学期新生购书清单";
        int yearNum = Integer.parseInt(year);
        int gradeNum = Integer.parseInt(grade);
        int semNum = Integer.parseInt(sem);
        List<Bookpurchaseview> list = bookpurchaseviewDAO.findByYearAndSemAndGrade(yearNum,semNum, gradeNum);
        bookpurchaseDAO.deleteAll();
        bookpurchaseDAO.saveTemp(list);
        return generateXlsFile(fileName);
    }

    /**
     * 读取bookpurchase表里面的内容，生成xls表
     * @param xlsName 文件名
     * @return 文件名
     */
    File generateXlsFile(String xlsName){
        List booklist = bookpurchaseDAO.getBookList();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("购书清单总表");
        HSSFRow firstrow = sheet.createRow(0);
        List<Object[]> bookList = booklist;
        HSSFCell[] firstcell = new HSSFCell[8];
        String[] names = new String[8];
        names[0] = NO;
        names[1] = BKNAME;
        names[2] = AUTHOR;
        names[3] = ISBN;
        names[4] = PUBLISHER;
        names[5] = BKNUM;
        names[6] = CAMPUS;
        names[7] = SUPPLIER;
        for (int j = 0; j < 8; j++) {
            HSSFCell cell = firstrow.createCell((short) j);
            cell.setCellValue(new HSSFRichTextString(names[j]));
        }
        // 遍历书本列表，写入到Excel文件
        Iterator it = bookList.iterator();
        int i = 1;
        while (it.hasNext()) {
            Object[] obj = (Object[]) it.next();
            // 创建一行
            HSSFRow row = sheet.createRow(i);
            // 创建单元格
            for (int j = 0; j < 8; j++) {
                HSSFCell cell = row.createCell((short) j);
                switch (j) {
                    case 0:// No序号
                        cell.setCellValue(new HSSFRichTextString(i + ""));
                        break;
                    case 1:// BKNAME书名
                        cell.setCellValue(new HSSFRichTextString((String) obj[5]));
                        break;
                    case 2:// AUTHOR作者
                        cell.setCellValue(new HSSFRichTextString((String) obj[6]));
                        break;
                    case 3:// ISBN
                        cell.setCellValue(new HSSFRichTextString((String) obj[9]));
                        break;
                    case 4:// PUBLISHER出版社
                        String pubEdition = null;
                        if (obj[8].equals("1")) {
                            pubEdition = (String) obj[7];
                        } else {
                            pubEdition = obj[7].toString() + obj[8].toString() + "版";
                        }
                        cell.setCellValue(new HSSFRichTextString(pubEdition));
                        break;
                    case 5:// BKNUM书本数量
                        cell.setCellValue(new HSSFRichTextString((String) obj[12]));
                        break;
                    case 6:// CAMPUS校区
                        cell.setCellValue(new HSSFRichTextString((String) obj[11]));
                        break;
                    case 7:// SUPPLIER供应商
                        cell.setCellValue(new HSSFRichTextString((String) obj[10]));
                        break;
                }
            }
            i++;
        }
        OutputStream out;
        try {
            File file = new File(System.getProperty("java.io.tmpdir"),xlsName+".xls");
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
