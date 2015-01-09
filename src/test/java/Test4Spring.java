import com.bean.book.Book;
import com.bean.supplier.Supplier;
import com.dao.BkpurchaseDAO;
import com.dao.ClassDAO;
import com.dao.CourseDAO;
import com.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * DATE:2014/11/24
 * TIME:20:45
 * Created by guofan on 2014/11/24
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class Test4Spring extends AbstractJUnit4SpringContextTests {
    @Autowired
    BkpurchaseDAO bkpurchaseDAO;
    @Autowired
    ClassService classService;
    @Autowired
    BookService bookService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    CourseBookViewService courseBookViewService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseDAO courseDAO;
//    @Autowired
//    SupplierService supplierService;

    public static void cout(Object x) {
        System.out.println(x);
    }

    @org.junit.Test
    public void main() {
//        System.out.println("This is a test!");
//        classdao.findById("2007020101").toString();
//        JSONObject x = JSONObject.fromObject(t);
//        List x =  new ArrayList();
//        x.save(classService.findById("2007020101"));
//        List y = class2List(x);
//        System.out.println(y.toString());
//        JSONArray t = fromObject(y);
//        cout(y);
//        bookService.delete(bookService.findById("14041738021"));

//        cout(getSubList(classService.getAll(), 1, 10).toString());
//        cout(getSubList(classService.getAll(), 1, 10));
//        classService.
//        System.out.println(x);
//        cout(classService.accurateQuery("信息工程学院", "医学信息工程", "2010", "黄"));
//        cout(JSONArray.fromObject(courseBookViewService.getAll()));
//        cout(courseBookViewService.findAllCourse());
//        cout(JSONObject.fromObject(true));
//        Supplier t = supplierService.findById("1029");
//        cout(t);
//        supplierService.delete(t);
//        cout(JSONArray.fromObject(courseDAO.getCourseByColMajorSem("信息工程学院", "信息管理与信息系统", "1")));
//        cout(bookService.findById("14041700168").toString());
        JsonConfig config = new JsonConfig();
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//        config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        String[] excludes = {"bkpurchases","coursebks","books"};
        config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        config.setExcludes(excludes);
        List book = bookService.searchByISBN("1");
        cout(JSONArray.fromObject(book,config));
    }

}
