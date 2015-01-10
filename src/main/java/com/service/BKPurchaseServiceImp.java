package com.service;

import com.bean.bkpurchase.Bkpurchase;
import com.bean.book.Book;
import com.bean.bookpurchaseview.Bookpurchaseview;
import com.dao.*;
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
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
@Service("bKPurchaseService")
public class BKPurchaseServiceImp implements BKPurchaseService {
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
    private BkpurchaseDAO bkpurdao;
    @Autowired
    private BookcorsupDAO bcsdao;
    @Autowired
    private CourclassDAO ccdao;
    @Autowired
    private BookDAO bookdao;
    @Autowired
    private BookpurchaseviewDAO bookpurchaseviewDAO;
    @Autowired
    private BookpurchaseDAO bookpurchaseDAO;

    public BkpurchaseDAO getBkpurdao() {
        return bkpurdao;
    }

    public void setBkpurdao(BkpurchaseDAO bkpurdao) {
        this.bkpurdao = bkpurdao;
    }

    public BookcorsupDAO getBcsdao() {
        return bcsdao;
    }

    public void setBcsdao(BookcorsupDAO bcsdao) {
        this.bcsdao = bcsdao;
    }

    public CourclassDAO getCcdao() {
        return ccdao;
    }

    public void setCcdao(CourclassDAO ccdao) {
        this.ccdao = ccdao;
    }

    public BookDAO getBookdao() {
        return bookdao;
    }

    public void setBookdao(BookDAO bookdao) {
        this.bookdao = bookdao;
    }

    public BookpurchaseviewDAO getBookpurchaseviewDAO() {
        return bookpurchaseviewDAO;
    }

    public void setBookpurchaseviewDAO(BookpurchaseviewDAO bookpurchaseviewDAO) {
        this.bookpurchaseviewDAO = bookpurchaseviewDAO;
    }

    public BookpurchaseDAO getBookpurchaseDAO() {
        return bookpurchaseDAO;
    }

    public void setBookpurchaseDAO(BookpurchaseDAO bookpurchaseDAO) {
        this.bookpurchaseDAO = bookpurchaseDAO;
    }




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
            list = bcsdao.findbklist(param);
//            System.out.println("1.param" + param);
        } else {
            for (int i = 0; i < 11; i++) {//循环11次
                param += "'" + (2 * i + 2) + "',";//偶数（2，4，6，8，10，12，14，16，18，20，22）
            }
            param += "'" + 24 + "'";
//            System.out.println("2.param" + param);
            list = bcsdao.findbklist(param);
        }
        return list;
    }

    public List getcorlistbyidbk(String idbk) {
        // 判断上学年，下学年
        return bcsdao.findcorlistbyidbk(idbk);
    }

    public List getNum(String idcor, String semester) {
        // 判断上学年，下学年
        return ccdao.findByCorSem(idcor, semester);
    }

    public List<Map> getBKPurInfo() {
        Set<Map> purinfolist = new HashSet<Map>();
        List<Map> list = new ArrayList<Map>();
        List<Bkpurchase> bkpurlist = bkpurdao.getAll();
        for (Bkpurchase bk : bkpurlist) {
            Map map = new HashMap();
            Integer total = 0;
            String idbk = bk.getBook().getIdbk();
            List<Book> booklist = bookdao.findByPropertyA("idbk",idbk);
            for (Book b : booklist) {
                map.put("idbk", idbk);
                map.put("BKName", b.getBkname());
                map.put("Author", b.getAuthor());
                map.put("supplier", b.getSupplier().getSupplier());
            }
            for (Bkpurchase bkp : bkpurlist) {
                if (idbk.equals(bkp.getBook().getIdbk())) {

                    if (bkp.getCampus().equals("黄") && bkp.getBknum() != null) {
                        map.put("HJHcampus", bkp.getCampus());
                        map.put("HJHBKnum", bkp.getBknum());
                        total += bkp.getBknum();
                    }
                    if (bkp.getCampus().equals("昙") && bkp.getBknum() != null) {
                        map.put("THLcampus", bkp.getCampus());
                        map.put("THLBKnum", bkp.getBknum());
                        total += bkp.getBknum();
                    }
                }
            }
            map.put("total", total);
            purinfolist.add(map);
        }
        for (Map bk : purinfolist) {
            System.out.println("idbk:" + bk.get("idbk") + "-bkname:"
                    + bk.get("BKName") + "-Author:" + bk.get("Author")
                    + "-HJHcampus:" + bk.get("HJHBKnum") + "-THLcampus:"
                    + bk.get("THLBKnum") + "-total:" + bk.get("total")
                    + "-supplier:" + bk.get("supplier"));
            list.add(bk);
        }
        System.out.println(purinfolist.size() + "," + bkpurlist.size());
        return list;
    }

    public List getPurInfoBySupplier(String supplier) {
        // 返回采购清单到页面
        List result = new ArrayList();
        List<Object[]> bookList = bookpurchaseDAO.getBookList();
        Iterator it = bookList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object[] obj = (Object[]) it.next();
            Map temp = new HashMap();

            if (obj[10].equals(supplier) || "----全部----".equals(supplier)) {
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

    /**
     * todo
     * it is wrong
     */
    public void alterBKPurchase(String semester) {
        try {
            /** 获取xml文件路径获取 **/
            String absxmlFilePath = this.getClass().getClassLoader()
                    .getResource("date.xml").getPath();
            String xmlFilePath = absxmlFilePath.substring(absxmlFilePath
                    .indexOf("webapps"));
            // System.out.println("absxmlFilePath:" + absxmlFilePath);
            xmlFilePath = ".." + File.separator + xmlFilePath;// MyEclipse
            Properties props = System.getProperties(); // 获得系统属性集
            String osName = props.getProperty("os.name"); // 操作系统名称
            if (osName.equals("Mac OS X")) {
                xmlFilePath = absxmlFilePath;// Mac OS
            }
            // System.out.println("xmlFilePath:" + xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            NodeList nodeList = document.getElementsByTagName("购书单日期");// 获得名字的节点集合
            int size = nodeList.getLength();
            for (int k = 0; k < size; k++) {
                Node node = nodeList.item(k);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    if (!semester.equals("")) {
                        elementNode.setTextContent(semester);// setTextContent()实现修改
                    }
                }
            }
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            File file = new File(xmlFilePath);
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transformer.transform(domSource, xmlResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * it is wrong
     * todo
     */
    public String getBKPurDate() {
        String str = null;
        try {
            /** 获取xml文件路径获取 **/
            String absxmlFilePath = this.getClass().getClassLoader()
                    .getResource("date.xml").getPath();
            String xmlFilePath = absxmlFilePath.substring(absxmlFilePath
                    .indexOf("webapps"));
            // System.out.println("absxmlFilePath:" + absxmlFilePath);
            xmlFilePath = ".." + File.separator + xmlFilePath;// MyEclipse
            Properties props = System.getProperties(); // 获得系统属性集
            String osName = props.getProperty("os.name"); // 操作系统名称
            if (osName.equals("Mac OS X")) {
                xmlFilePath = absxmlFilePath;// Mac OS
            }
            // System.out.println("xmlFilePath:" + xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            NodeList nodeList = document.getElementsByTagName("购书单日期");// 获得名字的节点集合
            int size = nodeList.getLength();
            for (int k = 0; k < size; k++) {
                Node node = nodeList.item(k);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    str = elementNode.getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
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

    public List generateBookList(String purDate) {
        String purchaseDateStr = this.getBKPurDate();
        // 学期格式2012-2013-1
        // 获取学年
        String yearStr = purchaseDateStr.substring(0,
                purchaseDateStr.indexOf("-"));
        int year = Integer.parseInt(yearStr);
        // 获取第一个‘-’的下标
        String semStr = purchaseDateStr.substring(purchaseDateStr.length() - 1);
        int sem = Integer.parseInt(semStr);

        // System.out.println("year:" + year);
        // System.out.println("sem:" + sem);

        // List<Bookpurchaseview> result = new ArrayList<Bookpurchaseview>();
        //
        // // 1.获取数据库视图中所有的数据
        // List<Bookpurchaseview> list = bookpurchaseviewDAO.getAll();
        //
        // System.out.println("list.size:" + list.size());
        // Iterator<Bookpurchaseview> it = list.iterator();
        // int i = 0;
        // // 2.筛选条件为
        // // (grade - year) * 2 + sem = semester;
        // // 的课程信息
        // while (it.hasNext()) {
        // Bookpurchaseview bkv = (Bookpurchaseview) it.next();
        // int tempSem = (year - Integer.parseInt(bkv.getGrade())) * 2 + 1;
        // int sem = Integer.parseInt(bkv.getSemester());
        // if (tempSem == sem) {
        // result.save(bkv);
        // System.out.println(bkv.toString());
        // }
        // i++;
        // }
        //
        // System.out.println("result.size:" + result.size());
        // System.out.println("i:" + i);
        //
        // // 3.获取无重复书本idbk列表
        // Set<String> idbkSet = new HashSet<String>();
        // Iterator<Bookpurchaseview> itb = result.iterator();
        // while (itb.hasNext()) {
        // Bookpurchaseview bkv = (Bookpurchaseview) itb.next();
        // idbkSet.save(bkv.getIdbk());
        // }
        // System.out.println("idbkSet.size:" + idbkSet.size());
        //
        // // 4.统计各书本购买数量
        // Iterator<String> itc = idbkSet.iterator();
        // Map<String, Integer> bkMap = new HashMap<String, Integer>();
        // while (itc.hasNext()) {
        // String idbk = (String) itc.next();
        // // 初始化bklist
        // bkMap.put(idbk, new Integer(0));
        // Iterator itd = result.iterator();
        // while (itd.hasNext()) {
        // Bookpurchaseview bkv = (Bookpurchaseview) itd.next();
        // if (bkv.getIdbk().equals(idbk)) {
        // int num = bkv.getStunum() + bkMap.get(idbk);
        // bkMap.put(idbk, num);
        // }
        // }
        // }
        //
        // System.out.print(bkMap.toString());
        //
        // bookpurchaseDAO.batchSave(result);

        List<Bookpurchaseview> list = bookpurchaseviewDAO.findByYearAndSem(
                year, sem);
        // 清空bookpurchase表
        bookpurchaseDAO.bathDelete();
        // 将筛选后的课程信息保存到临时表bookpurchase
        bookpurchaseDAO.batchSave(list);

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
        // 生成采购清单Excel文件
        generateXlsFile();
        return result;
    }

    public List generateFreshManBookList(String purDate) {
        String purchaseDateStr = this.getBKPurDate();
        // 学期格式2012-2013-1
        // 获取学年
        String yearStr = purchaseDateStr.substring(0,
                purchaseDateStr.indexOf("-"));
        int year = Integer.parseInt(yearStr);
        // 获取第一个‘-’的下标
        String semStr = purchaseDateStr.substring(purchaseDateStr.length() - 1);
        int sem = Integer.parseInt(semStr);

        List<Bookpurchaseview> list = bookpurchaseviewDAO
                .findByYearAndSemAndGrade(year, sem, year);
        // 清空bookpurchase表
        bookpurchaseDAO.bathDelete();
        // 将筛选后的课程信息保存到临时表bookpurchase
        bookpurchaseDAO.batchSave(list);

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
        // 生成采购清单Excel文件
        generateFreshManXlsFile();
        return result;
    }

    //    todo
    void generateFreshManXlsFile() {
    }

    //    todo
    void generateXlsFile() {
    }

}
