package com.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bean.bookpurchaseview.Bookpurchaseview;
import com.bean.bookpurchaseview.BookpurchaseviewId;

public class POI2EXCEL {

    public static void createEXCEL(List<Bookpurchaseview> bkpurview)
            throws Exception {
        if (bkpurview != null && bkpurview.size() > 0) {
            int CountColumnNum = 7;
            List<BookpurchaseviewId> bkpurviewIDs = new ArrayList();
            // 创建Excel文档
            HSSFWorkbook wb = new HSSFWorkbook();
            // sheet 对应一个工作页
            int n = 0;
            for (int i = 0; i < bkpurview.size(); i++) {
                bkpurviewIDs.add(bkpurview.get(i).getId());
            }
            List<String> idclses = new ArrayList();
            Map<String, String> idclsString = new HashMap();
            for (int i = 0; i < bkpurview.size(); i++) {
                if (!idclses.contains(
                        bkpurviewIDs.get(i).getIdcls())) {
                    idclses.add(bkpurviewIDs.get(i).getIdcls());
                    String header = bkpurviewIDs.get(i).getGrade()
                            + bkpurviewIDs.get(i).getMajor()
                            + bkpurviewIDs.get(i).getClsno() + "班第"
                            + bkpurviewIDs.get(i).getSemester() + "学期教材发放清单";
                    idclsString.put(bkpurviewIDs.get(i).getIdcls(), header);

                    System.out.println(header);
                }
            }
            for (int i = 0; i < idclses.size(); i++) {
                String headerStr = (String) idclsString.get(idclses.get(i));
                HSSFSheet sheet = wb.createSheet(headerStr);
                HSSFRow header1 = sheet.createRow(0); // 下标为0的行开始
                HSSFRow header2 = sheet.createRow(1); // 下标为1的行
                HSSFRow space1 = sheet.createRow(2); // 下标为1的行
                HSSFRow stuNumRow = sheet.createRow(3); // 下标为2的行
                HSSFRow space2 = sheet.createRow(4); // 下标为2的行
                for (int m = 0; m < CountColumnNum; m++) {
                    stuNumRow.createCell((short) m);
                    header1.createCell((short) m);
                    header2.createCell((short) m);
                    space1.createCell((short) m);
                    space2.createCell((short) m);
                }
                header1.getCell((short) 0).setCellValue(new HSSFRichTextString(headerStr));
                HSSFCellStyle cs = wb.createCellStyle();//创建一个style

                HSSFFont littleFont = wb.createFont();//创建一个Font
                littleFont.setFontName("SimSun");
                littleFont.setFontHeightInPoints((short) 12);
                cs.setFont(littleFont);//设置字体
                cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
                header1.getCell((short) 0).setCellStyle(cs);

                org.apache.poi.hssf.util.Region region = new org.apache.poi.hssf.util.Region(0, (short) 0, 1, (short) 5);
                sheet.addMergedRegion(region);
                stuNumRow.getCell((short) 5).setCellValue(new HSSFRichTextString("人数:"));

                HSSFRow firstrow = sheet.createRow(5); // 下标为3的行
                HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
                String[] names = new String[CountColumnNum];
                names[0] = "序号";
                names[1] = "教材名称";
                names[2] = "出版社";
                names[3] = "作者";
                names[4] = "单价";
                names[5] = "备注";
                for (int m = 0; m < CountColumnNum; m++) {
                    firstcell[m] = firstrow.createCell((short) m);
                    firstcell[m].setCellValue(new HSSFRichTextString(
                            names[m]));
                }
                int k = 6;
                for (int j = 0; j < bkpurview.size() - 1; j++) {
                    if (idclses.get(i).equals(bkpurviewIDs.get(j).getIdcls())) {
                        // 创建电子表格的一行
                        if (!bkpurviewIDs.get(j).getBkname().equals("") && !bkpurviewIDs.get(j).getBkname().equals("已发")) {
                            HSSFRow row = sheet.createRow(k); // 下标为1的行开始
                            HSSFCell cell = row.createCell((short) 0);
                            cell.setCellValue(new HSSFRichTextString(k - 5 + ""));
                            HSSFCell cell1 = row.createCell((short) 1);
                            cell1.setCellValue(new HSSFRichTextString(bkpurviewIDs.get(j).getBkname()));
                            HSSFCell cell2 = row.createCell((short) 2);
                            cell2.setCellValue(new HSSFRichTextString(bkpurviewIDs.get(j).getPublisher()));
                            HSSFCell cell3 = row.createCell((short) 3);
                            cell3.setCellValue(new HSSFRichTextString(bkpurviewIDs.get(j).getAuthor()));
                            k++;
                        }
                        // 设置表格的编码集，使支持中文
                        //
                        // 先判断数据库中的数据类型
                        // 将结果集里的值放入电子表格中
                    }
                }
                HSSFRow rail1 = sheet.createRow(k); // 下标为0的行开始
                HSSFRow rail2 = sheet.createRow(++k); // 下标为1的行
                HSSFCell[] railcell = new HSSFCell[CountColumnNum];
                String[] rails = new String[CountColumnNum];
                rails[0] = "联系电话:";
                rails[1] = "           ";
                rails[2] = "领书人:";
                rails[3] = "           ";
                rails[4] = "发书人:";
                rails[5] = "           ";
                rails[6] = "发书日期:";
                for (int m = 0; m < CountColumnNum; m++) {
                    rail1.createCell((short) m);
                    railcell[m] = rail2.createCell((short) m);
                    railcell[m].setCellValue(new HSSFRichTextString(
                            rails[m]));
                }
                if (k == 7) {
                    wb.removeSheetAt(wb.getSheetIndex(headerStr));
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
            FileOutputStream out = new FileOutputStream("E:\\"
                    + sdf.format(new Date()) + bkpurview.get(0).getId().getCol()
                    + "教材发放清单.xls");
            wb.write(out);
            out.close();
        }
        System.out.println("success");
    }
    // for (Iterator iterator = bkpurviewIDs.iterator();
    // iterator.hasNext();) {
    // BookpurchaseviewId bookpurchaseviewId = (BookpurchaseviewId) iterator
    // .next();
    // String header = bookpurchaseviewId.getGrade()
    // + bookpurchaseviewId.getMajor()
    // + bookpurchaseviewId.getClsno() + "班第"
    // + bookpurchaseviewId.getSemester() + "学期教材发放清单";
    // System.out.println(header);

}
// 创建文件输出流，准备输出电子表格
