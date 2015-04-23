package com.service.imp;

import com.bean.bookpurchaseview.Bookpurchaseview;
import com.dao.BookpurchaseviewDAO;
import com.service.BKDistributeService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("bKDistributeService")
public class BKDistributeServiceImp implements BKDistributeService {
    @Autowired
    private BookpurchaseviewDAO bookpurchaseviewDAO;

    public List BKDistInfoQuery(int year, int sem, String idcls) {
        return bookpurchaseviewDAO.findByYearAndSemAndCol(year, sem, idcls);
    }

    public File BKDistInfoQuery2Doc(int year, int sem, String idcls){
        try {
            return createWord(bookpurchaseviewDAO.findByYearAndSemAndCol(year, sem, idcls));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File createWord(List<Bookpurchaseview> bkpurview) throws Exception {
        XWPFDocument doc = new XWPFDocument();
        //创建第一段文字，即文档标题
        XWPFParagraph pHeader = doc.createParagraph();
        // 设置字体对齐方式
        pHeader.setAlignment(ParagraphAlignment.CENTER);
        //pHeader.setVerticalAlignment(TextAlignment.TOP);
        // 第一页要使用pHeader所定义的属性
        XWPFRun rt1 = pHeader.createRun();
        // 设置字体是否加粗
        rt1.setBold(true);
        rt1.setFontSize(15);
        // 设置使用何种字体
        rt1.setFontFamily("Courier");
        // 设置上下两行之间的间距
        rt1.setTextPosition(20);
        Bookpurchaseview.BookpurchaseviewId bkpurviewID = bkpurview.get(0).getId();
        System.out.println(bkpurviewID.getCol() + bkpurviewID.getGrade() + bkpurviewID.getMajor()
                + bkpurviewID.getClsno() + "班第" + bkpurviewID.getSemester() + "学期教材发放清单");
        String header = bkpurviewID.getCol() + bkpurviewID.getGrade() + bkpurviewID.getMajor()
                + bkpurviewID.getClsno() + "班第" + bkpurviewID.getSemester() + "学期教材发放清单";
        rt1.setText(header);//"信息工程学院2010级医学信息工程（1）班第7学期教材发放清单"
        int bookInfosize = bkpurview.size();
        XWPFParagraph pnumberInfo = doc.createParagraph();
        pnumberInfo.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun rnumber = pnumberInfo.createRun();
        rnumber.setTextPosition(60);
        rnumber.setText("数量：_____");
        XWPFTable table = doc.createTable(bookInfosize, 6);
        // 设置表头信息
        table.setCellMargins(30, 100, 30, 1200);
        List<XWPFTableCell> tableCells = table.getRow(0).getTableCells();
        tableCells.get(0).setText("序号");
        tableCells.get(1).setText("教材名称");
        tableCells.get(2).setText("出版社");
        tableCells.get(3).setText("作者");
        tableCells.get(4).setText("单价");
        tableCells.get(5).setText("备注");
        for (int i = 1; i < bookInfosize; i++) {
            table.getRow(i).getTableCells().get(0).setText(i + "");
            table.getRow(i).getTableCells().get(1).setText(bkpurview.get(i).getId().getBkname());
            table.getRow(i).getTableCells().get(2).setText(bkpurview.get(i).getId().getPublisher());
            table.getRow(i).getTableCells().get(3).setText(bkpurview.get(i).getId().getAuthor());
        }
        XWPFParagraph p3 = doc.createParagraph();
        XWPFRun r3 = p3.createRun();
        r3.setText("");
        XWPFParagraph p2 = doc.createParagraph();
        p2.setVerticalAlignment(TextAlignment.BOTTOM);
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        r2.setText("联系电话：___________   领书人：_______   发书人：_______   发书日期：____年____月____日");
        r2.addCarriageReturn();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        String wordName = sdf.format(new Date()) + bkpurviewID.getCol() + bkpurviewID.getGrade() + bkpurviewID.getMajor()
                + bkpurviewID.getClsno() + "班第" + bkpurviewID.getSemester() + "学期教材发放清单.doc";
        File file = new File(System.getProperty("java.io.tmpdir"),wordName);
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
        return file;
    }
}
